package com.delicacy.oatmeal.test.example.generic;

/**
 * plate
 *
 * @author zyt
 * @create 2018-02-27 17:21
 **/
class Plate<T> {
    private T item;

    public Plate(T t) {
        item = t;
    }

    public void set(T t) {
        item = t;
    }

    public T get() {
        return item;
    }
}