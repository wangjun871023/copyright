Ext.define('MyDesktop.store.TeacherStore', {
    extend: 'Ext.data.Store',
    requires: [
        'MyDesktop.model.TeacherModel'
    ],
    autoSync: true,
    model: 'MyDesktop.model.TeacherModel',
    sorters:[{
    	property:'updateTime',
    	direction:'DESC'
    }],
    proxy: {
        type: 'ajax',
    	api : {
			create  : 'teacher/createTeachers.action',
			destroy : 'teacher/deleteTeachers.action',
			update : 'teacher/updateTeachers.action',
			read : 'teacher/findAllTeachers.action'
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