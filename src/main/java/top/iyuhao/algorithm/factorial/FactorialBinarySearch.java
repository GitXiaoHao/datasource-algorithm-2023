package top.iyuhao.algorithm.factorial;

/**
 * @author yuhao
 * @time 2023/10/23
 * 递归二分查找
 **/
public class FactorialBinarySearch {
    private static int f(int[] nums, int target, int i, int j) {
        if (i > j) {
            return -1;
        }
        int mid = (i + j) >>> 1;
        int midNum = nums[mid];
        if (target < midNum) {
            return f(nums, target, i, mid);
        } else if (midNum < target) {
            return f(nums, target, mid, j);
        } else {
            return mid; //找到了目标值，返回目标值的下标
        }
    }

    public int search(int[] nums, int target) {
        return f(nums, target, 0, nums.length - 1); //从0开始查找，到nums.length - 1结束查找
    }
}

