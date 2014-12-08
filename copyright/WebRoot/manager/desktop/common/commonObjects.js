//窗口对象
var loginWindowObject = null;
var userPasswordBackWindowObject = null;
var myDesktopApp = null;

//登录用户对象
//最后修改
var userNameVar = "wangjun10";
//var userCourseVar = null;
var userCourseVar = '0001';

var userIdentityVar = null;
//用户登录
var loginTagVar = null;
//var loginTagVar = true;
//学生激活
var studentTagVar = null;


var teacherIdVar = '5628c3f7-006b-4050-8aa1-00b6b1fcf628';
var teacherNameVar = '王军';

var studentIdVar = '003ea7be-6a8a-4a13-b3ca-7b790666c3c4';
var studentNameVar = '吴伟';

//登录用户信息
var currentTeacherRecord = null;
var currentStudentRecord = null;
//分页的条数
var itemsPerPageVar = 20;

//系统常量
//身份
var identityStudentConstant = '0001';
var identityTeacherConstant = '0002';
var identityAdminConstant = '0003';
//作业状态
var homeworkUnDoConstant = '0001';
var homeworkDoConstant = '0002';
var homeworkReadConstant = '0003';
//有效性
var validConstant = '0001';
var invalidConstant = '0002';
//激活
var activeConstant = '0001';
var inactiveConstant = '0002';


//桌面开始 用户名
var appUserNameStr = "管理员";


//密码一致的检验
Ext.apply(Ext.form.field.VTypes, {
    passwordEqual:  function(v,obj) {
       return  Ext.getCmp("passwordId").getValue()==v;
       return false;
    },
    passwordEqualText: '密码不一致'
});

//有效无效
function changeValidCode(value) {
	if (value == validConstant) {
		return "<span style='color:green'>有效</span>";
	}else if(value == invalidConstant){
		return "<span style='color:red'>无效</span>";
	} else {
		return '';
	}
}

function changeActiveCode(value) {
	if (value == validConstant) {
		return "<span style='color:green'>已激活</span>";
	}else if(value == invalidConstant){
		return "<span style='color:red'>未激活</span>";
	} else {
		return '';
	}
}

//重写树刷新
Ext.override(Ext.data.TreeStore, {
    load : function(options) {
        options = options || {};
        options.params = options.params || {};
        var me = this, node = options.node || me.tree.getRootNode(), root;
        if (!node) {
            node = me.setRootNode( {
                expanded : true
            });
        }
        if (me.clearOnLoad) {
            node.removeAll(false);
        }
        Ext.applyIf(options, {
            node : node
        });
        options.params[me.nodeParam] = node ? node.getId() : 'root';
        if (node) {
            node.set('loading', true);
        }
        return me.callParent( [ options ]);
    }
});



