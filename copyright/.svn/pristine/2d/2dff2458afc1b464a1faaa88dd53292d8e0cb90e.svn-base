Ext.define('MyDesktop.store.ClassHeadHomeworkStore', {
    extend: 'Ext.data.TreeStore',
    requires: [
       'MyDesktop.model.TreeNodeModel'
    ],
    model: 'MyDesktop.model.TreeNodeModel',
    root : {
       text : '课头作业信息',
       nodeType:'async',
       id:'0',
       expanded:true
    },
    proxy: {
        type: 'ajax',
        url:'homeworkCheck/findCourseHeadHomework.action?teacherId='+teacherIdVar,
    	reader : {
			type : 'json',
			root : 'jsonArray'
		},
		sorters: [{
            property: 'leaf',
            direction: 'ASC'
        }, {
            property: 'text',
            direction: 'ASC'
        }],	
        listeners:{
    		exception:function(){
    			Ext.Msg.alert(messageBoxTitle, messageBoxError);
    		}
    	}
    }
});