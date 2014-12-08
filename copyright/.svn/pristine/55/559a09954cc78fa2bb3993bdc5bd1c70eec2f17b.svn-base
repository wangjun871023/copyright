/*
	学生添加窗口
*/
Ext.define('MyDesktop.teacher.CreateStudentWindow', {
    extend: 'Ext.window.Window',
    requires: [
        'Ext.form.Panel'
    ],
	modal:true,
	title: createStudentWindowTitleStr,
    iconCls:'user',
	width:courseHeadStudentWindowWidth,
    height:courseHeadStudentWindowHeight,
	animCollapse:false,
    constrainHeader:true,
	layout: 'fit',
	gridStore:null,
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
			items: [{
					hidden:true,
					//课头id
					name:'id'
				},
			    {
					fieldLabel:courseHeadNameStr,
					name: 'classHeadName',
					fieldCls:'fieldClsReadyOnly',
					readOnly:true,
					maxLength:textfieldMaxLength,
					emptyText:fieldTextEmptyText+userNameStr
				}, {
					fieldLabel:courseTeacherStr,
					name: 'teacherName',
					fieldCls:'fieldClsReadyOnly',
					readOnly:true,
					value:teacherNameVar,
					maxLength:textfieldMaxLength
				}, {
					fieldLabel: userNameStr,
					name: 'studentCode',
					minLength:textfieldMinLength,
					maxLength:textfieldMaxLength
				}, {
					fieldLabel:studentNumberStr,
					name: 'studentNo',
					maxLength:textfieldMaxLength
				},{
					fieldLabel: nameStr,
					name: 'studentName',
					maxLength:textfieldMaxLength
				},{
					maxLength:textfieldMaxLength,
					xtype:'combobox',
					fieldLabel: genderStr,
					name: 'studentGender',
					store:Ext.create('MyDesktop.store.GenderStore'),
					queryMode: 'local',
				    displayField: 'genderName',
				    valueField: 'id',
				    editable:false,
				    value:'男',
				    emptyText:comboboxEmptyText
				}
				,{
					fieldLabel: specialtyStr,
					name: 'studentSpecialty',
					maxLength:textfieldMaxLength
				},{
					fieldLabel: classStr,
					name: 'studentClass',
					maxLength:textfieldMaxLength
				},{
					fieldLabel:gradeStr,
					name: 'studentGrade',
					maxLength:textfieldMaxLength
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
				    editable:false,
				    emptyText:comboboxEmptyText
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
		var gridStore = this.up("window").gridStore;
		
		if (form.isValid()) {
			form.submit({
				//检查用户名是否可用
				url:userNameCheckActionUrl,
				waitMsg : waitMsgStr,
				success: function(form, action) {
					var values = form.getValues();
					values.classHeadId = form.getValues().id;
					values.id = '';
					var model = Ext.create('MyDesktop.model.StudentModel', values);
					gridStore.insert(0,model);
					windowVar.close();
					gridStore.currentPage = 1;
					gridStore.load(1);
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