package com.copyright.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * UserEntity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_table")
public class UserEntity extends com.copyright.common.base.BaseForm implements
		java.io.Serializable {

	// Fields

	private String id;
	private String userCode;
	private String userName;
	private String userPassword;
	private String userPasswordQuestion;
	private String userPasswordAnswer;
	private String createTime;
	private String updateTime;
	private String createUserId;
	private String updateUserId;
	private String courseId;
	private String identityId;
	private String classHeadId;
	

	// Constructors

	/** default constructor */
	public UserEntity() {
	}

	/** full constructor */
	public UserEntity(String userCode, String userName,
			String userPassword, String userPasswordQuestion,
			String userPasswordAnswer, String createTime, String updateTime,
			String createUserId, String updateUserId) {
		this.userCode = userCode;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userPasswordQuestion = userPasswordQuestion;
		this.userPasswordAnswer = userPasswordAnswer;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.createUserId = createUserId;
		this.updateUserId = updateUserId;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid2")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 100)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "user_code", length = 100)
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "user_name", length = 100)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_password", length = 100)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "user_password_question", length = 100)
	public String getUserPasswordQuestion() {
		return this.userPasswordQuestion;
	}

	public void setUserPasswordQuestion(String userPasswordQuestion) {
		this.userPasswordQuestion = userPasswordQuestion;
	}

	@Column(name = "user_password_answer", length = 100)
	public String getUserPasswordAnswer() {
		return this.userPasswordAnswer;
	}

	public void setUserPasswordAnswer(String userPasswordAnswer) {
		this.userPasswordAnswer = userPasswordAnswer;
	}

	@Column(name = "create_time", length = 100)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", length = 100)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "create_user_id", length = 100)
	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "update_user_id", length = 100)
	public String getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	
	@Column(name = "course_id", length = 100)
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	@Column(name = "identity_id", length = 100)
	public String getIdentityId() {
		return identityId;
	}
	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}
	@Column(name = "class_head_id", length = 100)
	public String getClassHeadId() {
		return classHeadId;
	}

	public void setClassHeadId(String classHeadId) {
		this.classHeadId = classHeadId;
	}
	
	
}