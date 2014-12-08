package com.copyright.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 

/**
 * <p>
 * 	    字符串操作通用类
 * </p>
 * @author jt.tao
 * @version 
 */
public class StringUtils {
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
	 * 判断字符串是否为空,空是指: null 或 空串 或 全是空格的字符串
	 * 
	 * @param a_value
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(isNull(str) || str.trim().length()==0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 如果str为null,给str赋值
	 * @param str
	 * @param value
	 * @return
	 */
	public static String checkNull(String str,String value) {
		if(isEmpty(str) && value!=null) {
			str=value;
		}
		return str;
	}
	/**
	 * 如果str为null,给str赋值"" 
	 * @param str
	 * @return
	 */
	public static String checkNull(String str) {
		if(str==null) {
			str="";
		}
		return str;
	}
	/**
	 * 移除字符
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
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
	/**
	 * 得到 字符字节个数
	 * @param str
	 * @return
	 */
	public int getWordCount(String str) {
		int length=0;
		for(int i=0; i<str.length(); i++) {
			int ascii=Character.codePointAt(str,i);
			if(ascii >=0&&ascii<=255){
				length++;	
			}else{
				length +=2;
			}
		}
		return length;
	}
	/**
     * 得到 字符字节个数
     * @param str
     * @return
     */
	public int getWordCountReg(String str) { 
		str=str.replaceAll(" [^\\x00-\\xff] "," ** ");
		int length=str.length();
		return length;
	}
	/**
	 * 转换为特殊格式
	 * \' 单引号(\u0027)
   	 * \" 双引号(\u0022)
  	 * \\ 反斜杠(\u005c)
	 */
	public static String javaConvert(String str){
		if(isEmpty(str)){
			return str;
		}
		str=str.replaceAll("\\","\\u005c");
		str=str.replaceAll("\'","\\u0027");
		str=str.replaceAll("\"","\\u0022");
		return str;
	}
	
	/**
	 * 数组转换以","分隔的字符串
	 * @param param
	 * @return
	 */
	public static String arrJoin(String[] param){
		StringBuffer result=new StringBuffer("");
		if(param!=null&&param.length>0){
			for(int i=0;i<param.length;i++){
				if(i>0){
					result.append(",");
				}
				result.append("'"+param[i]+"'");
			}
		}
		return result.toString();
	}
	/**
	 * 数组转换以指定分隔符连接的字符串
	 * @param param
	 * @return
	 */
	public static String arrJoin(String[] param,String splitStr){
		StringBuffer result=new StringBuffer("");
		if(isEmpty(splitStr)==true){
			splitStr=SysConst.SYS_SPLIT_COLON;
		}
		if(param!=null&&param.length>0){
			int size=param.length;
			for(int i=0;i<size;i++){
				if(i>0){
					result.append(splitStr);
				}
				result.append(param[i]);
			}
		}
		return result.toString();
	}
	
	
//	/**
//	 * 
//	 * 功能说明:过滤字符串,设定默认值
//	 * @param str
//	 *            传入的字符串
//	 * @param defaultValue
//	 *            返回默认值
//	 * @return 过滤后的字符串(含有默认值参数的，返回默认值)
//	 */
//	public static String filterNull_string(String str,String...defaultValue) {
//		
//		return null;
//	}
//	
//	/**
//	 * 功能说明:过滤字符串，把字符串转换成整数
//	 * 
//	 * @param str
//	 *            传入的字符串
//	 * @param defaultValue
//	 *            返回默认值
//	 * @return 过滤后的字符串(含有默认值参数的，返回默认值)
//	 */
//	public static int filterNull_int(String str,Integer...defaultValue) {
//
//		return 0;
//	}
//
//	/**
//	 * 功能说明:sql语句中in语句的转化器，例如：传入参数为1,2,3 转换结果为'1','2','3' 处理过程：将 id in (1,2,3)
//	 * 转化为 1','2','3 再通过最外层的单引号,结果为'1','2','3'
//	 * 
//	 * @param sql
//	 *            1,2,3
//	 * @return '1','2','3'
//	 */
//	public static String parseSelectIn(String sql) {
//		Pattern pattern=Pattern.compile("\\w*\\s+[i,I][n,N]\\s+\\((,?\\s*\\d+\\s*)+\\s*\\)\\s+\\w*");
//		Matcher matcher = pattern.matcher(sql);
//		if(matcher.find()){
//			
//		}
//		return null;
//	}
	/**
	 * 功能说明: 将字符串按照制定分隔符拆分成对应的数组
	 * 
	 * @param str
	 *            传入的字符串
	 * @param format
	 *            通过什么间隔符将传入的字符串间隔成数组 例如,
	 * @return 数组
	 */
	public static String[] parseStrToArr(String str, String format) {
		if (str == null)
			return null;
		String[] arr = str.split(format);
		return arr;
	}
	

	/**
	 * 功能说明:对字符串进行用0补位，例如ZeroString("45",4) 结果为0045
	 * 
	 * @param str
	 *            要补位的字符串
	 * @param size
	 *            最终生成的字符串位数
	 */
	public static String zeroString(String str, int size) {
		str = (str == null) ? "" : str.trim();
		char[] ch = str.toCharArray();
		int len = ch.length;
		int lens = size - len;
		if (lens <= 0) {
			return str;
		}
		String rStr = "";
		for (int i = 0; i < size; i++) {
			if (lens > i) {
				rStr += "0";
				continue;
			}
			rStr += ch[i - lens];
		}
		return rStr;
	}
	
	/**
	 * 功能描述：替换字符串
	 * 
	 * @param from
	 *            String 原始字符串
	 * @param to
	 *            String 目标字符串
	 * @param source
	 *            String 母字符串
	 * @return String 替换后的字符串
	 */
	public static String replace(String from, String to, String source) {
		if (source == null || from == null || to == null)
			return null;
		StringBuffer str = new StringBuffer("");
		int index = -1;
		while ((index = source.indexOf(from)) != -1) {
			str.append(source.substring(0, index) + to);
			source = source.substring(index + from.length());
			index = source.indexOf(from);
		}
		str.append(source);
		return str.toString();
	}
	
	/**
	 * 功能描述：判断是否为整数
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为浮点数，包括double和float
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 是浮点数返回true,否则返回false
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?\\d+\\.\\d+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是不是合法字符 c 要判断的字符
	 */
	public static boolean isLetter(String str) {
		if (str == null || str.length() < 0) {
			return false;
		}
		Pattern pattern = Pattern.compile("[\\w\\.-_]*");
		return pattern.matcher(str).matches();
	}
	 
	/**
	 * 功能描述：判断输入的字符串是否符合Email样式.
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 是Email样式返回true,否则返回false
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.length() < 1 || email.length() > 256) {
			return false;
		}
		Pattern pattern = Pattern
				.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		return pattern.matcher(email).matches();
	}
	/**
   * 把数组中的id信息输出,组合成sql语句
   * @param arr
   * @param entityName 
   * @return ( id=1 or id=2 or id=3 )
   * @throws Exception
   */
	public static String idsToSqlStr(String ids,String entityName)throws Exception{
	  return idsToSqlStr(ids,SysConst.SYS_SPLIT_COLON,entityName);
	}
	/**
   * 把数组中的id信息输出,组合成sql语句
   * @param arr
   * @param entityName 
   * @return ( id=1 or id=2 or id=3 )
   * @throws Exception
   */
	public static String idsToSqlStr(String ids,String splitStr,String entityName)throws Exception{
		StringBuffer result=new StringBuffer("");   
		String[] arr=null;
		try{  
			arr=StringUtils.strToArray(ids,splitStr);
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
   * @return ( id=1 or id=2 or id=3 )
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
	 * 功能描述：判断输入的字符串是否为纯汉字
	 * 
	 * @param str
	 *            传入的字符窜
	 * @return 如果是纯汉字返回true,否则返回false
	 */
	public static boolean isChinese(String str) {
		Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 功能描述：是否为空白,包括null和""
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 功能描述：判断是否为质数
	 * 
	 * @param x
	 * @return
	 */
	public static boolean isPrime(int x) {
		if (x <= 7) {
			if (x == 2 || x == 3 || x == 5 || x == 7)
				return true;
		}
		int c = 7;
		if (x % 2 == 0)
			return false;
		if (x % 3 == 0)
			return false;
		if (x % 5 == 0)
			return false;
		int end = (int) Math.sqrt(x);
		while (c <= end) {
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 6;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 6;
		}
		return true;
	}

	/**
	 * 功能描述：人民币转成大写
	 * 
	 * @param str
	 *            数字字符串
	 * @return String 人民币转换成大写后的字符串
	 */
	public static String hangeToBig(String str) {
		double value;
		try {
			value = Double.parseDouble(str.trim());
		} catch (Exception e) {
			return null;
		}
		char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
		char[] vunit = { '万', '亿' }; // 段名表示
		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
		long midVal = (long) (value * 100); // 转化成整形
		String valStr = String.valueOf(midVal); // 转化成字符串

		String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
		String rail = valStr.substring(valStr.length() - 2); // 取小数部分

		String prefix = ""; // 整数部分转化的结果
		String suffix = ""; // 小数部分转化的结果
		// 处理小数点后面的数
		if (rail.equals("00")) { // 如果小数部分为0
			suffix = "整";
		} else {
			suffix = digit[rail.charAt(0) - '0'] + "角"
					+ digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
		}
		// 处理小数点前面的数
		char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
		char zero = '0'; // 标志'0'表示出现过0
		byte zeroSerNum = 0; // 连续出现0的次数
		for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
			int idx = (chDig.length - i - 1) % 4; // 取段内位置
			int vidx = (chDig.length - i - 1) / 4; // 取段位置
			if (chDig[i] == '0') { // 如果当前字符是0
				zeroSerNum++; // 连续0次数递增
				if (zero == '0') { // 标志
					zero = digit[0];
				} else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
					prefix += vunit[vidx - 1];
					zero = '0';
				}
				continue;
			}
			zeroSerNum = 0; // 连续0次数清零
			if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
				prefix += zero;
				zero = '0';
			}
			prefix += digit[chDig[i] - '0']; // 转化该数字表示
			if (idx > 0)
				prefix += hunit[idx - 1];
			if (idx == 0 && vidx > 0) {
				prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
			}
		}

		if (prefix.length() > 0)
			prefix += '圆'; // 如果整数部分存在,则有圆的字样
		return prefix + suffix; // 返回正确表示
	}

	/**
	 * 功能描述：判断是不是合法的手机号码
	 * 
	 * @param handset
	 * @return boolean
	 */
	public static boolean isHandset(String handset) {
		try {
			String regex = "^1[\\d]{10}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(handset);
			return matcher.matches();
		} catch (RuntimeException e) {
			return false;
		}
	}
	
	/**
	 * 将一个指定字符串以重复的数相连
	 * @param repeatTime
	 * @param metaString
	 * @return
	 */
	public static String getRepeatString(int repeatTime, String metaString) {
		String repeatString = null;
		if (repeatTime > 0 && metaString != null) {
			int intMetatStringLength = metaString.length();
			if (intMetatStringLength == 0) {
				repeatString = "";
			} else {
				StringBuffer tempStringBuffer = new StringBuffer(repeatTime
						* intMetatStringLength);
				for (int i = 0; i < repeatTime; i++)
					tempStringBuffer.append(metaString);

				repeatString = tempStringBuffer.toString();
			}
		}
		return repeatString;
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

}
