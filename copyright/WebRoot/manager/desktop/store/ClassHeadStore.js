Ext.define('MyDesktop.store.ClassHeadStore', {
    extend: 'Ext.data.TreeStore',
    requires: [
       'MyDesktop.model.ClassHeadModel'
    ],
    model: 'MyDesktop.model.ClassHeadModel',
    root : {
       text : '课头信息',
       id:'0',
       expanded : true
    },
    clearOnLoad:true,
    proxy: {
        type: 'ajax',
        api : {
			destroy : 'courseHead/deleteCourseHeads.action',
			update : 'courseHead/updateCourseHeads.action',
			read : 'courseHead/findCourseHeadsTree.action'
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
			this.proxy.api.read = 'courseHead/findCourseHeadsTree.action?teacherId='+teacherIdVar;
		}
	}
});