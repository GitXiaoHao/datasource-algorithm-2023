package top.iyuhao;

import top.iyuhao.algorithm.BinarySearch;

import java.util.Random;

/**
 * @author yuhao
 * @date 2023/10/17
 **/
public class Utils {
    public static int[] getRandomArray(int number,boolean randomNum){
        int[] arr = new int[number];
        Random random = new Random();
        if (randomNum) {
            for (int i = 0; i < number; i++) {
                arr[i] = random.nextInt(number);
            }
            return arr;
        }
        for (int i = 0; i < number; i++) {
            arr[i] = i;
        }
        return arr;
    }


}
