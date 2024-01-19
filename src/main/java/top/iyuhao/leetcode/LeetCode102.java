package top.iyuhao.leetcode;


import top.iyuhao.algorithm.queue.MyLinkedListQueue;
import top.iyuhao.utilclass.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuhao
 * @time 2024/1/8 20:38
 * @description 102. 二叉树的层序遍历
 *
 **/
public class LeetCode102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        MyLinkedListQueue<TreeNode> queue = new MyLinkedListQueue<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();

            for (int i = 0; i <size ; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                3,

                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7))
                );
        LeetCode102 leetCode102 = new LeetCode102();
        List<List<Integer>> result = leetCode102.levelOrder(root);
        System.out.println(result);
    }
}
