package com.copyright.common.base;

import java.io.Serializable;
import java.util.List;

import net.sf.json.JSONArray;

import com.copyright.util.BaseFunction;
import com.copyright.util.SysConst;
 
/**
 * 用户对象的实体定义的基类 
 * @author 
 */
public class BaseForm  extends BaseEntity implements Serializable{//extends BaseEntity
	//操作类型add,new:表示新增回复modify,edit:表示修改回复 view：表示查看回复
	private String method=""; 
	//删除的时候，保存id的
	private String ids="";
	//总记录数
	private String totalRows; 
  //总页数
	//private String totalPage; 
  //页大小
	private String pageSize="";
	private int pageSizeInt=0;  
  //当前页号 
	private String pageNo=""; 
  //当前页号 
	private int pageNoInt=0;
	//操作失败的默认提示信息
	private String info="操作失败!"; 
	//操作成功的默认提示信息
	private String infoSucess="操作成功!"; 
  //模块id 
	private String mid="";
  //模块名称
	private String mname="";
	//extjs的当前请求页号
	private String start="";
	//extjs
	private String limit=""; 
	//排序字段名,hibernate映射的字段名
	private String sort="";
    //排序顺序 :desc asc
	private String dir="";
	//操作结果true表示成功,false表示失败
	private boolean success=false;
	private boolean result=true; 
	private int resultInt=-1; 
	private String info_id="";  
	//items
	private List items;
	private JSONArray jsonArray;
	
	public int getResultInt() {
		return resultInt;
	} 
	public void setResultInt(int resultInt) {
		this.resultInt=resultInt;
	} 
	/**
	 * 所有action需要调用的函数
	 */
	public void initPageInfo() {
		getLimit();
		getStart();
	}
	/**
	 * 
	 */
	public void initMobilePageInfo(){
		getPageSize();
		getPageNo();
	}
    /**
     * 得到服务器端每页数据条数
     * @return
     */
	public String getPageSize() {
		pageSize=BaseFunction.checkNull(pageSize);
		if(BaseFunction.isEmpty(pageSize)==true
				||"0".equals(pageSize)==true
				||"null".equals(pageSize)==true){			   
			  pageSize=Long.toString(SysConst.SYS_DEFAULT_PAGESIE);
			  pageSizeInt=SysConst.SYS_DEFAULT_PAGESIE;
		}
		else{ 
			try{
			  pageSizeInt=Integer.parseInt(pageSize);
			}
			catch(Exception ex){
				pageSizeInt=SysConst.SYS_DEFAULT_PAGESIE;
				ex.printStackTrace();
			}
		}
		return pageSize;
	} 
 
	/**
	 * 得到当前页号
	 * @return
	 */
	public String getPageNo() { 
		if(BaseFunction.isEmpty(pageNo)==true
				||"0".equals(pageNo)==true||"null".equals(pageNo)==true){	
		   pageNo="1";
	    } 
		try{
			pageNoInt=Integer.parseInt(pageNo);
		}
		catch(Exception ex){
			pageNo="1";
			pageNoInt=1;
			ex.printStackTrace();
		}
		return pageNo;
	} 
	public void setPageNo(String pageNo) {
		this.pageNo=pageNo;
	} 
	public int getPageSizeInt() {
		return pageSizeInt;
	} 
	public void setPageSizeInt(int pageSizeInt) {
		this.pageSizeInt=pageSizeInt;
	} 
	public void setPageSize(String pageSize) {
		this.pageSize=pageSize;
	} 
	public int getPageNoInt() {
		return pageNoInt;
	} 
	public void setPageNoInt(int pageNoInt) {
		this.pageNoInt=pageNoInt;
	} 
	
	public String getOrgStart() {
		return this.start;
	}
	/**
	 * 处理extjs中当前页号
	 * @return
	 */
	public String getStart() {
		if(BaseFunction.isEmpty(start)==true) {
			start="1";
		}
		try {
			pageNoInt=Integer.parseInt(start);
		} catch(Exception ex) {
			pageNoInt=1;
//			logger.error(ex);
		}
		if(pageNoInt<1){
			pageNoInt=1;
		}
		else{ 
		  pageNoInt=(pageNoInt/pageSizeInt)+1;
		}
		start=Integer.toString(pageNoInt);
		return start;
	} 
	public void setStart(String start) {
		this.start=start;
	} 
	/**
	 * 处理extjs中每页数据条数
	 * @return
	 */
	public String getLimit() {
		limit=BaseFunction.checkNull(limit);
		if(limit==null ||limit.trim().length()==0
				||"0".equals(limit)==true ||"null".equals(limit)==true) {
			limit=Long.toString(SysConst.SYS_DEFAULT_PAGESIE);
			pageSizeInt=SysConst.SYS_DEFAULT_PAGESIE;
		} else {
			try {
				int pos=limit.indexOf(",");
				if(pos>0){
					pageSizeInt=Integer.parseInt(limit.substring(0,pos));
				}else{
				pageSizeInt=Integer.parseInt(limit);
				}
			} catch(Exception ex) {
				pageSizeInt=SysConst.SYS_DEFAULT_PAGESIE;
//				logger.error(ex);
			}
			if(pageSizeInt <0) {
				pageSizeInt=SysConst.SYS_DEFAULT_PAGESIE;
			}
			limit=Integer.toString(pageSizeInt);
		}
		return limit;
	} 
	public String getOrgLimit(){
		return this.limit;
	}
	public void setLimit(String limit) {
		this.limit=limit;
	} 
	public boolean getSuccess() {
		return success;
	} 
	public void setSuccess(boolean success) {
		this.success=success;
	} 
	public String getInfo_id() {
		return info_id;
	} 
	public void setInfo_id(String info_id) {
		this.info_id=info_id;
	} 
	public String getSort() {
		return sort;
	} 
	public void setSort(String sort) {
		this.sort=sort;
	} 
	public String getDir() {
		return dir;
	} 
	public void setDir(String dir) {
		this.dir=dir;
	} 
	public boolean isResult() {
		return result;
	} 
	public void setResult(boolean result) {
		this.result=result;
	}
	public String getInfo() {
  	return info;
  }
	public void setInfo(String info) {
  	this.info = info;
  }
	public String getInfoSucess() {
  	return infoSucess;
  }
	public void setInfoSucess(String infoSucess) {
  	this.infoSucess = infoSucess;
  } 
	public String getMid() {
  	return mid;
  }
	public void setMid(String mid) {
  	this.mid = mid;
  }
	public String getMname() {
  	return mname;
  }
	public void setMname(String mname) {
  	this.mname = mname;
  }
	public String getMethod() {
  	return method;
  }
	public void setMethod(String method) {
  	this.method = method;
  }
	public String getIds() {
  	return ids;
  }
	public void setIds(String ids) {
  	this.ids = ids;
  }
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
	public JSONArray getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}
	public String getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(String totalRows) {
		this.totalRows = totalRows;
	}
}
