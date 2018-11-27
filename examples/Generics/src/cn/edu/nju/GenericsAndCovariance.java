package cn.edu.nju;

import java.util.ArrayList;
import java.util.List;

class Fruit{

}
class Apple extends Fruit{

}

class Plate<T>{
    private T item;
    public Plate(T t){item=t;}
    public void set(T t){item=t;}
    public T get(){return item;}
}

public class GenericsAndCovariance {

    public static void main(String[] args){
        Plate<? extends Fruit> p=new Plate<Apple>(new Apple());

        //不能存入任何元素
        //p.set(new Fruit());    //Error
        //p.set(new Apple());    //Error
        //读取出来的东西只能存放在Fruit或它的基类里。
        Fruit newFruit1=p.get();
        Object newFruit2=p.get();
        //Apple newFruit3=p.get();    //Error

        Plate<? super Fruit> p2=new Plate<Fruit>(new Fruit());
        //存入元素正常
        p2.set(new Fruit());
        p2.set(new Apple());
        //读取出来的东西只能存放在Object类里。
        //Apple newFruit31=p2.get();    //Error
        //Fruit newFruit11=p2.get();    //Error
        Object newFruit21=p2.get();


    }

}
