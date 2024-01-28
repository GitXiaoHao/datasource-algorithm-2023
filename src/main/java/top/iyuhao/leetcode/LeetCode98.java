package top.iyuhao.leetcode;

import top.iyuhao.utilclass.TreeNode;

import java.util.LinkedList;

/**
 * @author yuhao
 * @time 2024/1/28 14:02
 * @desc leetcode98 验证二叉搜索树
 **/
public class LeetCode98 {
    public boolean isValidBST(TreeNode root) {
        TreeNode node = root;
        long prev = Long.MIN_VALUE;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            }else {
                node = stack.pop();
                if (prev >= node.val) {
                    return false;
                }
                prev = node.val;
                node = node.right;
            }
        }
        return true;
    }
}
