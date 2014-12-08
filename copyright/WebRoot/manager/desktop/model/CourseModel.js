Ext.define('MyDesktop.model.CourseModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id',  type: 'string'},
        {name: 'courseCode',  type: 'string'},
        {name: 'courseName',  type: 'string'}
    ]
});