package com.delicacy.oatmeal.test.example.generic;

import com.delicacy.oatmeal.test.example.generic.entity.Apple;
import com.delicacy.oatmeal.test.example.generic.entity.Food;
import com.delicacy.oatmeal.test.example.generic.entity.Fruit;

/**
 * main
 *
 * @author zyt
 * @create 2018-02-27 17:19
 **/
public class Bootstrap {
    public static void main(String[] args) {
        /*苹果 IS-A 水果
        装苹果的盘子 NOT-IS-A 装水果的盘子*/
        //Plate<Fruit> p=new Plate<Apple>(new Apple());//Error

        //上下界通配符
        //一个能放水果以及一切是水果派生类的盘子
        Plate<? extends Fruit> p=new Plate<Apple>(new Apple());

        // 上界<? extends T>不能往里存，只能往外取
        //p.set(new Fruit());//Error
        //p.set(new Apple());//Error
        //读取出来的东西只能存放在Fruit或它的基类里。
        Fruit newFruitl=p.get();
        Object newFruit2=p.get();
        //Apple newFruit3=p.get();//Error

        //一个能放水果以及一切是水果基类的盘子
        //下界<? super T>不影响往里存，但往外取只能放在Fruit或者Object对象
        Plate<? super Fruit> pp = new Plate<Food>(new Food());
        //Plate<? super Fruit> ppp = new Plate<Apple>(new Apple()); //error

        pp.set(new Fruit());
        pp.set(new Apple());
        //我知道我比你大，但是我不知道，我比你大到什么程度
        //pp.set(new Food()); //error

        //Apple newFruit5=p.get();
        Fruit newFruit6=p.get();
        Object newFruit4=p.get();

        /*PECS

        请记住PECS原则：生产者（Producer）使用extends，消费者（Consumer）使用super。

        生产者使用extends
        如果你需要一个列表提供T类型的元素（即你想从列表中读取T类型的元素），你需要把这个列表声明成<? extends T>，比如List<? extends Integer>，因此你不能往该列表中添加任何元素。

        消费者使用super
        如果需要一个列表使用T类型的元素（即你想把T类型的元素加入到列表中），你需要把这个列表声明成<? super T>，比如List<? super Integer>，因此你不能保证从中读取到的元素的类型。

        即是生产者，也是消费者
        如果一个列表即要生产，又要消费，你不能使用泛型通配符声明列表，比如List<Integer>。*/

    }
}
