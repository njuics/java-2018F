package cn.edu.nju.selfbounded;

public class CRGWithBasicHolder {

    public static void main(String[] args){
        SubType s1 = new SubType();
        SubType s3 = s1.get();
        s1.print();
        s3.print();
    }
}
