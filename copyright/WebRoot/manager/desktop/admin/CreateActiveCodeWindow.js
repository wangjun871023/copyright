/*
	创建激活码窗口
*/
Ext.define('MyDesktop.admin.CreateActiveCodeWindow', {
    extend: 'Ext.window.Window',
    requires: [
        'Ext.form.Panel'
    ],
	modal:true,
	title: createActiveCodeWindowTitleStr,
	titleAlign:'center',
    iconCls:'user',
	width:createActiveCodeWindowWidth,
    height:createActiveCodeWindowHeight,
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
		            xtype: 'numberfield',
		            maxValue: activeCodeCountMax,
		            minValue: 1,
					fieldLabel: activeCodeCountStr,
					name: 'activeCodeCount',
				    emptyText:fieldTextEmptyText+activeCodeCountStr
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
			window.location.href = createActiveCodesUrl+'?courseId='+userCourseVar+'&activeCodeCount='+form.getValues().activeCodeCount;
			windowVar.close();
		}
	},
	onCancel:function() {
		this.up('window').close();
	}
});