Ext.define('MyDesktop.store.HomeworkStore', {
    extend: 'Ext.data.Store',
    requires: [
        'MyDesktop.model.HomeworkModel'
    ],
    model: 'MyDesktop.model.HomeworkModel',
    proxy: {
        type: 'ajax',
        api : {
			update : 'homework/updateHomeworks.action',
			read : 'homework/findAllHomeworks.action'
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
    }
});