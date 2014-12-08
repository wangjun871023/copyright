Ext.define('MyDesktop.student.StudentHomeworkInfoWindow', {
    extend: 'Ext.window.Window',
    requires: [
        'Ext.form.Panel'
    ],
    id:'student-homework-info-win',
	modal:true,
	title: studentHomeworkInfoWindowStr,
	titleAlign:'center',
    iconCls:'user',
    width:courseStudentManagerWindowWidth,
    height:courseStudentManagerWindowHeight,
	animCollapse:false,
    constrainHeader:true,
    maximizable:true,
    border:false,
    layout: 'fit',
	initComponent: function() {
		var me = this;
		me.items = [
	   	      me.getFormView()
		];
		me.callParent(arguments);
	},
	getFormView:function(){
		var form = {
			xtype: 'form',
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
					readOnly:true
				},{
					fieldLabel: homeworkNameStr,
					readOnly:true,
					fieldCls:'fieldClsReadyOnly',
					name: 'homeworkName',
				    emptyText:comboboxEmptyText
				},{
					xtype: 'datefield',
					format: 'Y年m月d日',
					id: 'start',
					readOnly:true,
					editable: false,
					fieldCls:'fieldClsReadyOnly',
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
					readOnly:true,
					fieldCls:'fieldClsReadyOnly',
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
						    readOnly:true,
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
			    	name:'classHeadId'
			    }
			],
			listeners:{
				
			}
		};
		return form;
	}
});