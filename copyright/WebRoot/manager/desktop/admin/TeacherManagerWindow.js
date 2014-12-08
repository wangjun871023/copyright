/*
		任课教师用户管理窗口
*/
Ext.define('MyDesktop.admin.TeacherManagerWindow', {
    extend: 'Ext.ux.desktop.Module',
    requires: [
        'Ext.grid.Panel',
	    'Ext.selection.CheckboxModel',
	    'MyDesktop.admin.TeacherManagerListCreateWindow',
	    'MyDesktop.admin.TeacherManagerListEditWindow',
	    'MyDesktop.common.LiveSearchGridPanel',
	    'MyDesktop.store.TeacherStore'
    ],
    id:'teacher-manager-win',
    init : function(){
        this.launcher = {
            text: teacherManagerWindowTitleStr,
            iconCls:'icon-grid'
        };
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('teacher-manager-win');
        if(!win){
            win = desktop.createWindow({
                id: 'teacher-manager-win',
                title:teacherManagerWindowTitleStr,
                width:teacherManagerWindowWidth,
                height:teacherManagerWindowHeight,
                iconCls: 'icon-grid',
                animCollapse:false,
                constrain : true,
                layout: 'fit',
                items: [{
                    border: false,
					xtype: 'livesearchgridpanel',
					loadMask:true,
					selModel:Ext.create('Ext.selection.CheckboxModel'),
                    store:Ext.create('MyDesktop.store.TeacherStore'),
                    columns: [{
							xtype: 'rownumberer',
							text:sequenceStr,
							width:30,
							resizable :false 
						},
	                    {
	                        text:userNameStr,
	                        flex: 1,
	                        sortable: true,
	                        dataIndex: 'teacherCode'
	                    },
	                    {
	                        text: teacherNameStr,
	                        flex: 1,
	                        sortable: true,
	                        dataIndex: 'teacherName'
	                    },
	                    {
	                        text: teacherGenderStr,
	                        flex: 1,
	                        sortable: true,
	                        dataIndex: 'teacherGender'
	                    },  {
	                        text: academyStr,
	                        flex: 1,
	                        sortable: true,
	                        dataIndex: 'teacherAcademy'
	                    }, {
	                        text: schoolOfTeacher,
	                        flex: 1,
	                        sortable: true,
	                        dataIndex: 'teacherSchool'
	                    },{
	                        text: validStatusStr,
	                        flex: 1,
	                        sortable: true,
	                        renderer :changeValidCode,
	                        dataIndex: 'teacherStatus'
	                }],
					tbar:[{
						xtype: 'button',
						tooltip:allValidStr,
						text:allValidStr,
						action:'showAll',
						toggleGroup:'status',
						iconCls:'showAll',
						allowDepress:false,
						handler: this.onAllSearch
					},{
						xtype: 'button',
						tooltip:validStr,
						text:validStr,
						action:'showComplete',
						toggleGroup:'status',
						iconCls:'showComplete',
						allowDepress:false,
						handler: this.onValidSearch
					},{
						xtype: 'button',
						tooltip:invalidStr,
						text:invalidStr,
						toggleGroup:'status',
						action:'showActive',
						allowDepress:false,
						iconCls:'showActive',
						handler: this.onInvalidSearch
					},'-',{
							text:addTeacherStr,
							tooltip:addTeacherStr,
							iconCls:'user-add',
							handler: this.onCreateTeacherAction
						}, {
							text:updateTeacherStr,
							tooltip:updateTeacherStr,
							iconCls:'user-edit',
							handler: this.onUpdateTeacherAction
						},'-',{
							text:validStr,
							tooltip:validStr,
							iconCls:'valid',
							handler: this.onValidTeacherAction
						},{
							text:invalidStr,
							tooltip:invalidStr,
							iconCls:'invalid',
							handler: this.onInValidTeacherAction
						}],
					listeners: {
							itemdblclick:this.onItemdblclick,
							render: this.onRender
					}
                }]
            });
        }
        return win;
    },
    onAllSearch:function(){
    	var gridVar =this.ownerCt.up('grid');
    	gridVar.store.load({
			params:{
				courseId :userCourseVar,
				validStatus:''
			}
		});
    },
    onValidSearch:function(){
    	var gridVar =this.ownerCt.up('grid');
    	gridVar.store.load({
			params:{
				courseId :userCourseVar,
				validStatus:validConstant
			}
		});
    },
    onInvalidSearch:function(){
    	var gridVar =this.ownerCt.up('grid');
    	gridVar.store.load({
			params:{
				courseId :userCourseVar,
				validStatus:invalidConstant
			}
		});
    },
    onCreateTeacherAction:function(){
    	new MyDesktop.admin.TeacherManagerListCreateWindow({
    		store:this.ownerCt.up('grid').store
    	}).show();			
    },
    onUpdateTeacherAction:function(){
    	var grid = this.ownerCt.up('grid');
    	var selectedModels = grid.getSelectionModel();
		if (selectedModels.getCount()!=1){
			Ext.Msg.alert(messageBoxTitle, modifyInfoStr);
		}else{
			var view = new MyDesktop.admin.TeacherManagerListEditWindow().show();	
			view.down('form').loadRecord(selectedModels.getSelection()[0]);
		}
    },
    onValidTeacherAction:function(){
    	var gridVar =this.ownerCt.up('grid');
		var selectedModels = gridVar.getSelectionModel();
		if (selectedModels.getCount()<1){
			Ext.Msg.alert(messageBoxTitle, validInfoStr);
		}else{
			Ext.Msg.show({
			     title:messageBoxTitle,
			     msg: validConfirmInfoStr,
			     buttons: Ext.Msg.OKCANCEL,
			     icon: Ext.Msg.QUESTION,
			     fn:function(buttonId){
			    	 if (buttonId=='ok'){
			    		 var models = selectedModels.getSelection();
			    		 Ext.Array.each(models, function(model, index, models) {
			    			 model.set('teacherStatus',validConstant);
			    		 });
			    	 }
			     }
			});
		}
    },
    onInValidTeacherAction:function(){
    	var gridVar =this.ownerCt.up('grid');
		var selectedModels = gridVar.getSelectionModel();
		if (selectedModels.getCount()<1){
			Ext.Msg.alert(messageBoxTitle, invalidInfoStr);
		}else{
			Ext.Msg.show({
			     title:messageBoxTitle,
			     msg: invalidConfirmInfoStr,
			     buttons: Ext.Msg.OKCANCEL,
			     icon: Ext.Msg.QUESTION,
			     fn:function(buttonId){
			    	 if (buttonId=='ok'){
			    		 var models = selectedModels.getSelection();
			    		 Ext.Array.each(models, function(model, index, models) {
			    			 model.set('teacherStatus',invalidConstant);
			    		 });
			    	 }
			     }
			});
		}
    },
    onItemdblclick:function(grid, record){
		var view = new MyDesktop.admin.TeacherManagerListEditWindow().show();
		view.down('form').loadRecord(record);
	},
	onRender:function (grid) {
		grid.store.load({
			params:{
				courseId :userCourseVar
			}
		});
    }
});