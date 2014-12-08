/*
		学生作业窗口
*/
Ext.define('MyDesktop.student.StudentHomeworkScoreManagerWindow', {
    extend: 'Ext.ux.desktop.Module',
    requires: [
        'Ext.data.ArrayStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer',
	    'Ext.selection.CheckboxModel',
	    'MyDesktop.student.StudentDoHomeworkWindow',
	    'MyDesktop.student.StudentHomeworkFinshWindow',
	    'MyDesktop.student.StudentHomeworkInfoWindow'
    ],
    id:'student-homework-score-manager-win',
    init : function(){
        this.launcher = {
            text: studentHomeworScorekManagerWindowTitleStr,
            iconCls:'icon-grid'
        };
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('student-homework-score-manager-win');
        if(!win){
            win = desktop.createWindow({
                id:'student-homework-score-manager-win',
                title:studentHomeworScorekManagerWindowTitleStr,
                width:courseStudentManagerWindowWidth,
                height:courseStudentManagerWindowHeight,
                iconCls: 'icon-grid',
                animCollapse:false,
                constrainHeader:true,
                border:false,
                layout: 'fit',
                items: [{
					border: false,
					region:'center',
					xtype: 'livesearchgridpanel',
					border:true,
					store: Ext.create('MyDesktop.store.HomeworkStudentStore'),
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
							text: statusStr,
							flex: 1,
							sortable: true,
							dataIndex: 'studentHomeworkStatus'
						},
						{
							text: scoreStr,
							flex: 1,
							sortable: true,
							dataIndex: 'studentScore'
						},{
							xtype:'actioncolumn',
							text:actionStr,
							width:80,
					        items: [{
				                icon: '../../common/icons/new_list.png', 
				                tooltip: homeworkInfoStr,
				                handler: this.onGetHomeworkInfo
				            },{
				            	icon: '../../common/icons/mark_complete.png', 
				                tooltip: searchHomeworkInfoStr,
				                handler: this.onSearchHomeworkInfo
				            }]
						}
					],
					listeners: {
						beforerender: this.onBeforeRender
					//	itemdblclick: this.onItemdblclick
					},	
					tbar:[
					    
				]
			}]
            });
        }
        return win;
    },
    onBeforeRender:function(grid){
    	grid.getStore().load({
    		params:{
    			studentId:studentIdVar
    		}
    	});
    },
    onGetHomeworkInfo:function(grid, rowIndex, colIndex){
    	var win = new MyDesktop.student.StudentHomeworkInfoWindow().show();
    	var form = win.down('form');
    	var rec = grid.getStore().getAt(rowIndex);
    	form.loadRecord(rec);
    },
    onSearchHomeworkInfo:function(grid, rowIndex, colIndex){
    	var win = new MyDesktop.student.StudentHomeworkFinshWindow().show();
    	var form = win.down('form');
    	var rec = grid.getStore().getAt(rowIndex);
    	form.loadRecord(rec);
    }
});