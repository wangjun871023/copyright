/*
	作业信息窗口
*/
Ext.define('MyDesktop.teacher.HomeworkInfoWindow', {
    extend: 'Ext.window.Window',
    requires: [
        'Ext.form.Panel'
    ],
	modal:true,
	title: homeworkInfoWindowTitleStr,
	titleAlign:'center',
    iconCls:'user',
    width:createHomeworkWindowWidth,
    height:createHomeworkWindowHeight,
	animCollapse:false,
    constrainHeader:true,
    maximizable:true,
    border:false,
	layout: 'border',
	initComponent: function() {
		var me = this;
		me.items = [
	   	       me.getFormView(),
	   	       me.getGirdView()
		];
		me.callParent(arguments);
	},
	getFormView:function(){
		var form = {
			split: true,  
			title:homeworkInfoStr,
			xtype: 'form',
			region:'west',
			width:'45%',
			layout: 'anchor',
			bodyPadding: bodyPaddingInt,
			defaults: {
				anchor: '100%',
				allowBlank: false
			},     
			defaultType: 'textfield',
			items: [
			    {
					fieldLabel:courseHeadNameStr,
					name: 'classHeadName',
					maxLength:textfieldMaxLength,
					emptyText:fieldTextEmptyText+userNameStr,
					fieldCls:'fieldClsReadyOnly',
					readOnly:true
				}, {
					fieldLabel: courseTeacherStr,
					fieldCls:'fieldClsReadyOnly',
					name: 'teacherName',
					maxLength:textfieldMaxLength,
					value:teacherNameVar,
					readOnly:true
				},{
					fieldLabel:homeworkNameStr,
					name: 'homeworkName',
					emptyText:fieldTextEmptyText+homeworkNameStr
				},{
					xtype: 'datefield',
					format: 'Y年m月d日',
					id: 'start',
					editable: false,
					maxLength:textfieldMaxLength,
					fieldLabel: startDateStr,
					name: 'startDate',
					listeners: {
						change: function(field, newValue) {
							Ext.getCmp('end').setMinValue(newValue);					
						}
					}
				}, {
					id: 'end',
					xtype: 'datefield',
					format: 'Y年m月d日',
					editable: false,
					maxLength:textfieldMaxLength,
					fieldLabel: endDateStr,
					name: 'endDate',
					listeners: {
						change: function(field, newValue) {
							Ext.getCmp('start').setMaxValue(newValue);					
						}
					}
				},{
					xtype: 'displayfield',
		            fieldLabel: accessoryStr,
		            name:'homeworkFileName'
			    },{
			    	xtype:'container',
			    	anchor:'100% -200',
			    	layout:'fit',
			    	items:[
						{
						    xtype: 'htmleditor',
						    name:'homeworkContent',
						    labelAlign: 'top',
						    allowBlank:true,
						    fieldLabel: homeworkContentStr
						}
			    	]
			    },{
			    	hidden:true,
			    	name:'studentIds',
			    	id:'studentIds'
			    },{
			    	hidden:true,
			    	name:'homeworkId'
			    },{
			    	hidden:true,
			    	name:'classHeadId',
			    	listeners:{
						change:this.onBeforeRender
					}
			    }
			]
		};
		return form;
	},
	getGirdView:function(){
		var grid = {
			border: false,
			region:'center',
			title:studentInfoStr,
			xtype: 'livesearchgridpanel',
			border:true,
			selModel:Ext.create('Ext.selection.CheckboxModel'),
			store: Ext.create('MyDesktop.store.HomeworkStudentStore'),
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
			}
			]
		};
		return grid;
	},
	onBeforeRender:function(){
		var grid = this.up("window").down("livesearchgridpanel");
		var store = grid.getStore();
		var homeworkIdField = this.up("window").down("textfield[name=homeworkId]");
		store.load({
			params:{
				homeworkId:homeworkIdField.getValue()
			}
		});
	}
});