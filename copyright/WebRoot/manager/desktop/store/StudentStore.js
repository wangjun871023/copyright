Ext.define('MyDesktop.store.StudentStore', {
    extend: 'Ext.data.Store',
    requires: [
        'MyDesktop.model.StudentModel'
    ],
    model: 'MyDesktop.model.StudentModel',
    pageSize: itemsPerPageVar,
    proxy: {
        type: 'ajax',
        api : {
			create  : 'student/createStudents.action',
			destroy : 'student/deleteStudents.action',
			update : 'student/updateStudents.action',
			read : 'student/findAllStudents.action'
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