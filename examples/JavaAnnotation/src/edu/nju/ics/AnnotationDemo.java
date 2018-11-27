package edu.nju.ics;

@AuthorAnno(name="Chun", website="http://github.com/caochun", revision=1)
public class AnnotationDemo {

    public static void main(String[] args) {
        System.out.println("I am main method");
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
//    @AuthorAnno(name="lvr", website="hello", revision=2)
    public void demo(){
        System.out.println("I am demo method");
    }

}
