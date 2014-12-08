/*
	课头添加窗口
*/
Ext.define('MyDesktop.teacher.CreateCourseHeadWindow', {
    extend: 'Ext.window.Window',
    requires: [
        'Ext.form.Panel'
    ],
	modal:true,
	title: createCourseHeadWindowTitleStr,
    iconCls:'user',
	width:courseHeadWindowWidth,
    height:courseHeadWindowHeight,
	animCollapse:false,
    constrainHeader:true,
	layout: 'fit',
	treeStore:null,
	initComponent: function() {
		var me = this;
		//view
		me.items = [
	   	       me.getFormView()
		];
		me.callParent(arguments);
	},
	getFormView:function(){
		var form = {
			border: false,
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
					emptyText:fieldTextEmptyText+courseHeadNameStr
				}, {
					fieldLabel: courseTeacherStr,
					fieldCls:'fieldClsReadyOnly',
					name: 'teacherName',
					maxLength:textfieldMaxLength,
					value:teacherNameVar,
					readOnly:true
				},{
					xtype: 'datefield',
					format:  'Y年m月d日',
					id: 'start',
					editable: false,
					maxLength:textfieldMaxLength,
					fieldLabel:  startDateStr,
					emptyText:comboboxEmptyText+startDateStr,
					name: 'startDate',
					listeners: {
						change: function(field, newValue) {
							Ext.getCmp('end').setMinValue(newValue);					
						}
					}
				}, {
					id: 'end',
					xtype: 'datefield',
					format:  'Y年m月d日',
					editable: false,
					maxLength:textfieldMaxLength,
					fieldLabel: endDateStr,
					emptyText:comboboxEmptyText+endDateStr,
					name: 'endDate',
					listeners: {
						change: function(field, newValue) {
							Ext.getCmp('start').setMaxValue(newValue);					
						}
					}
				},{
					xtype: 'filefield',
		            emptyText: courseExcelStr,
		            fieldLabel: importExcelStr,
		            name: 'courseHeadExcel',
		            buttonText: '',
		            buttonConfig: {
		                cls: 'excel',
		                width:25
		            }
			    },{
		    	   xtype: 'hiddenfield',
		           name: 'teacherId',
		           value: teacherIdVar
			    },{
			       xtype: 'hiddenfield',
			       name: 'courseId',
			       value: userCourseVar	
			    }
			],
			buttons: [{
				text: submitStr,
				formBind: true,
				disabled: true,
				handler: this.onSubmit
			},{
				text: cancelStr,
				handler: this.onCancel
			}]
		};
		return form;
	},
	onSubmit:function(){
		var form = this.up('form').getForm();
		var windowVar = this.up('window');
		var treeStore = this.up('window').treeStore;
		if (form.isValid()) {
			form.submit({
				url:createCourseHeadActionUrl,
				waitMsg : waitMsgStr,
				success: function(form, action) {
					treeStore.load();
					//关闭窗口
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
	onCancel:function(){
		this.up('window').close();
	}
});