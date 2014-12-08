/*
		课头学生信息管理窗口
*/
Ext.define('MyDesktop.teacher.CourseStudentManagerWindow', {
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
		'MyDesktop.store.StudentStore'
    ],
    id:'course-student-manager-win',
    init : function(){
        this.launcher = {
            text: courseStudentManagerWindowTitleStr,
            iconCls:'icon-grid'
        };
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('course-student-manager-win');
        if(!win){
            win = desktop.createWindow({
                id:'course-student-manager-win',
                title:courseStudentManagerWindowTitleStr,
                width:courseStudentManagerWindowWidth,
                height:courseStudentManagerWindowHeight,
                iconCls: 'icon-grid',
                animCollapse:false,
                constrainHeader:true,
                border:false,
                layout: 'fit',
                items: [{
					xtype:'container',
					layout:'border',
					items:[{
						xtype:'simpletreepanel',
						split: true,  
						store:Ext.create('MyDesktop.store.ClassHeadStore'),
						region:'west',
						itemclick: this.onItemclick,
						dockedItems:[{
				            xtype: 'toolbar',
				            items: [{
									text:addCourseHeadStr,
									tooltip:addStr,
									iconCls:'add',
									handler: this.onAddCourseHead
								}, {
									text:modifyCourseHeadStr,
									tooltip:modifyStr,
									iconCls:'option',
									handler: this.onModifyCourseHead
								}
								/*
								,
								{
									text:deleteCourseHeadStr,
									tooltip:deleteStr,
									iconCls:'remove',
									handler: this.onDeleteCourseHead
							}*/]
						}]
					},{
						border: false,
						region:'center',
						xtype: 'searchgridpanel',
						border:true,
						selModel:Ext.create('Ext.selection.CheckboxModel'),
						store:  Ext.create('MyDesktop.store.StudentStore'),
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
							flex: 1,
							sortable: true,
							dataIndex: 'studentNo'
						},{
							text: nameStr,
							flex: 1,
							sortable: true,
							dataIndex: 'studentName'
						}, {
							text: genderStr,
							flex: 1,
							sortable: true,
							dataIndex: 'studentGender'
						},  {
							text: specialtyStr,
							flex: 1,
							sortable: true,
							dataIndex: 'studentSpecialty'
						},{
							text: classStr,
							flex: 1,
							sortable: true,
							dataIndex: 'studentClass'
						},{
							text: gradeStr,
							flex: 1,
							sortable: true,
							dataIndex: 'studentGrade'
						},{
							text: studyAgainStr,
							flex: 1,
							sortable: true,
							dataIndex: 'studentAgain'
						},{
	                        text: validStatusStr,
	                        flex: 1,
	                        sortable: true,
	                        renderer :changeValidCode,
	                        dataIndex: 'studentStatus'
						},{
	                        text: '激活状态',
	                        flex: 1,
	                        sortable: true,
	                        renderer :changeActiveCode,
	                        dataIndex: 'activeStatus'
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
							text:'已激活',
							action:'showComplete',
							toggleGroup:'status',
							iconCls:'showComplete',
							allowDepress:false,
							handler: this.onSearch
						},{
							xtype: 'button',
							tooltip:invalidStr,
							text:'未激活',
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
						},'->',studentNumberStr+"或姓名:",
						{
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
							 width: 150
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
		grid.getStore().load({
			params:{
				teacherId : currentTeacherRecord.get('id')
			}
		});
		grid.getStore().extraParams = {
				teacherId : currentTeacherRecord.get('id'),
        		limit:itemsPerPageVar
		};
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
    onAddCourseHead:function(){
    	var tree = this.up("window").down("simpletreepanel");
		new MyDesktop.teacher.CreateCourseHeadWindow({
			treeStore:tree.getStore()
		}).show();
	},
    onModifyCourseHead:function(btn){
    	var tree =  btn.ownerCt.ownerCt;
    	var records = tree.getView().getChecked();
    	if (records.length>0){
    		var record = records[0];
    		var view = new MyDesktop.teacher.UpdateCourseHeadWindow({
    			treeStore:tree.getStore()
    		}).show();
    		var form = view.down("form");
    		record.set("classHeadId",record.get('id'));
    		form.getForm().loadRecord(record);
    	}else{
    		Ext.Msg.alert(messageBoxTitle, modifyInfoStr);
    	}
    },
    /*
    onDeleteCourseHead:function(btn){
    	var tree =  btn.ownerCt.ownerCt;
    	var records = tree.getView().getChecked();
    	var gridStore = this.up("window").down("searchgridpanel").getStore();
    	if (records.length>0){
    		Ext.Msg.show({
			     title:messageBoxTitle,
			     msg: deleteConfirmInfoStr,
			     buttons: Ext.Msg.OKCANCEL,
			     icon: Ext.Msg.QUESTION,
			     fn:function(buttonId){
			    	 if (buttonId=='ok'){
		    			records[0].remove();
		    			tree.getStore().sync();
		        		var store = gridStore;
		        		store.removeAll(false);
			    	 }
			     }
			});
    	}else{
    		Ext.Msg.alert(messageBoxTitle, deleteInfoStr);
    	}
    },*/
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
			    		 gridVar.getStore().sync();
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
			     msg: invalidConfirmInfoStr,
			     buttons: Ext.Msg.OKCANCEL,
			     icon: Ext.Msg.QUESTION,
			     fn:function(buttonId){
			    	 if (buttonId=='ok'){
			    		 var models = selectedModels.getSelection();
			    		 Ext.Array.each(models, function(model, index, models) {
			    			 model.set('studentStatus',invalidConstant);
			    		 });
			    		 gridVar.getStore().sync();
			    	 }
			     }
			});
		}
	},
    onItemdblclick:function(grid, record){
		var view = new MyDesktop.teacher.UpdateStudentWindow().show();
		view.down('form').loadRecord(record);
	},
	onAddStudent:function(){
		var tree = this.up("window").down("simpletreepanel");
		var grid = this.up("window").down("searchgridpanel");
		var records = tree.getView().getChecked();
    	if (records.length>0){
    		var view = new MyDesktop.teacher.CreateStudentWindow({
    			gridStore:grid.getStore()
    		}).show();	
    		var record = records[0];
    		var form = view.down("form");
    		form.getForm().loadRecord(record);
    	}else{
    		Ext.Msg.alert(messageBoxTitle, addInfoStr);
    	}
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
	onDeleteStudent:function(){
		var gridVar = this.up("window").down("searchgridpanel");
		var selectedModels = gridVar.getSelectionModel();
		if (selectedModels.getCount()<1){
			Ext.Msg.alert(messageBoxTitle, deleteInfoStr);
		}else{
			Ext.Msg.show({
			     title:messageBoxTitle,
			     msg: deleteConfirmInfoStr,
			     buttons: Ext.Msg.OKCANCEL,
			     icon: Ext.Msg.QUESTION,
			     fn:function(buttonId){
			    	 if (buttonId=='ok'){
			    		 gridVar.getStore().remove(selectedModels.getSelection());
			    	 }
			     }
			});
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
            		teacherId : currentTeacherRecord.get('id'),
            		studentStatus:status,
            		key:key
			};
			store.loadPage(1,{
				params:{
					key:key,
					classHeadId:record.get("id"),
					teacherId : currentTeacherRecord.get('id'),
					studentStatus:status
				}
			});
		}else{
			var store =  this.up("window").down("searchgridpanel").getStore();
			store.extraParams = {
            		key:key,
            		limit:itemsPerPageVar,
            		studentStatus:status,
            		teacherId : currentTeacherRecord.get('id'),
            		classHeadId:''
			};
			store.loadPage(1,{
				params:{
					key:key,
					classHeadId:'',
					teacherId : currentTeacherRecord.get('id'),
					studentStatus:status
				}
			});
		}
	}
});