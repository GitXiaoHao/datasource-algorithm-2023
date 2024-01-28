package top.iyuhao.algorithm.binarytree;

import java.util.LinkedList;

/**
 * @author yuhao
 * @time 2024/1/27 11:43
 * 根据后缀表达式构造表达式树
 **/
public class SuffixConstructionTree {
    private static class TreeNode {
        public String  val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(String val) {
            this.val = val;
        }

        public TreeNode(String val,TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 1. 遇到数字入栈
     * 2. 遇到运算符出栈，建立节点关系
     * @param suffixExpression
     * @return
     */
    public TreeNode constructExpressionTree(String[] suffixExpression) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        for (String s : suffixExpression) {
            switch (s){
                case "+","-","*","/" -> {
                    TreeNode right = stack.pop();
                    TreeNode left = stack.pop();
                    stack.push(new TreeNode(s,left,right));
                }
                default -> {
                    stack.push(new TreeNode(s));
                }
            }
        }
        return stack.pop();
    }
}
