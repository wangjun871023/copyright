/**
 * Copyright (c) 2005-2010 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: SimpleHibernateDao.java 1205 2010-09-09 15:12:17Z xiaoxiu $
 */
package com.copyright.common.hibernate;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.copyright.util.BaseFunction;
import com.copyright.util.ReflectionUtils;
  

/**
 * 封装Hibernate原生API的DAO泛型基类.
 * 
 * 可在Service层直接使用, 也可以扩展泛型DAO子类使用, 见两个构造函数的注释.
 * 参考Spring2.5自带的Petlinc例子, 取消了HibernateTemplate, 直接使用Hibernate原生API.
 * 
 * @param <T> DAO操作的对象类型
 * @param <PK> 主键类型
 * 
 * @author xiao
 */
@SuppressWarnings("unchecked") 
public class SimpleHibernateDao<T, PK extends Serializable> {

		protected Logger logger = LoggerFactory.getLogger(getClass());

		protected SessionFactory sessionFactory;

		protected Class<T> entityClass;

		/**
		 * 用于Dao层子类使用的构造函数.
		 * 通过子类的泛型定义取得对象类型Class.
		 * eg.
		 * public class UserDao extends SimpleHibernateDao<User, Long>
		 */
		public SimpleHibernateDao() {
			this.entityClass = ReflectionUtils.getClassGenricType(getClass());
		}

		/**
		 * 用于用于省略Dao层, 在Service层直接使用通用SimpleHibernateDao的构造函数.
		 * 在构造函数中定义对象类型Class.
		 * eg.
		 * SimpleHibernateDao<User, Long> userDao = new SimpleHibernateDao<User, Long>(sessionFactory, User.class);
		 */
		public SimpleHibernateDao(final SessionFactory sessionFactory, final Class<T> entityClass) {
			this.sessionFactory = sessionFactory;
			this.entityClass = entityClass;
		}

