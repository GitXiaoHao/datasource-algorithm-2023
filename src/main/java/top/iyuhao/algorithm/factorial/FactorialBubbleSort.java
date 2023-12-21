package top.iyuhao.algorithm.factorial;

/**
 * @author yuhao
 * @time 2023/10/23
 * 递归实现冒泡排序
 **/
public class FactorialBubbleSort {
    public void sort(int[] arr) {
        bubbleSort(arr, arr.length);
    }
    private static void bubbleSort(int[] arr, int n) {
        if (n == 1) {
            return;
        }
        //减少递归
        int x = 0;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                x = i;
            }
        }
        bubbleSort(arr, x);
    }
}
