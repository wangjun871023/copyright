package com.copyright.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JsonParseUtil {
	public static List<Object> parseObject(String jsonStr, Object object,
			String rootKey) {
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		List result = null;
		JsonNode rootNode = null;
		String root = rootKey;
		Object form = null;
		try {
			if (StringUtils.isEmpty(jsonStr) == true || object == null)
				return result;
			rootNode = mapper.readTree(jsonStr);
			// ensure that "last name" isn't "Xmler"; if is, change to "Jsoner"
			if (!StringUtils.isEmpty(root)) {
				JsonNode node = rootNode.path(root);
				int size = 0;
				if (node != null) {
					if (node.isArray()) {
						size = node.size();
						result = new ArrayList(size);
						for (int i = 0; i < size; i++) {
							JsonNode childNode = node.get(i);
							Iterator itr = childNode.fieldNames();
							form = object.getClass().newInstance();
							while (itr.hasNext() == true) {
								String key = (String) itr.next();
								String value = childNode.get(key).asText();

								// if(value!=null){
								// value=value.replaceAll("\"","");
								// System.out.println("value 2:"+value);
								// }
								if (value != null
										&& "NULL".equals(value.toUpperCase()))
									BeanUtils.setProperty(form, key, null);
								else
									BeanUtils.setProperty(form, key, value);
							}
							result.add(form);
						}
					} else {
						Iterator itr = node.fieldNames();
						form = object.getClass().newInstance();
						result = new ArrayList();
						while (itr.hasNext() == true) {
							String key = (String) itr.next();
							String value = node.get(key).asText();

							// if(value!=null){
							// value=value.replaceAll("\"","");
							// }
							if (value != null
									&& "NULL".equals(value.toUpperCase()))
								BeanUtils.setProperty(form, key, null);
							else
								BeanUtils.setProperty(form, key, value);
						}
						result.add(form);
					}
				}
			} else {
				Iterator itr = rootNode.fieldNames();
				form = object.getClass().newInstance();
				result = new ArrayList();
				while (itr.hasNext() == true) {
					String key = (String) itr.next();
					String value = rootNode.get(key).asText();

					if (value != null && "NULL".equals(value.toUpperCase()))
						BeanUtils.setProperty(form, key, "");
					else
						BeanUtils.setProperty(form, key, value);
				}
				result.add(form);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static <T> List<T> getList(String jsonVal, Class<?> clazz) throws JsonParseException, JsonMappingException, IOException {
		TypeFactory t = TypeFactory.defaultInstance();
		ObjectMapper objectMapper = new ObjectMapper();
		// 指定容器结构和类型（这里是ArrayList和clazz）
		List<T> list = objectMapper.readValue(jsonVal,
				t.constructCollectionType(ArrayList.class, clazz));
		// 如果T确定的情况下可以使用下面的方法：
		// List<T> list = objectMapper.readValue(jsonVal, new
		// TypeReference<List<T>>() {});
		return list;
	}
}
