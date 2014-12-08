/*
		课程资源与消息窗口
*/
Ext.define('MyDesktop.admin.ResourcesAndMsgManagerWindow', {
    extend: 'Ext.ux.desktop.Module',
    requires: [
        'Ext.data.ArrayStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer',
	    'Ext.selection.CheckboxModel',
		'MyDesktop.common.SearchGridPanel',
		'MyDesktop.common.SimpleTreePanel',
		'MyDesktop.teacher.CreateCourseHeadWindow',
		'MyDesktop.teacher.UpdateCourseHeadWindow',
		'MyDesktop.teacher.CreateStudentWindow',
		'MyDesktop.teacher.UpdateStudentWindow',
		'MyDesktop.store.ClassHeadStore',
		'MyDesktop.store.TeacherClassHeadStore',
		'MyDesktop.store.StudentStore'
    ],
    id:'resources-msg-manager-win',
    init : function(){
        this.launcher = {
            text: resourcesAndMsgManagerWindowTitleStr,
            iconCls:'icon-grid'
        };
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('resources-msg-manager-win');
        if(!win){
            win = desktop.createWindow({
                id:'resources-msg-manager-win',
                title:resourcesAndMsgManagerWindowTitleStr,
                width:resourcesAndMsgManagerWindowWidth,
                height:resourcesAndMsgManagerWindowHeight,
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
						region:'center',
						xtype: 'searchgridpanel',
						border:true,
						selModel:Ext.create('Ext.selection.CheckboxModel'),
						store: Ext.create('MyDesktop.store.StudentStore'),
						columns: [{
							xtype: 'rownumberer',
							text:sequenceStr,
							width:30
						},{
							text:courseHeadNameStr,
							flex: 1,
							sortable: true,
							dataIndex: 'classHeadName'
						},{
							text: studentNumberStr,
							flex: 2,
							sortable: true,
							dataIndex: 'studentNo'
						},{
							text: nameStr,
							flex: 1,
							sortable: true,
							dataIndex: 'studentName'
						}, {
							text: genderStr,
							width:40,
							sortable: true,
							dataIndex: 'studentGender'
						},  {
							text: specialtyStr,
							flex: 1,
							sortable: true,
							dataIndex: 'studentSpecialty'
						},{
							text: classStr,
							flex: 2,
							sortable: true,
							dataIndex: 'studentClass'
						},{
							text: gradeStr,
							flex: 1,
							sortable: true,
							dataIndex: 'studentGrade'
						},{
							text: studyAgainStr,
							width:40,
							sortable: true,
							dataIndex: 'studentAgain'
						},{
	                        text: validStatusStr,
	                        flex: 1,
	                        sortable: true,
	                        renderer :changeValidCode,
	                        dataIndex: 'studentStatus'
						}],
						listeners: {
							itemdblclick:this.onItemdblclick,
				            render:this.onRender
						},	
						tbar:[{
							xtype: 'button',
							tooltip:allValidStr,
							text:allValidStr,
							action:'',
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
							text: modifyStudentStr,
							tooltip:modifyStudentStr,
							iconCls:'user-edit',
							handler: this.onUpdateStudent
						},'-',{
							text:validStr,
							tooltip:validStr,
							iconCls:'valid',
							handler: this.onValidStudentAction
						},{
							text:invalidStr,
							tooltip:invalidStr,
							iconCls:'invalid',
							handler: this.onInvalidStudentAction
						},'->',studentNumberStr,{
							 xtype: 'textfield',
							 name: 'searchField',
							 hideLabel: true,
							 listeners: {  
				                    specialkey: function(field,e){    
				                        if (e.getKey()==Ext.EventObject.ENTER){    
				                        	var button = this.up('window').down('button[action=search]');
				                        	button.fireEvent('click');
				                        }  
				                    }  
				             },
							 width: 200
						}, {
							xtype: 'button',
							text: searchStr,
							action:'search',
							tooltip:searchStr,
							iconCls:'search',
							listeners:{
								'click':this.onSearch
							}
						}]
					}]
                }]
            });
        }
        return win;
    },
    onRender:function(){
		var grid = this.up("window").down("searchgridpanel");
		grid.getStore().load();
	},
	onValidStudentAction:function(){
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
			    			 model.set('studentStatus',validConstant);
			    		 });
			    	 }
			     }
			});
		}
	},
	onInvalidStudentAction:function(){
		var gridVar =this.ownerCt.up('grid');
		var selectedModels = gridVar.getSelectionModel();
		if (selectedModels.getCount()<1){
			Ext.Msg.alert(messageBoxTitle, invalidInfoStr);
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
			    			 model.set('studentStatus',invalidConstant);
			    		 });
			    	 }
			     }
			});
		}
	},
    onItemclick:function (tree,record){
        var records = tree.getChecked();
    	var searchField = this.up('window').down('textfield[name=searchField]');
    	searchField.setValue("");
        Ext.Array.each(records, function(rec){
        	if (record.isLeaf()&&rec!=record){
        		 rec.set("checked",false);
        	}
        });
        if (record.isLeaf()){
        	if (record.get('checked')==true){
        		record.set("checked",false);
        	}else{
        		record.set("checked",true);
        	}
        	var button = this.up('window').down('button[action=search]');
        	button.fireEvent('click');
        }
    },
    onItemdblclick:function(grid, record){
		var view = new MyDesktop.teacher.UpdateStudentWindow().show();
		view.down('form').loadRecord(record);
	},
	onUpdateStudent:function(){
		var grid = this.up("window").down("searchgridpanel");
		var selectedModels = grid.getSelectionModel();
		if (selectedModels.getCount()!=1){
			Ext.Msg.alert(messageBoxTitle, modifyInfoStr);
		}else{
			var view = new MyDesktop.teacher.UpdateStudentWindow().show();	
			view.down('form').loadRecord(selectedModels.getSelection()[0]);
		}
	},
	onSearch:function(){
		var key = this.up("window").down("textfield[name=searchField]").getValue();
		var records =this.up("window").down("simpletreepanel").getView().getChecked();
		var showComplete = this.up('window').down('button[action=showComplete]');
    	var showActive = this.up('window').down('button[action=showActive]');
    	var status ='';
    	if (showComplete.pressed ==true){
    		status = validConstant;
    	}
    	if (showActive.pressed ==true){
    		status = invalidConstant;
    	}
		
		if (records.length>0){
			//有树参数
			var record = records[0];
			var store = this.up("window").down("searchgridpanel").getStore();
			store.extraParams = {
            		classHeadId:record.get('id'),
            		limit:itemsPerPageVar,
            		key:key,
            		studentStatus:status
			};
			store.loadPage(1,{
				params:{
					key:key,
					classHeadId:record.get("id"),
					studentStatus:status
				}
			});
		}else{
			var store =  this.up("window").down("searchgridpanel").getStore();
			store.extraParams = {
            		key:key,
            		classHeadId:'',
            		limit:itemsPerPageVar,
            		studentStatus:status
			};
			store.loadPage(1,{
				params:{
					key:key,
					classHeadId:'',
					studentStatus:status
				}
			});
		}
	}
});