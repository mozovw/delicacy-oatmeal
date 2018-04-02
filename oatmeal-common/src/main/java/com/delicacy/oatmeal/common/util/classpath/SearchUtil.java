package com.delicacy.oatmeal.common.util.classpath;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Hello world!
 *
 */
public class SearchUtil {
	public SearchUtil() {

	}

	/**
	 * @Title: searchMothodFromClass
	 * @Description: TODO
	 * @throws ClassNotFoundException
	 * @return: void
	 */
	public static void searchMothodFromClasses(String mothodName) {
		String s = StringUtils.substringBeforeLast(
				new File("").getAbsolutePath(), "\\");
		Collection<File> listFiles = FileUtils.listFiles(new File(s),
				new String[] { "java" }, true);
		for (File file : listFiles) {
			s = StringUtils.substringAfter(file.getAbsolutePath(),
					"src\\main\\java\\");
			s = StringUtils.substringBefore(StringUtils.replace(s, "\\", "."),
					".java");
			Class<?> forName = null;
			try {
				forName = Class.forName(s);

			} catch (Exception e) {
				// TODO: handle exception
				continue;
			}
			if (forName == null) {
				continue;
			}
			Method[] methods = forName.getMethods();
			boolean b = true;
			for (Method method : methods) {
				if (!method.getName().contains(mothodName)
						|| StringUtils.isBlank(mothodName)) {
					continue;
				}
				
				Type[] genericParameterTypes = method
						.getGenericParameterTypes();
				String returnType = StringUtils.replace(method.getReturnType()
						.toString(), "class ", "");
				String string = "method--> ";
				if (genericParameterTypes.length == 0) {
					string = string + returnType + "  " + method.getName()
							+ "  ()";
					continue;
				}
				string = string + returnType.toString() + "  "
						+ method.getName() + " ( ";
				for (int i = 0; i < genericParameterTypes.length; i++) {
					String type = genericParameterTypes[i].toString();
					type = StringUtils.replace(type, "class ", "");
					if (i == genericParameterTypes.length - 1) {
						string = StringUtils.join(string, type, " )");
						continue;
					}
					string = StringUtils.join(string, type, ", ");
				}
				if (b) {
					System.out.println(forName);
					b = false;
				}
				System.out.println(string);
			}
		}
	}
}
