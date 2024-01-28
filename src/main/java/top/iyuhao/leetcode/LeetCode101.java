package top.iyuhao.leetcode;

import top.iyuhao.utilclass.TreeNode;

/**
 * @author yuhao
 * @time 2024/1/26 18:28
 * @desc 101. 对称二叉树
 **/
public class LeetCode101 {


    public static boolean isSymmetric(TreeNode root) {
        return check(root.left,root.right);
    }
    private static boolean check(TreeNode left, TreeNode right){
        if(left == null && right == null) {
            return true;
        }
        if(left == null || right == null) {
            return false;
        }
        if(left.val != right.val) {
            return false;
        }
        return check(left.left, right.right) && check(left.right,right.left  );
    }


}
