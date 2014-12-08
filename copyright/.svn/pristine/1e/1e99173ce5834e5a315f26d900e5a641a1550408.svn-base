Ext.define('MyDesktop.model.HomeworkCheckModel', {
    extend: 'Ext.data.Model',
    fields: [
	     {name: 'id',  type: 'string'},
	     {name: 'studentNo',  type: 'string'},
	     {name: 'studentName',  type: 'string'},
	     {name: 'studentGender',  type: 'string'},
	     {name: 'studentSpecialty',  type: 'string'},
	     {name: 'studentClass',  type: 'string'},
	     {name: 'studentGrade',  type: 'string'},
	     {name: 'studentScore',  type: 'string'},
        {name: 'homeworkId',  type: 'string'},
        {name: 'homeworkName',  type: 'string'},
        {name: 'studentHomeworkId',  type: 'string'},
        {name: 'studentHomeworkFileName',  type: 'string', convert:function(fileName, record){
        	if (fileName!=null){
        		var filePath = "common/downloadFile?fileId="+record.data.studentFileId;
            	return "<a href = '"+filePath+"'>"+fileName+"</a>";
        	}
        	return "";
        }},
        {name: 'studentHomeworkFilePath',  type: 'string'},
        {name: 'startDate',  type: 'string'},
        {name: 'endDate',  type: 'string'},
        {name: 'studentId',  type: 'string'},
        {name: 'classHeadName',  type: 'string'},
        {name: 'teacherName',  type: 'string'},
        {name: 'studentHomeworkStatus',  type: 'string',convert:function(status, record){
        	if (status==homeworkDoConstant){
        		return "<span style='color:green'>"+completeStr+"</span>";
        	}
        	if (status==homeworkUnDoConstant){
        		return "<span style='color:red'>"+noCompleteStr+"</span>";
        	}
        	if (status==homeworkReadConstant){
        		return "<span style='color:grey'>"+readCompleteStr+"</span>";
        	}
        	return '';
        }},
        {name: 'studentHomeworkContent',  type: 'string'},
        {name: 'studentFileId',  type: 'string'},
        {name: 'createTime',  type: 'string'},
        {name: 'updateTime',  type: 'string'},
        {name: 'createUserId',  type: 'string'},
        {name: 'updateUserId',  type: 'string'}
    ]
});