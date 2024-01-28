package top.iyuhao.leetcode;

import top.iyuhao.utilclass.TreeNode;

/**
 * @author yuhao
 * @time 2024/1/27 11:22
 * @desc 翻转二叉树
 **/
public class LeetCode226 {
    public TreeNode invertTree(TreeNode root) {
        fn(root);
        return root;
    }
    public void fn(TreeNode root){
        if(root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        fn(root.left);
        fn(root.right);
    }


}