		/**
		 * 取得sessionFactory.
		 */
		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}

		/**
		 * 采用@Autowired按类型注入SessionFactory,当有多个SesionFactory的时候Override本函数.
		 */
		@Autowired
		public void setSessionFactory(final SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

		/**
		 * 取得当前Session.
		 */
		public Session getSession() {
			return sessionFactory.getCurrentSession();
		}

		/**
		 * 保存新增或修改的对象.
		 */ 
		private void save1(final T entity) {
			Assert.notNull(entity, "entity不能为空");
			Session session = getSessionFactory().openSession();   
      Transaction tran =null;   
      try {   
      	if(session!=null){
      	  tran = session.beginTransaction(); 
          tran.begin();   
          session.saveOrUpdate(entity);
          tran.commit();  
      	}
      } catch (Exception ex) {   
          tran.rollback();   
      } finally {   
      	if(session!=null){
      	   session.close();  
      	}
      }    
			logger.debug("save entity: {}", entity);
		}

		/**
		 * 保存新增或修改的对象.
		 */
		public void save(final T entity) {
			Assert.notNull(entity, "entity不能为空");
			getSession().saveOrUpdate(entity);
			logger.debug("save entity: {}", entity);
		}
		/**
		 * 删除对象.
		 * 
		 * @param entity 对象必须是session中的对象或含id属性的transient对象.
		 */
		public void delete(final T entity) {
			Assert.notNull(entity, "entity不能为空"); 
			getSession().delete(entity);
			logger.debug("delete entity: {}", entity);
		}
		/**
		 * 删除对象.
		 * 
		 * @param entity 对象必须是session中的对象或含id属性的transient对象.
		 */
		private void delete1(final T entity) {
			Assert.notNull(entity, "entity不能为空"); 
			Session session = getSessionFactory().openSession();   
      Transaction tran =null;   
      try {   
      	if(session!=null){
      	  tran = session.beginTransaction(); 
          tran.begin();   
          session.delete(entity);
          tran.commit();  
      	}
      } catch (Exception ex) {   
          tran.rollback();   
      } finally {   
      	if(session!=null){
      	   session.close();  
      	}
      }     
			logger.debug("delete entity: {}", entity);
		}

		/**
		 * 按id删除对象.
		 */
		public void delete(final PK id) {
			Assert.notNull(id, "id不能为空");
			delete(get(id));
			logger.debug("delete entity {},id is {}", entityClass.getSimpleName(), id);
		}

		/**
		 * 按id获取对象.
		 */
		public T get(final PK id) {
			Assert.notNull(id, "id不能为空");
			return (T) getSession().load(entityClass, id);
		}

		/**
		 *	获取全部对象.
		 */
		public List<T> getAll() {
			return find();
		}

		/**
		 *	获取全部对象,支持排序.
		 */
		public List<T> getAll(String orderBy, boolean isAsc) {
			Criteria c = createCriteria();
			if (isAsc) {
				c.addOrder(Order.asc(orderBy));
			} else {
				c.addOrder(Order.desc(orderBy));
			}
			return c.list();
		}

		/**
		 * 按属性查找对象列表,匹配方式为相等.
		 */
		public List<T> findBy(final String propertyName, final Object value) {
			Assert.hasText(propertyName, "propertyName不能为空");
			Criterion criterion = Restrictions.eq(propertyName, value);
			return find(criterion);
		}

		/**
		 * 按属性查找唯一对象,匹配方式为相等.
		 */
		public T findUniqueBy(final String propertyName,final Object value) {
			Assert.hasText(propertyName,"propertyName不能为空");
			if(value==null){
				return null;
			}
			if(value instanceof String){
				if(BaseFunction.isEmpty((String)value)==true){
					return null;
				}
			}
			else{
				if(value instanceof Long){
					Long temp=(Long)value;
					if(temp.intValue()<=0){
						return null;
					}
				}
				else{ 
					if(value instanceof Integer){
						Integer temp=(Integer)value;
						if(temp.intValue()<=0){
							return null;
						}
					}
				}
			}
			Criterion criterion=Restrictions.eq(propertyName,value);
			return(T) createCriteria(criterion).uniqueResult();
		}


		/**
		 * 按id列表获取对象.
		 */
		public List<T> findByIds(List<PK> ids) {
			return find(Restrictions.in(getIdName(), ids));
		}

		/**
		 * 按HQL查询对象列表.
		 * 
		 * @param values 数量可变的参数,按顺序绑定.
		 */
		public <X> List<X> find(final String hql) {
			return createQuery(hql).list();
		}
		/**
		 * 按HQL查询对象列表.
		 * 
		 * @param values 数量可变的参数,按顺序绑定.
		 */
		public <X> List<X> find(final String hql, final Object... values) {
			return createQuery(hql, values).list();
		}

		/**
		 * 按HQL查询对象列表.
		 * 
		 * @param values 命名参数,按名称绑定.
		 */
		public <X> List<X> find(final String hql, final Map<String, ?> values) {
			return createQuery(hql, values).list();
		}
		/**
		 * 按HQL查询唯一对象.
		 * 
		 * @param values 数量可变的参数,按顺序绑定.
		 */
		public <X> X findUnique(final String hql) {
			return (X) createQuery(hql).uniqueResult();
		}
		/**
		 * 按HQL查询唯一对象.
		 * 
		 * @param values 数量可变的参数,按顺序绑定.
		 */
		public <X> X findUnique(final String hql, final Object... values) {
			return (X) createQuery(hql, values).uniqueResult();
		}

		/**
		 * 按HQL查询唯一对象.
		 * 
		 * @param values 命名参数,按名称绑定.
		 */
		public <X> X findUnique(final String hql, final Map<String, ?> values) {
			return (X) createQuery(hql, values).uniqueResult();
		}

		/**
		 * 执行HQL进行批量修改/删除操作.
		 */
		public int batchExecute(final String hql, final Object... values) {
			return createQuery(hql, values).executeUpdate();
		}

		/**
		 * 执行HQL进行批量修改/删除操作.
		 * @return 更新记录数.
		 */
		public int batchExecute(final String hql, final Map<String, ?> values) {
			return createQuery(hql, values).executeUpdate();
		}

		/**
		 * 根据查询HQL与参数列表创建Query对象.
		 * 
		 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
		 * 
		 */
		public Query createQuery(final String queryString) {
			Assert.hasText(queryString,"queryString不能为空"); 
			Query query=getSession().createQuery(queryString); 
			return query;
		}
		/**
		 * 根据查询HQL与参数列表创建Query对象.
		 * 
		 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
		 * 
		 * @param values 数量可变的参数,按顺序绑定.
		 */
		public Query createQuery(final String queryString,final Object... values) {
			Assert.hasText(queryString,"queryString不能为空"); 
				Query query=getSession().createQuery(queryString);
				if(values!=null&&values.length>0) { 
					if(values[0] instanceof Hashtable){ 
						Hashtable temp=(Hashtable)values[0];
						query=setParamHash(query,temp);
					}
					else{
						if(values[0] instanceof List){ 
							List temp=(List)values[0];
							int size=temp.size();
							for(int i=0; i<size; i++) {
								Object param=temp.get(i); 
								//query.setParameter(i,values[i]); 
							    if(param instanceof String){
					            	String paramValue=(String)param;
					            	 query.setString(i,paramValue);
					            }
					            else{
					            	if(param instanceof Integer ){
					            		Integer paramValue=(Integer)param;
						            	 query.setInteger(i,paramValue.intValue());
					            	}
					            	else{
					            		if(param instanceof Long ){
						            		Long paramValue=(Long)param;
							            	 query.setLong(i,paramValue.longValue());
						            	}
					            		else{
					            			if(param instanceof Double ){
						            			Double paramValue=(Double)param;
								            	 query.setDouble(i,paramValue.doubleValue());
							            	}else{
							            		if(param instanceof Float ){
							            			Float paramValue=(Float)param;
									            	 query.setFloat(i,paramValue.floatValue());
								            	}
							            	}
					            		} 	
					            	} 
					            } 
							  
							}
						}
						else{
							for(int i=0; i<values.length; i++) {
								query.setParameter(i,values[i]); 
							}
						}
						
					}
				} 
		
			return query;
		}


		/**
		 * 根据查询HQL与参数列表创建Query对象.
		 * 
		 * @param values 命名参数,按名称绑定.
		 */
		public Query createQuery(final String queryString, final Map<String, ?> values) {
			Assert.hasText(queryString, "queryString不能为空");
			Query query = getSession().createQuery(queryString);
			if (values != null) {
				query.setProperties(values);
			}
			return query;
		}
		
		/**
		 * 
		 * @param query
		 * @param values
		 * @return
		 */
		private Query setParamHash(Query query,Hashtable<String,?> values){
			if(values!=null) {
				//query.setProperties(values);
				 Enumeration parameterNames=values.keys(); 
			        while(parameterNames.hasMoreElements()==true){    
			        String    pName=(String)parameterNames.nextElement();  
			        Object   param=values.get(pName);       
			            if(param instanceof String){
			            	String paramValue=(String)param;
			            	 query.setString(pName,paramValue);
			            }
			            else{
			            	if(param instanceof Integer ){
			            		Integer paramValue=(Integer)param;
				            	 query.setInteger(pName,paramValue.intValue());
			            	}
			            	else{
			            		if(param instanceof Double ){
			            			Double paramValue=(Double)param;
					            	 query.setDouble(pName,paramValue.doubleValue());
				            	}
			            	}
			            		
			            }
			           
			        }    

			}
			return query;
		}

		/**
		 * 按Criteria查询对象列表.
		 * 
		 * @param criterions 数量可变的Criterion.
		 */
		public List<T> find(final Criterion... criterions) {
			return createCriteria(criterions).list();
		}

		/**
		 * 按Criteria查询唯一对象.
		 * 
		 * @param criterions 数量可变的Criterion.
		 */
		public T findUnique(final Criterion... criterions) {
			return (T) createCriteria(criterions).uniqueResult();
		}

		/**
		 * 根据Criterion条件创建Criteria.
		 * 
		 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
		 * 
		 * @param criterions 数量可变的Criterion.
		 */
		public Criteria createCriteria(final Criterion... criterions) {
			Criteria criteria = getSession().createCriteria(entityClass);
			for (Criterion c : criterions) {
				criteria.add(c);
			}
			return criteria;
		}

		/**
		 * 初始化对象.
		 * 使用load()方法得到的仅是对象Proxy, 在传到View层前需要进行初始化.
		 * 只初始化entity的直接属性,但不会初始化延迟加载的关联集合和属性.
		 * 如需初始化关联属性,可实现新的函数,执行:
		 * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合.
		 * Hibernate.initialize(user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
		 */
		public void initEntity(T entity) {
			Hibernate.initialize(entity);
		}

		/**
		 * @see #initEntity(Object)
		 */
		public void initEntity(List<T> entityList) {
			for (T entity : entityList) {
				Hibernate.initialize(entity);
			}
		}

		/**
		 * 为Query添加distinct transformer.
		 */
		public Query distinct(Query query) {
			query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			return query;
		}

		/**
		 * 为Criteria添加distinct transformer.
		 */
		public Criteria distinct(Criteria criteria) {
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			return criteria;
		}

		/**
		 * 取得对象的主键名.
		 */
		public String getIdName() {
			ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
			return meta.getIdentifierPropertyName();
		}
	} 