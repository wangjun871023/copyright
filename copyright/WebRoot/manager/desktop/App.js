/*
	应用桌面
*/
Ext.define('MyDesktop.App', {
    extend: 'Ext.ux.desktop.App',
    requires: [
        'Ext.window.MessageBox',
		//desktop控件
        'Ext.ux.desktop.ShortcutModel',
        'MyDesktop.desktopbean.SystemStatus',
        'MyDesktop.desktopbean.VideoWindow',
        'MyDesktop.desktopbean.GridWindow',
        'MyDesktop.desktopbean.TabWindow',
        'MyDesktop.desktopbean.AccordionWindow',
        'MyDesktop.desktopbean.Notepad',
        'MyDesktop.desktopbean.BogusMenuModule',
        'MyDesktop.desktopbean.BogusModule',
        'MyDesktop.desktopbean.Settings',
		//我的窗口
		'MyDesktop.user.UserLoginWindow',
		
		'MyDesktop.admin.TeacherManagerWindow',
		'MyDesktop.admin.StudentManagerWindow',
		'MyDesktop.admin.ClassHeadManagerWindow',
		'MyDesktop.admin.HomeworkTemplateManagerWindow',
		'MyDesktop.admin.ActiveCodeManagerWindow',
		'MyDesktop.admin.CourseManagerWindow',
		'MyDesktop.admin.ResourcesAndMsgManagerWindow',
		
		'MyDesktop.teacher.CourseStudentManagerWindow',
		'MyDesktop.teacher.HomeworkManagerWindow',
		'MyDesktop.teacher.HomeworkCheckManagerWindow',

		'MyDesktop.student.StudentHomeworkManagerWindow',
		'MyDesktop.student.StudentHomeworkScoreManagerWindow',
		'MyDesktop.student.StudentActiveCourseWindow'
    ],
    init: function() {
        // custom logic before getXYZ methods get called...
        this.callParent();
        if (loginTagVar==null){
        	//如果用户没有登录，显示登录窗口
        	this.showUserLoginWindow();
        }
        if (loginTagVar== true && studentTagVar == true){
        	//如果学生用户没有激活，显示激活窗口
        	this.showStudentActiveCourseWindow();
        }
    },
    getModules : function(){
    	//管理员模块
    	if (userIdentityVar==identityAdminConstant){
    		 return [
				new MyDesktop.admin.TeacherManagerWindow(),
				new MyDesktop.admin.ClassHeadManagerWindow(),
				new MyDesktop.admin.StudentManagerWindow(),
				new MyDesktop.admin.HomeworkTemplateManagerWindow(),
				new MyDesktop.admin.ActiveCodeManagerWindow()
    		];
    	}
    	
    	//教师模块
    	if (userIdentityVar==identityTeacherConstant){
    		return [
    		       new MyDesktop.teacher.CourseStudentManagerWindow(),
    		       new MyDesktop.teacher.HomeworkManagerWindow(),
    		       new MyDesktop.teacher.HomeworkCheckManagerWindow()
    		];
    	}
    	
    	//学生模块
    	if (userIdentityVar==identityStudentConstant){
    		return [
    		       new MyDesktop.student.StudentHomeworkManagerWindow(),
    		       new MyDesktop.student.StudentHomeworkScoreManagerWindow()
    		];
    	}
    	
    	//全部模块
        return [
//            new MyDesktop.desktopbean.VideoWindow(),
//            new MyDesktop.desktopbean.SystemStatus(),
//            new MyDesktop.desktopbean.GridWindow(),
//            new MyDesktop.desktopbean.TabWindow(),
//            new MyDesktop.desktopbean.AccordionWindow(),
//            new MyDesktop.desktopbean.Notepad(),
//            new MyDesktop.desktopbean.BogusMenuModule(),
//            new MyDesktop.desktopbean.BogusModule(),
			new MyDesktop.admin.TeacherManagerWindow(),
			new MyDesktop.admin.StudentManagerWindow(),
			new MyDesktop.admin.ClassHeadManagerWindow(),
			new MyDesktop.admin.HomeworkTemplateManagerWindow(),
			new MyDesktop.admin.ActiveCodeManagerWindow(),
	//		new MyDesktop.admin.CourseManagerWindow(),
	//		new MyDesktop.admin.ResourcesAndMsgManagerWindow(),
			
			new MyDesktop.teacher.CourseStudentManagerWindow(),
			new MyDesktop.teacher.HomeworkManagerWindow(),
			new MyDesktop.teacher.HomeworkCheckManagerWindow(),
			
			new MyDesktop.student.StudentHomeworkManagerWindow(),
			new MyDesktop.student.StudentHomeworkScoreManagerWindow()
        ];
    }, 

    getDesktopConfig: function () {
        var me = this, ret = me.callParent();
        //管理员图标
    	if (userIdentityVar==identityAdminConstant){
    		 return Ext.apply(ret, {
    	            contextMenuItems: [
    	                { text: 'Change Settings', handler: me.onSettings, scope: me }
    	            ],
    	            shortcuts: Ext.create('Ext.data.Store', {
    	                model: 'Ext.ux.desktop.ShortcutModel',
    	                data: [
    					    { name: teacherManagerWindowTitleStr, iconCls: 'grid-shortcut', module: 'teacher-manager-win' },
    					    { name: studentManagerWindowTitleStr, iconCls: 'accordion-shortcut', module: 'student-manager-win' },
    					    { name: classHeadManagerWindowTitleStr, iconCls: 'accordion-shortcut', module: 'classhead-manager-win' },
    					    { name: homeworkTemplateManagerWindowTitleStr, iconCls: 'grid-shortcut', module: 'homework-template-manager-win' },
    					    { name: activeCodeManagerWindowTitleStr, iconCls: 'grid-shortcut', module: 'active-code-manager-win' }
    					    
    	                ]
    	            }),
    	            wallpaper: 'wallpapers/Blue-Sencha.jpg',
    	            wallpaperStretch: false
    	      });
    	}
    	
    	//教师图标
    	if (userIdentityVar==identityTeacherConstant){
    		return Ext.apply(ret, {
    			contextMenuItems: [
                   { text: 'Change Settings', handler: me.onSettings, scope: me }
               ],
               shortcuts: Ext.create('Ext.data.Store', {
            	   model: 'Ext.ux.desktop.ShortcutModel',
            	   data: [
            	          { name: courseStudentManagerWindowTitleStr, iconCls: 'accordion-shortcut', module: 'course-student-manager-win' },
            	          { name: homeworkManagerWindowTitleStr, iconCls: 'accordion-shortcut', module: 'homework-manager-win' },
            	          { name: homeworkCheckManagerWindowTitleStr, iconCls: 'grid-shortcut', module: 'homework-check-manager-win' }
            	   ]
               }),
               wallpaper: 'wallpapers/Blue-Sencha.jpg',
               wallpaperStretch: false
    		});
    	}
    	
    	//学生图标
    	if (userIdentityVar==identityStudentConstant){
    		return Ext.apply(ret, {
    			contextMenuItems: [
                   { text: 'Change Settings', handler: me.onSettings, scope: me }
               ],
               shortcuts: Ext.create('Ext.data.Store', {
            	   model: 'Ext.ux.desktop.ShortcutModel',
            	   data: [
						{ name: studentHomeworkManagerWindowTitleStr, iconCls: 'grid-shortcut', module: 'student-homework-manager-win' },
						{ name: studentHomeworScorekManagerWindowTitleStr, iconCls: 'grid-shortcut', module: 'student-homework-score-manager-win' }
            	   ]
               }),
               wallpaper: 'wallpapers/Blue-Sencha.jpg',
               wallpaperStretch: false
    		});
    	}
    	
    	
    	//默认图标
        return Ext.apply(ret, {
            contextMenuItems: [
                { text: wallpaperStr, handler: me.onSettings, scope: me }
            ],
            shortcuts: Ext.create('Ext.data.Store', {
                model: 'Ext.ux.desktop.ShortcutModel',
                data: [
//                    { name: 'Grid Window', iconCls: 'grid-shortcut', module: 'grid-win' },
//                    { name: 'Accordion Window', iconCls: 'accordion-shortcut', module: 'acc-win' },
//                    { name: 'Notepad', iconCls: 'notepad-shortcut', module: 'notepad' },
//                    { name: 'System Status', iconCls: 'cpu-shortcut', module: 'systemstatus'},
//				    { name: teacherManagerWindowTitleStr, iconCls: 'grid-shortcut', module: 'teacher-manager-win' },
//				    { name: studentManagerWindowTitleStr, iconCls: 'accordion-shortcut', module: 'student-manager-win' },
//				    { name: classHeadManagerWindowTitleStr, iconCls: 'accordion-shortcut', module: 'classhead-manager-win' },
//				    { name: homeworkTemplateManagerWindowTitleStr, iconCls: 'grid-shortcut', module: 'homework-template-manager-win' },
//				    { name: activeCodeManagerWindowTitleStr, iconCls: 'grid-shortcut', module: 'active-code-manager-win' },
		//		    { name: courseManagerWindowTitleStr, iconCls: 'grid-shortcut', module: 'course-manager-win' },
		//		    { name: resourcesAndMsgManagerWindowTitleStr, iconCls: 'grid-shortcut', module: 'resources-msg-manager-win' },
				    
//				    { name: courseStudentManagerWindowTitleStr, iconCls: 'accordion-shortcut', module: 'course-student-manager-win' },
//				    { name: homeworkManagerWindowTitleStr, iconCls: 'accordion-shortcut', module: 'homework-manager-win' },
//				    { name: homeworkCheckManagerWindowTitleStr, iconCls: 'grid-shortcut', module: 'homework-check-manager-win' },
//				    { name: studentHomeworkManagerWindowTitleStr, iconCls: 'grid-shortcut', module: 'student-homework-manager-win' },
//				    { name: studentHomeworScorekManagerWindowTitleStr, iconCls: 'grid-shortcut', module: 'student-homework-score-manager-win' }
                ]
            }),
            wallpaper: 'wallpapers/Blue-Sencha.jpg',
            wallpaperStretch: false
        });
    },
    
    // config for the start menu
    getStartConfig : function() {
        var me = this, ret = me.callParent();
        return Ext.apply(ret, {
            title: appUserNameStr,
            iconCls: 'user',
            height: 300,
            toolConfig: {
                width: 100,
                items: [  {
	                	text:passwordBackInfoStr,
	                	iconCls:'logout',
	                	handler: me.onPasswordBackInfo,
	                	scope: me
	                },
	                {
	                	text:modifyPasswordStr,
	                	iconCls:'logout',
	                	handler: me.onModifyPassword,
	                	scope: me
	                },
	                '-',
                    {
                        text:wallpaperStr,
                        iconCls:'settings',
                        handler: me.onSettings,
                        scope: me
                    },
                    {
                        text:loginOutStr,
                        iconCls:'logout',
                        handler: me.onLogout,
                        scope: me
                    }
                ]
            }
        });
    },

    getTaskbarConfig: function () {
        var ret = this.callParent();
        return Ext.apply(ret, {
            quickStart: [
           //     { name: 'Accordion Window', iconCls: 'accordion', module: 'acc-win' },
             //   { name: 'Grid Window', iconCls: 'icon-grid', module: 'grid-win' }
            ],
            trayItems: [
                { xtype: 'trayclock', flex: 1 }
            ]
        });
    },

	showUserLoginWindow:function(){
		loginWindowObject = new MyDesktop.user.UserLoginWindow();
		loginWindowObject.show();
	},
	showStudentActiveCourseWindow:function(){
		var record = currentStudentRecord;
		if(record.get('studentActiveCode')==''){
			var studentActiveCourseWindow = new MyDesktop.student.StudentActiveCourseWindow();
			studentActiveCourseWindow.show();
			var form = studentActiveCourseWindow.down("form");
			record.set('studentId',record.get('id'));
			form.getForm().loadRecord(record);
		}
	},
    onLogout: function () {
        Ext.Msg.confirm(loginOutStr, '您确定关闭系统吗？',function(btn, text){
        	 if (btn == 'yes'){
        		 var browserName=navigator.appName; 
    		    if (browserName=="Netscape") 
    		    { 
    		        self.opener=null;
    		        self.open('','_self');
    		        self.close();
    		    } 
    		    if (browserName=="Microsoft Internet Explorer") 
    		    { 
    		        window.opener = "whocares"; 
    		        window.close(); 
    		    } 
        	 }
        });
    },
    onSettings: function () {
        var dlg = new MyDesktop.desktopbean.Settings({
            desktop: this.desktop
        });
        dlg.show();
    },
    onPasswordBackInfo:function(){
    	Ext.create("MyDesktop.user.UserPasswordBackInfoWindow").show();
    },
    onModifyPassword:function(){
    	Ext.create("MyDesktop.user.ModifyPasswordWindow").show();
    }
});
