package top.iyuhao.algorithm;


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import top.iyuhao.Utils;

import java.util.List;
import java.util.Optional;


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


    @Test
    public void singleLinkedListTest01() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.loop(System.out::println);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    @Test
    public void singleLinkedListTest02() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        Assertions.assertIterableEquals(List.of(1, 2, 3, 4), list);
        Assert.assertEquals(Optional.of(1).get(), list.get(0));
        Assert.assertEquals(Optional.of(2).get(), list.get(1));
        Assert.assertEquals(Optional.of(3).get(), list.get(2));
        Assert.assertEquals(Optional.of(4).get(), list.get(3));
        list.get(11);
    }

    @Test
    public void singleLinkedListTest03() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.insert(0, 1);
        list.insert(1, 2);
        list.insert(2, 3);
        list.insert(3, 4);
        list.insert(2, 5);
        Assertions.assertIterableEquals(List.of(1, 2, 5, 3, 4), list);

    }
}
