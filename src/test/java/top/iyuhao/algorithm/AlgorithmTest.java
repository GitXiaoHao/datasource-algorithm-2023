package top.iyuhao.algorithm;


import org.junit.Assert;
import org.junit.Test;
import top.iyuhao.Utils;

import javax.sound.midi.Soundbank;


/**
 * @author yuhao
 * @date 2023/10/17
 **/
public class AlgorithmTest {
    @Test
    public void binarySearchBasicTest() {
        int[] arr = {1, 5, 6, 8, 9, 12, 15, 18, 21, 25, 64, 3465};
        Assert.assertEquals(0, BinarySearch.binarySearchBasic(arr, 1));
        Assert.assertEquals(1, BinarySearch.binarySearchBasic(arr, 5));
        Assert.assertEquals(2, BinarySearch.binarySearchBasic(arr, 6));
        Assert.assertEquals(3, BinarySearch.binarySearchBasic(arr, 8));
        Assert.assertEquals(-1, BinarySearch.binarySearchBasic(arr, 10));
    }

    @Test
    public void testTime() {
        int i1 = 99999;
        int[] arr = Utils.getRandomArray(100000, false);
        long startBasic = System.nanoTime();
        Assert.assertEquals(i1, BinarySearch.binarySearchBasic(arr, i1));
        long endBasic = System.nanoTime();
        System.out.println((endBasic - startBasic) + "ns");
        long startNative = System.nanoTime();
        Assert.assertEquals(i1, BinarySearch.binarySearchAfterNative(arr, i1));
        long endNative = System.nanoTime();
        System.out.println((endNative - startNative) + "ns");
        long start = System.nanoTime();
        for (int i : arr) {
            if (i == i1) {
                break;
            }
        }
        long end = System.nanoTime();
        System.out.println((end - start) + "ns");
    }

    @Test
    public void dynamicArrayTest() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(1);
        array.add(5);
        array.add(65);
        array.add(63);
        array.add(5);
        array.add(5);
        System.out.println(array);
        array.forEach(System.out::println);
        for (Integer integer : array) {
            System.out.printf(integer + " ");
        }
        System.out.println();
        array.stream().forEach(System.out::println);
    }
    @Test
    public void dynamicArrayTest02() {
        DynamicArray<Integer> array = new DynamicArray<>(1);
        array.add(1);
        array.add(22);
        array.forEach(System.out::println);
    }
}
