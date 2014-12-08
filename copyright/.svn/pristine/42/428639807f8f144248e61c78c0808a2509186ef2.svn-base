Ext.define('MyDesktop.store.TeacherClassHeadStore', {
    extend: 'Ext.data.TreeStore',
    requires: [
       'MyDesktop.model.TreeNodeModel'
    ],
    model: 'MyDesktop.model.TreeNodeModel',
    clearOnLoad:true,
    root : {
       text : '课程信息',
       nodeType:'async',
       id:'0',
       expandable:true,
       expanded:true
    },
    proxy: {
        type: 'ajax',
        api : {
			read : 'courseHead/findTeacherCourseHeads.action'
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
    },
    listeners:{
		beforeload:function(){
			this.proxy.api.read = 'courseHead/findTeacherCourseHeads.action?courseId='+userCourseVar;
		}
	}
});