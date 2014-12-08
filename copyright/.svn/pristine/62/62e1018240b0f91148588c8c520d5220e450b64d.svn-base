/*
	任课教师新增的信息维护
*/
Ext.define('MyDesktop.admin.TeacherManagerListCreateWindow', {
	extend : 'Ext.window.Window',
	title :TeacherManagerListCreateWindowTitle,
	iconCls:'user',
	width:TeacherManagerListEditWindowWidth,
	height:TeacherManagerListEditWindowHeight,
	layout : 'fit',
	modal:true,
	store:null,
	initComponent: function() {
		var me = this;
		me.items = [
		       me.getForm()
		];
		me.callParent(arguments);
	},
	getForm:function(){
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
					hidden:true,
					name : 'courseId',
					value: userCourseVar
			}],
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
		var gridStore = windowVar.store;
		if (form.isValid()) {
			form.submit({
				//检查用户名是否可用
				url:userNameCheckActionUrl,
				waitMsg : waitMsgStr,
				success: function(form, action) {
					var model = Ext.create('MyDesktop.model.TeacherModel', form.getValues());
					gridStore.insert(0,model);
					gridStore.load({
						params:{
							courseId:userCourseVar
						}
					});
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
	onCancel: function(){
		this.up('window').close();
	}
});