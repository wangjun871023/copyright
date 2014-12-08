/*
	作业信息窗口
*/
Ext.define('MyDesktop.admin.HomeworkTemplateInfoWindow', {
    extend: 'Ext.window.Window',
    requires: [
        'Ext.form.Panel'
    ],
	modal:true,
	title: homeworkInfoWindowTitleStr,
	titleAlign:'center',
    iconCls:'user',
    width:createHomeworkWindowWidth,
    height:createHomeworkWindowHeight,
	animCollapse:false,
    constrainHeader:true,
    maximizable:true,
    border:false,
	layout: 'fit',
	initComponent: function() {
		var me = this;
		me.items = [
	   	       me.getFormView()
		];
		me.callParent(arguments);
	},
	getFormView:function(){
		var form = {
			split: true,  
			title:homeworkInfoStr,
			xtype: 'form',
			region:'west',
			width:'45%',
			layout: 'anchor',
			bodyPadding: bodyPaddingInt,
			defaults: {
				anchor: '100%',
				fieldCls:'fieldClsReadyOnly',
				readOnly:true,
				allowBlank: false
			},     
			defaultType: 'textfield',
			items: [
			   {
					fieldLabel:homeworkNameStr,
					name: 'homeworkName',
					emptyText:fieldTextEmptyText+homeworkNameStr
				},{
					xtype: 'displayfield',
		            fieldLabel: accessoryStr,
		            name:'homeworkFileName'
			    },{
			    	xtype:'container',
			    	anchor:'100% -100',
			    	layout:'fit',
			    	items:[
						{
						    xtype: 'htmleditor',
						    name:'homeworkContent',
						    labelAlign: 'top',
						    readOnly:true,
						    fieldLabel: homeworkContentStr
						}
			    	]
			    }
			]
		};
		return form;
	}
});