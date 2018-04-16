package com.delicacy.oatmeal.java.lamdbatest;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 常用操作类函数是接口
 *
 * @author zyt
 * @create 2018-04-15 17:23
 **/
public class LambdaUtil {

    public static <T> void save(T t, Predicate<T> predicate, Consumer<T> consumer1 ,Consumer<T> consumer2) {
        if (predicate.test(t)) consumer1.accept(t);
        else  consumer2.accept(t);
    }

    //如果存在，则更改，否则插入
    public static <T> void save(T t,  Consumer<T> consumer1 ,Consumer<T> consumer2) {
        save(t, (t1)-> Objects.isNull(t1),consumer1,consumer2);
    }

    public static <T> void add(T t, Predicate<T> predicate, Consumer<T> consumer) {
        if (!predicate.test(t)) consumer.accept(t);
    }

    //如果不存在，则新增
    public static <T> void add(T t,  Consumer<T> consumer) {
        add(t, (t1)-> Objects.isNull(t1),consumer);

    }

    //如果存在，则更改
    public static <T> void update(T t, Predicate<T> predicate, Consumer<T> consumer) {
        if (predicate.test(t)) consumer.accept(t);
    }

    //如果存在，则更改
    public static <T> void update(T t,  Consumer<T> consumer) {
        update(t, (t1)-> !Objects.isNull(t1),consumer);
    }


    public static <T,R> R save(T t, Predicate<T> predicate, Function<T,R>  function1, Function<T,R>  function2) {
        if (predicate.test(t)) return function1.apply(t);
        else  return function2.apply(t);
    }

    //如果存在，则更改，否则插入
    public static <T,R> R save(T t, Function<T,R>  function1, Function<T,R>  function2) {
        return save(t,(t1)->Objects.isNull(t),function1,function2);
    }

    public static <T,R> R saveList(Collection<T> ts, Predicate<Collection<T>> predicate, Function<Collection<T>,R>  function1,Function<Collection<T>,R>  function2) {
        if (predicate.test(ts)) return function1.apply(ts);
        else  return function2.apply(ts);
    }

    //如果存在，则更改，否则插入
    public static <T,R> R saveList(Collection<T> ts, Function<Collection<T>,R>  function1,Function<Collection<T>,R>  function2) {
        return saveList(ts,(ts1)-> isEmply(ts1),function1,function2);
    }

    private static boolean isEmply(Collection collection){
        return collection == null||collection.isEmpty();
    }


}
