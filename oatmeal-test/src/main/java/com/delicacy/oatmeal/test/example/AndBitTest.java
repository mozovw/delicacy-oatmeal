package com.delicacy.oatmeal.test.example;


import com.delicacy.oatmeal.test.exam.atomic.Stopwatch;

/**
 * &的操作
 *
 * @author zyt
 * @create 2018-05-04 9:28
 **/
public class AndBitTest {

    public static void main(String[] args) {
        t1();
        t2();
        t1();
    }

    private static void t2() {
        long[] ll;
        Stopwatch stopwatch;

        ll = new long[10000000];
        stopwatch = new Stopwatch();
        stopwatch.start();
        for (int i = 0; i<10000000; i++){
            int i1 = i & 999999;
            ll[i] = i1;
        }
        stopwatch.stop();
        System.out.println(stopwatch.getElapsedTime());
    }

    private static void t1() {
        long[] ll = new long[10000000];
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        for (int i = 0; i<10000000; i++){
            int i1 = i % 1000000;
            ll[i] = i1;
        }
        stopwatch.stop();
        System.out.println(stopwatch.getElapsedTime());
    }

}
