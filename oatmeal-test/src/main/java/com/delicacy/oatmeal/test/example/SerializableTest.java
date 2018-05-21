package com.delicacy.oatmeal.test.example;

import lombok.Data;

import java.io.*;

/**
 * 序列化测试
 *
 * @author zyt
 * @create 2018-05-01 14:31
 **/
public class SerializableTest {


    public static void main(String[] args) throws IOException {
        Foo myFoo = new Foo();
        myFoo .setWidth(37);
        myFoo.setHeight(70);
        try(FileOutputStream fs = new FileOutputStream("foo.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);){
            os.writeObject(myFoo);
        }
    }

    @Data
    private static class Foo implements Serializable {
        private static final long serialVersionUID = 2856063392246743199L;
        int width;
        int Height;
    }
}
