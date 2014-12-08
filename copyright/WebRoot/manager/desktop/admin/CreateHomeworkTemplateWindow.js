/*
	作业添加窗口
*/
Ext.define('MyDesktop.admin.CreateHomeworkTemplateWindow', {
    extend: 'Ext.window.Window',
    requires: [
        'Ext.form.Panel'
    ],
	modal:true,
	title: createHomeworkWindowTitleStr,
	titleAlign:'center',
    iconCls:'user',
	width:createHomeworkWindowWidth,
    height:createHomeworkWindowHeight,
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
			split: true,  
			title:homeworkInfoStr,
			xtype: 'form',
			width:'45%',
			layout: 'anchor',
			bodyPadding: bodyPaddingInt,
			defaults: {
				anchor: '100%',
				allowBlank: false
			},     
			defaultType: 'textfield',
			items: [
			    {
					fieldLabel: homeworkNameStr,
					name: 'homeworkName',
				    emptyText:fieldTextEmptyText+homeworkNameStr
				},{
					xtype: 'filefield',
		            emptyText: accessoryEmptyStr,
		            fieldLabel: accessoryStr,
		            name: 'uploadFile',
		            allowBlank:true,
		            buttonText: '',
		            buttonConfig: {
		                cls: 'uploadFile'
		            }
			    },{
			    	xtype:'container',
			    	anchor:'100% -100',
			    	layout:'fit',
			    	items:[
						{
						    xtype: 'htmleditor',
						    name:'homeworkContent',
						    labelAlign: 'top',
						    allowBlank:true,
						    fieldLabel: homeworkContentStr
						}
			    	]
			    },{
				    hidden:true,
				    name:'courseId',
				    value:userCourseVar
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
		var form =this.up("window").down("form").getForm();
		var windowVar = this.up('window');
		if (form.isValid()) {
			form.submit({
				url:createHomeworkTemplateUrl,
				waitMsg : waitMsgStr,
				success: function(form, action) {
					windowVar.gridStore.load({
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