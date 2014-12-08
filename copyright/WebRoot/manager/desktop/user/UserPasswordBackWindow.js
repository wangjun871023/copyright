/*
	密码找回窗口
*/
Ext.define('MyDesktop.user.UserPasswordBackWindow', {
    extend: 'Ext.window.Window',
    requires: [
        'MyDesktop.user.UserPasswordConfirmWindow'
    ],
	modal:true,
	resizable:false,
	title: userPasswordBackWindowTitleStr,
    iconCls:'lock',
    closable:false,
	width:userPasswordBackWindowWidth,
    height:userPasswordBackWindowHeight,
	animCollapse:false,
    constrainHeader:true,
    layout: 'border',
	initComponent: function() {
		var me = this;
		//view
		me.items = [
		       //右边form
	   	       me.getRightFormView(),
	   	       me.getLeftLogoPicView()
		];
		me.callParent(arguments);
	},
	getRightFormView:function(){
		var form = {
			border: false,
			xtype: 'form',
			layout: 'anchor',
			region:'center',
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
					minLength:textfieldMinLength,
					emptyText:fieldTextEmptyText+userNameStr
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
				text: resetStr,
				handler: this.onResetAction
			},{
				text: backToLoginWinStr,
				handler:this.onBackToLoginAction
			}]
		};
		return form;
	},
	getLeftLogoPicView:function (){
		var pic = {
			xtype:'image',
			region:'west',
			width:'52%',
			src:userPasswordBackPicSrc
		};
		return pic;
	},
	onSubmitAction:function(){
		var form = this.up('form').getForm();
		if (form.isValid()) {
			form.submit({
				url:backPasswordFormActionUrl,
				waitMsg :waitMsgStr,
				success: function(form, action) {
					userNameVar = form.getValues().username;
					userPasswordBackWindowObject.hide();
					//清空Form值
					form.reset();
					//打开修改密码的界面
					new MyDesktop.user.UserPasswordConfirmWindow().show();
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
	}
});