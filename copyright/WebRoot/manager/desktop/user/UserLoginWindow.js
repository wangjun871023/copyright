/*
	用户登录窗口
*/
Ext.define('MyDesktop.user.UserLoginWindow', {
    extend: 'Ext.window.Window',
    requires: [
		'MyDesktop.user.UserPasswordBackWindow',
		'MyDesktop.store.CourseStore',
		'MyDesktop.store.IdentityStore',
		'MyDesktop.common.RandomCodeContainer'
    ],
    // 遮罩
	modal:true,
	resizable:false,
    iconCls:'fellowUser',
    title: userLoginWindowTitleStr,
	width:userloginWindowWidth,
    height:userloginWindowHeight,
	constrain : true,
    closable:false, 
	layout: 'border',
	initComponent: function() {
		var me = this;
		//view
		me.items = [
		       //右边loginForm
	   	       me.getRightLoginFormView(),
	   	       //左边Pic
	   	       me.getLeftLogoPicView()
		];
		me.callParent(arguments);
	},
	getRightLoginFormView:function(){
		var form ={
			border: false,
			xtype: 'form',
			region:'center',
			bodyPadding: bodyPaddingInt,
			layout: 'anchor',
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
					fieldLabel: userPassStr,
					inputType: 'password',
					minLength:textfieldMinLength,
					name: 'password',
					emptyText:fieldTextEmptyText+userPassStr
				},{
					xtype:'combobox',
					fieldLabel: courseSelectStr,
					name: 'coureValue',
					store:Ext.create('MyDesktop.store.CourseStore'),
					queryMode: 'local',
				    displayField: 'courseName',
				    value:'0001',
				    valueField: 'id',
				    editable:false,
				    emptyText:comboboxEmptyText
				},{
					xtype:'combobox',
					fieldLabel: identityStr,
					name: 'identityValue',
					store:Ext.create('MyDesktop.store.IdentityStore'),
					queryMode: 'local',
				    displayField: 'identityName',
				    valueField: 'id',
				    value:'0001',
				    editable:false,
				    emptyText:comboboxEmptyText
				},{
					xtype:'label',
					html:instructionsText
				}
				
			//	Ext.create('MyDesktop.common.RandomCodeContainer')
			],
			buttons: [{
					text: submitStr,
					formBind: true,
				    disabled: true,
					handler: this.onLoginAction
				},{
					text: resetStr,
					handler: this.onResetAction
				},{
					text: passwordBackStr,
					handler: this.onPasswordBackAction
			}]
		};
		return form;
	},
	getLeftLogoPicView:function (){
		var pic = {
			xtype:'image',
			region:'west',
			width:'43%',
			src:loginPicSrc
		};
		return pic;
	},
	onLoginAction:function(){
		var form = this.up('form').getForm();
		if (form.isValid()) {
			form.submit({
				url:loginFormActionUrl,
				waitMsg : waitMsgStr,
				success: function(form, action) {
					//登录成功 设置系统变量
					loginTagVar = true;
					userNameVar = form.getValues().username;
					userCourseVar = form.getValues().coureValue;
					userIdentityVar = form.getValues().identityValue;
					
					//读取用户信息
					//教师
					if (userIdentityVar == identityTeacherConstant){
						var store = Ext.create('MyDesktop.store.TeacherStore');
						store.load({
							params:{
								teacherCode : userNameVar,
								courseId:userCourseVar
							},
							callback: function(records, operation, success) {
								currentTeacherRecord = store.getAt(0);
								appUserNameStr = currentTeacherRecord.get("teacherName");
								teacherIdVar = currentTeacherRecord.get("id");
								teacherNameVar = currentTeacherRecord.get("teacherName");
								loginWindowObject.close();
								myDesktopApp = new MyDesktop.App();
								return;
						    }
						});
					}
					//学生
					if (userIdentityVar == identityStudentConstant){
						studentTagVar = true;
						var store = Ext.create('MyDesktop.store.StudentStore');
						store.load({
							params:{
								studentCode:userNameVar,
								courseId:userCourseVar
							},
							callback: function(records, operation, success) {
								currentStudentRecord = store.getAt(0);
								appUserNameStr = currentStudentRecord.get("studentName");
								studentIdVar = currentStudentRecord.get("id");
								studentNameVar = currentStudentRecord.get("studentName");
								loginWindowObject.close();
								myDesktopApp = new MyDesktop.App();
								return;
							}
						});
					}
					//管理员
					if (userIdentityVar == identityAdminConstant){
						loginWindowObject.close();
						myDesktopApp = new MyDesktop.App();
					}
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
		//重置
		this.up('form').getForm().reset();
	},
	onPasswordBackAction:function(){
		//loginWindow隐藏
		loginWindowObject.hide();
		//打开密码找回窗口
		if (userPasswordBackWindowObject==null){
			userPasswordBackWindowObject = new MyDesktop.user.UserPasswordBackWindow();
		}
		userPasswordBackWindowObject.show();
	}
});