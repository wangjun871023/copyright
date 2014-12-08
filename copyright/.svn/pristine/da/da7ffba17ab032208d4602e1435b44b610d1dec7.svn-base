package com.copyright.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copyright.common.base.BaseForm;
import com.copyright.dao.UserDao;
import com.copyright.i18n.Constants;
import com.copyright.i18n.I18n;
import com.copyright.pojo.UserEntity;
import com.copyright.service.UserService;
import com.copyright.util.Md5Util;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	/**
	 * 登录Service
	 */
	@Override
	public BaseForm login(String username, String password, String coureValue,String identityValue,
			String randomCode, HttpSession session) {
		BaseForm baseForm = new BaseForm();
		//验证码是否正确
		/*
		if (!checkRandomCode(randomCode,session)){
			//验证码不正确
			baseForm.setSuccess(false);
			baseForm.setInfo(I18n.SECURITY_CODE_ERROR);
			return baseForm;
		}*/
		//检验身份  学生 教师 管理员
		if (!checkAccount(username,password,coureValue,identityValue)){
			//检验不身份符合
			baseForm.setSuccess(false);
			baseForm.setInfo(I18n.USER_INFO_ERROR);
			return baseForm;
		}
		//用户信息正确，写入session
		writeIntoLoginSession(username,session);
		baseForm.setSuccess(true);
		return baseForm;
	}
	/**
	 * 密码找回Service
	 */
	@Override
	public BaseForm backPassword(String username, String questionOfPassword,
			String answerOfPassword) {
		BaseForm baseForm = new BaseForm();
		//核对用户信息
		if (checkUserPassword(username,questionOfPassword,answerOfPassword)){
			//信息正确
			baseForm.setSuccess(true);
		}
		return baseForm;
	}
	/**
	 * 修改密码Service
	 */
	@Override
	public BaseForm modifyPassword(String username, String password) {
		BaseForm baseForm = new BaseForm();
		UserEntity user = userDao.findUserByUserName(username);
		if (user!=null){
			user.setUserPassword(Md5Util.md5(password));
			userDao.save(user);
		}
		baseForm.setSuccess(true);
		baseForm.setInfoSucess(I18n.MODIFY_PASSWORD_SUCCESS);
		return baseForm;
	}
	
	/**
	 * 用户名是否可用检查Service
	 * @return
	 */
	@Override
	public BaseForm userNameCheck(String username) {
		BaseForm baseForm = new BaseForm();
		if (checkUserName(username)){
			//用户名不可用
			baseForm.setSuccess(false);
			baseForm.setInfo(I18n.USERNAME_EXSIT);
			return baseForm;
		}
		baseForm.setSuccess(true);
		return baseForm;
	}
	
	
	/**
	 * 密码重置Service
	 */
	@Override
	public BaseForm passwordReset(String username) {
		BaseForm baseForm = new BaseForm();
		UserEntity user  = userDao.findUserByUserName(username);
		userDao.updateResetPassword(user);
		baseForm.setSuccess(true);
		return baseForm;
	}
	
	
	
	
	
	
	
	/**
	 * 校验验证码是否正确
	 */
	private boolean checkRandomCode(String randomCode, HttpSession session) {
		String sessionRandomCode = (String) session.getAttribute(Constants.SECURITY_CODE);
		if (randomCode.equals(sessionRandomCode)){
			return true;
		}
		return false;
	}
	
	/**
	 * 检验身份（学生 教师 管理员），用户名与密码是否正确
	 */
	private boolean checkAccount(String username, String password,
			String coureValue, String identityValue) {
		UserEntity user = userDao.findUserBy4Info(username,Md5Util.md5(password),coureValue,identityValue);
		if (user!=null){
			return true;
		}
		return false;
	}
	
	/**
	 * 写入登录UserSession
	 */
	private void writeIntoLoginSession(String username, HttpSession session) {
		UserEntity user = userDao.findUserByUserName(username);
		if (user!=null){
			session.setAttribute(Constants.LOGIN_USER_ENTITY, user);
		}
	}
	
	/**
	 * 核对用户密码返回信息
	 */
	private boolean checkUserPassword(String username, String questionOfPassword, String answerOfPassword) {
		List<UserEntity> users = userDao.findUsersBy3Info(username,questionOfPassword,answerOfPassword);
		if (users!=null && users.size()>0){
			return true; 
		}
		return false;
	}
	

	/**
	 * 核对用户名是否可用
	 */
	private boolean checkUserName(String username) {
		UserEntity user = userDao.findUserByUserName(username);
		if (user!=null){
			return true;
		}
		return false;
	}
	/**
	 * 密码信息
	 */
	@Override
	public BaseForm passwordBackInfo(String username,
			String questionOfPassword, String answerOfPassword) {
		BaseForm baseForm = new BaseForm();
		userDao.createPassWordBackInfo(username,questionOfPassword,answerOfPassword);
		baseForm.setSuccess(true);
		return baseForm;
	}
	
	/**
	 * checkPassword
	 */
	@Override
	public BaseForm checkPassword(String username, String password) {
		BaseForm baseForm = new BaseForm();
		List list = userDao.findUserByUsernameAndPassword(username,password);
		if (list!=null&&list.size()>0){
			baseForm.setSuccess(true);
		}else{
			baseForm.setSuccess(false);
			baseForm.setInfo("原始密码不正确");
		}
		return baseForm;
	}
	@Override
	public BaseForm passwordReset(String username, String password) {
		BaseForm baseForm = new BaseForm();
		UserEntity user  = userDao.findUserByUserName(username);
		user.setUserPassword(Md5Util.md5(password));
		userDao.updateUser(user);
		baseForm.setSuccess(true);
		return baseForm;
	}

}