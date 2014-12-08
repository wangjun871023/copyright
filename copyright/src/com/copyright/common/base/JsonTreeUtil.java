package com.copyright.common.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.copyright.util.SysConst;

/**
 * 这个类将一个List<T>拼成json树
 */
public class JsonTreeUtil {
	protected static Log logger = LogFactory.getLog(JsonTreeUtil.class);

   /**
    * 
    * @param list
    * @return
    */
	public static JSONArray createTreeStore(List<? extends BaseForm> list){
		JSONArray result=new JSONArray();
		// map用来保存递归遍历dataList时创建的一个个完整的树节点
		HashMap map = new HashMap<Object, JSONObject>();
		try{
			if(list==null||list.size()<1){
				return result;
			}
			List<ITreeNode> rList = new ArrayList<ITreeNode>();//
			List<ITreeNode> topNodes=new ArrayList<ITreeNode>();//根结点集合
			for (BaseForm obj : list){
				ITreeNode node =(ITreeNode) obj;
				//0代码根结点， 注意 ：四维设计一颗树不只一个根结点，所有根结点都以0
				if(SysConst.SYS_NO_USE_FLAG.equals(node.getFatherNodeID().toString())) {
					topNodes.add(node);
				}
				rList.add(node);
			} 
			for(ITreeNode topNode:topNodes){
				result.add(getChildren(rList,topNode,true,map));
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			logger.error(ex,ex);
		}
		return result;
	}
	/**
	 * 
	 * @param list
	 * @param leafCheck 设置叶子节点是否选中标志，TRUE表示选中
	 * @return
	 */
	public static JSONArray createTreeStore(List<? extends BaseForm> list,boolean leafCheck){
		if(list==null||list.size()<1){
			return new JSONArray();
			}
		HashMap map = new HashMap<Object, JSONObject>();
		List<ITreeNode> rList = new ArrayList<ITreeNode>();//
		List<ITreeNode> topNodes=new ArrayList<ITreeNode>();//根结点集合
		for (BaseForm obj : list){
			ITreeNode node =(ITreeNode) obj;
			//0代码根结点， 注意 ：四维设计一颗树不只一个根结点，所有根结点都以0
			if(SysConst.SYS_NO_USE_FLAG.equals(node.getFatherNodeID().toString())) {
				topNodes.add(node);
			}
			rList.add(node);
		}
		JSONArray arr=new JSONArray();
		for(ITreeNode topNode:topNodes){
			arr.add(getChildren(rList,topNode,leafCheck,map));
		}
		return arr;
	}
	/**
	 * 
	 * @param list
	 * @param title
	 * @return
	 */
	public static JSONArray createTreeStore(List<? extends BaseForm> list,String title){
		if(list==null||list.size()<1){
			list=new ArrayList();
		}
		HashMap map = new HashMap<Object, JSONObject>();
		List<ITreeNode> rList = new ArrayList<ITreeNode>();//
		List<ITreeNode> topNodes=new ArrayList<ITreeNode>();//根结点集合
		for (BaseForm obj : list){
			ITreeNode node =(ITreeNode) obj;
			//0代码根结点， 注意 ：四维设计一颗树不只一个根结点，所有根结点都以0
			if(SysConst.SYS_NO_USE_FLAG.equals(node.getFatherNodeID().toString())) {
				topNodes.add(node);
			}
			rList.add(node);
		}
		JSONArray arr=new JSONArray();
		for(ITreeNode topNode:topNodes){
			arr.add(getChildren(rList,topNode,true,map));
		}
		JSONArray jArr=arr;
		if(!StringUtils.isEmpty(title)){
			jArr=new JSONArray();
			// 人为指定根节点
			JSONObject o = new JSONObject();
			o.put("text", title);
			o.put("id", SysConst.SYS_NO_USE_FLAG);
			o.put("cls", "folder");
			//o.put("checked", true);
			o.put("children", arr);
			jArr.add(o);
		}
		return jArr;
	}
	/**
	 * 
	 * @param list
	 * @param node
	 * @param leafCheck 设置叶子节点是否选中标志，TRUE表示选中
	 * @return
	 */
	private static JSONObject getChildren(List<ITreeNode> list, ITreeNode node,boolean leafCheck,HashMap map) {
		List<ITreeNode> childNodes = findChildren(list, node,map);
		int childNodesSize = childNodes.size();
		JSONObject obj = null;
		// 递归的结束条件为该节点没有子节点
		if (childNodesSize <= 0) {
			// 没有子节点 ，可以回溯
			obj = new JSONObject();
			obj.put("text", node.getText());
			obj.put("id", node.getNodeID());
			//System.out.println(node.getText()+":"+node.getIsChecked());
			//if(leafCheck)obj.put("checked", node.getIsChecked());
			obj.put("leaf", true);
			return obj;
		}
		JSONArray jArray = new JSONArray();
		for (int i = 0; i < childNodesSize; i++) {
			jArray.add(getChildren(list, childNodes.get(i),leafCheck,map));
		}
		obj = new JSONObject();
		obj.put("text", node.getText());
		obj.put("id", node.getNodeID());
		obj.put("children", jArray);
		obj.put("expand", true);
		obj.put("cls", "folder");
	//	obj.put("checked", node.getIsChecked());
		// System.out.println("@"+node.getText()+":"+node.getIsChecked());
		return obj;
	}

	public static List<ITreeNode> findChildren(List<ITreeNode> list, ITreeNode node,HashMap map) {
		List<ITreeNode> objList=new ArrayList<ITreeNode>();
		try {
//			Object id = node.getNodeID();//查找所有父节点ID等于当前节点的NODE
			for (ITreeNode i : list) {
				if(String.valueOf(node.getNodeID()).
						equals(String.valueOf(i.getFatherNodeID()))){
					objList.add(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e,e);
		}
		return objList;
	}

	/**
	 * 这个方法遍历dataList,以递归的方式访问 对于title这个参数需要做一些说明，如果最后生成的树的根节点来自数据库，则不需要title
	 * 如果数据库返回的是一些平级的数据，则需要人为指定一个根节点。
	 * 
	 * @param dataList
	 * @return
	 */
	public static JSONArray createJsonTree(List<ITreeNode> dataList,
			String title, boolean checked) {
		// map用来保存递归遍历dataList时创建的一个个完整的树节点
		HashMap map = new HashMap<Object, JSONObject>();
		// 这是要返回的结果，数组形式，里面只有一个元素，即根节点，子节点信息在内部
		JSONArray result_arr = new JSONArray();
	   try{
			// 为了确保万无一失，一开始对map也clear一下
			map.clear();
			// 这是根节点，要放到result_arr里面去
			JSONObject result_obj = null;
			if (title != null) {
				// 人为指定根节点
				result_obj = new JSONObject();
				result_obj.put("text", title);
				result_obj.put("id", 0);
				result_obj.put("cls", "folder");
				//result_obj.put("checked", false);
			}
			// 判断dataList里面是否有数据
			if (dataList != null) {
				// 遍历dataList,对每个元素调用createTreeNode方法，将创建的JSONObject放入map
				// 最后如果map里面只有一个元素，证明这就是根节点，如果多余一个，则是平级的
				for (ITreeNode node : dataList) {
					JSONObject nodeJson = createTreeNode(node, dataList, checked,map);
					if (nodeJson != null) {
						map.put(node.getNodeID(), nodeJson);
					}
				}
				// 将map中数据放到Collection中便于迭代读取
				Collection<JSONObject> co = map.values();
				// 如果人为指定根节点，则将map中的元素放到根节点下面，否则直接将map中
				// 的元素返回
				JSONArray children = null;
				// 人为指定根节点
				if (result_obj != null) {
					children = new JSONArray();
					for (Iterator<JSONObject> iterator = co.iterator(); iterator
							.hasNext();) {
						children.add(iterator.next());
					}
					result_obj.put("children", children);
					result_arr.add(result_obj);
				} else {
					for (Iterator<JSONObject> iterator = co.iterator(); iterator
							.hasNext();) {
						result_arr.add(iterator.next());
					}
				}
				// 情况静态对象map的数据，避免多次调用时数据混乱
				map.clear();
			}
	   }
	   catch(Exception ex){
		   logger.error(ex,ex);
	   }
		return result_arr;
	}

	/**
	 * 
	 * 对dataList的每一个元素进行遍历，node_tmp读取该元素信息后接着寻找其子节点，直至无子节点
	 * 这时候拼装node_tmp成一个完整的JSONObject并返回
	 */
	private static JSONObject createTreeNode(ITreeNode node,
			List<ITreeNode> dataList, boolean checked,HashMap map) {
		JSONObject node_tmp = null;
		// 判断node是否被访问过
		if (!node.getVisited()) {
			node_tmp = new JSONObject();
			if (checked) {
			//	node_tmp.put("checked", true);
			}
			node_tmp.put("text", node.getText());
			node_tmp.put("id", node.getNodeID());
			//node_tmp.put("checked", false);
			// 寻找子节点
			if (findChildren(node, dataList, node_tmp, checked,map)) {
				// 有子节点，图标用文件夹
				node_tmp.put("cls", "folder");
			}
			// 无子节点，为叶子节点
			else
				node_tmp.put("leaf", true);
			// 节点访问完毕
			node.setVisited(true);
		}
		return node_tmp;
	}

	/**
	 * 寻找node节点在dataList中的所有子节点，此时node节点对应的JSONObject是node_tmp
	 * 而node节点如果要找的子节点已经访问过，则其信息保存在node_final中
	 * 如果未访问过则直接使用代码加到node_tmp的children属性中。
	 * 
	 * @param node
	 * @param dataList
	 * @param node_tmp
	 * @param node_final
	 * @return
	 */
	private static boolean findChildren(ITreeNode node,
			List<ITreeNode> dataList, JSONObject node_tmp, boolean checked,HashMap map) {
		// 判断是否有子节点
		boolean result = false;
		// 存放子节点
		JSONArray children = new JSONArray();
		for (ITreeNode nodeToVisit : dataList) {
			// 判断nodeToVisit是否是node的子节点
			if (nodeToVisit.getFatherNodeID() == node.getNodeID()) {
				result = true;
				// 判断nodeToVisit是否已经访问过
				if (!nodeToVisit.getVisited()) {
					// 未访问过,递归调用
					children
							.add(createTreeNode(nodeToVisit, dataList, checked,map));
				} else {
					// 已经访问过,则说明这个节点对应的JSONObject已经在map中了
					Object id = nodeToVisit.getNodeID();
					children.add(map.get(id));
					map.remove(id);
				}

			}
		}
		if (result) {
			node_tmp.put("children", children);
		}
		return result;
	}

	/**
	 * 这个方法是为了解决List无法直接进行强制类型转换的问题
	 * 
	 * @param list
	 * @return
	 */
	public static List<ITreeNode> entityToTreeNode(List<? extends BaseForm> list) {
		List<ITreeNode> resultList = new ArrayList<ITreeNode>();
		for (BaseForm obj : list) {
			resultList.add((ITreeNode) obj);
		}
		return resultList;
	}
	
	public static boolean checkChildren(List<ITreeNode> list, ITreeNode node,String p) {
		List<ITreeNode> childNodes = findChildren(list, node,null);
		int childNodesSize = childNodes.size();
		JSONObject obj = null;
		// 递归的结束条件为该节点没有子节点
		if (childNodesSize <= 0) {
			return false;
		}
		JSONArray jArray = new JSONArray();
		for (int i = 0; i < childNodesSize; i++) {
			ITreeNode n=childNodes.get(i);
			if(n.getNodeID().toString().equals(p))return true;
			if( checkChildren(list,n,p))return true;
		}
		return false;
	}
}
