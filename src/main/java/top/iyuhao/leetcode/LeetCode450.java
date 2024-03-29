package top.iyuhao.leetcode;

import top.iyuhao.utilclass.TreeNode;

/**
 * @author yuhao
 * @time 2024/1/28 12:41
 * @desc 删除二叉搜索树中的节点
 **/
public class LeetCode450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode minNode = getMinNode(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, root.val);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    private TreeNode getMinNode(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


}
