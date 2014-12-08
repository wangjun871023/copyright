Ext.define('MyDesktop.store.TeacherTreeStore', {
    extend: 'Ext.data.TreeStore',
    requires: [
       'MyDesktop.model.TreeNodeModel'
    ],
    model: 'MyDesktop.model.TreeNodeModel',
    clearOnLoad:true,
    root : {
       text : '教师信息',
       nodeType:'async',
       id:'0',
       expandable:true,
       expanded:true
    },
    proxy: {
        type: 'ajax',
        api : {
			read : 'teacher/findTeacherTree.action?courseId='+userCourseVar
		},
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