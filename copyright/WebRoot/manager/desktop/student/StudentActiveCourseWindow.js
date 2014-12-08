Ext.define('MyDesktop.student.StudentActiveCourseWindow', {
    extend: 'Ext.window.Window',
    requires: [
        'Ext.form.Panel',
    ],
    id:'student-active-course-win',
	modal:true,
	resizable:false,
	title: studentActiveCourseWindowTitleStr,
    iconCls:'user',
	width:studentActiveCourseWindowWidth,
    height:studentActiveCourseWindowHeight,
	animCollapse:false,
    constrainHeader:true,
    closable:false, 
	layout: 'fit',
	items:[{
		xtype:'container',
		layout:'fit',
		items:[{
			border: false,
			frame:false,
			xtype: 'form',
			// Reset and Submit buttons
			buttons: [{
				text: submitStr,
				formBind: true, //only enabled once the form is valid
				disabled: true,
				handler: function() {
					var form = this.up('form').getForm();
					var win = this.up('window');
					
					if (form.isValid()) {
						form.submit({
							url:activeStudentActionUrl,
							waitMsg : waitMsgStr,
							success: function(form, action) {
								alert(action.result.info);
								win.close();
								Ext.create("MyDesktop.user.UserPasswordBackInfoWindow").show();
							},
							failure: function(form, action) {
								Ext.Msg.alert(messageBoxTitle, action.result.info);
							}
						});
					}
				}
			},{
				text: backToLoginWinStr,
				handler: function() {
					this.up('window').close();
					//打开登录窗口
					loginTagVar = null;
					studentTagVar = null;
					userIdentityVar = null;
					myDesktopApp = new MyDesktop.App();
				//	new MyDesktop.user.UserLoginWindow().show();
				}
			}],
			layout: 'anchor',
			bodyPadding: bodyPaddingInt,
			defaults: {
				anchor: '100%',
				allowBlank: true
			},     
			defaultType: 'textfield',
			items: [{
				fieldLabel:'姓名',
				name: 'studentName',
				fieldCls:'fieldClsReadyOnly',
				readOnly:true
			},{
				fieldLabel: '学号',
				name: 'studentNo',
				fieldCls:'fieldClsReadyOnly',
				readOnly:true
			},{
				fieldLabel: specialtyStr,
				name: 'studentSpecialty',
				fieldCls:'fieldClsReadyOnly',
				readOnly:true
			},{
				fieldLabel:gradeStr,
				name: 'studentGender',
				fieldCls:'fieldClsReadyOnly',
				readOnly:true
			},{
				fieldLabel: classStr,
				fieldCls:'fieldClsReadyOnly',
				readOnly:true,
				name: 'studentClass'
			},{
				hidden:true,
				name: 'courseId',
				value:userCourseVar
		//		allowBlank: false
			},{
				hidden:true,
				name: 'studentId'
			//	allowBlank: false
			},{
				fieldLabel: '激活码',
				name: 'studentActiveCode',
				allowBlank: false
			}]
		}]
	}]
});