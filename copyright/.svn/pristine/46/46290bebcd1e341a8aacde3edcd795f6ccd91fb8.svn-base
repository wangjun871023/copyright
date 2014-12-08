Ext.define("MyDesktop.common.SimpleTreePanel",{
	extend:"Ext.tree.Panel",
	alias : 'widget.simpletreepanel',
    width: 250,
	height: 300,
	useArrows: true,
	store:null,
	itemclick:function (){
	},
    initComponent: function() {
		var me = this;
		me.store = this.store;
		me.listeners = {
			itemclick: this.itemclick
		};
		me.bbar = Ext.create('Ext.ux.statusbar.StatusBar', {
            defaultText: me.defaultStatusText,
            name: 'searchStatusBar',
            items:[
            	{
    	        	xtype: 'button',
    	            cls:'x-btn-icon x-tbar-loading',
    	            tooltip: '刷新',
    	            handler: me.onRefresh,
    	            scope: me
                }
            ]
        });
		me.callParent(arguments);
    },
    onRefresh:function(){
    	var store =this.store;
    	store.load();
    }
});


 
         