package com.delicacy.oatmeal.java.lamdbatest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * lamdba一些问题
 *
 * @author zyt
 * @create 2018-04-17 15:27
 **/
public class LamdbaQuestionDemo {

    @Test
    public void testInner(){
        String msg = "good morning";
        new Thread(()->System.out.println(msg)).start();
        System.out.println(msg);

        Supplier<ArrayList> runnable = ArrayList::new;
        ArrayList arrayList = runnable.get();
        arrayList.add(2);
        System.out.println(arrayList);
    }


}
