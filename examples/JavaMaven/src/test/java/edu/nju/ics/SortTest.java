package edu.nju.ics;


import org.junit.Test;

import java.util.Random;

public class SortTest {

    @Test(timeout = 2000)
    public void testSort() throws Exception {
        int[] arr = new int[500000]; //数组长度为50000
        int arrLength = arr.length;
        //随机生成数组元素
        Random r = new Random();
        for (int i = 0; i < arrLength; i++) {
            arr[i] = r.nextInt(arrLength);
        }

        new Sort().qSort(arr);
    }

}
