Ext.define('MyDesktop.store.HomeworkTemplateStore', {
    extend: 'Ext.data.Store',
    requires: [
        'MyDesktop.model.HomeworkTemplateModel'
    ],
    model: 'MyDesktop.model.HomeworkTemplateModel',
    autoLoad:true,
    proxy: {
        type: 'ajax',
        api : {
        	update : 'homeworkTemplate/updateHomeworkTemplates.action',
        	read : 'homeworkTemplate/findAllHomeworkTemplates.action'
		},
    	reader : {
			type : 'json',
			root : 'items',
			totalProperty:'totalRows'
		},
		writer : {
			type : 'json'
		},
		listeners:{
			exception:function(){
				Ext.Msg.alert(messageBoxTitle, messageBoxError);
			}
		}
    },
    listeners:{
		beforeload:function(){
			this.proxy.api.read = 'homeworkTemplate/findAllHomeworkTemplates.action?courseId='+userCourseVar;
		}
	}
});