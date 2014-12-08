/*
		作业管理窗口
*/
Ext.define('MyDesktop.teacher.HomeworkManagerWindow', {
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
		'MyDesktop.teacher.CreateHomeworkWindow',
		'MyDesktop.teacher.HomeworkInfoWindow',
		'MyDesktop.store.ClassHeadStore',
		'MyDesktop.store.StudentStore'
    ],
    id:'homework-manager-win',
    init : function(){
        this.launcher = {
            text: homeworkManagerWindowTitleStr,
            iconCls:'icon-grid'
        };
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('homework-manager-win');
        if(!win){
            win = desktop.createWindow({
                id:'homework-manager-win',
                title:homeworkManagerWindowTitleStr,
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
							itemclick:this.onItemclick
						},{
							border: false,
							region:'center',
							xtype: 'livesearchgridpanel',
							border:true,
							selModel:Ext.create('Ext.selection.CheckboxModel'),
							store: Ext.create('MyDesktop.store.HomeworkStore'),
							columns: [
								{
									xtype: 'rownumberer',
									text:sequenceStr,
									width:30
								},
								{
									text:homeworkNameStr,
									flex: 1,
									sortable: true,
									dataIndex: 'homeworkName'
								},	{
									text:courseHeadNameStr,
									flex: 1,
									sortable: true,
									dataIndex: 'classHeadName'
								},
								{
									text: startDateStr,
									flex: 1,
									sortable: true,
									dataIndex: 'startDate'
								},
								{
									text: endDateStr,
									flex: 1,
									sortable: true,
									dataIndex: 'endDate'
								},
								{
									text: studentNumStr,
									flex: 1,
									sortable: true,
									dataIndex: 'studentNum'
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
							}, {
								xtype: 'button',
								text: searchStr,
								hidden :true,
								action:'search',
								tooltip:searchStr,
								iconCls:'search',
								listeners:{
									'click':this.onSearch
								}
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
    onHomeworkInfo:function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex);
		var view =Ext.create('MyDesktop.teacher.HomeworkInfoWindow').show();
		record.set('homeworkId',record.get('id'));
		view.down('form').loadRecord(record);
    },
    onItemclick:function (tree,record){
    	if (!record.isRoot()){
         	var store = this.up("window").down("livesearchgridpanel").getStore();
         	if (record.get('checked')==true){
         		record.set("checked",false);
         	}else{
         		record.set("checked",true);
         	}
         	
        	var button = this.up('window').down('button[action=search]');
          	button.fireEvent('click');
        }
	},
	onItemdblclick:function(grid,record){
		var grid = this.up("window").down("livesearchgridpanel");
		var view =Ext.create('MyDesktop.teacher.HomeworkInfoWindow').show();
		record.set('homeworkId',record.get('id'));
		view.down('form').loadRecord(record);
	},
	onAddHomework:function(){
		var records = this.up("window").down("simpletreepanel").getView().getChecked();
		var gridStore = this.up("window").down("livesearchgridpanel").getStore();
    	if (records.length>0){
    		var classHeadNamesStr = '';
    		var classHeadIdsStr = '';
    		Ext.Array.each(records, function(rec){
    			classHeadNamesStr += rec.get('classHeadName')+';';
    			classHeadIdsStr += '\''+rec.get('id')+'\',';
    	    });
    		var view = Ext.create('MyDesktop.teacher.CreateHomeworkWindow',{
    			gridStore:gridStore
    		}).show();
    		var form = view.down("form");
    		var rec = Ext.create('MyDesktop.model.RecordModel');
    		rec.set('classHeadNames',classHeadNamesStr);
    		rec.set('classHeadIds',classHeadIdsStr);
    		
    		form.getForm().loadRecord(rec);
    	}else{
    		Ext.Msg.alert(messageBoxTitle, addInfoStr);
    	}
	},
	onUpdateHomework:function(){
		var grid = this.up("window").down("livesearchgridpanel");
    	var selectedModels = grid.getSelectionModel();
    	var records = this.up("window").down("simpletreepanel").getView().getChecked();
    	var classHeadId ='';
    	if (records.length>0){
    		var record = records[0];
    		classHeadId = record.get('id');
    	}
		if (selectedModels.getCount()!=1){
			Ext.Msg.alert(messageBoxTitle, modifyInfoStr);
		}else{
			var view =Ext.create('MyDesktop.teacher.UpdateHomeworkWindow',{
				gridStore:grid.getStore(),
				classHeadId:classHeadId
			}).show();
			var rec = selectedModels.getSelection()[0];
			rec.set('homeworkId',rec.get('id'));
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
 		var records = this.up("window").down("simpletreepanel").getChecked();
 		var classHeadIdsStr = '';
    	if (records.length>0){
    		Ext.Array.each(records, function(rec){
    			classHeadIdsStr += '\''+rec.get('id')+'\',';
    		});
    	}
		store.load({
			params:{
				teacherId : currentTeacherRecord.get('id'),
				classHeadIds:classHeadIdsStr,
				homeworkStatus:status
			}
		});
	}
});