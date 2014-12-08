/*
		课头信息管理窗口
*/
Ext.define('MyDesktop.admin.ClassHeadManagerWindow', {
    extend: 'Ext.ux.desktop.Module',
    requires: [
		'MyDesktop.common.LiveSearchGridPanel',
		'MyDesktop.common.SimpleTreePanel',
		'MyDesktop.teacher.CreateCourseHeadWindow',
		'MyDesktop.teacher.UpdateCourseHeadWindow',
		'MyDesktop.admin.StudentInfoWindow',
		'MyDesktop.store.ClassHeadStore',
		'MyDesktop.store.TeacherClassHeadStore',
		'MyDesktop.store.StudentStore'
    ],
    id:'classhead-manager-win',
    init : function(){
        this.launcher = {
            text: classHeadManagerWindowTitleStr,
            iconCls:'icon-grid'
        };
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('classhead-manager-win');
        if(!win){
            win = desktop.createWindow({
                id:'classhead-manager-win',
                title:classHeadManagerWindowTitleStr,
                width:classHeadManagerWindowWidth,
                height:classHeadManagerWindowHeight,
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
						store:Ext.create('MyDesktop.store.TeacherTreeStore'),
						region:'west',
						itemclick: this.onItemclick
					},{
						border: false,
						region:'center',
						xtype: 'livesearchgridpanel',
						border:true,
						selModel:Ext.create('Ext.selection.CheckboxModel'),
						store: Ext.create('MyDesktop.store.ClassHeadEntityStore'),
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
								text: startDateStr,
								flex: 1,
								sortable: true,
								dataIndex: 'startDate'
							},{
								text: endDateStr,
								flex: 1,
								sortable: true,
								dataIndex: 'endDate'
							}, {
								text: teacherOfClassStr,
								flex: 1,
								sortable: true,
								dataIndex: 'teacherName'
							},{
		                        text: validStatusStr,
		                        flex: 1,
		                        sortable: true,
		                        renderer :changeValidCode,
		                        dataIndex: 'classHeadStatus'
							},{
								xtype:'actioncolumn',
								text:actionStr,
								width:40,
						        items: [{
					            	icon: '../../common/icons/mark_complete.png', 
					                tooltip: studentInfoStr,
					                handler: this.onShowStudentInfo
					            }]
							}
						],
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
							text:addCourseHeadStr,
							tooltip:addStr,
							iconCls:'add',
							handler: this.onAddCourseHead
						}, {
							text:modifyCourseHeadStr,
							tooltip:modifyStr,
							iconCls:'option',
							handler: this.onModifyCourseHead
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
						},{
							xtype: 'button',
							text: searchStr,
							hidden:true,
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
		var grid = this.up("window").down("livesearchgridpanel");
		grid.getStore().load({
			params:{
				courseId:userCourseVar
			}
		});
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
			    			 model.set('classHeadStatus',validConstant);
			    		 });
			    		 gridVar.getStore().sync();
			    	 }
			     }
			});
		}
	},
	onShowStudentInfo:function(grid, rowIndex, colIndex) {
		var rec = grid.getStore().getAt(rowIndex);
		new MyDesktop.admin.StudentInfoWindow({
			classHeadId:rec.get('id')
		}).show();
	},
	onInvalidAction:function(){
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
			    			 model.set('classHeadStatus',invalidConstant);
			    		 });
			    		 gridVar.getStore().sync();
			    	 }
			     }
			});
		}
	},
	onAddCourseHead:function(){
    	var tree = this.up("window").down("simpletreepanel");
    	var records =this.up("window").down("simpletreepanel").getView().getChecked();
    	if (records.length>0){
			var record = records[0];
			var view = new MyDesktop.teacher.CreateCourseHeadWindow({
	    		treeStore:tree.getStore()
	    	}).show();
			var form = view.down("form");
			record.set('teacherId',record.get('id'));
    		form.getForm().loadRecord(record);
		}else{
			Ext.Msg.alert(messageBoxTitle, addInfoStr);
		}
	},
	onModifyCourseHead:function(btn){
		var grid = this.up("window").down("livesearchgridpanel");
		var selectedModels = grid.getSelectionModel();
		if (selectedModels.getCount()!=1){
			Ext.Msg.alert(messageBoxTitle, modifyInfoStr);
		}else{
			var record = selectedModels.getSelection()[0];
			var view = new MyDesktop.teacher.UpdateCourseHeadWindow({
    			treeStore:grid.getStore()
    		}).show();
    		var form = view.down("form");
    		record.set("classHeadId",record.get('id'));
    		form.getForm().loadRecord(record);
		}
	},
    onItemclick:function (tree,record){
        var records = tree.getChecked();
		var showComplete = this.up('window').down('button[action=showComplete]');
    	var showActive = this.up('window').down('button[action=showActive]');
    	var status ='';
    	if (showComplete.pressed ==true){
    		status = validConstant;
    	}
    	if (showActive.pressed ==true){
    		status = invalidConstant;
    	}
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
		var view = new MyDesktop.teacher.UpdateCourseHeadWindow({
			treeStore:grid.getStore()
		}).show();
		var form = view.down("form");
		record.set("classHeadId",record.get('id'));
		form.getForm().loadRecord(record);
	},
	onSearch:function(){
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
			var store = this.up("window").down("livesearchgridpanel").getStore();
			store.load({
				params:{
					teacherId:record.get("id"),
					classHeadStatus:status
				}
			});
		}else{
			var store =  this.up("window").down("livesearchgridpanel").getStore();
			store.load({
				params:{
					classHeadStatus:status
				}
			});
		}
	}
});