/*
	作业添加窗口
*/
Ext.define('MyDesktop.teacher.CreateHomeworkWindow', {
    extend: 'Ext.window.Window',
    requires: [
        'Ext.form.Panel'
    ],
	modal:true,
	title: createHomeworkWindowTitleStr,
	titleAlign:'center',
    iconCls:'user',
	width:createHomeworkWindowWidth,
    height:createHomeworkWindowHeight,
	animCollapse:false,
    constrainHeader:true,
    maximizable:true,
    border:false,
	layout: 'border',
	gridStore:null,
	initComponent: function() {
		var me = this;
		me.items = [
	   	       me.getFormView(),
	   	       me.getGirdView()
		];
		me.buttons = me.getButtons();
		me.callParent(arguments);
	},
	getFormView:function(){
		var form = {
			split: true,  
			title:homeworkInfoStr,
			xtype: 'form',
			region:'west',
			width:'45%',
			layout: 'anchor',
			bodyPadding: bodyPaddingInt,
			defaults: {
				anchor: '100%',
				allowBlank: false
			},     
			defaultType: 'textfield',
			items: [
			    {
					fieldLabel:courseHeadNameStr,
					name: 'classHeadNames',
					fieldCls:'fieldClsReadyOnly',
					readOnly:true
				}, {
					fieldLabel: courseTeacherStr,
					fieldCls:'fieldClsReadyOnly',
					name: 'teacherName',
					maxLength:textfieldMaxLength,
					value:teacherNameVar,
					readOnly:true
				},{
					xtype:'combobox',
					fieldLabel: homeworkNameStr,
					emptyText:comboboxEmptyText+homeworkNameStr,
					queryMode: 'local',
					store: Ext.create('MyDesktop.store.HomeworkTemplateStore'),
					displayField: 'homeworkName',
					valueField: 'id',
					name: 'homeworkName',
					listeners: {
						select: this.onHideComp
					}
				},{
					xtype: 'datefield',
					format: 'Y年m月d日',
					id: 'start',
					editable: false,
					maxLength:textfieldMaxLength,
					fieldLabel: startDateStr,
					emptyText:comboboxEmptyText+startDateStr,
					name: 'startDate',
					listeners: {
						change: function(field, newValue) {
							Ext.getCmp('end').setMinValue(newValue);					
						}
					}
				}, {
					id: 'end',
					xtype: 'datefield',
					format: 'Y年m月d日',
					editable: false,
					maxLength:textfieldMaxLength,
					fieldLabel: endDateStr,
					emptyText:comboboxEmptyText+endDateStr,
					name: 'endDate',
					listeners: {
						change: function(field, newValue) {
							Ext.getCmp('start').setMaxValue(newValue);					
						}
					}
				},{
					xtype: 'filefield',
		            emptyText: accessoryEmptyStr,
		            fieldLabel: accessoryStr,
		            name: 'uploadFile',
		            allowBlank:true,
		            buttonText: '',
		            buttonConfig: {
		                cls: 'uploadFile'
		            }
			    },{
			    	xtype:'container',
			    	anchor:'100% -200',
			    	layout:'fit',
			    	items:[
						{
						    xtype: 'htmleditor',
						    name:'homeworkContent',
						    labelAlign: 'top',
						    allowBlank:true,
						    fieldLabel: homeworkContentStr
						}
			    	]
			    },{
			    	hidden:true,
			    	name:'studentIds',
			    	allowBlank:true,
			    	id:'studentIds'
			    },{
			    	hidden:true,
			    	name:'classHeadIds',
					listeners:{
						change:this.onBeforeRender
					}
			    }
			]
		};
		return form;
	},
	getGirdView:function(){
		var grid = {
			border: false,
			region:'center',
			title:studentInfoStr,
			xtype: 'livesearchgridpanel',
			border:true,
			selModel:Ext.create('Ext.selection.CheckboxModel'),
			store: Ext.create('MyDesktop.store.StudentStore'),
			columns: [{
				xtype: 'rownumberer',
				text:sequenceStr,
				width:30
			},{
				text: studentNumberStr,
				flex: 1,
				sortable: true,
				dataIndex: 'studentNo'
			},{
				text: nameStr,
				flex: 1,
				sortable: true,
				dataIndex: 'studentName'
			}, {
				text: genderStr,
				flex: 1,
				sortable: true,
				dataIndex: 'studentGender'
			},  {
				text: specialtyStr,
				flex: 1,
				sortable: true,
				dataIndex: 'studentSpecialty'
			},{
				text: classStr,
				flex: 1,
				sortable: true,
				dataIndex: 'studentClass'
			},{
				text: gradeStr,
				flex: 1,
				sortable: true,
				dataIndex: 'studentGrade'
			}],
			listeners: {
				selectionchange:this.onSelectionchange
			}
		};
		return grid;
	},
	getButtons:function (){
		var buttons =  [{
			text: submitStr,
			handler:  this.onSubmit
		},{
			text: cancelStr,
			handler: this.onCancel
		}];
		return buttons;
	},
	onShowComp:function(){
		var filefield  =this.up("window").down("filefield");
		var htmleditor  =this.up("window").down("htmleditor");
		filefield.show();
		htmleditor.show();
	},
	onHideComp:function(){
		var filefield  =this.up("window").down("filefield");
		var htmleditor  =this.up("window").down("htmleditor");
		filefield.hide();
		htmleditor.hide();
	},
	onBeforeRender:function(){
		var fieldClassHead  =this.up("window").down("textfield[name=classHeadIds]");
		var grid = this.up("window").down("livesearchgridpanel");
		var store = grid.getStore();
		store.load({
			params:{
				limit:"",
				classHeadIds:fieldClassHead.getValue()
			},
			callback: function(records, operation, success) {
				grid.getSelectionModel().selectAll(); 
			}
		});
	},
	onSubmit:function(){
		var form =this.up("window").down("form").getForm();
		var windowVar = this.up('window');
		var studentIdsField =this.up("window").down("textfield[name=studentIds]");
		var fieldClassHead  =this.up("window").down("textfield[name=classHeadIds]");
		if (form.isValid()) {
			if (studentIdsField.getValue()==""){
				Ext.Msg.alert(messageBoxTitle, chooseStudentStr);
				return false;
			}
			form.submit({
				url:createHomeworkUrl,
				waitMsg : waitMsgStr,
				success: function(form, action) {
					windowVar.gridStore.load({
						params:{
							classHeadIds:fieldClassHead.getValue()	
						}
					});
					windowVar.close();
				},
				failure: function(form, action) {
					if (action.result!=null){
						Ext.Msg.alert(messageBoxTitle, action.result.info);
					}else{
						Ext.Msg.alert(messageBoxTitle, messageBoxError);
					}
				}
			});
		}
	},
	onCancel:function() {
		this.up('window').close();
	},
	onSelectionchange:function(model,selectedModel){
		var ids = "";
		Ext.Array.each(selectedModel, function(rec) {
		    ids += '\''+rec.get('id')+'\',';
		});
		var studentIdsField =this.up("window").down("textfield[name=studentIds]");
		studentIdsField.setValue(ids);
	}
});