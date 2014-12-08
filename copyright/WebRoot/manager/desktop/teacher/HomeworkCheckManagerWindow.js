/*
		检查学生作业理窗口
*/
Ext.define('MyDesktop.teacher.HomeworkCheckManagerWindow', {
    extend: 'Ext.ux.desktop.Module',
    id:'homework-check-manager-win',
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
		'MyDesktop.teacher.StudentHomeworkInfoCheckWindow',
		'MyDesktop.store.ClassHeadStore',
		'MyDesktop.store.StudentStore'
    ],
    init : function(){
        this.launcher = {
            text: '检查学生作业窗口',
            iconCls:'icon-grid'
        };
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('homework-check-manager-win');
        if(!win){
            win = desktop.createWindow({
                id:'homework-check-manager-win',
                title:'检查学生作业窗口',
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
						store:Ext.create('MyDesktop.store.ClassHeadHomeworkStore'),
						region:'west',
						itemclick: this.onItemclick
					},{
						border: false,
						region:'center',
						xtype: 'searchgridpanel',
						border:true,
						store: Ext.create('MyDesktop.store.HomeworkCheckStore'),
						columns: [{
							xtype: 'rownumberer',
							text:sequenceStr,
							width:30
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
							text: statusStr,
							flex: 1,
							sortable: true,
							dataIndex: 'studentHomeworkStatus'
						},{
							text: scoreStr,
							flex: 1,
							sortable: true,
							dataIndex: 'studentScore'
						},{
							xtype:'actioncolumn',
							text:actionStr,
							width:40,
					        items: [{
				            	icon: '../../common/icons/mark_complete.png', 
				                tooltip: searchHomeworkInfoStr,
				                handler: this.onStudentHomeworkInfo
				            }]
						}],
						listeners: {
							itemdblclick:this.onItemdblclick,
							render:this.onRender
						},	
						tbar:[{
							xtype: 'button',
							tooltip:allStr,
							text:allStr,
							action:'showAll',
							toggleGroup:'status',
							iconCls:'showAll',
							allowDepress:false,
						 	handler: this.onSearch
						},{
							xtype: 'button',
							tooltip:completeStr,
							text:completeStr,
							action:'showComplete',
							toggleGroup:'status',
							iconCls:'showComplete',
							allowDepress:false,
							handler: this.onSearch
						},{
							xtype: 'button',
							tooltip:noCompleteStr,
							text:noCompleteStr,
							toggleGroup:'status',
							action:'showActive',
							allowDepress:false,
							iconCls:'showActive',
							handler: this.onSearch
						},'->',studentNumberStr+"或姓名:",{
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
    onStudentHomeworkInfo:function(grid, rowIndex, colIndex){
    	var grid = this.up('window').down('searchgridpanel');
    	var rec = grid.getStore().getAt(rowIndex);
    	if(rec.get('studentHomeworkStatus')!= "<span style='color:red'>未完成</span>"){
    		var view = new MyDesktop.teacher.StudentHomeworkInfoCheckWindow({
    			gridStore:grid.getStore()
    		}).show();
    		var form = view.down('form');
    		form.loadRecord(rec);
    	}else{
    		Ext.Msg.alert(messageBoxTitle, '未完成不可以查看');
    	}
	},
    onItemdblclick:function(grid, record){
    	if(record.get('studentHomeworkStatus')!= "<span style='color:red'>未完成</span>"){
    		var view = new MyDesktop.teacher.StudentHomeworkInfoCheckWindow({
        		gridStore:grid.getStore()
        	}).show();
    		var form = view.down('form');
        	form.loadRecord(record);
    	}else{
    		Ext.Msg.alert(messageBoxTitle, '未完成不可以查看');
    	}
	},
	onSearch:function(){
		var searchField = this.up('window').down('textfield[name=searchField]');
    	var key = searchField.getValue();
    	var grid = this.up('window').down('searchgridpanel');
    	var tree = this.up('window').down('simpletreepanel');
    	var showComplete = this.up('window').down('button[action=showComplete]');
    	var showActive = this.up('window').down('button[action=showActive]');
    	var status ='';
    	if (showComplete.pressed ==true){
    		status = homeworkDoConstant;
    	}
    	if (showActive.pressed ==true){
    		status = homeworkUnDoConstant;
    	}
    	
		var records = tree.getView().getChecked();
		if (records.length>0){
			//有树参数
			var record = records[0];
			var store = grid.getStore();
			store.extraParams = {
					key:key,
					homeworkId:record.get('id'),
					limit:itemsPerPageVar,
					teacherId : currentTeacherRecord.get('id'),
					studentHomeworkStatus:status
			};
			store.loadPage(1,{
				params:{
					key:key,
					studentHomeworkStatus:status,
					teacherId : currentTeacherRecord.get('id'),
					homeworkId:record.get("id")
				}
			});
		}else{
			var store = grid.getStore();
			store.extraParams = {
					studentHomeworkStatus:status,
					limit:itemsPerPageVar,
					homeworkId:'',
					teacherId : currentTeacherRecord.get('id'),
            		key:key
			};
			store.loadPage(1,{
				params:{
					key:key,
					teacherId : currentTeacherRecord.get('id'),
					studentHomeworkStatus:status,
					homeworkId:''
				}
			});
		}
	}
});