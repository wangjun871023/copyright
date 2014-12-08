Ext.define("MyDesktop.common.RandomCodeContainer",{
	extend:"Ext.container.Container",
	alias : 'widget.randomcodecontainer',
	layout:'hbox',
	src:'common/getRandomCode.action',
	initComponent: function() {
		var me = this;
		me.items = [{
			xtype:'textfield',
			minLength:4,
			maxLength:4,
			fieldLabel: randomCodeStr,
			emptyText:fieldTextEmptyText,
			name: 'randomCode',
			flex:0.5,
			allowBlank: false
		},{
			margin:'0 0 0 5',
			//验证码图片	
			xtype:'image',
			width:50,
			src:me.src,
			listeners: {
		        click: {
		        	element: 'el',
		            fn: function(event,image){ 
		            	image.src = me.src+'?'+new Date();
		            }
		        }
		    }
		}];
		me.callParent(arguments);
	}
});




