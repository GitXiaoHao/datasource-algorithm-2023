package top.iyuhao.leetcode;

import top.iyuhao.utilclass.TreeNode;

import java.util.Arrays;

/**
 * @author yuhao
 * @time 2024/1/27 12:54
 * @desc 105. 从前序与中序遍历序列构造二叉树
 **/
public class LeetCode105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        int index = 0;
        for(; index < inorder.length; index++){
            if(inorder[index] == preorder[0]){
                break;
            }
        }
        int[] preLeft = Arrays.copyOfRange(preorder, 1, index + 1);
        int[] inLeft = Arrays.copyOfRange(inorder, 0, index);
        int[] preRight = Arrays.copyOfRange(preorder, index + 1, preorder.length);
        int[] inRight = Arrays.copyOfRange(inorder, index + 1, inorder.length);
        root.left = buildTree(preLeft, inLeft);
        root.right = buildTree(preRight, inRight);
        return root;
    }
}
