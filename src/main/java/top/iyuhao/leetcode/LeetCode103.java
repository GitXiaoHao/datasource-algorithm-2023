package top.iyuhao.leetcode;

import top.iyuhao.utilclass.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuhao
 * @time 2024/1/15 19:20
 * @description leetcode 103. 二叉树的锯齿形层序遍历
 **/
public class LeetCode103 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                3,

                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );
        LeetCode103 leetCode103 = new LeetCode103();
        List<List<Integer>> result = leetCode103.zigzagLevelOrder(root);
        System.out.println(result);
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i <size ; i++) {
                TreeNode node = queue.poll();
                if (result.size() % 2 == 0) {
                    level.addLast(node.val);
                }else {
                    level.addFirst(node.val);
                }
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

}
