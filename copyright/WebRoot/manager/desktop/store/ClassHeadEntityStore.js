Ext.define('MyDesktop.store.ClassHeadEntityStore', {
    extend: 'Ext.data.Store',
    requires: [
       'MyDesktop.model.ClassHeadModel'
    ],
    model: 'MyDesktop.model.ClassHeadModel',
    proxy: {
        type: 'ajax',
        api : {
			read : 'courseHead/findCourseHeads.action',
			update:'courseHead/updateCourseHeadInfo.action'
		},
    	reader : {
			type : 'json',
			root : 'items'
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
    	/*
		beforeload:function(){
			this.proxy.api.read = 'courseHead/findCourseHeads.action?courseId='+teacherIdVar;
		}*/
	}
});