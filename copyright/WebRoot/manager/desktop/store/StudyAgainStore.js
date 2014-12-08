Ext.define('MyDesktop.store.StudyAgainStore', {
    extend: 'Ext.data.Store',
    requires: [
        'MyDesktop.model.StudyAgainModel'
    ],
    model: 'MyDesktop.model.StudyAgainModel',
    data : [
            {id: '是',name:'是'},
            {id: '否',name:'否'}
    ]
});