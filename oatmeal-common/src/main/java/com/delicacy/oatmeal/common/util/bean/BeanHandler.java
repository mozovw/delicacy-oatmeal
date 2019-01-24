package com.delicacy.oatmeal.common.util.bean;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * bean对象的处理
 */
public class BeanHandler {
    /**
     * 去掉bean中所有属性为字符串的前后空格
     *
     * @param bean
     * @throws Exception
     */
    public static void beanAttributeValueTrim(Object bean)  {
        if (bean != null) {
            //获取所有的字段包括public,private,protected,private
            Field[] fields = bean.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                if (f.getType().getName().equals("java.lang.String")) {
                    String key = f.getName();
                    //获取字段名
                    Object value = null;
                    try {
                        value = getFieldValue(bean, key);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (value == null) continue;
                    try {
                        setFieldValue(bean, key, value.toString().trim());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 选择性复制（from对象有属性即覆盖to对象，目的为了将null复制给to对象）
     * @param from
     * @param to
     * @param ignoreString //忽略的字段
     * @param <T>
     */
    public static <T> void optCopy(T from, T to, String... ignoreString) {
        Assert.assertNotNull(from);
        Assert.assertNotNull(to);
        Field[] fields = from.getClass().getDeclaredFields();
        Field[] toFields = to.getClass()
                .getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                if(StringUtils.equalsAnyIgnoreCase(f.getName(),ignoreString)){
                    continue;
                }
                for (Field ff : toFields) {
                    ff.setAccessible(true);
                    if (ff.getName().equals(f.getName())){
                        Object t = f.get(from);
                        ff.set(to,t);
                        break;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 利用反射通过get方法获取bean中字段fieldName的值
     *
     * @param bean
     * @param fieldName
     * @return
     * @throws Exception
     */
    private static Object getFieldValue(Object bean, String fieldName)
            throws Exception {
        StringBuffer result = new StringBuffer();
        String methodName = result.append("get")
                .append(fieldName.substring(0, 1).toUpperCase())
                .append(fieldName.substring(1)).toString();

        Object rObject = null;
        Method method = null;

        @SuppressWarnings("rawtypes")
        Class[] classArr = new Class[0];
        method = bean.getClass().getMethod(methodName, classArr);
        rObject = method.invoke(bean, new Object[0]);

        return rObject;
    }

    /**
     * 利用发射调用bean.set方法将value设置到字段
     *
     * @param bean
     * @param fieldName
     * @param value
     * @throws Exception
     */
    private static void setFieldValue(Object bean, String fieldName, Object value)
            throws Exception {
        StringBuffer result = new StringBuffer();
        String methodName = result.append("set")
                .append(fieldName.substring(0, 1).toUpperCase())
                .append(fieldName.substring(1)).toString();

        //利用反射调用bean.set方法将value设置到字段
        Class[] classArr = new Class[1];
        classArr[0] = "java.lang.String".getClass();
        Method method = bean.getClass().getMethod(methodName, classArr);
        method.invoke(bean, value);
    }
}
