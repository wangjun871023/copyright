Ext.define('MyDesktop.student.StudentDoHomeworkWindow', {
    extend: 'Ext.window.Window',
    requires: [
        'Ext.form.Panel'
    ],
    id:'student-do-homework-win',
	modal:true,
	title: studentDoHomeworkWindowTitle,
	titleAlign:'center',
    iconCls:'user',
    width:courseStudentManagerWindowWidth,
    height:courseStudentManagerWindowHeight,
	animCollapse:false,
    constrainHeader:true,
    maximizable:true,
    border:false,
    layout: 'fit',
    gridStore:null,
	initComponent: function() {
		var me = this;
		me.items = [
	   	      me.getFormView()
		];
		me.buttons = me.getButtons();
		me.callParent(arguments);
	},
	getFormView:function(){
		var form = {
			xtype: 'form',
			layout: 'anchor',
			bodyPadding: bodyPaddingInt,
			defaults: {
				anchor: '100%'
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
				},	{
					fieldLabel: homeworkNameStr,
					name: 'homeworkName',
					fieldCls:'fieldClsReadyOnly',
				    emptyText:comboboxEmptyText
				}, {
					fieldLabel: courseTeacherStr,
					fieldCls:'fieldClsReadyOnly',
					name: 'teacherName',
					fieldCls:'fieldClsReadyOnly',
					maxLength:textfieldMaxLength,
					readOnly:true
				},{
					fieldLabel: studentNameStr,
					fieldCls:'fieldClsReadyOnly',
					name: 'studentName',
					maxLength:textfieldMaxLength,
					readOnly:true
				}, {
					xtype: 'datefield',
					format: 'Y年m月d日',
					fieldCls:'fieldClsReadyOnly',
					id: 'start',
					editable: false,
					readOnly:true,
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
					fieldCls:'fieldClsReadyOnly',
					editable: false,
					readOnly:true,
					maxLength:textfieldMaxLength,
					fieldLabel: endDateStr,
					name: 'endDate',
					listeners: {
						change: function(field, newValue) {
							Ext.getCmp('start').setMaxValue(newValue);					
						}
					}
				},{
					xtype: 'filefield',
		            emptyText: accessoryEmptyStr,
		            fieldLabel: accessoryStr,
		            name: 'uploadFile',
//		            allowBlank:false,
		            buttonText: '',
		            buttonConfig: {
		                cls: 'uploadFile'
		            }
			    },{
			    	xtype:'container',
			    	anchor:'100% -200',
			    	layout:'fit',
			    	items:[
						{
						    xtype: 'htmleditor',
						    name:'studentHomeworkContent',
						    labelAlign: 'top',
						    allowBlank:true,
						    fieldLabel: homeworkContentStr
						}
			    	]
			    },{
			    	hidden:true,
			    	name:'studentHomeworkId'
			    },{
			    	hidden:true,
			    	name:'studentFileId'
			    }
			]
		};
		return form;
	},
	getButtons:function (){
		var buttons =  [{
			text: submitStr,
			handler:  this.onSubmit
		},{
			text: cancelStr,
			handler: this.onCancel
		}];
		return buttons;
	},
	onSubmit:function(){
		var form = this.up('window').down('form').getForm();
		var windowVar = this.up('window');
		if (form.isValid()) {
			form.submit({
				url:editHomeworkStudentUrl,
				waitMsg : waitMsgStr,
				success: function(form, action) {
					var gridStore = windowVar.gridStore;
					gridStore.load({
						params:{
			    			studentId:studentIdVar
			    		}
					});
					windowVar.close();
				},
				failure: function(form, action) {
					if (action.result!=null){
						Ext.Msg.alert(messageBoxTitle, action.result.info);
					}else{
						Ext.Msg.alert(messageBoxTitle, messageBoxError);
					}
				}
			});
		}
	},
	onCancel:function() {
		this.up('window').close();
	}
});