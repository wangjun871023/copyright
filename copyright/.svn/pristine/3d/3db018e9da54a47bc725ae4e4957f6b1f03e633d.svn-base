package com.copyright.pojo;

import com.copyright.common.base.ITreeNode;


public class TreeNode implements java.io.Serializable,ITreeNode{

	private String id;
	private String createTime;
	private String updateTime;
	
	private String text;
	private String parentId;
	private boolean leaf;
	private boolean expandable;
	
	public TreeNode() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	@Override
	public Object getNodeID() {
		return getId();
	}

	@Override
	public Object getFatherNodeID() {
		return getParentId();
	}

	@Override
	public boolean getVisited() {
		return false;
	}

	@Override
	public void setVisited(boolean visited) {
	}

	@Override
	public boolean getIsChecked() {
		return false;
	}

	public boolean isExpandable() {
		return expandable;
	}
	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}
	
}