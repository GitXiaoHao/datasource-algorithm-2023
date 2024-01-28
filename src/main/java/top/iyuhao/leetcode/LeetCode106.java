package top.iyuhao.leetcode;

import top.iyuhao.utilclass.TreeNode;

import java.util.Arrays;

/**
 * @author yuhao
 * @time 2024/1/27 13:09
 * @desc 106. 从中序与后序遍历序列构造二叉树
 **/
public class LeetCode106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder.length == 0 || postorder.length == 0) {
            return null;
        }
        int rootVal = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootVal);
        int index = 0;
        for(; index < inorder.length; index++) {
            if(inorder[index] == rootVal) {
                break;
            }
        }
        int[] inLeft = Arrays.copyOfRange(inorder, 0, index);
        int[] postLeft = Arrays.copyOfRange(postorder, 0, index);
        int[] inRight = Arrays.copyOfRange(inorder, index + 1, inorder.length);
        int[] postRight = Arrays.copyOfRange(postorder, index, postorder.length - 1);
        root.left = buildTree(inLeft, postLeft);
        root.right = buildTree(inRight, postRight);
        return root;
    }


}
