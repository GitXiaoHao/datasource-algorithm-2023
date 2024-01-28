package top.iyuhao.leetcode;

import top.iyuhao.utilclass.TreeNode;

/**
 * @author yuhao
 * @time 2024/1/28 16:19
 * @desc 1008. 前序遍历构造二叉树
 *
 **/
public class LeetCode1008 {
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            insert(root, preorder[i]);
        }
        return root;
    }

    private TreeNode insert(TreeNode root, int i) {
        if (root == null) {
            return new TreeNode(i);
        }
        if (i < root.val) {
            root.left = insert(root.left, i);
        }else if(i > root.val){
            root.right = insert(root.right, i);
        }
        return root;
    }


    /**
     * 上限法
     * 1. 遍历数组中每一个值，根据值创建节点
     *      - 每个节点若成功创建都有：左孩子上限，右孩子上限
     * 2. 处理下一个值时，如果超过此上限，那么
     *      - 将null作为上个节点的孩子
     *      - 不能创建节点对象
     *      - 直到不超过上限为止
     * 3. 重复1 2 两步
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder2(int[] preorder) {
        return insert2(preorder,Integer.MAX_VALUE);
    }
    private int position = 0;
    /**
     * 依次处理preorder中的每一个值，返回创建好的节点或null
     * 1. 如果超过上限，那么返回null 作为孩子返回
     * 2. 如果没超过上限，创建节点，并设置其左右孩子， 左右孩子完整后返回
     * @param preorder
     * @param max 上限值，左孩子上限，右孩子上限
     * @return
     */
    private TreeNode insert2(int[] preorder, int max) {
        if(position == preorder.length || preorder[position] > max) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[position++]);
        root.left = insert2(preorder, root.val);
        root.right = insert2(preorder, max);
        return root;
    }

    /**
     * 分治法
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder3(int[] preorder) {
        return partition(preorder, 0, preorder.length - 1);   //返回的是根节点，所以要加上根节点的val，这样才能够构造完整的二叉树
    }
    private TreeNode partition(int[] preorder,int start,int end){
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[start]);
        int index = start + 1;
        while (index <= end && preorder[index] < root.val) {
            index++;
        }
        //index 就是右子树的起点
        root.left = partition(preorder, start + 1, index - 1);
        root.right = partition(preorder, index, end);
        return root;
    }

}
