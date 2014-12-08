/*
	学生添加窗口
*/
Ext.define('MyDesktop.teacher.UpdateStudentWindow', {
    extend: 'Ext.window.Window',
    requires: [
        'Ext.form.Panel'
    ],
	modal:true,
	title: updateStudentWindowTitleStr,
    iconCls:'user',
	width:courseHeadStudentWindowWidth,
    height:courseHeadStudentWindowHeight+70,
	animCollapse:false,
    constrainHeader:true,
	layout: 'fit',
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
					fieldCls:'fieldClsReadyOnly',
					name:'classHeadName',
					submitValue:false,
					readOnly:true,
					emptyText:fieldTextEmptyText+userNameStr
				}, {
					fieldLabel:courseTeacherStr,
					fieldCls:'fieldClsReadyOnly',
					readOnly:true,
					name:'teacherName',
					value:teacherNameVar
				}, {
					//用户名
					fieldLabel: userNameStr,
					name: 'studentCode',
					fieldCls:'fieldClsReadyOnly',
					readOnly:true
				}, {
					fieldLabel:studentNumberStr,
					name: 'studentNo',
					fieldCls:'fieldClsReadyOnly',
					readOnly:true
				},{
					fieldLabel: nameStr,
					name: 'studentName',
					fieldCls:'fieldClsReadyOnly',
					readOnly:true
				},{
					maxLength:textfieldMaxLength,
					xtype:'combobox',
					fieldLabel: genderStr,
					name: 'studentGender',
					store:Ext.create('MyDesktop.store.GenderStore'),
					queryMode: 'local',
					fieldCls:'fieldClsReadyOnly',
					readOnly:true,
				    displayField: 'genderName',
				    valueField: 'id',
				    editable:false,
				    value:'男',
				    emptyText:comboboxEmptyText
				}
				,{
					fieldLabel: specialtyStr,
					name: 'studentSpecialty',
					fieldCls:'fieldClsReadyOnly',
					readOnly:true
				},{
					fieldLabel: classStr,
					name: 'studentClass',
					fieldCls:'fieldClsReadyOnly',
					readOnly:true
				},{
					fieldLabel:gradeStr,
					name: 'studentGrade',
					fieldCls:'fieldClsReadyOnly',
					readOnly:true
				},{
					fieldLabel: '激活码',
					name: 'studentActiveCode',
					fieldCls:'fieldClsReadyOnly',
					readOnly:true,
					allowBlank:true
				},{
					fieldLabel:'激活时间',
					allowBlank:true,
					fieldCls:'fieldClsReadyOnly',
					readOnly:true,
					name: 'studentActiveTime'
				},{
					maxLength:textfieldMaxLength,
					xtype:'combobox',
					fieldLabel:studyAgainStr,
					name: 'studentAgain',
					store:Ext.create('MyDesktop.store.StudyAgainStore'),
					queryMode: 'local',
				    displayField: 'name',
				    valueField: 'id',
				    value:'否',
				    fieldCls:'fieldClsReadyOnly',
					readOnly:true,
				    editable:false,
				    emptyText:comboboxEmptyText
				},{
					xtype: 'fieldcontainer',
					fieldLabel: passwordResetStr,
			        defaultType: 'checkboxfield',
			        items: [{
		                name:'passwordReset',
		            	allowBlank:true,
		                inputValue: '1'
		            }]
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
		var passwordResetObj =  this.up('window').down("checkboxfield[name=passwordReset]");
		//确认判断是否重置密码
		if (passwordResetObj.getValue()=='1'){
			Ext.Msg.show({
			     title:messageBoxTitle,
			     msg: passwordConfirmInfoStr,
			     buttons: Ext.Msg.OKCANCEL,
			     icon: Ext.Msg.QUESTION,
			     fn:function(buttonId){
			    	 if (buttonId=='ok'){
				    	 if (form.isValid()) {
				    		//写入修改的字段
				    		//不需要重置密码
							var record = form.getRecord();
							var values = form.getValues();
							//删除不需要的数据
							delete values.teacherName;
							delete values.classHeadName;
							delete values.passwordReset;
							//写入修改的字段
						 	record.set(values);
							form.submit({ 
								//检查是否重置密码
								url:passwordResetActionUrl,
								params: {
									studentCode: form.getRecord().get('studentCode')
								},
								waitMsg : waitMsgStr,
								success: function(form, action) {
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
			    	}
			     }
			});
		}else{
			 if (form.isValid()) {
				//不需要重置密码
				var record = form.getRecord();
				var values = form.getValues()
				//写入修改的字段
			 	record.set(values);
			 	windowVar.close();
			 }
		}
	},
	onCancel:function(){
		this.up('window').close();
	}
});