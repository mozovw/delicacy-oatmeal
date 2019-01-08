package com.delicacy.oatmeal.common.util.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.reflections.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 比较2个对象的值的不同的情况
 * <p>
 * 注意：对声明值比较类为基本类型的但传入非基本类型的话不保证程序和数据的正确性。
 * <p>
 * 目前支持的比较类型有：
 * 1、普通dto(不包含复杂对象)的比较
 * 可用的方法有：
 * {@link CompareDifferenceUtil#isDifference(Object, Object)}
 * {@link CompareDifferenceUtil#isDifferenceSelective(Object, Object)}
 * {@link CompareDifferenceUtil#compare(Object, Object)}
 * {@link CompareDifferenceUtil#compareSelective(Object, Object)}
 * {@link CompareDifferenceUtil#getDiffernceList(Object, Object)}
 * {@link CompareDifferenceUtil#getDiffernceListSelective(Object, Object)}
 * 2、普通基本类型的集合的比较
 * 可用的方法有：
 * {@link CompareDifferenceUtil#isDifference(Collection, Collection)}
 * {@link CompareDifferenceUtil#compareCollection(Collection, Collection)}
 * {@link CompareDifferenceUtil#compareCollectionSelective(Collection, Collection)}
 * 4、主从一对一的比较
 * 可用的方法有：
 * {@link CompareDifferenceUtil#compareMainAndDetail(Object, Object, String, Object, Object)}
 * {@link CompareDifferenceUtil#compareMainAndDetailSelective(Object, Object, String, Object, Object)}
 */
public class CompareDifferenceUtil {
    private static final String ADD = "add";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final Set<String> ignoreFileds = new HashSet<>(5);

    private static final Map<String, Set<Field>> fieldCache = new HashMap<>();
    private static final Map<String, Set<Method>> methodCache = new HashMap<>();

    static {
        ignoreFileds.add("serialVersionUID");
        ignoreFileds.add("createBy");
        ignoreFileds.add("createTime");
        ignoreFileds.add("updateBy");
        ignoreFileds.add("updateTime");
    }

    /**
     * 比较2个对象是否有差异
     *
     * @param oldObj
     * @param newObj
     * @return
     */
    public static boolean isDifference(Object oldObj, Object newObj) {
        return !getDiffernceList(oldObj, newObj, true, false).isEmpty();
    }

    /**
     * 比较2个对象是否有差异（新增中的属性为null的话跳过比较该属性）
     *
     * @param oldObj
     * @param newObj
     * @return
     */
    public static boolean isDifferenceSelective(Object oldObj, Object newObj) {
        return !getDiffernceList(oldObj, newObj, true, true).isEmpty();
    }

    /**
     * 比较2个集合是否有差异（基本类型）
     *
     * @param oldCollection
     * @param newCollection
     * @return
     */
    public static <T> boolean isDifference(Collection<T> oldCollection, Collection<T> newCollection) {
        return !compareCollectionList(oldCollection, newCollection).isEmpty();
    }

    /**
     * 得到变化的列的数据
     *
     * @param oldObj
     * @param newObj
     * @return
     */
    public static List<DifferenceWapper> getDiffernceList(Object oldObj, Object newObj) {
        return getDiffernceList(oldObj, newObj, false, false);
    }

    /**
     * 得到变化的列的数据（新增中的属性为null的话跳过比较该属性）
     *
     * @param oldObj
     * @param newObj
     * @return
     */
    public static List<DifferenceWapper> getDiffernceListSelective(Object oldObj, Object newObj) {
        return getDiffernceList(oldObj, newObj, false, true);
    }

    /**
     * 比较主从对象的复杂类型的数据（主从一对一关系）
     *
     * @param oldMainObj     主对象的老对象
     * @param newMainObj     助对象的新对象
     * @param detailAttrName 主对象中detail的属性名称
     * @param oldDetailObj   明细对象的老对象(复杂类型的对象，但非集合)
     * @param newDetailObj   明细对象的新对象(复杂类型的对象，但非集合)
     * @return
     */
    public static String compareMainAndDetail(Object oldMainObj, Object newMainObj, String detailAttrName, Object oldDetailObj, Object newDetailObj) {
        return aggregateDataAndConvertToJson(getDiffernceList(oldMainObj, newMainObj), detailAttrName, getDiffernceList(oldDetailObj, newDetailObj));
    }

    /**
     * 比较主从对象的复杂类型的数据（主从一对一关系）
     *
     * @param oldMainObj     主对象的老对象
     * @param newMainObj     助对象的新对象
     * @param detailAttrName 主对象中detail的属性名称
     * @param oldDetailObj   明细对象的老对象(复杂类型的对象，但非集合)
     * @param newDetailObj   明细对象的新对象(复杂类型的对象，但非集合)
     * @return
     */
    public static String compareMainAndDetailSelective(Object oldMainObj, Object newMainObj, String detailAttrName, Object oldDetailObj, Object newDetailObj) {
        return aggregateDataAndConvertToJson(getDiffernceListSelective(oldMainObj, newMainObj), detailAttrName, getDiffernceListSelective(oldDetailObj, newDetailObj));
    }

    /**
     * 得到2个相同对象的相同字段的不同的值
     *
     * @param oldObj 变更前对象，不能为null
     * @param newObj 变更后对象，不能为null
     * @return
     */
    public static String compare(Object oldObj, Object newObj) {
        List<DifferenceWapper> result = getDiffernceList(oldObj, newObj);
        if (!result.isEmpty()) {
            return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
        }
        return null;
    }

    /**
     * 得到2个相同对象的相同字段的不同的值，newObj中有属性为null，就跳过这个属性
     *
     * @param oldObj 变更前对象，不能为null
     * @param newObj 变更后对象，不能为null
     * @return
     */
    public static String compareSelective(Object oldObj, Object newObj) {
        List<DifferenceWapper> result = getDiffernceListSelective(oldObj, newObj);
        if (!result.isEmpty()) {
            return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
        }
        return null;
    }


    /**
     * 比较2个字符串集合的差异(只支持基本类型)
     *
     * @param <T>
     * @param oldCollection
     * @param newCollection
     * @return
     */
    public static <T> String compareCollection(Collection<T> oldCollection, Collection<T> newCollection) {
        List<DifferenceWapper> result = compareCollectionList(oldCollection, newCollection);
        if (result.isEmpty()) {
            return null;
        }
        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 比较2个字符串集合的差异(只支持基本类型),如果newCollection为null，则忽略比较
     *
     * @param <T>
     * @param oldCollection
     * @param newCollection
     * @return
     */
    public static <T> String compareCollectionSelective(Collection<T> oldCollection, Collection<T> newCollection) {
        List<DifferenceWapper> result = compareCollectionListSelective(oldCollection, newCollection);
        if (result.isEmpty()) {
            return null;
        }
        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    /**
     * @param oldObj
     * @param newObj
     * @param onlyCheck 是否仅校验（遇到不同则立刻返回）
     * @param selective true代表如果newObj的属性的值为null则跳过该属性比较
     * @return
     */
    private static List<DifferenceWapper> getDiffernceList(Object oldObj, Object newObj, boolean onlyCheck, boolean selective) {
        List<DifferenceWapper> result = new ArrayList<>();

        // 是否是创建类型的比较
        boolean isCreate = false;
        if (oldObj == null) {
            isCreate = true;
        } else {
            if (null == newObj) {
                throw new RuntimeException("更改后的对象不能为null");
            }
        }
        if (!isCreate && !newObj.getClass().equals(oldObj.getClass())) {
            throw new RuntimeException("两个需要比较的对象必须为同一类型");
        }
        // 同一个对象就没必要对比了
        if (oldObj == newObj) {
            return result;
        }

        Set<Field> fields = getField(newObj.getClass());
        Set<Method> methods = getMethod(newObj.getClass());

        if (null != fields && !fields.isEmpty()) {
            String methodName = null;
            String fieldName = null;
            for (Field field : fields) {
                if (ignoreFileds.contains(field.getName())) {
                    continue;
                }
                fieldName = field.getName();
                methodName = "get".concat(fieldName.substring(0, 1).toUpperCase()).concat(fieldName.substring(1));

                Method method = filterMethod(methods, methodName);
                if (null == method) {
                    // 是小boolean类型则需要特殊处理
                    if ("boolean".equals(field.getType().getTypeName())) {
                        if (fieldName.startsWith("is")) {
                            // 如果第三位是大写
                            boolean upper = isUpperString(fieldName.substring(2, 3));
                            if (upper) {
                                methodName = fieldName;
                            } else {
                                methodName = "is".concat(fieldName.substring(0, 1).toUpperCase()).concat(fieldName.substring(1));
                            }
                        } else {
                            methodName = "is".concat(fieldName.substring(0, 1).toUpperCase()).concat(fieldName.substring(1));
                        }
                        method = filterMethod(methods, methodName);
                    }
                    if (null == method) {
                        continue;
                    }
                }
                try {
                    Object oldValue = null;
                    if (!isCreate) {
                        oldValue = method.invoke(oldObj);
                    }
                    Object newValue = method.invoke(newObj);
                    DifferenceWapper differenceWapper = new DifferenceWapper();
                    Difference difference = new Difference();
                    if (null == oldValue) {
                        if (null != newValue) {
                            difference.setNewValue(newValue);
                            difference.setOldValue(oldValue);
                            differenceWapper.setColumn(fieldName);
                            differenceWapper.setDifference(difference);
                            result.add(differenceWapper);
                        }
                    } else {
                        if (null != newValue) {
                            // 处理0.0和0的不同
                            if (newValue instanceof BigDecimal) {
                                BigDecimal newValue1 = (BigDecimal) newValue;
                                BigDecimal oldValue1 = (BigDecimal) oldValue;
                                if (newValue1.compareTo(oldValue1) != 0) {
                                    difference.setNewValue(newValue);
                                    difference.setOldValue(oldValue);
                                    differenceWapper.setColumn(fieldName);
                                    differenceWapper.setDifference(difference);
                                    result.add(differenceWapper);
                                }
                            } else if (!oldValue.equals(newValue)) {
                                difference.setNewValue(newValue);
                                difference.setOldValue(oldValue);
                                differenceWapper.setColumn(fieldName);
                                differenceWapper.setDifference(difference);
                                result.add(differenceWapper);
                            }
                        } else {
                            if (!selective) {
                                difference.setNewValue(newValue);
                                difference.setOldValue(oldValue);
                                differenceWapper.setColumn(fieldName);
                                differenceWapper.setDifference(difference);
                                result.add(differenceWapper);
                            }
                        }
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                /**
                 * 如果仅仅校验是否不同，则提前返回
                 */
                if (onlyCheck && !result.isEmpty()) {
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * 获取类的所有字段
     *
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    private static Set<Field> getField(Class<?> type) {
        String className = type.getName();
        if (fieldCache.get(className) == null || fieldCache.get(className).isEmpty()) {
            synchronized (className) {
                if (fieldCache.get(className) == null || fieldCache.get(className).isEmpty()) {
                    fieldCache.put(className, ReflectionUtils.getAllFields(type));
                }
            }
        }
        return fieldCache.get(className);
    }

    /**
     * 获取类的所有方法
     *
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    private static Set<Method> getMethod(Class<?> type) {
        String className = type.getName();
        if (methodCache.get(className) == null || methodCache.get(className).isEmpty()) {
            synchronized (className) {
                if (methodCache.get(className) == null || methodCache.get(className).isEmpty()) {
                    methodCache.put(className, ReflectionUtils.getAllMethods(type));
                }
            }
        }
        return methodCache.get(className);
    }

    /**
     * 比较2个字符串集合的差异(只支持基本类型)
     *
     * @param <T>
     * @param oldCollection
     * @param newCollection
     * @return
     */
    private static <T> List<DifferenceWapper> compareCollectionList(Collection<T> oldCollection, Collection<T> newCollection) {
        String flag = UPDATE;
        if (null == oldCollection || oldCollection.isEmpty()) {
            if (null == newCollection || newCollection.isEmpty()) {
                throw new RuntimeException("两个需要比较的集合不能都为空");
            }
            flag = ADD;
        } else {
            if (null == newCollection || newCollection.isEmpty()) {
                flag = DELETE;
            }
        }

        if (ADD.equals(flag)) {
            return compareCollectionAdd(newCollection);
        } else if (DELETE.equals(flag)) {
            return compareCollectionDelete(oldCollection);
        } else {
            return compareCollectionUpdate(oldCollection, newCollection);
        }
    }

    /**
     * 比较2个字符串集合的差异(只支持基本类型)，如果newCollection为null，则忽略比较
     *
     * @param <T>
     * @param oldCollection
     * @param newCollection
     * @return
     */
    private static <T> List<DifferenceWapper> compareCollectionListSelective(Collection<T> oldCollection, Collection<T> newCollection) {
        String flag = UPDATE;
        if (null == oldCollection || oldCollection.isEmpty()) {
            if (null == newCollection || newCollection.isEmpty()) {
                return new ArrayList<>();
            }
            flag = ADD;
        } else {
            if (null == newCollection || newCollection.isEmpty()) {
                return new ArrayList<>();
            }
        }

        if (ADD.equals(flag)) {
            return compareCollectionAdd(newCollection);
        } else if (DELETE.equals(flag)) {
            return compareCollectionDelete(oldCollection);
        } else {
            return compareCollectionUpdate(oldCollection, newCollection);
        }
    }

    /**
     * 新增
     *
     * @param newCollection
     * @return
     */
    private static <T> List<DifferenceWapper> compareCollectionAdd(Collection<T> newCollection) {
        List<DifferenceWapper> result = new ArrayList<>(1);
        DifferenceWapper diff = new DifferenceWapper();
        Difference difference = new Difference();
        difference.setNewValue(newCollection);
        diff.setColumn("add");
        diff.setDifference(difference);
        result.add(diff);
        return result;
    }

    /**
     * 删除
     *
     * @param oldCollection
     * @return
     */
    private static <T> List<DifferenceWapper> compareCollectionDelete(Collection<T> oldCollection) {
        List<DifferenceWapper> result = new ArrayList<>(1);
        DifferenceWapper diff = new DifferenceWapper();
        Difference difference = new Difference();
        difference.setOldValue(oldCollection);
        diff.setColumn("delete");
        diff.setDifference(difference);
        result.add(diff);
        return result;
    }

    /**
     * 更新
     *
     * @param oldCollection
     * @param newCollection
     * @return
     */
    private static <T> List<DifferenceWapper> compareCollectionUpdate(Collection<T> oldCollection, Collection<T> newCollection) {
        List<DifferenceWapper> ret = new ArrayList<>();

        Set<T> result = new HashSet<>();
        Set<T> oldSet = new HashSet<>();
        oldSet.addAll(oldCollection);
        Set<T> newSet = new HashSet<>();
        newSet.addAll(newCollection);

        //取交集
        result.addAll(oldCollection);
        result.retainAll(newCollection);

        //没有变更
        if (result.size() == newSet.size() && result.size() == oldSet.size()) {
            return ret;
        }

        //被删除的
        //取差集
        oldSet.removeAll(result);

        //新增的
        //取差集
        newSet.removeAll(result);

        if (!oldSet.isEmpty()) {
            DifferenceWapper diff = new DifferenceWapper();
            Difference difference = new Difference();
            difference.setOldValue(oldSet);
            diff.setColumn("delete");
            diff.setDifference(difference);
            ret.add(diff);
        }
        if (!newSet.isEmpty()) {
            DifferenceWapper diff = new DifferenceWapper();
            Difference difference = new Difference();
            difference.setNewValue(newSet);
            diff.setColumn("add");
            diff.setDifference(difference);
            ret.add(diff);
        }

        return ret;
    }

    /**
     * 过滤方法
     *
     * @param methods
     * @param methodName
     * @return
     */
    private static Method filterMethod(Set<Method> methods, String methodName) {
        if (null != methods && !methods.isEmpty()) {
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    return method;
                }
            }
        }
        return null;
    }

    /**
     * 匹配是否是大写字母
     *
     * @param str
     * @return
     */
    private static boolean isUpperString(String str) {
        Pattern p = Pattern.compile("[A-Z]");
        Matcher matcher = p.matcher(str);
        return matcher.matches();
    }

    /**
     * 聚合主和明细数据的变更记录
     *
     * @param main
     * @param attrName 显示在主对象里的属性
     * @param detail   明细对象的属性差异的list
     */
    private static List<DifferenceWapper> aggregateData(List<DifferenceWapper> main, String attrName, List<DifferenceWapper> detail) {
        if (null != detail && !detail.isEmpty()) {
            if (null == main || main.isEmpty()) {
                main = new ArrayList<>(1);
            }
            DifferenceWapper differenceWapper = new DifferenceWapper();
            Difference difference = new Difference();
            difference.setNewValue(detail);
            differenceWapper.setDifference(difference);
            differenceWapper.setColumn(attrName);
            main.add(differenceWapper);
        }
        return main;
    }

    /**
     * 聚合主和明细数据的变更记录并转换成json
     *
     * @param main
     * @param attrName 显示在主对象里的属性
     * @param detail   明细对象的属性差异的list
     */
    private static String aggregateDataAndConvertToJson(List<DifferenceWapper> main, String attrName, List<DifferenceWapper> detail) {
        List<DifferenceWapper> result = aggregateData(main, attrName, detail);
        if (null != result && !result.isEmpty()) {
            return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
        }
        return null;
    }


    static class DifferenceWapper implements Serializable {
        String column;
        Difference difference;

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public Difference getDifference() {
            return difference;
        }

        public void setDifference(Difference difference) {
            this.difference = difference;
        }
    }


    static class Difference implements Serializable {

        private Object oldValue;
        private Object newValue;

        public Object getOldValue() {
            return oldValue;
        }

        public void setOldValue(Object oldValue) {
            this.oldValue = oldValue;
        }

        public Object getNewValue() {
            return newValue;
        }

        public void setNewValue(Object newValue) {
            this.newValue = newValue;
        }
    }

    private static Boolean containsAnyForString(String s,String ... ss){
        if (ss.length==0){
            return false;
        }
        for (String v:ss){
            if (v.equals(s)){
                return true;
            }
        }
        return false;
    }

    public static  <T> String compare(T t1,T t2,String... property){
        Field[] fields = t1.getClass().getDeclaredFields();T o = null;
        try {
            o = (T)t2.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                if(containsAnyForString(f.getName(),property)){
                    continue;
                }
                Object t = f.get(t1);
                if (t!=null){
                    Object tt = f.get(t2);
                    f.set(o,tt);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        String s = CompareDifferenceUtil.compareSelective(t1,o);
        return s;
    }

    public static void main(String[] args) {
        User user = new User();
        user.setAge(1);
        user.setName("a");
        user.setCreateTime(new Date());

        User user2 = new User();
        user2.setAge(2);
        user2.setCreateTime(new Date());

        String compare = CompareDifferenceUtil.compare(user, user2);
        System.out.println(compare);

        compare = CompareDifferenceUtil.compareSelective(user, user2);
        System.out.println(compare);

        compare = CompareDifferenceUtil.compare(user, user2, "");
        System.out.println(compare);


    }

    static class User{
        private String name;
        private int age;
        private Date createTime;

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}