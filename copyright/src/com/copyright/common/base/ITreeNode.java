package com.copyright.common.base;

/**
 * 这个接口的创建是为了考虑后台生成树形菜单的json数据而设计。
 * 希望使用JsonTreeUtil的功能的实体类都必须实现这个接口
 * 接口中声明了一些树节点的基本信息，例如对id，text等信息的读取
 * @author Edward
 *
 */
public interface ITreeNode {

	//获取text属性
	public String getText();
	//获取ID
	public Object getNodeID();
	//获取父ID，用于判断两个节点的父子关系
	public Object getFatherNodeID();
	//判断是否被访问过
	public boolean getVisited();
	
	public void setVisited(boolean visited);
	
	public boolean getIsChecked();
	
}
