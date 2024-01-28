package top.iyuhao.algorithm.avltree;

import top.iyuhao.utilclass.AVLNode;

/**
 * @author yuhao
 * @time 2024/1/28 18:00
 * Avl 树
 * - 二叉搜索树在插入和删除时，节点可能失衡
 * - 如果在插入或者删除节点时，树失衡，需要进行旋转 始终让二叉搜索树保持平衡 称为自平衡的二叉搜索树
 * - AVL是自平衡二叉搜索树的实现之一
 **/
public class AvlTree<E> {
    private AVLNode<E> root;
    public void put(int key, E value) {
        root = doPut(root, key, value);
    }

    private AVLNode<E> doPut(AVLNode<E> root, int key, E value) {
        return null;
    }

    private int getHeight(AVLNode<E> node) {
        return node == null ? 0 : node.height;
    }

    private void updateHeight(AVLNode<E> node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    /**
     * 平衡因子
     *
     * @param node
     * @return >1 <-1 不平衡  >1左边高 <-1右边高  0平衡
     */
    private int balanceFactor(AVLNode<E> node) {
        return getHeight(node.left) - getHeight(node.right);
    }

    private AVLNode<E> balance(AVLNode<E> node) {
        if (node == null) {
            return null;
        }
        int bf = this.balanceFactor(node);
        // 左边高
        if (bf > 1) {
            if (balanceFactor(node.left) >= 0) {
                //LL
                return rightRotate(node);
            }
            // LR
            return leftRightRotate(node);
        } else if (bf < -1) {
            if (balanceFactor(node.right) > 0) {
                // RL
                return rightLeftRotate(node);
            }
            // RR
            return leftRotate(node);
        }
        return node;
    }

    /**
     * 右旋
     *
     * @param node 新的根节点
     * @return
     */
    private AVLNode<E> rightRotate(AVLNode<E> node) {
        //节点的左节点
        AVLNode<E> left = node.left;
        //将 左节点的 右节点 给  node的左节点
        node.left = left.right;
        //将 左节点 的 右节点 赋为 node
        left.right = node;
        updateHeight(node);
        updateHeight(left);
        return left;
    }

    private AVLNode<E> leftRotate(AVLNode<E> node) {
        //节点的右节点
        AVLNode<E> right = node.right;
        //将 右节点的左节点 给 node的右节点
        node.right = right.left;
        //将 右节点 的 左节点 赋为 node
        right.left = node;
        updateHeight(node);
        updateHeight(right); //更新右节点的高度，因为右节点的左节点被赋值为node，所以右节点的高度应该更新为node的高�
        return right;
    }

    private AVLNode<E> leftRightRotate(AVLNode<E> node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    private AVLNode<E> rightLeftRotate(AVLNode<E> node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

}
