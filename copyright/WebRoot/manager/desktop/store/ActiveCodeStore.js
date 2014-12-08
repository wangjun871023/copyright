Ext.define('MyDesktop.store.ActiveCodeStore', {
    extend: 'Ext.data.Store',
    requires: [
        'MyDesktop.model.ActiveCodeModel'
    ],
    model: 'MyDesktop.model.ActiveCodeModel',
    autoLoad:true,
    pageSize: itemsPerPageVar,
    proxy: {
        type: 'ajax',
        api : {
        	read : 'activeCode/findAllActiveCodes.action'
		},
    	reader : {
			type : 'json',
			root : 'items',
			totalProperty:'totalRows'
		},
		writer : {
			type : 'json'
		},
		listeners:{
			exception:function(){
				Ext.Msg.alert(messageBoxTitle, messageBoxError);
			}
		}
    },
    listeners:{
		beforeload:function(){
			this.proxy.api.read = 'activeCode/findAllActiveCodes.action?courseId='+userCourseVar;
		}
	}
});