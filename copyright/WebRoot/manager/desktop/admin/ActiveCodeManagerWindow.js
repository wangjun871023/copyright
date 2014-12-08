/*
		激活码管理窗口
*/
Ext.define('MyDesktop.admin.ActiveCodeManagerWindow', {
    extend: 'Ext.ux.desktop.Module',
    requires: [
        'Ext.data.ArrayStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer',
	    'Ext.selection.CheckboxModel',
		'MyDesktop.common.SearchGridPanel',
		'MyDesktop.common.SimpleTreePanel',
		'MyDesktop.teacher.CreateCourseHeadWindow',
		'MyDesktop.teacher.UpdateCourseHeadWindow',
		'MyDesktop.teacher.CreateStudentWindow',
		'MyDesktop.teacher.UpdateStudentWindow',
		'MyDesktop.store.ClassHeadStore',
		'MyDesktop.admin.CreateActiveCodeWindow',
		'MyDesktop.store.StudentStore'
    ],
    id:'active-code-manager-win',
    init : function(){
        this.launcher = {
            text: activeCodeManagerWindowTitleStr,
            iconCls:'icon-grid'
        };
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('active-code-manager-win');
        if(!win){
            win = desktop.createWindow({
                id:'active-code-manager-win',
                title:activeCodeManagerWindowTitleStr,
                width:activeCodeManagerWindowWidth,
                height:activeCodeManagerWindowHeight,
                iconCls: 'icon-grid',
                animCollapse:false,
                constrainHeader:true,
                border:false,
                layout: 'fit',
                items: [{
					xtype:'container',
					layout:'fit',
					items:[{
						border: false,
						region:'center',
						xtype: 'searchgridpanel',
						border:true,
						selModel:Ext.create('Ext.selection.CheckboxModel'),
						store: Ext.create('MyDesktop.store.ActiveCodeStore'),
						columns: [{
							xtype: 'rownumberer',
							text:sequenceStr,
							width:30
						},{
							text:activeCodeStr,
							flex: 1,
							sortable: true,
							dataIndex: 'id'
						},{
							text: courseNameStr,
							flex: 2,
							sortable: true,
							dataIndex: 'courseName'
						},{
							text: studentNumberStr,
							flex: 1,
							sortable: true,
							dataIndex: 'studentNo'
						}, {
							text: studentNameStr,
							flex: 1,
							sortable: true,
							dataIndex: 'studentName'
						},  {
							text: studentActiveCodeStr,
							flex: 1,
							sortable: true,
							dataIndex: 'studentActiveCode'
						},{
							text: studentActiveTimeStr,
							flex: 2,
							sortable: true,
							dataIndex: 'studentActiveTime'
						},{
	                        text: statusStr,
	                        flex: 1,
	                        sortable: true,
	                        renderer :changeActiveCode,
	                        dataIndex: 'activeStatus'
						}],
						listeners: {
				            render:this.onRender
						},	
						tbar:[{
							xtype: 'button',
							tooltip:allValidStr,
							text:allValidStr,
							action:'',
							toggleGroup:'status',
							iconCls:'showAll',
							allowDepress:false,
							handler: this.onSearch
						},{
							xtype: 'button',
							tooltip:validStr,
							text:activeStr,
							action:'showComplete',
							toggleGroup:'status',
							iconCls:'showComplete',
							allowDepress:false,
							handler: this.onSearch
						},{
							xtype: 'button',
							tooltip:invalidStr,
							text:inactiveStr,
							toggleGroup:'status',
							action:'showActive',
							allowDepress:false,
							iconCls:'showActive',
							handler: this.onSearch
						},'-',{
							xtype: 'button',
							text: createActiveCodeStr,
							tooltip:createActiveCodeStr,
							iconCls:'user-edit',
							handler: this.onCreateActiveCode
						},'->',studentNumberStr,{
							 xtype: 'textfield',
							 name: 'searchField',
							 hideLabel: true,
							 listeners: {  
				                    specialkey: function(field,e){    
				                        if (e.getKey()==Ext.EventObject.ENTER){    
				                        	var button = this.up('window').down('button[action=search]');
				                        	button.fireEvent('click');
				                        }  
				                    }  
				             },
							 width: 200
						}, {
							xtype: 'button',
							text: searchStr,
							action:'search',
							tooltip:searchStr,
							iconCls:'search',
							listeners:{
								'click':this.onSearch
							}
						}]
					}]
                }]
            });
        }
        return win;
    },
    onRender:function(){
		var grid = this.up("window").down("searchgridpanel");
		grid.getStore().load();
	},
	onCreateActiveCode:function(){
		var grid = this.up("window").down("searchgridpanel");
		var store = grid.getStore();
		
		new MyDesktop.admin.CreateActiveCodeWindow({
			gridStore: store
    	}).show();		
	},
	onSearch:function(){
		var key = this.up("window").down("textfield[name=searchField]").getValue();
		var showComplete = this.up('window').down('button[action=showComplete]');
    	var showActive = this.up('window').down('button[action=showActive]');
    	var status ='';
    	if (showComplete.pressed ==true){
    		status = validConstant;
    	}
    	if (showActive.pressed ==true){
    		status = invalidConstant;
    	}
		
		//有树参数
		var store = this.up("window").down("searchgridpanel").getStore();
		store.extraParams = {
        		limit:itemsPerPageVar,
        		key:key,
        		activeStatus:status
		};
		store.loadPage(1,{
			params:{
				key:key,
				activeStatus:status
			}
		});
	}
});