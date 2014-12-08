package com.copyright.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ReadRequestPayloadUtil {
	public static  <T> List<T> getEntityListByReadRequestPayload(HttpServletRequest request,Class<?> clazz) throws Exception{
		List<T> list ;
		String jsonStr = readRequestPayload(request);
		if (jsonStr.charAt(0)!='['){
			list = (List<T>) JsonParseUtil.parseObject(jsonStr,clazz.newInstance(), null);
		}else{
			list = JsonParseUtil.getList(jsonStr,clazz);
		}
		return list;
	}

	public static String readRequestPayload(HttpServletRequest request) throws IOException{
		BufferedReader br = request.getReader();
		String line = "";
		StringBuilder builder = new StringBuilder();
		while((line=br.readLine())!=null){
			builder.append(line);
		}
		return builder.toString();
	}
}
