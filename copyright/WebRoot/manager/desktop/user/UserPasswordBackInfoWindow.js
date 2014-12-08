/*
	密码信息窗口
*/
Ext.define('MyDesktop.user.UserPasswordBackInfoWindow', {
    extend: 'Ext.window.Window',
	modal:true,
	resizable:false,
	title: '找回密码信息',
    iconCls:'lock',
	width:userPasswordBackWindowWidth,
    height:userPasswordBackWindowHeight,
	animCollapse:false,
    constrainHeader:true,
    layout: 'fit',
	initComponent: function() {
		var me = this;
		//view
		me.items = [
		       //右边form
	   	       me.getRightFormView()
		];
		me.callParent(arguments);
	},
	getRightFormView:function(){
		var form = {
			border: false,
			xtype: 'form',
			layout: 'anchor',
			bodyPadding: bodyPaddingInt,
			defaults: {
				anchor: '100%',
				allowBlank: false,
				maxLength:textfieldMaxLength
			},     
			defaultType: 'textfield',
			items: [{
					fieldLabel:userNameStr,
					name: 'username',
					readOnly:true,
					fieldCls:'fieldClsReadyOnly',
					value:userNameVar
				},{
					xtype:'combobox',
					fieldLabel: questionOfPasswordStr,
					store:Ext.create('MyDesktop.store.QuestionStore'),
					name: 'questionOfPassword',
					queryMode: 'local',
				    displayField: 'questionName',
				    valueField: 'id',
				    editable:false,
				    emptyText:comboboxEmptyText
				},{
					fieldLabel:answerOfPasswordStr,
					name: 'answerOfPassword',
					allowBlank: false,
					emptyText:fieldTextEmptyText+answerOfPasswordStr
				}
			], 
			buttons: [{
				text: submitStr,
				formBind: true, //only enabled once the form is valid
				disabled: true,
				handler: this.onSubmitAction
			},{
				text: cancelStr,
				handler: this.onCancel
			}]
		};
		return form;
	},
	onSubmitAction:function(){
		var form = this.up('form').getForm();
		var windowTmp = this.up('window');
		if (form.isValid()) {
			form.submit({
				url:passwordBackInfoActionUrl,
				waitMsg :waitMsgStr,
				success: function(form, action) {
					Ext.Msg.alert(messageBoxTitle, "保存成功");
					windowTmp.close();
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
	onResetAction:function(){
		this.up('form').getForm().reset();
	},
	onBackToLoginAction:function(){
		userPasswordBackWindowObject.hide();
		//打开登录窗口
		loginWindowObject.show();
	},
	onCancel:function() {
		this.up('window').close();
	}
});