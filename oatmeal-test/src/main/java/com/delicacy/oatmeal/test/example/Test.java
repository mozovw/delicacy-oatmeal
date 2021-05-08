package com.delicacy.oatmeal.test.example;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


public class Test {

    private static final String str = "dd";

    public static void main(String[] args)   {
        Float aa = 100.1f;
        Float bb = 100.1f;


        System.out.println(aa==bb);

        int a =10;
        System.out.println(a/2.5);

        System.out.println(10^3);
        //
        // test();
        System.out.println(1^11^2);

        System.out.println(str);

        MyLamda m = y -> System.out.println("ss"+y);
        m.test3();

        int i = Objects.hashCode(new Object());
        System.out.println(i);

    }


    @FunctionalInterface
     interface MyLamda {
         void test1(String y);
        default String test2(){
            return "123";
        }
        default String test3(){
            return "123";
        }
        // static⽅法也可以定义
        static void test4(){
            System.out.println("234");
        }
    }

    private static void test() throws UnsupportedEncodingException {
        String ss = new String("\u5feb\u901f\u5f00\u53d1\u5e73\u53f0"
                .getBytes(), StandardCharsets.UTF_8);
        System.out.println(ss);
        System.out.println("\u5b9a\u4e49\u6700\u5927\u8fde\u63a5\u6570");
        //2的3次方
        int i = 1 << 3;
        System.out.println(i);
        //64/(2*2)
        i = 64 >> 3;
        System.out.println(i);
        //i*(2*2*2*2)
        i = i <<= 4;
        System.out.println(i);
        System.out.println(64 >> 4 == 64 >>> 4);

        i = 32;
        if (i == 32 & (i += 1) == 33) {
            System.out.println(i);
        }
        if (i == 32 && (i += 1) == 33) {
            System.out.println(i);
        }

        //ConcurrentLinkedQueue
        System.out.println(1 << 29);

        int i1 = Runtime.getRuntime().availableProcessors();
        System.out.println(i1);
    }
}
