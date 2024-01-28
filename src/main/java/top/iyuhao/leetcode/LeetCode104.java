package top.iyuhao.leetcode;

import top.iyuhao.utilclass.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuhao
 * @time 2024/1/26 20:02
 * @description 104. 二叉树的最大深度
 **/
public class LeetCode104 {
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    public int maxDepth2(TreeNode root) {
        TreeNode cur = root;
        TreeNode pop = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int max = 0;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                max = Math.max(max, stack.size());
                cur = cur.left;
            }else {
                TreeNode peek = stack.peek();
                if(peek.right == null || peek.right == pop) {
                    pop = stack.pop();
                }else {
                    cur = peek.right;
                }
            }
        }
        return max;
    }
    public int maxDepth3(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null) {
            return 0;
        }
        queue.offer(root);
        int max = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    if(node.left != null) {
                        queue.offer(node.left);
                    }
                    if(node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
            max++;
        }
        return max;
    }
}
