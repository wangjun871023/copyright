package com.copyright.common.core;

import com.copyright.common.base.BaseForm;
 
public interface EntityDao<T> {
	/**
	 * 保存信息 
	 * @param object 
	 * @return int
	 */
	public abstract BaseForm create(T object) throws Exception;  
	/**
	 * 删除信息
	 * @param ids
	 * @param object
	 * @return
	 */
	public abstract BaseForm delete(Object ids,T object) throws Exception;	
	/**
	 * 更改信息
	 * @param object
	 * @throws Exception 
	 */ 
	public abstract BaseForm update(T object)throws  Exception;    
	/**
	 * 根据人员id获取人员 
	 * @param id  String
	 * @return Example
	 * @throws Exception 
	 */
  public abstract T getById(Object id)throws  Exception;   
	/**
	 * 得到分页数据
	 * @param form
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
   public Page listPage(T form,int pageNo,int pageSize)throws Exception;
}
