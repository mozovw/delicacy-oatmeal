package com.delicacy.oatmeal.test.exam.parallelstream;


import com.delicacy.oatmeal.test.exam.atomic.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yutao
 * @create 2018-08-08 10:47
 **/
public class ParalleStreamTest {



    private static int maxnum = 10000000;




    public static void main(String[] args) {

        IntStream.range(0,3).forEach(value -> todo());

    }


    private static void todo(){

         List<Integer> list1 = new ArrayList<>(maxnum);
         List<Integer> list2 = new ArrayList<>(maxnum);
         List<Integer> list3 = new ArrayList<>(maxnum);
         List<Integer> list4 = new ArrayList<>(maxnum);
         Lock lock = new ReentrantLock();


        long stopwatch = stopwatch(IntStream.range(0, maxnum), t -> t.forEach(list1::add));
        System.out.println("串行执行的大小：" + list1.size()+",时间："+stopwatch);

        long stopwatch1 = stopwatch(IntStream.range(0, maxnum), t -> t.parallel().forEach(list2::add));
        System.out.println("并行执行的大小：" + list2.size()+",时间："+stopwatch1);

        long stopwatch2 = stopwatch(IntStream.range(0, maxnum), t -> t.parallel().forEach(i -> {
            lock.lock();
            try {
                list3.add(i);
            } finally {
                lock.unlock();
            }
        }));
        System.out.println("加锁lock并行执行的大小：" + list3.size()+",时间："+stopwatch2);

        long stopwatch3 = stopwatch(IntStream.range(0, maxnum), t -> t.parallel().forEach(i->{
            synchronized (list4){
                list4.add(i);
            }
        }));
        System.out.println("加锁sync并行执行的大小：" + list4.size()+",时间："+stopwatch3);

        List<Integer> intStreams = list4;
        Stopwatch stopwatch4= new Stopwatch();
        stopwatch4.start();
        List<Integer> collect = intStreams.parallelStream().collect(Collectors.toList());
        stopwatch4.stop();
        System.out.println("collect并行执行的大小：" + collect.size()+",时间："+stopwatch4.getElapsedTime());


        System.out.println();

    }


    private static <T> long stopwatch(T t,Consumer<T> consumer){
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        consumer.accept(t);
        stopwatch.stop();
        return stopwatch.getElapsedTime();
    }


}
