package com.copyright.i18n;

import java.io.File;

public class Constants {
	/**
	 * Session中的验证码
	 */
	public static final String SECURITY_CODE = "SESSION_SECURITY_CODE";
	/**
	 * Session中的User对象
	 */
	public static final String LOGIN_USER_ENTITY = "LOGIN_USER_ENTITY";
	/**
	 * 用户登录身份 学生：0001 教师：0002 管理员：0003
	 */
	public static final String IDENTITY_STUDENT = "0001";
	public static final String IDENTITY_TEACHER = "0002";
	public static final String IDENTITY_ADMIN = "0003";
	/**
	 * 作业当前状态 未做：0001 已做：0002 已审批：0003
	 */
	public static final String HOMEWORK_STATUS_UNDO = "0001";
	public static final String HOMEWORK_STATUS_DO = "0002";
	public static final String HOMEWORK_STATUS_READ = "0003";

	/**
	 * 上传文件路径
	 */
	public static final String UPLOAD_PATH ="/manager/desktop/uploadFiles";

	/**
	 * 上传文件类型
	 */
	public static final String FILE_ALL_TYPE = "doc,docx,rar,zip,txt";
	public static final String FILE_EXCEL_TYPE = "xls,xlsx";
	public static final String FILE_UPLOAD_SIZE = "2M";

	/**
	 * 树根节点id
	 */
	public static final String TREE_ROOT_ID = "0";
	
	/**
	 * 有效性id
	 */
	public static final String VALID_CONSTANT = "0001";
	public static final String INVALID_CONSTANT = "0002";
	
	/**
	 * 有效性id
	 */
	public static final String ACTIVE_CONSTANT = "0001";
	public static final String INACTIVE_CONSTANT = "0002";
	
	
	
}
