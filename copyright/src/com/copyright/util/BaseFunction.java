package com.copyright.util;

/**
 *
 * <p>Title: </p>
 * <p>Description:包的所有的公共的方法 </p>
 * <p>Copyright: Copyright(c) 2005</p>
 * <p>Company: </p>
 * @author 
 * @version 5.0
 */  
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
 
public final class BaseFunction {  
    
	private static boolean SET_TIMEZONE=false;  
	public BaseFunction() {

	}
	/** 
	 * 清空hash表中数据
	 * @param hash
	 */
	public static void clearHash(Hashtable hash) {
		if(hash!=null) {
			hash.clear();
		}
		hash=null;
	}
	/** 
	 * 清空map中对象
	 * @param hash
	 */
	public static void clearMap(HashMap hash) {
		if(hash!=null) {
			hash.clear();
		}
		hash=null;
	}
	/** 
	 * @param hash
	 */
	public static void clearMap(Map hash) {
		if(hash!=null) {
			hash.clear();
		}
		hash=null;
	}
	/** 
	 * 清空list中对象
	 * @param list
	 */
	public static void clearList(List list) {
		if(list!=null) {
			list.clear();
		}
		list=null;
	}

	/**
	 * 判断字符串是否为空,空是指: null 或 空串 或 全是空格的字符串
	 * 
	 * @param a_value
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str==null ||str.trim().length()==0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 判断Long对象是否为空,空是指: null 或  0
	 * 
	 * @param a_value
	 * @return
	 */
	public static boolean isEmpty(Long str) {
		if(str==null||str.longValue()==0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 判断字符串是否为null对象
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		boolean result=false;
		if(str==null) {
			result=true;
		}
		return result;
	}

	/**
	 * 如果str为null,给str赋值
	 * @param str
	 * @param value
	 * @return
	 */
	public static String checkNull(String str,String value) {
		if(isEmpty(str)==true) {
			if(value!=null) {
				str=value;
			}
		}
		return str;
	}

	/**
	 * 如果str为null,给str赋值 
	 * @param str
	 * @param value
	 * @return
	 */
	public static String checkNull(String str) {
		if(str==null) {
			str="";
		}
		return str;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String removeChar(String str) {
		String result=str;
		StringBuffer temp=null;
		if(result==null ||result.trim().length()==0) {
			return result;
		} else {
			temp=new StringBuffer("");
			for(int i=0; i<result.length(); i++) {
				if(result.charAt(i)==' ') {
				} else {
					temp.append(result.charAt(i));
				}
			}
			result=temp.toString();
		}
		return result;
	}

	
	public static String javaConvert(String str){
		/*
		 * \' 单引号(\u0027)
       \" 双引号(\u0022)
      \\ 反斜杠(\u005c)
		 */
		if(isEmpty(str)==false){
			str=str.replaceAll("\\\\","\\\\\\\\");
		}
		return str;
	}
	
	/**
	 * 得到两个时间之间相差的秒数
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	public static long getSecond(long begintime,long endtime){
		long diff=endtime-begintime;
		long result = diff / (1000);
		return result;
	}
	
	 /**
	  * 
	  * @param fatherPathcode 父节点的最大值
	  * @param currentMaxValue 当前编号的最大值 
	  * @param fixLength int 流水字符串的长度
	  * @param beforeFill boolean 标记如果长度不够的话,0填充是前填充还是后填充 
	  * @return
	  */
	 public static String getLineIdBySequenceName(String fatherPathcode
			                                      ,String currentMaxValue
	                                          ,int fixLength 
	                                          ,boolean beforeFill){
	   String resultLineIdStr=null;
	   long tempLineIdLong=0;
	   try{
		   if(isEmpty(fatherPathcode)==true){
			   if(isEmpty(currentMaxValue)==true){
				   tempLineIdLong=1;
			   } 
			   else{
				   tempLineIdLong=Long.parseLong(currentMaxValue)+1;
			   }
		   }
		   else{
			   if(isEmpty(currentMaxValue)==true){
				   tempLineIdLong=1;
			   } 
			   else{
				   int posInt=-1;
				   posInt=currentMaxValue.indexOf(fatherPathcode);
				   currentMaxValue=currentMaxValue.substring(posInt+fatherPathcode.length()+1,currentMaxValue.length());
				   tempLineIdLong=Long.parseLong(currentMaxValue)+1;
			   }
		   } 
	   }
	   catch(Exception ex){
		   ex.printStackTrace();
	   }
	   resultLineIdStr=Long.toString(tempLineIdLong);
	   if(fixLength<0){
	     fixLength=SysConst.SYS_LINE_LENGTH;
	   }
	   /**
	    * 如果长度小于设置的长度,用0填充, 如果beforeFill为true的话,就是用0前填充,否则就是用0后填充
	    */
	   if(resultLineIdStr.length()<fixLength){
	     for(int i=resultLineIdStr.length(); i<fixLength; i++) {
	      if(beforeFill==true){
	        resultLineIdStr="0" + resultLineIdStr;
	      }
	      else{
	        resultLineIdStr+="0";
	      }
	     }
	   }
	   if(isEmpty(fatherPathcode)==true){
		   
	   }
	   else{
		   resultLineIdStr=fatherPathcode+SysConst.SYS_PATH_CODE_SPLIT+resultLineIdStr;
	   }
	   return resultLineIdStr;
	} 

	 

	/**
	 * 
	 * @param object
	 * @param propertyName
	 * @return
	 */
	public static String getPropertyName(Object object,String propertyName){
		String result=null;
		try{ 
			if(isEmpty(propertyName)==false){
		      result=BeanUtils.getProperty(object,propertyName);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
   public static String getServerCurrentDateAndTime(Date date,String pattern){ 
		String result=null;
		java.text.SimpleDateFormat df=null;
		try{
			if(isEmpty(pattern)==true){
				pattern="yyyy-MM-dd";
			}
			df=new SimpleDateFormat(pattern);
			if(SET_TIMEZONE==false){
				  TimeZone tz=TimeZone.getTimeZone("ETC/GMT-8");
				  TimeZone.setDefault(tz);
				  SET_TIMEZONE=true;
				} 
			if(date!=null){
			  result=df.format(date);
			}
	       if(isEmpty(result)==true){
	       	result="";
	       }	
		}
		catch(Exception ex){
		  ex.printStackTrace();
		} 
		return result;
   }
	/**
	 * 得到日期的字符串格式 
	 * @param date
	 * @return String
	 */
	public static String getServerCurrentDateAndTime(Date date) {
		 String pattern="yyyy-MM-dd HH:mm:ss.SSS";
		 return getServerCurrentDateAndTime(date,pattern);
	}
	 
	/**
	 * 得到服务器系统日期和时间 ,格式 yyyy-MM-dd HH:mm:ss.SSS
	 * @return String
	 */
	public static String getServerCurrentDateAndTime() {
		String pattern="yyyy-MM-dd HH:mm:ss"; 
		java.util.Date date=new Date();
		return getServerCurrentDateAndTime(date,pattern); 
	}

	/**
	 * 从日期字符串中得到年份
	 * 
	 * @return String
	 */
	public static String getYearFromDateStr(String dateStr) {
		String result=null;
		dateStr=checkNull(dateStr,"");
		if(dateStr.length()==0) {
			//提取服务器年份 
			result=getServerSysCurrentYear();
		} else {
			if(dateStr.length() > 4) {
				result=dateStr.substring(0,4);
			} else {
				//提取服务器年份  
				result=getServerSysCurrentYear();
			}
		}
		return result;
	}
	/** 
	 * @return String
	 */
	public static String getServerSysCurrentYear() { 
		java.util.Date date=null; 
		String pattern="yyyy"; 
		date=new Date();
		return getServerCurrentDateAndTime(date,pattern); 
	}
	  /**
     * 
     * @param current_time
     * @return
     */
	public static String getServerCurrentDate(String current_time) {
		String result="";
		long currentLong=0;
		try{
			 if(isEmpty(current_time)==false){
				 try{
					 currentLong=Long.parseLong(current_time); 
				 }
				 catch(Exception ex){
					 ex.printStackTrace();
				 }
				 result=getServerCurrentDate(currentLong);
			 }
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return result ;
	}
    /**
     * 
     * @param current
     * @return
     */
	public static String getServerCurrentDate(long current) {
		String result="";
		try{
			String pattern="yyyy-MM-dd HH:mm:ss"; 
			Date date=new Date(current);
			if(date!=null){
			  result=getServerCurrentDateAndTime(date,pattern);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return result ;
	}
	/**
	 * 得到服务器系统日期和时间,日期的字符串格式  
	 * @return String
	 */
	public static String getServerCurrentYearMonthDay() {
		String pattern="yyyy-MM-dd"; 
		Date date=new Date();
		return getServerCurrentDateAndTime(date,pattern); 
	}
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static  String[] listToArr(List list){
		String[] result=null;
		if(emptyList(list)==true){
			int size=list.size();
			result=new String[size];
			for(int i=0;i<size;i++){
				result[i]=(String)list.get(i);
			}
		}
		return result;
	}
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static  String listToStr(List list,String splitStr){
		StringBuffer result=new StringBuffer("");
		String temp_splitStr=null;
		if(emptyList(list)==true){ 
			if(isEmpty(splitStr)==true){
				temp_splitStr=SysConst.SYS_SPLIT_COLON;
			}
			else{
				temp_splitStr=splitStr;
			}
			int size=list.size();
			for(int i=0;i<size;i++){
				if(i>0){
					result.append(temp_splitStr);
				}
				result.append((String)list.get(i));
			}
		}
		return result.toString();
	}
	 
	/**
	 * 得到服务器系统日期
	 * 
	 * @return String
	 */
	public static String getServerCurrentSysDate() {
		String pattern="yyyy-MM-dd"; 
		java.util.Date date=new java.util.Date();
		return getServerCurrentDateAndTime(date,pattern); 
	}

	/**
	 * 得到服务器系统日期
	 * 
	 * @return String
	 */
	public static String getServerCurrentSysDateChStr() {
		String pattern="yyyy年MM月dd日"; 
		java.util.Date date=new java.util.Date();
		return getServerCurrentDateAndTime(date,pattern); 
	}

	 

	
	
	
	public static String getTime(long time){
		String result="";
		time=time/1000;
		if(time>24*60*60){
    	
    }
    else{
    	if(time>60*60){
    		
    	}
    	else{
    		
    	}
    }
		return result;
	}
	
	/**
	 * 得到请求url:
	 * @param request
	 * @return
	 */
	public static String getRequestUrl(HttpServletRequest request) {
		StringBuffer result=new StringBuffer("");
		result.append("http://" + request.getServerName());
		result.append(":" + request.getServerPort());
		result.append("/" + request.getContextPath() + "/"
				+ request.getServletPath()); 
		return result.toString(); 
	}

	/**
	 * 得到请求数据:
	 * @param request
	 * @return
	 */
	public static String getRequestData(HttpServletRequest request) {
		StringBuffer result=new StringBuffer("");
		result.append(getRequestUrl(request));
		Enumeration enumType=request.getParameterNames();
		int count=0;
		while(enumType.hasMoreElements()) {
			String fieldName=(String) enumType.nextElement();
			String fieldValue=request.getParameter(fieldName);
			fieldValue=fieldValue==null ? "" : request.getParameter(fieldName);
			if(count>0){
				result.append("&" + fieldName + "=" + fieldValue);	
			}
			else{
				result.append("?" + fieldName + "=" + fieldValue);
				count=1;
			}
			
		}
		return result.toString();

	}
	
	/**
	 * 如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？

答案是取X-Forwarded-For中第一个非unknown的有效IP字符串

	 * @param request
	 * @return
	 */
    public static String getIp(HttpServletRequest request){ 
    	    String ip=request.getHeader("x-forwarded-for");  
    	    if(ip==null ||ip.length()==0 ||"unknown".equalsIgnoreCase(ip)) {  
    	        ip=request.getHeader("Proxy-Client-IP");  
    	    }  
    	    if(ip==null ||ip.length()==0 ||"unknown".equalsIgnoreCase(ip)) {  
    	        ip=request.getHeader("WL-Proxy-Client-IP");  
    	    }  
    	    if(ip==null ||ip.length()==0 ||"unknown".equalsIgnoreCase(ip)) {  
    	        ip=request.getRemoteAddr();  
    	    }  
    	    return ip;   
    }
	/**
	 * 
	 * @param request
	 * @return
	 */
	public static StringBuffer getRequestHead(HttpServletRequest request) {
		StringBuffer result=new StringBuffer("");
		String headerName=null;
		if(request!=null) {
			Enumeration enumType=request.getHeaderNames();
			if(enumType!=null) {
				while(enumType.hasMoreElements()==true) {
					headerName=(String) enumType.nextElement();
					result.append(headerName + ":"
							+ request.getHeader(headerName) + ";");
				}
			}
		}
		return result;
	}

		  /**
		     * 把数组中的id信息输出,组合成sql语句
		     * @param arr
		     * @param entityName 
		     * @return( id=1 or id=2 or id=3 )
		     * @throws Exception
		     */
			public static String arrToSqlStr(String[] arr,String entityName)throws Exception{
				StringBuffer result=new StringBuffer("");   
				try{  
					if(arr!=null&&arr.length>0){ 
						for(int i=0;i<arr.length;i++){ 
							if(i>0){
								result.append(" or ");
							}
							result.append(entityName+"='"+(String)arr[i]+"'");
						}
					} 
				}
		    	catch(Exception ex){ 
		    		ex.printStackTrace();
		    	}
		    	return result.toString();
			}
			/**
		     * 把数组中的id信息输出,组合成sql语句
		     * @param arr
		     * @param entityName 
		     * @return( id=1 or id=2 or id=3 )
		     * @throws Exception
		     */
			public static String arrToSqlStr(String[] arr,String entityName,List param)throws Exception{
				StringBuffer result=new StringBuffer("");   
				String temp=null;
				try{  
					if(arr!=null&&arr.length>0){ 
						for(int i=0;i<arr.length;i++){ 
							if(i>0){
								result.append(" or ");
							}
							result.append(entityName+"=?");
							temp=(String)arr[i];
							param.add(checkNull(temp));
						}
					} 
				}
		    	catch(Exception ex){ 
		    		ex.printStackTrace();
		    	}
		    	return result.toString();
			}
			/**
		     * 把数组中的id信息输出,组合成sql语句
		     * @param list
		     * @param entityName 
		     * @return( id=1 or id=2 or id=3 )
		     * @throws Exception
		     */
			public static String listToSqlStr(List list,String entityName,List param)throws Exception{
				StringBuffer result=new StringBuffer("");   
				Long objectLong=null; 
				Integer objectInt=null;
				try{  
					if(list!=null&&list.size()>0){ 
						for(int i=0;i<list.size();i++){ 
							if(i>0){
								result.append(" or ");
							}
							result.append(entityName+"=?");
							if(list.get(i) instanceof String){
							  param.add((String)list.get(i));
							}
							else{
								if(list.get(i) instanceof Long){
									objectLong=(Long)list.get(i);
									if(objectLong!=null){
									  param.add(objectLong.longValue());
									}
									else{
									  param.add("0");
									}
								}
								else{
									if(list.get(i) instanceof Integer){
										objectInt=(Integer)list.get(i);
										if(objectInt!=null){
										  param.add(objectInt.intValue());
										}
										else{
										  param.add("0");
										}
									}
								}
							}
						}
					} 
				}
		    	catch(Exception ex){ 
		    		ex.printStackTrace();
		    	}
		    	return result.toString();
			}
			
			/**
		     * 把数组中的id信息输出,组合成sql语句
		     * @param list
		     * @param entityName 
		     * @return( id=1 or id=2 or id=3 )
		     * @throws Exception
		     */
			public static String listToSqlStr(List list,String entityName)throws Exception{
				StringBuffer result=new StringBuffer("");  
				try{  
					if(list!=null&&list.size()>0){ 
						int size=list.size();
						for(int i=0;i<size;i++){ 
							if(i>0){
								result.append(" or ");
							}
							result.append(entityName+"=?");  
						}
					} 
				}
		    	catch(Exception ex){ 
		    		ex.printStackTrace();
		    	}
		    	return result.toString();
			}
		 
		  /**
		   * 
//统计由分隔符分割组成的字符串有几个有效的id

		   * @param ids
		   */
		  public static int getCountInt(String ids){
			  String[] temp_arr=null;
			  int result=0;
			  if(isEmpty(ids)==false){
				 temp_arr=strToArray(ids);
				 if(temp_arr!=null){
					  for(int i=0;i<temp_arr.length;i++){
						  if(temp_arr[i]!=SysConst.SYS_SPLIT_COLON&&isEmpty(temp_arr[i])==false){
							 result++;
						  }
					  }
				 } 
			  }
			  return result; 
		  }
		  
			/**
			 * 判断list是否大小为0
			 * @param dataList
			 * @return
			 */
			public static boolean emptyList(List dataList){
				boolean result=true;
				if(dataList!=null&&dataList.size()>0){
					result=false;
				}
				return result;
			}
		 
		    
		    /**
			 * 判断该字符串是否为日期格式 
			 * @param dateStr
			 * @param pattern
			 * @return
			 */
			public static boolean isDate(String dateStr,String pattern) {
				boolean  result=false;
				if(isEmpty(dateStr)==false) {
					SimpleDateFormat formatter=null;
					Date date=null; 
					try { 
						if(isEmpty(pattern)==true){
						  pattern="yyyy-MM-dd";
						}
						formatter=new SimpleDateFormat(pattern);
						date=formatter.parse(dateStr); 
						result=true;
					} catch(Exception ex) {
						date=null;
						result=false;
						ex.printStackTrace();   
					}
					if(date==null){
						result=false;
					}
				}
				return result;
			}
		  /**
		   * 移动运营商  多普达 s900 cellid:61790-8545-460-0
		   * 中国电信 运营商 天语手机 0-539426348-0-2070684148
		   * 看是否为有效的移动基站 
		   * @param arr
		   * @return
		   */
			public static boolean validYidongCellId(String[] arr){
		  	boolean result=true;
		  	if(arr!=null&&arr.length==4){
		  		 if("0".equals(arr[0])==true){
		  			 result=false;
		  		 } 
		  	}
		  	return result;
		  }
		  
		  /** 
		   * 中国电信 运营商 华为c8500手机 3156-13824-3-460-00 
		   *               如果是飞行模式 -1--1--1
		   *          三星i559基站信息 :0-13824-3     
		   * 看是否为有效的基站 
		   * @param arr
		   * @return
		   */
		  public static boolean validDianxingCellId(String[] arr,String cell_id){
		  	boolean result=true;
		  	if(arr!=null){
		  	  if("0".equals(arr[0])==true
		  	  		||"-1--1--1".equals(cell_id)==true
		  	  		||"[-1,-1]".equals(cell_id)==true
			      	||arr.length==6
				      ||arr.length==3)	{
		  	  	 result=false;
		  	  }
		  	} 
		  	return result;
		  }
		  
		  /**
		   * 把带有分割符的字符串，通过分割符，把字符串放到数组中
		   * 如果str是null或“”，则返回null字符串
		   * 如果splitStr是null或“”,则使用默认分割符号
		   * @param str
		   * @param splitStr
		   * @return
		   */
		  public static String[] strToArray(String str){
		      String[] result=null; 
		      String splitStr=null;
		      try
		      {
		          if(isEmpty(str)==true){
		        	  return result;
		          }  
		          //看字符串是用什么分隔的
		          if(str.indexOf(SysConst.SYS_SPLIT_COLON)>=0){
		        	  splitStr=SysConst.SYS_SPLIT_COLON;
		          }
		          else{
		        	  if(str.indexOf(SysConst.SYS_SPLIT_SEMICOLON)>=0){
			        	  splitStr=SysConst.SYS_SPLIT_SEMICOLON;
			          }
		        	  else{
		        		  if(str.indexOf(SysConst.SYS_SPLIT_COMMA)>=0){
				        	  splitStr=SysConst.SYS_SPLIT_COMMA;
				          }  
		        	  }
		          }
		          result=strToArray(str,splitStr);
		      }
		      catch(Exception ex){
		    	  ex.printStackTrace();
		      } 
		      return result;
		  }
		  /**
		   * 把带有分割符的字符串，通过分割符，把字符串放到数组中
		   * 如果str是null或“”，则返回null字符串
		   * 如果splitStr是null或“”,则使用默认分割符号
		   * @param str
		   * @param splitStr
		   * @return
		   */
		  public static String[] strToArray(String str,String splitStr){
		      String[] result=null;
		      List tempList=null;
		      try
		      {
		          if(isEmpty(str)==true){
		        	  return result;
		          } 
		          if(splitStr==null){
					  splitStr=SysConst.SYS_NAME_SPLIT;
				  }
		          StringTokenizer st=new StringTokenizer(str,splitStr);
		          tempList=new ArrayList();
		          while(st.hasMoreElements()==true){        	 
		        	  tempList.add(st.nextToken());
		          }
		          if(tempList.size()>0){
		          	int size=tempList.size();
		        	  result=new String[size];
		        	  for(int i=0;i<size;i++){
		        		  result[i]=(String)tempList.get(i); 
		        	  }
		          }
		          
		      }
		      catch(Exception ex){
		    	  ex.printStackTrace();
		      } 
		      return result;
		  }
	public static void main(String[] args) {
		
		String[] arr=BaseFunction.strToArray(";0.0;0.0;0.0;0.0;1309496708121:;0.0;0.0;0.0;0.0;1309497127401",SysConst.SYS_SPLIT_COLON);
		if(arr!=null&&arr.length>0){
			int size=arr.length;
			for(int i=0;i<size;i++){
				if(isEmpty(arr[i])==false){
					String[] temp=BaseFunction.strToArray(arr[i],SysConst.SYS_SPLIT_SEMICOLON);
			    System.out.println(temp[0]);
			    System.out.println(temp[1]);
			    System.out.println(temp[2]);
			    System.out.println(temp[3]);
				}
			}
		}
	 
	} 
}
