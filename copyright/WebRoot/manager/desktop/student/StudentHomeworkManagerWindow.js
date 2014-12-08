/*
		学生作业窗口
*/
Ext.define('MyDesktop.student.StudentHomeworkManagerWindow', {
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
    id:'student-homework-manager-win',
    init : function(){
        this.launcher = {
            text: studentHomeworkManagerWindowTitleStr,
            iconCls:'icon-grid'
        };
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('student-homework-manager-win');
        if(!win){
            win = desktop.createWindow({
                id:'student-homework-manager-win',
                title:studentHomeworkManagerWindowTitleStr,
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
						},{
							xtype:'actioncolumn',
							text:actionStr,
							width:60,
					        items: [{
				                icon: '../../common/icons/new_list.png', 
				                tooltip: homeworkInfoStr,
				                handler: this.onGetHomeworkInfo
				            },{
				            	icon: '../../common/icons/edit_task.png', 
				                tooltip: studentDoHomeworkWindowTitle,
				                handler: this.onEditHomework
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
					tbar:[{
						xtype: 'button',
						tooltip:allStr,
						text:allStr,
						toggleGroup:'status',
						iconCls:'showAll',
					 	handler: this.onShowAll
					},{
						xtype: 'button',
						tooltip:completeStr,
						text:completeStr,
						toggleGroup:'status',
						iconCls:'showComplete',
						handler: this.onShowComplete
					},{
						xtype: 'button',
						tooltip:noCompleteStr,
						text:noCompleteStr,
						toggleGroup:'status',
						iconCls:'showActive',
						handler: this.onShowUnComplete
					}
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
    onEditHomework:function(grid, rowIndex, colIndex){
    	var grid = this.up('window').down('livesearchgridpanel');
    	var rec = grid.getStore().getAt(rowIndex);
    	console.log(rec);
    	Date.prototype.format =function(format)
    	{
    	var o = {
    	"M+" : this.getMonth()+1, //month
    	"d+" : this.getDate(), //day
    	"h+" : this.getHours(), //hour
    	"m+" : this.getMinutes(), //minute
    	"s+" : this.getSeconds(), //second
    	"q+" : Math.floor((this.getMonth()+3)/3), //quarter
    	"S" : this.getMilliseconds() //millisecond
    	}
    	if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
    	(this.getFullYear()+"").substr(4- RegExp.$1.length));
    	for(var k in o)if(new RegExp("("+ k +")").test(format))
    	format = format.replace(RegExp.$1,
    	RegExp.$1.length==1? o[k] :
    	("00"+ o[k]).substr((""+ o[k]).length));
    	return format;
    	}
    	var today =  new Date().format("yyyy年MM月dd日");
    	if( (today >= rec.get('startDate')) && (today < rec.get('endDate'))){
    		var win = new MyDesktop.student.StudentDoHomeworkWindow({
    			gridStore:grid.getStore()
    		}).show();
    		var form = win.down('form');
    		rec.set('studentHomeworkId',rec.get('id'));
    		form.loadRecord(rec);
    	}else{
    		//作业不可修改
    		Ext.Msg.alert(messageBoxTitle, "当前作业不可修改");
    	}
    },
    onSearchHomeworkInfo:function(grid, rowIndex, colIndex){
    	var win = new MyDesktop.student.StudentHomeworkFinshWindow().show();
    	var form = win.down('form');
    	var rec = grid.getStore().getAt(rowIndex);
    	form.loadRecord(rec);
    },
    onShowAll:function(obj){
    	var grid = this.up('window').down('livesearchgridpanel');
    	grid.getStore().load({
    		params:{
    			studentId:studentIdVar
    		}
    	});
    },
    onShowComplete:function(){
    	var grid = this.up('window').down('livesearchgridpanel');
    	grid.getStore().load({
    		params:{
    			studentId:studentIdVar,
    			studentHomeworkStatus :homeworkDoConstant
    		}
    	});
    },
    onShowUnComplete:function(){
    	var grid = this.up('window').down('livesearchgridpanel');
    	grid.getStore().load({
    		params:{
    			studentId:studentIdVar,
    			studentHomeworkStatus :homeworkUnDoConstant
    		}
    	});
    }
});