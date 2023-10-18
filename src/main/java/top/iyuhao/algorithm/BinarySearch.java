package top.iyuhao.algorithm;

import java.util.Arrays;

/**
 * @author yuhao
 * @date 2023/10/17
 * 二分查找
 **/
public class BinarySearch {
    /**
     * 二分查找基础
     *
     * @param arr    数组
     * @param target 查找的数字
     * @return 返回对应下标
     */
    public static int binarySearchBasic(int[] arr, int target) {
        //定义左索引
        int left = 0;
        //定义右索引
        int right = arr.length - 1;
        //循环查找 如果左索引小于等于右索引则一直循环查找

        while (left <= right) {
            //定义中间值
            // /2 可能溢出
            int mid = (left + right) >>> 1;
            //找到中间值
            int midNum = arr[mid];
            //判断找到的数与中间值
            if (midNum < target) {
                //如果需要找的数 大于 中间值 将左索引 移至右侧
                left = mid + 1;
            } else if (target < midNum) {
                //如果需要找的数 小于 中间值 将右索引 移至左侧
                right = mid - 1;
            } else {
                //相等 直接返回
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找改动版
     *
     * @param arr    数组
     * @param target 查找的数字
     * @return 返回对应下标
     */

    public static int binarySearchAfterNative(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            int midNum = arr[mid];
            if (target < midNum) {
                //查找的值小于中间值
                right = mid;
            } else if (midNum < target) {
                //中间值小于查找值
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找平衡版
     *
     * @param arr    数组
     * @param target 查找的值
     * @return 返回对应下标
     */
    public static int binarySearchBalance(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (1 < right - left) {
            int mid = (left + right) >>> 1;
            if (target < arr[mid]) {
                //查找的值小于中间值
                right = mid;
            } else {
                left = mid;
            }
        }
        //在循环外判断
        if (target == arr[left]) {
            return left;
        }
        return -1;
    }

    /**
     * 使用Java内置二分查找
     *
     * @param arr    数组
     * @param target 查找的值
     * @return 返回对应下标
     */
    public int[] binarySearchUseJava(int[] arr, int target) {
        int index = Arrays.binarySearch(arr, target);
        if (index < 0) {
            //如果小于 0 则没有找到 插入到数组中
            int insertIndex = Math.abs(index + 1);
            int[] arrTemp = new int[arr.length + 1];
            //copy
            System.arraycopy(arr, 0, arrTemp, 0, insertIndex);
            //插入查找的值
            arrTemp[insertIndex] = target;
            System.arraycopy(arr, insertIndex, arrTemp, insertIndex + 1, arr.length - insertIndex);
            return arrTemp;
        }

        return arr;
    }

    /**
     * 找数组中最左边的数
     * @param arr    数组
     * @param target 查找的值
     * @return 返回对应下标
     */
    public int binarySearchLeftMost(int[] arr, int target){
        //定义左索引
        int left = 0;
        //定义右索引
        int right = arr.length - 1;
        int candidate = -1;
        //循环查找 如果左索引小于等于右索引则一直循环查找
        while (left <= right) {
            //定义中间值
            // /2 可能溢出
            int mid = (left + right) >>> 1;
            //找到中间值
            int midNum = arr[mid];
            //判断找到的数与中间值
            if (midNum < target) {
                //如果需要找的数 大于 中间值 将左索引 移至右侧
                left = mid + 1;
            } else if (target < midNum) {
                //如果需要找的数 小于 中间值 将右索引 移至左侧
                right = mid - 1;
            } else {
                //相等
                candidate = mid;
                right = mid - 1;
            }
        }
        return candidate;
    }
    /**
     * 找数组中最左边的数
     * @param arr    数组
     * @param target 查找的值
     * @return 返回 >= target 的靠左索引
     */
    public int binarySearchLeftMost2(int[] arr, int target){
        //定义左索引
        int left = 0;
        //定义右索引
        int right = arr.length - 1;
        //循环查找 如果左索引小于等于右索引则一直循环查找
        while (left <= right) {
            //定义中间值
            // /2 可能溢出
            int mid = (left + right) >>> 1;
            //找到中间值
            int midNum = arr[mid];
            //判断找到的数与中间值
            if (midNum <= target) {
                //如果需要找的数 大于 中间值 将左索引 移至右侧
                left = mid + 1;
            } else {
                //如果需要找的数 小于 中间值 将右索引 移至左侧
                right = mid - 1;
            }
        }
        return left;
    }
}
