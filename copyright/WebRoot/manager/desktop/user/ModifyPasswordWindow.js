/*
	密码重置窗口
*/
Ext.define('MyDesktop.user.ModifyPasswordWindow', {
    extend: 'Ext.window.Window',
    requires: [
        'Ext.form.Panel'
    ],
    id:'user-password-confirm-win',
	modal:true,
	resizable:false,
	title: '密码修改窗口',
    iconCls:'logout',
	width:userPasswordConfirmWidth,
    height:userPasswordConfirmHeight,
	animCollapse:false,
    constrainHeader:true,
	layout: 'border',
	initComponent: function() {
		var me = this;
		//view
		me.items = [
	   	       me.getLeftFormView(),
	   	       me.getRightLogoPicView()
		];
		me.callParent(arguments);
	},
	getLeftFormView:function(){
		var form = {
				border: false,
				xtype: 'form',
				layout: 'anchor',
				region:'center',
				bodyPadding: bodyPaddingInt,
				defaults: {
					anchor: '100%',
					allowBlank: false,
					minLength:textfieldMinLength,
					maxLength:textfieldMaxLength
				},     
				defaultType: 'textfield',
				items: [{
					fieldLabel:userNameStr,
					name: 'username',
					readOnly:true,
					fieldCls:'fieldClsReadyOnly',
					emptyText:fieldTextEmptyText+userNameStr
				},{
					fieldLabel: '原始密码',
					inputType: 'password',
					name: 'passwordOrig',
					emptyText:fieldTextEmptyText+userPassStr
				},{
					fieldLabel: userPassStr,
					inputType: 'password',
					id:'passwordId',
					name: 'password',
					emptyText:fieldTextEmptyText+userPassStr
				},{
					fieldLabel: userPasswordConfirmStr,
					inputType: 'password',
					name: 'password2',
					vtype: 'passwordEqual',
					emptyText:fieldTextEmptyText+userPassStr
				}],
				// Reset and Submit buttons
				buttons: [{
					text: submitStr,
					formBind: true, //only enabled once the form is valid
					disabled: true,
					handler: this.onSubmit
				},{
					text: '取消',
					handler: this.onCancel
				}],
				listeners:{
					afterrender:this.onAfterRender
				}
			};
		return form;
	},
	getRightLogoPicView:function (){
		var pic = {
			xtype:'image',
			region:'west',
			width:'52%',
			src:userPasswordBackConfirmSrc
		};
		return pic;
	},
	onSubmit:function(){
		var form = this.up('form').getForm();
		var windowTmp = this.up('window');
		if (form.isValid()) {
			form.submit({
				url:userPasswordConfirmActionUrl,
				waitMsg : waitMsgStr,
				success: function(form, action) {
					Ext.Msg.alert(messageBoxTitle, action.result.infoSucess);
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
	onBackToLoginAction:function(){
		this.up('window').close();
		//打开登录窗口
		loginWindowObject.show();
	},
	onAfterRender:function(comp){
		var form = comp.ownerCt.down('form').getForm();
		form.setValues({
			username:userNameVar
		});
	},
	onCancel:function() {
		this.up('window').close();
	}
	
});