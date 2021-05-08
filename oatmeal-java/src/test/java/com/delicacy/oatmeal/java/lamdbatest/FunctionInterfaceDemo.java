package com.delicacy.oatmeal.java.lamdbatest;

import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * function processor
 * 方法参数以往都是类，变量属性，但是并没有当函数作为入参，而函数入参是有必要的，
 * 1、对于有些方法入参其实只需要一个函数操作后的结果，我不需要知道什么参数类型啊
 * 2、大大减少了很多不必要的代码，什么创建匿名类啊
 * 3、但是上手需要一段时间，改变一个编程习惯和思维需要一段时间
 * @author zyt
 * @create 2018-04-15 16:40
 **/
public class FunctionInterfaceDemo {

    public static void main(String[] args) {
        Person person = new Person();
        person.setAge(11);
        person.setName("consumer");
        person.selfintroduc(e->{return "我叫"+e.getName()+",我今年"+e.getAge(); });



        File f = new File(".");
        File[] files = f.listFiles((File dir, String name) -> {
            if (name.endsWith(".txt")) {
                return true;
            }
            return false;
        });

        ArrayList<Object> objects = new ArrayList<>();
        objects.stream().forEach(e-> System.out.println(e));


        List<Object> objects1 = Arrays.asList(new Object(), new Object(), new Object(), new Object(), new Object(), new Object());
    }

}

@Data
class Person{
    private String name;
    private int age;

    public void selfintroduc(Action<Person> action){
        System.out.println(action.selfintroduce(this));
    }

}

//声明函数接口的注解
@FunctionalInterface
interface Action<T> {
    String selfintroduce(T t);
}