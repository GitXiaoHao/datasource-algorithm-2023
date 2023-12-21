package top.iyuhao.algorithm.factorial;

/**
 * @author yuhao
 * @time 2023/10/24
 * 递归实现插入排序
 **/
public class FactorialInsertionSort {

    /**
     * @param arr
     * @param low 未排序区的下边界
     */
    private static void insertionSort(int[] arr, int low) {
        if (low == arr.length) {
            return;
        }
        //待插入元素
        int temp = arr[low];
        //已排序区的下边界
        int i = low - 1;
        //没有找到插入位置
        while (i >= 0 && arr[i] > temp) {
            arr[i + 1] = arr[i];//空出插入位置
            i--;
        }
        //找到插入位置
        if(i + 1 != low) {
            arr[i + 1] = temp;
        }
        insertionSort(arr, low + 1);
    }
    public void sort(int[] arr) {
        insertionSort(arr, 1);
    }
}
