Ext.define('MyDesktop.store.HomeworkStudentStore', {
    extend: 'Ext.data.Store',
    requires: [
        'MyDesktop.model.HomeworkStudentModel'
    ],
    model: 'MyDesktop.model.HomeworkStudentModel',
    proxy: {
        type: 'ajax',
        api : {
			read : 'homeworkStudent/findAllHomeworkStudent.action'
		},
    	reader : {
			type : 'json',
			root : 'items'
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