Ext.define('MyDesktop.model.QuestionModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id',  type: 'string'},
        {name: 'questionCode',  type: 'string'},
        {name: 'questionName',  type: 'string'}
    ]
});