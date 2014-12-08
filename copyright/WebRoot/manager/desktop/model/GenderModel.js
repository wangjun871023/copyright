Ext.define('MyDesktop.model.GenderModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id',  type: 'string'},
        {name: 'genderCode',  type: 'string'},
        {name: 'genderName',  type: 'string'}
    ]
});