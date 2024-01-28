package top.iyuhao.algorithm.binarytree;

import top.iyuhao.utilclass.TreeNode;

/**
 * @author yuhao
 * @time 2024/1/26 15:40
 **/
public class TreeTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,new TreeNode(4),null),
                new TreeNode(3,new TreeNode(5),new TreeNode(6)));
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
    }

    /**
     * 前序遍历
     * @param node
     */
    static void preOrder(TreeNode node){
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     * @param node
     */
    static void inOrder(TreeNode node){
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }
    static void postOrder(TreeNode node){
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + " ");
    }
}
