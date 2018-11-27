package cn.edu.nju.selfbounded;


class A extends SelfBounded<A>{}
class B extends SelfBounded<A>{}

public class SelfBounded<T extends SelfBounded<T>> {
    T element;
    SelfBounded<T> set(T arg){
        element = arg;
        return this;
    }
    T get(){return element;}
    void print(){
        System.out.println(element.getClass().getSimpleName());
    }



    public static void main(String[] args){

        A a = new A();
        a.set(new A());
        a.print();

        B b = new B(), a2 = new B();
        //b.set(b2);
        b.print();

    }
}
