Ext.define('MyDesktop.store.GenderStore', {
    extend: 'Ext.data.Store',
    requires: [
        'MyDesktop.model.GenderModel'
    ],
    model: 'MyDesktop.model.GenderModel',
    data : [
            {id: '男',genderCode: '0001',genderName:'男'},
            {id: '女',genderCode: '0002',genderName:'女'}
    ]
    /*
    ,proxy: {
        type: 'ajax',
        url: 'course/findAllCourses.action',
    	reader : {
			type : 'json',
			root : 'items'
		}
    },
    autoLoad: true
    */
});