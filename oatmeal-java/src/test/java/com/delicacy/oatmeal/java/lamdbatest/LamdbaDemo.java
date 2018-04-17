package com.delicacy.oatmeal.java.lamdbatest;


import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Lamdbas java8 函数式开发简单汇总
 */
public class LamdbaDemo {

    /**
     * 测试排序
     */
    @Test
    public void testSort() {

        List<People> peoples = new ArrayList<>();

        // 先通过name比较,在通过age比较
        Collections.sort(peoples, People.BY_LAST_AND_AGE);

        // 先通过name比较,在通过age比较
        Collections.sort(peoples, People::compareLastAndAge);

        // 先通过name比较,在通过age比较
        Collections.sort(peoples, People.BY_LAST.thenComparing(People.BY_AGE));

        // 先通过name比较,在通过age比较
        Collections.sort(peoples, Comparator.comparing(People::getName).thenComparing(People::getAge));

    }

    /**
     * 属性提取
     */
    @Test
    public void testFilter() {

        List<People> peoples = new ArrayList<>();
        peoples.add(new People(10, "swx", "1"));
        peoples.add(new People(2, "Brown", "1"));
        peoples.add(new People(3, "sadffsa", "2"));
        peoples.add(new People(1, "Brown", "3"));
        peoples.add(new People(5, "ccc", "1"));
        peoples.add(new People(6, "Brown", "2"));
        peoples.add(new People(7, "Brown", "3"));


        // 先定义两个断言(条件)
        Predicate<People> drinkingAge = (it) -> it.getAge() >= 2;
        Predicate<People> brown = (it) -> it.getName().equals("Brown");

        peoples.stream().filter(drinkingAge.and(brown))
                .forEach((it) -> System.out.println("Have a beer, " + it.getAge() + ":" + it.getName()));
        peoples.stream().filter(k -> k.getName().equals("Brown") && k.getAge() >= 2 && k.getAge() <= 7)
                .forEach(t -> System.out.println(+t.getAge() + ":" + t.getName()));

        // 提取age
        IntStream ages = peoples.stream().mapToInt(k -> k.getAge());
        IntStream ages2 = peoples.stream().mapToInt(People::getAge);

        // 取最大值
        OptionalInt max = ages2.reduce(Math::max);
        System.out.println(max.getAsInt());

        // allMatch:是否所有满足
        System.out.println(ages2.allMatch(item -> item < 100));

        // anyMatch:是否满足之一
        System.out.println(ages2.anyMatch(item -> item < 100));

        // noneMatch:是否都不
        System.out.println(ages2.noneMatch(item -> item < 100));


        ages.boxed().collect(Collectors.toList()).forEach(k -> System.out.println(k));


        // 提取name
        List<String> names = peoples.stream().map(People::getName).collect(Collectors.toList());
        System.out.println(names);
    }

    /**
     * 获取 最大值 最小值 等各种操作做
     */
    @Test
    public void testMin() {
        List<People> peoples = new ArrayList<>();
        peoples.add(new People(10, "swx", "1"));
        peoples.add(new People(2, "Brown", "1"));
        peoples.add(new People(3, "sadffsa", "2"));
        peoples.add(new People(1, "Brown", "3"));
        peoples.add(new People(5, "ccc", "1"));
        peoples.add(new People(6, "Brown", "2"));
        peoples.add(new People(7, "Brown", "3"));

        // 方法一
        People people = peoples.stream().max((o1, o2) -> o1.getAge().compareTo(o2.getAge())).get();
        System.out.println(people.getAge());

        // 1.取最大值、最小值
        Function<People, Integer> getLevel = p -> p.getAge();
        Comparator<People> comparator = Comparator.comparing(People::getAge);
        People max = peoples.stream().collect(Collectors.maxBy(comparator)).get();
        People min = peoples.stream().collect(Collectors.minBy(comparator)).get();

        // 取最大值  或者
        OptionalInt max2 = peoples.stream().mapToInt(k -> k.getAge()).reduce(Math::max);
        System.out.println(max.getAge());

        // 求和
        int sumq = peoples.stream().mapToInt(k -> k.getAge()).reduce((sum, age) -> sum + age).getAsInt();
        System.out.println("sumq:" + sumq);

        // 2.获得平均值
        ToIntFunction<People> getAverage = p -> p.getAge();
        int avg = peoples.stream().collect(Collectors.averagingInt(getAverage)).intValue();
        System.out.println(avg);

        // 3.字符串 [swx/Brown/sadffsa/Brown/ccc/Brown/Brown]
        String str = peoples.stream().map(People::getName).collect(Collectors.joining("/", "[", "]")).toString();
        System.out.println(str);

        // 4.分組
        Function<People, String> country = p -> p.getGroup();
        Map<String, List<People>> result = peoples.stream().collect(Collectors.groupingBy(country));
        result.forEach((k, v) -> {
            System.out.println(k + ":");
            v.forEach(m -> System.out.println(m.getGroup() + " " + m.getName()));
            System.out.println("----");
        });


        // 5.多线程处理集合
        peoples.parallelStream().filter((it) -> it.getAge() >= 1)
                .forEach((it) -> System.out.println("Have a beer " + it.getName() + "   " + Thread.currentThread()));

    }


}


class People {

    private Integer age;
    private String name;
    private String group;

    public People(Integer age, String name, String group) {
        this.age = age;
        this.name = name;
        this.group = group;
    }

    /**
     * 定义排序器1
     */
    public static final Comparator<People> BY_LAST_AND_AGE = (p1, p2) -> {
        if (p1.name.equals(p2.name))
            return p1.age - p2.age;
        else
            return p1.name.compareTo(p2.name);
    };

    public Integer getAge() {
        return age;
    }

    /**
     * 定义排序器2
     */
    public static int compareLastAndAge(People p1, People p2) {
        if (p1.name.equals(p2.name))
            return p1.age - p2.age;
        else
            return p1.name.compareTo(p2.name);
    }

    /**
     * 分解排序
     */
    public static final Comparator<People> BY_FIRST = (lhs, rhs) -> lhs.name.compareTo(rhs.name);
    public static final Comparator<People> BY_LAST = (lhs, rhs) -> lhs.name.compareTo(rhs.name);
    public static final Comparator<People> BY_AGE = (lhs, rhs) -> lhs.age - rhs.age;

    /**
     * 以上等效于下面
     */
    public static final Comparator<People> BY_FIRST1 = Comparator.comparing(People::getName);
    public static final Comparator<People> BY_LAST2 = Comparator.comparing(People::getName);
    public static final Comparator<People> BY_AGE3 = Comparator.comparing(People::getAge);


    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}