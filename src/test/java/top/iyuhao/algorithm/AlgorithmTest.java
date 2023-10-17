package top.iyuhao.algorithm;


import org.junit.Assert;
import org.junit.Test;
import top.iyuhao.Utils;


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
        long startBasic = System.currentTimeMillis();
        Assert.assertEquals(i1, BinarySearch.binarySearchBasic(arr, i1));
        long endBasic = System.currentTimeMillis();
        System.out.println((endBasic - startBasic) + "ms");
        long startNative = System.currentTimeMillis();
        Assert.assertEquals(i1, BinarySearch.binarySearchAfterNative(arr, i1));
        long endNative = System.currentTimeMillis();
        System.out.println((endNative - startNative) + "ms");
        long start = System.currentTimeMillis();
        for (int i : arr) {
            if (i == i1) {
                break;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");
    }
}
