package top.iyuhao.leetcode;

import top.iyuhao.utilclass.TreeNode;

/**
 * @author yuhao
 * @time 2024/1/28 15:30
 * @desc 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
 **/
public class LeetCode938 {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        if (root.val >= L && root.val <= R) {
            sum += root.val;
        }
        if (root.val > L) {
            sum += rangeSumBST(root.left, L, R);
        }
        if (root.val < R) {
            sum += rangeSumBST(root.right, L, R);
        }
        return sum;
    }

}
