Ext.define('MyDesktop.model.HomeworkTemplateModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id',  type: 'string'},
        {name: 'homeworkCode',  type: 'string'},
        {name: 'homeworkName',  type: 'string'},
        {name: 'homeworkContent',  type: 'string'},
        {name: 'homeworkStatus',  type: 'string'},
        {name: 'fileId',  type: 'string'},
        {name: 'homeworkFileName',  type: 'string', convert:function(fileName, record){
        	if (fileName!=null){
        		var filePath = "common/downloadFile?fileId="+record.data.fileId;
        		return "<a href = '"+filePath+"'>"+fileName+"</a>";
        	}
        	return "";
        }},
        {name: 'homeworkFilePath',  type: 'string'},
        {name: 'courseId',  type: 'string'},
        {name: 'courseName',  type: 'string'},
        {name: 'createTime',  type: 'string'},
        {name: 'updateTime',  type: 'string'},
        {name: 'createUserId',  type: 'string'},
        {name: 'updateUserId',  type: 'string'}
    ]
});