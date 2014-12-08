package com.copyright.common.hibernate;

/**
*
* <p>Title:  </p>
* <p>Description: </p>
* <p>Copyright: Copyright(c) 2004</p>
* <p>Company: </p>
* @author 
* @version 1.0
*/ 
public class HqlCondition {
	/** 
	 * 选择语句
	 */
  private StringBuffer selStr=new StringBuffer("");
  /**
   * from语句
   */
  private StringBuffer fromStr=new StringBuffer("");
  /**
   * where语句
   */
  private StringBuffer whereStr=new StringBuffer("");
  /**
   * 排序语句
   */
  private StringBuffer orderStr=new StringBuffer("");
  /**
   * 
   */
  private StringBuffer groupStr=new StringBuffer("");
  /**
   * 参数值数组
   */
  private String[] paramValueArr;
  
  
  public String getFromStr() {
		return fromStr.toString();
	}
	public void setFromStr(String fromStr) {
		this.fromStr .append( fromStr);
	}
	public String getOrderStr() {
		return orderStr.toString();
	}
	public void setOrderStr(String orderStr) {
		this.orderStr .append( orderStr);
	}
	public String getSelStr() {
		return selStr.toString();
	}
	public void setSelStr(String selStr) {
		this.selStr .append( selStr);
	}
	public String getWhereStr() {
		return whereStr.toString();
	}
	public void setWhereStr(String whereStr) {
		this.whereStr.append( whereStr);
	}
	public String[] getParamValueArr() {
		return paramValueArr;
	}
	public void setParamValueArr(String[] paramValueArr) {
		this.paramValueArr=paramValueArr;
	}
	public String getGroupStr() {
		return groupStr.toString();
	}
	public void setGroupStr(String groupStr) {
		this.groupStr.append(groupStr);
	}
    /**
     * 
     */
	public String toString(){
		StringBuffer strBuffer=null;
		strBuffer=new java.lang.StringBuffer();
		strBuffer.append("");
		if(selStr!=null){
			  strBuffer.append(selStr.toString());
		}
		if(fromStr!=null){
			  strBuffer.append(fromStr.toString());
		}
		if(whereStr!=null){
			  strBuffer.append(whereStr.toString());
		}
		if(groupStr!=null){
			  strBuffer.append(groupStr.toString());
		}
		if(orderStr!=null){
			  strBuffer.append(orderStr.toString());
		}
		return strBuffer.toString();
	}

}
