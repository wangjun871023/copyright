Ext.define("MyDesktop.common.SearchGridPanel",{
	extend:"Ext.grid.Panel",
	alias : 'widget.searchgridpanel',
	requires: [
		'Ext.selection.CheckboxModel'
    ],
	loadMask: true,
	toolbarItems:null,
	extraParams:null,
	
    initComponent: function() {
		var me = this;

		me.dockedItems= [
		{
			xtype: 'pagingtoolbar',
			store: me.store,   // same store GridPanel is using
			dock: 'bottom',
			displayInfo: true
		}];
		
		me.store.on('beforeload', function (store, options) {
	        Ext.apply(me.store.proxy.extraParams, this.extraParams);
	    });
		
		me.callParent(arguments);
    }
});
