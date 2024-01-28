package top.iyuhao.leetcode;

import top.iyuhao.algorithm.heap.MyMinHeap;

/**
 * @author yuhao
 * @time 2024/1/25 9:22
 * @desc 215. 数组中的第K个最大元素
 * 解题思路：
 *      1. 向小顶堆放入前k个元素
 *      2. 剩余元素
 *          - 若 <= 堆顶元素，则虐过
 *          - 若 > 堆顶元素，则替换堆顶元素
 *      3. 这样小顶堆始终保留的是到目前为止，前k大的元素
 *      4. 循环结束，堆顶元素即为第K大元素
 **/
public class LeetCode215 {
    public int findKthLargest(int[] nums, int k) {
        MyMinHeap minHeap = new MyMinHeap(k);
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.replace(nums[i]);
            }
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode215().findKthLargest(new int[]{3,2,1,5,6,4},2));
        System.out.println(new LeetCode215().findKthLargest(new int[]{3,2,3,1,2,4,5,5,6},4));
    }
}
