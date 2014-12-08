Ext.define('MyDesktop.store.QuestionStore', {
    extend: 'Ext.data.Store',
    requires: [
        'MyDesktop.model.QuestionModel'
    ],
    model: 'MyDesktop.model.QuestionModel',
    data : [
            {id: '0001',questionCode: '0001',questionName:'您的一个室友的名字'},
            {id: '0002',questionCode: '0002',questionName:'您的宿舍号码'},
            {id: '0003',questionCode: '0003',questionName:'您的班级名'}
    ]
    /*
    proxy: {
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