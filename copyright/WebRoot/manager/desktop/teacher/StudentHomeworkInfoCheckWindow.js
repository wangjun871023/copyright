Ext.define('MyDesktop.teacher.StudentHomeworkInfoCheckWindow', {
    extend: 'Ext.window.Window',
    requires: [
        'Ext.form.Panel'
    ],
	modal:true,
	title: homeworkCheckInfoWindowStr,
	titleAlign:'center',
    iconCls:'user',
	width:homeworkCheckInfoWindowWidth,
    height:homeworkCheckInfoWindowHeight,
	animCollapse:false,
    constrainHeader:true,
    maximizable:true,
    border:false,
    layout: 'fit',
    gridStore:null,
	initComponent: function() {
		var me = this;
		me.items = [
	   	      me.getFormView()
		];
		me.buttons = me.getButtons();
		me.callParent(arguments);
	},
	getFormView:function(){
		var form = {
			xtype: 'form',
			layout: 'anchor',
			bodyPadding: bodyPaddingInt,
			defaults: {
				anchor: '100%'
			},     
			defaultType: 'textfield',
			items: [
			    {
					fieldLabel:courseHeadNameStr,
					name: 'classHeadName',
					maxLength:textfieldMaxLength,
					emptyText:fieldTextEmptyText+userNameStr,
					fieldCls:'fieldClsReadyOnly',
					readOnly:true
				}, {
					fieldLabel: courseTeacherStr,
					fieldCls:'fieldClsReadyOnly',
					name: 'teacherName',
					maxLength:textfieldMaxLength,
					readOnly:true
				},{
					fieldLabel: studentNameStr,
					fieldCls:'fieldClsReadyOnly',
					name: 'studentName',
					maxLength:textfieldMaxLength,
					readOnly:true
				},{
					fieldLabel: homeworkNameStr,
					readOnly:true,
					fieldCls:'fieldClsReadyOnly',
					name: 'homeworkName',
				    emptyText:comboboxEmptyText
				},{
					xtype: 'displayfield',
		            fieldLabel: accessoryStr,
		            name:'studentHomeworkFileName'
			    },{
			    	xtype:'container',
			    	anchor:'100% -200',
			    	layout:'fit',
			    	items:[
						{
						    xtype: 'htmleditor',
						    name:'studentHomeworkContent',
						    readOnly:true,
						    labelAlign: 'top',
						    allowBlank:true,
						    fieldLabel: homeworkContentStr
						}
			    	]
			    },{
					fieldLabel:scoreStr,
					xtype: 'numberfield',
			        maxValue: 100,
			        minValue: 0,
					name: 'studentScore',
					margin:'10 0 0 0',
					anchor:'20%',
					allowBlank: true,
					maxLength:textfieldMaxLength,
					emptyText:scoreStr
				},{
					hidden:true,
					name:'studentHomeworkId'
				},{
					hidden:true,
					name:'homeworkId'
				}
			]
		};
		return form;
	},
	getButtons:function (){
		var buttons =  [{
			text: submitStr,
			handler:  this.onSubmit
		},{
			text: cancelStr,
			handler: this.onCancel
		}];
		return buttons;
	},
	onSubmit:function(){
		var form =this.up('window').down('form').getForm();
		var windowVar = this.up('window');
		var homeworkId = this.up('window').down('textfield[name=homeworkId]');
		var gridStore = windowVar.gridStore;
		if (form.isValid()) {
			form.submit({
				url:updateHomeworkStudentScoreUrl,
				waitMsg : waitMsgStr,
				success: function(form, action) {
					gridStore.load({
						params:{
			    			homeworkId:homeworkId.getValue()
			    		}
					});
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
	onCancel:function() {
		this.up('window').close();
	}
});