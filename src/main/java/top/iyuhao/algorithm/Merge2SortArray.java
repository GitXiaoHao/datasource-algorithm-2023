package top.iyuhao.algorithm;

/**
 * @author yuhao
 * @time 2023/12/21 15:08
 * @desc 合并两个有序数组
 **/
public class Merge2SortArray {

    /**
     * @param arr  原始数组
     * @param i    第一个原始数组开始位置
     * @param iEnd 原始数组结束位置
     * @param j    第二个原始数组开始位置
     * @param jEnd 原始数组结束位置
     * @param arr2 合并数组
     * @param k    合并数组索引
     */
    public static void merge(int[] arr, int i, int iEnd, int j, int jEnd, int[] arr2, int k) {
        if (i > iEnd) {
            System.arraycopy(arr,j,arr2,k,(jEnd - j) + 1);
            return;
        }
        if (j > jEnd) {
            System.arraycopy(arr,i,arr2,k,(iEnd - i) + 1);
            return;
        }
        if (arr[i] < arr[j]) {
            arr2[k++] = arr[i++];
        } else {
            arr2[k++] = arr[j++];
        }
        merge(arr, i, iEnd, j, jEnd, arr2, k);
    }

    /**
     * 方法二 循环
     * @param arr
     * @param i
     * @param iEnd
     * @param j
     * @param jEnd
     * @param arr2
     */
    public static void merge2(int[] arr, int i, int iEnd, int j, int jEnd, int[] arr2) {
        int k = 0;
        while (i <= iEnd && j <= jEnd) {
            if (arr[i] < arr[j]) {
                arr2[k++] = arr[i++];
            } else {
                arr2[k++] = arr[j++];
            }
        }
        if(i > iEnd){
            System.arraycopy(arr,j,arr2,k,(jEnd - j) + 1);
        }
        if(j > jEnd){
            System.arraycopy(arr,i,arr2,k,(iEnd - i) + 1);
        }
    }
}
