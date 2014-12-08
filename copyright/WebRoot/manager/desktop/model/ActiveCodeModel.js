Ext.define('MyDesktop.model.ActiveCodeModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id',  type: 'string'},
        {name: 'activeCode',  type: 'string'},
        {name: 'courseName',  type: 'string'},
        {name: 'studentNo',  type: 'string'},
        {name: 'studentName',  type: 'string'},
        {name: 'studentActiveCode',  type: 'string'},
        {name: 'studentActiveTime',  type: 'string'},
        {name: 'activeStatus',  type: 'string'},
        {name: 'createTime',  type: 'string'},
        {name: 'updateTime',  type: 'string'},
        {name: 'createUserId',  type: 'string'},
        {name: 'updateUserId',  type: 'string'}
    ]
});