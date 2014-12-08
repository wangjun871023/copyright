/*
	学生信息窗口
*/
Ext.define('MyDesktop.admin.StudentInfoWindow', {
    extend: 'Ext.window.Window',
    requires: [
        'Ext.form.Panel'
    ],
	modal:true,
	title: studentInfoStr,
	titleAlign:'center',
    iconCls:'user',
	width:createHomeworkWindowWidth,
    height:createHomeworkWindowHeight,
	animCollapse:false,
    constrainHeader:true,
    maximizable:true,
    border:false,
	layout: 'fit',
	gridStore:null,
	classHeadId:null,
	initComponent: function() {
		var me = this;
		me.items = [
	   	       me.getGirdView()
		];
		me.callParent(arguments);
	},
	getGirdView:function(){
		var grid = {
			//title:studentInfoStr,
			xtype: 'livesearchgridpanel',
			border:true,
			store: Ext.create('MyDesktop.store.StudentStore'),
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
				dataIndex: 'stateSelectedClass'
			}],
			listeners:{
				'beforerender':this.onBeforeRender
			}
		};
		return grid;
	},
	onBeforeRender:function(){
		var classHeadIdVar  =this.up("window").classHeadId;
		this.up("window").down("livesearchgridpanel").getStore().load({
			params:{
				limit:"",
				classHeadId:classHeadIdVar
			}
		});
	}
});