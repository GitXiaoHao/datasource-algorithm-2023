package top.iyuhao.algorithm.binarytree;

import top.iyuhao.utilclass.TreeNode;

import java.util.LinkedList;

/**
 * @author yuhao
 * @time 2024/1/26 16:38
 * 迭代
 **/
public class TreeIteration {
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
    static void preOrder(TreeNode root){
        TreeNode curr = root;
        LinkedList<TreeNode> stack  = new LinkedList<>();
        while (curr != null || !stack.isEmpty()){
            if (curr != null) {
                System.out.print(curr.val + " ");
                stack.push(curr);
                curr = curr.left;
            }else {
                TreeNode node = stack.pop();
                curr = node.right;
            }
        }
    }
    static void inOrder(TreeNode root){
        TreeNode curr = root;
        LinkedList<TreeNode> stack  = new LinkedList<>();
        while (curr != null || !stack.isEmpty()){
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }else {
                TreeNode node = stack.pop();
                System.out.print(node.val + " ");
                curr = node.right;
            }
        }
    }
    static void postOrder(TreeNode root){
        TreeNode curr = root;
        TreeNode pop = null;
        LinkedList<TreeNode> stack  = new LinkedList<>();
        while (curr != null || !stack.isEmpty()){
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }else {
                TreeNode peek = stack.peek();
                if (peek.right == null  || peek.right == pop) {
                    pop = stack.pop();
                    System.out.print(pop.val + " ");
                }else{
                    curr = peek.right;
                }
            }
        }
    }
    private static void allOrder(TreeNode root){
        TreeNode curr = root;
        TreeNode pop = null;
        LinkedList<TreeNode> stack  = new LinkedList<>();
        while (curr != null || !stack.isEmpty()){
            if (curr != null) {
                stack.push(curr);
                //处理左子树
                //前序遍历打印时机

                curr = curr.left;
            }else {
                TreeNode peek = stack.peek();
                //没有右子树
                if (peek.right == null ) {
                    //中序遍历打印时机
                    pop = stack.pop();
                    // 后序遍历打印时机
                } else if (peek.right == pop) {
                    //右子树处理完成
                    pop = stack.pop();
                    //后序遍历打印时机

                } else{
                    //待处理右子树
                    //中序遍历打印时机
                    curr = peek.right;
                }
            }
        }
    }
}
