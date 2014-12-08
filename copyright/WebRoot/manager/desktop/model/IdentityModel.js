Ext.define('MyDesktop.model.IdentityModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id',  type: 'string'},
        {name: 'identityCode',  type: 'string'},
        {name: 'identityName',  type: 'string'}
    ]
});