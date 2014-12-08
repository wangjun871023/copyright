Ext.define('MyDesktop.store.HomeworkCheckStore', {
    extend: 'Ext.data.Store',
    requires: [
        'MyDesktop.model.HomeworkCheckModel'
    ],
    model: 'MyDesktop.model.HomeworkCheckModel',
    proxy: {
        type: 'ajax',
        api : {
			read : 'homeworkCheck/findAllHomeworkToCheck.action'
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