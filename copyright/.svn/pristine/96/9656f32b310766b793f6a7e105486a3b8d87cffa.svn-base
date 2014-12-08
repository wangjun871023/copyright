/*
		作业模板管理窗口
*/
Ext.define('MyDesktop.admin.HomeworkTemplateManagerWindow', {
    extend: 'Ext.ux.desktop.Module',
    requires: [
		'MyDesktop.admin.CreateHomeworkTemplateWindow',
		'MyDesktop.admin.UpdateHomeworkTemplateWindow'
    ],
    id:'homework-template-manager-win',
    init : function(){
        this.launcher = {
            text: homeworkTemplateManagerWindowTitleStr,
            iconCls:'icon-grid'
        };
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('homework-template-manager-win');
        if(!win){
            win = desktop.createWindow({
                id:'homework-template-manager-win',
                title:homeworkTemplateManagerWindowTitleStr,
                width:courseStudentManagerWindowWidth,
                height:courseStudentManagerWindowHeight,
                iconCls: 'icon-grid',
                animCollapse:false,
                constrainHeader:true,
                border:false,
                layout: 'fit',
                items: [{
					xtype:'container',
					layout:'fit',
					items:[{
							border: false,
							xtype: 'livesearchgridpanel',
							border:true,
							selModel:Ext.create('Ext.selection.CheckboxModel'),
							store: Ext.create('MyDesktop.store.HomeworkTemplateStore'),
							columns: [
								{
									xtype: 'rownumberer',
									text:sequenceStr,
									width:30
								},{
									text:homeworkNameStr,
									flex: 1,
									sortable: true,
									dataIndex: 'homeworkName'
								},{
									text:courseNameStr,
									flex: 1,
									sortable: true,
									dataIndex: 'courseName'
								},{
			                        text: validStatusStr,
			                        flex: 1,
			                        sortable: true,
			                        renderer :changeValidCode,
			                        dataIndex: 'homeworkStatus'
								},{
									xtype:'actioncolumn',
									text:actionStr,
									width:40,
							        items: [{
						            	icon: '../../common/icons/mark_complete.png', 
						                tooltip: searchHomeworkInfoStr,
						                handler: this.onHomeworkInfo
						            }]
								}
							],
							listeners: {
								itemdblclick: this.onItemdblclick,
								render:this.onRender
							},	
							tbar:[{
								xtype: 'button',
								tooltip:allValidStr,
								text:allValidStr,
								action:'showAll',
								toggleGroup:'status',
								iconCls:'showAll',
								allowDepress:false,
								handler: this.onSearch
							},{
								xtype: 'button',
								tooltip:validStr,
								text:validStr,
								action:'showComplete',
								toggleGroup:'status',
								iconCls:'showComplete',
								allowDepress:false,
								handler: this.onSearch
							},{
								xtype: 'button',
								tooltip:invalidStr,
								text:invalidStr,
								toggleGroup:'status',
								action:'showActive',
								allowDepress:false,
								iconCls:'showActive',
								handler: this.onSearch
							},'-',{
								xtype: 'button',
								text: addHomeworkStr,
								tooltip:addHomeworkStr,
								iconCls:'user-add',
								handler: this.onAddHomework
							},{
								xtype: 'button',
								text: updateHomeworkStr,
								tooltip:updateHomeworkStr,
								iconCls:'user-edit',
								handler: this.onUpdateHomework
							},'-',{
								text:validStr,
								tooltip:validStr,
								iconCls:'valid',
								handler: this.onValidAction
							},{
								text:invalidStr,
								tooltip:invalidStr,
								iconCls:'invalid',
								handler: this.onInvalidAction
							}
						]
					}]
                }]
            });
        }
        return win;
    }, 
    onRender:function(){
		var grid = this.up("window").down("livesearchgridpanel");
		grid.getStore().load();
	},
	onSearch:function(){
		var showComplete = this.up('window').down('button[action=showComplete]');
    	var showActive = this.up('window').down('button[action=showActive]');
    	var status ='';
    
    	if (showComplete.pressed ==true){
    		status = validConstant;
    	}
    	if (showActive.pressed ==true){
    		status = invalidConstant;
    	}
		var store = this.up("window").down("livesearchgridpanel").getStore();
		store.load({
			params:{
				courseId:userCourseVar,
				homeworkStatus:status
			}
		});
	},
    onHomeworkInfo:function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex);
		var view =Ext.create('MyDesktop.admin.HomeworkTemplateInfoWindow').show();
		view.down('form').loadRecord(record);
    },
	onItemdblclick:function(grid,record){
		var grid = this.up("window").down("livesearchgridpanel");
		var view =Ext.create('MyDesktop.admin.HomeworkTemplateInfoWindow').show();
		view.down('form').loadRecord(record);
	},
	onAddHomework:function(){
		var gridStore = this.up("window").down("livesearchgridpanel").getStore();
		var view = Ext.create('MyDesktop.admin.CreateHomeworkTemplateWindow',{
			gridStore:gridStore
		}).show();
	},
	onUpdateHomework:function(){
		var grid = this.up("window").down("livesearchgridpanel");
    	var selectedModels = grid.getSelectionModel();
		if (selectedModels.getCount()!=1){
			Ext.Msg.alert(messageBoxTitle, modifyInfoStr);
		}else{
			var view =Ext.create('MyDesktop.admin.UpdateHomeworkTemplateWindow',{
				gridStore:grid.getStore()
			}).show();
			var rec = selectedModels.getSelection()[0];
			rec.set('homeworkTemplateId',rec.get('id'));
			view.down('form').loadRecord(rec);
		}
	},
	onValidAction:function(){
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
			    			 model.set('homeworkStatus',validConstant);
			    		 });
			    		 gridVar.getStore().sync();
			    	 }
			     }
			});
		}
	},
	onInvalidAction:function(){
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
			    			 model.set('homeworkStatus',invalidConstant);
			    		 });
			    		 gridVar.getStore().sync();
			    	 }
			     }
			});
		}
	}
});