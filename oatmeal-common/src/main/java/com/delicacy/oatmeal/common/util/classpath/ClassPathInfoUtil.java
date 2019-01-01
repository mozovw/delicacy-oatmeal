package com.delicacy.oatmeal.common.util.classpath;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;


public class ClassPathInfoUtil {
	public static void main(String[] args) {
		String arg = "com.delicacy.oatmeal.common.util.classpath.ClassPathInfoUtil";
		getMethodNames(arg);
	}

	protected static void getMethodNames(String arg) {
		Map<String, Map<String, List>> classInfo2Map = classInfo2Map(arg);
		System.out.println(classInfo2Map);
		Iterator<Entry<String, Map<String, List>>> iterator = classInfo2Map
				.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Map<String, List>> next = iterator.next();
			Iterator<Entry<String, List>> it = next.getValue().entrySet()
					.iterator();
			while (it.hasNext()) {
				Map.Entry<java.lang.String, java.util.List> entry = (Map.Entry<java.lang.String, java.util.List>) it
						.next();
				String key = entry.getKey();
				//System.out.println(key);
				List value = entry.getValue();
				value.remove(0);
				String string = Arrays.toString(value.toArray());
				string = StringUtils.replaceEach(string, new String[]{"[","]"}, new String[]{"(",")"});
				//System.out.println(string);
				System.out.println(key+string);
			}
		}
	}

	/**
	 * @Title: classInfo2Map
	 * @Description: TODO
	 * @param arg
	 * @return: void
	 */

	public static Map<String, Map<String, List>> classInfo2Map(String arg) {
		// class--多个method--多个params
		Map<String, Map<String, List>> mm = new HashMap<>();
		Map<String, List> m = new HashMap<String, List>();
		Class<?> forName = null;
		String className = null;
		try {
			className = arg;
			forName = Class.forName(className);
		} catch (ClassNotFoundException e) {
		}
		Method[] methods = forName.getMethods();
		for (Method method : methods) {
			//
			String mothod = method.getName();
			int indexOfAny = StringUtils.indexOfAny(mothod, "hashCode",
					"getClass", "equals", "toString", "notify", "notifyAll",
					"wait", "main");
			if (indexOfAny != -1) {
				continue;
			}
			// add returnType to list
			String returnType = replaceTypeStr(method.getReturnType()
					.toString());
			//returnType = returnType.getClass().getSimpleName();
			if (StringUtils.isEmpty(returnType))
				returnType = "";
			List<String> list = new ArrayList<String>();
			list.add(returnType);
			// add parameterType to list
			Type[] genericParameterTypes = method.getGenericParameterTypes();
			if (genericParameterTypes.length == 0) {
				m.put(mothod, list);
				continue;
			}
			for (int i = 0; i < genericParameterTypes.length; i++) {
				String parameterType = genericParameterTypes[i].toString();
				parameterType = replaceTypeStr(parameterType);
				try {
					parameterType = Class.forName(parameterType).getSimpleName();
				} catch (ClassNotFoundException e) {
				}
				list.add(parameterType);
				m.put(mothod, list);
			}
		}
		//
		mm.put(className, m);
		return mm;
	}

	/**
	 * @Title: replaceTypeStr
	 * @Description: TODO
	 * @param type
	 * @return
	 * @return: String
	 */

	private static String replaceTypeStr(String type) {
		if (type.contains("<")) {
			type = type.substring(0,type.indexOf("<"));
		}
		return StringUtils.replaceEach(type, new String[] { "interface ",
				"class " }, new String[] { "", "" });
	}

	
}
