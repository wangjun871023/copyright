/*
	任课教师修改的信息维护
*/
Ext.define('MyDesktop.admin.TeacherManagerListEditWindow', {
	extend : 'Ext.window.Window',
	title :TeacherManagerListEditWindowTitle,
	iconCls:'user',
	width:TeacherManagerListEditWindowWidth,
	height:TeacherManagerListEditWindowHeight,
	layout : 'fit',
	modal:true,
	autoShow : true,
	initComponent: function() {
		var me = this;
		//view
		me.items = [
	   	       me.getFormView(),
		];
		me.callParent(arguments);
	},
	getFormView:function(){
		var form = {
			xtype : 'form',
			bodyPadding: bodyPaddingInt,
			border:false,
			layout: 'anchor',
			defaults: {
				anchor: '100%',
				allowBlank: false,
				maxLength:textfieldMaxLength
			},   
			defaultType: 'textfield',
			items : [ {
					fieldLabel : userNameStr,
					name : 'teacherCode',
					readOnly:true,
					fieldCls:'fieldClsReadyOnly',
					minLength:textfieldMinLength,
					emptyText:fieldTextEmptyText+userNameStr
				}, {
					fieldLabel :teacherNameStr,
					name : 'teacherName',
					emptyText:fieldTextEmptyText+userNameStr
				},{
					xtype:'combobox',
					fieldLabel: teacherGenderStr,
					name: 'teacherGender',
					store:Ext.create('MyDesktop.store.GenderStore'),
					queryMode: 'local',
				    displayField: 'genderName',
				    valueField: 'id',
				    value:teacherGenderDefault,
				    editable:false,
				    emptyText:comboboxEmptyText
				}, {
					fieldLabel :academyStr,
					name : 'teacherAcademy',
					value:academyDefault,
					emptyText:fieldTextEmptyText+academyStr
				} ,{
					fieldLabel : schoolOfTeacher,
					name : 'teacherSchool',
					value: schoolOfTeacherDefault,
					emptyText:fieldTextEmptyText+schoolOfTeacher
				},{
					xtype: 'fieldcontainer',
					fieldLabel: passwordResetStr,
			        defaultType: 'checkboxfield',
			        items: [{
		                name:'passwordReset',
		                inputValue: '1'
		            }]
			}],
			buttons: [{
					text: submitStr,
					formBind: true,
					disabled: true,
					handler:this.onSubmit
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
		var passwordResetObj =  form.getValues().passwordReset;
		//确认判断是否重置密码
		if (passwordResetObj=='1'){
			Ext.Msg.show({
			     title:messageBoxTitle,
			     msg: passwordConfirmInfoStr,
			     buttons: Ext.Msg.OKCANCEL,
			     icon: Ext.Msg.QUESTION,
			     fn:function(buttonId){
			    	 if (buttonId=='ok'){
				    	 if (form.isValid()) {
				    		//写入修改的字段
							var record = form.getRecord();
							var values = form.getValues();
							//删除不需要的数据
							delete values.passwordReset;
							//写入修改的字段
						 	record.set(values);
							form.submit({ 
								//检查是否重置密码
								url:passwordResetActionUrl,
								params: {
									teacherCode: form.getRecord().get('teacherCode')
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
	onCancel: function(){
		this.up('window').close();
	}
});