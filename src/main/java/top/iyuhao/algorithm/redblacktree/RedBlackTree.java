package top.iyuhao.algorithm.redblacktree;

import top.iyuhao.utilclass.RedBlackColor;
import top.iyuhao.utilclass.RedBlackNode;

/**
 * 红黑树特性
 * 1.每个节点都有颜色
 * 2.根节点是黑色的
 * 3.每个叶子节点是黑色的
 * 4.如果一个节点是红色的，则它的子节点都是黑色的
 * 5.从任一节点到其每个叶子节点的路径都包含相同数目的黑色节点
 * 6.每个节点的子节点个数在2到4之间
 * 7.红色节点不能相邻
 * 8.从根到任意一个叶子节点，路径中的黑色节点数一样（黑色完美平衡）
 *
 * @author yuhao
 * @time 2024/1/29 11:47
 **/
public class RedBlackTree<E> {
    private RedBlackNode<E> root;

    private boolean isRed(RedBlackNode<E> node) {
        return node != null && node.color == RedBlackColor.RED;
    }

    private boolean isBlack(RedBlackNode<E> node) {
        return !isRed(node);
    }

    /**
     * 右旋
     * 处理parent
     * 旋转后新根的父子关系
     *
     * @param node
     */
    private void rightRotate(RedBlackNode<E> node) {
        //先保存要旋转节点的父节点
        RedBlackNode<E> nodeParent = node.parent;
        //旋转节点的左节点
        RedBlackNode<E> nodeLeft = node.left;
        //旋转节点的左节点的右节点
        RedBlackNode<E> nodeLeftRight = nodeLeft.right;
        //换父节点
        if (nodeLeftRight != null) {
            nodeLeftRight.parent = node;
        }
        // 右旋
        nodeLeft.right = node;
        nodeLeft.parent = node.parent;
        //旋转节点 的左节点
        node.left = nodeLeftRight;
        //旋转节点的父节点
        node.parent = nodeLeft;
        // 旋转节点的父节点 的 子节点需要变
        if (nodeParent == null) {
            root = nodeLeft;
            return;
        }
        if (nodeParent.left == node) {
            nodeParent.left = nodeLeft;
        } else {
            nodeParent.right = nodeLeft;
        }
    }

    /**
     * 左旋
     *
     * @param node
     */
    public void leftRotate(RedBlackNode<E> node) {
        RedBlackNode<E> nodeParent = node.parent;
        //右子树
        RedBlackNode<E> nodeRight = node.right;
        //右子树的左节点
        RedBlackNode<E> nodeRightLeft = nodeRight.left;
        //右旋
        nodeRight.left = node;
        nodeRight.parent = node.parent;
        node.right = nodeRightLeft;
        node.parent = nodeRight;
        if (nodeParent == null) {
            root = nodeRight;
            return;
        }
        if (nodeParent.left == node) {
            nodeParent.left = nodeRight;
        } else {
            nodeParent.right = nodeRight;
        }
    }

    /**
     * 遇到红红不平衡进行调整
     *
     * @param key
     * @param val
     */
    public void put(int key, E val) {
        RedBlackNode<E> node = root;
        RedBlackNode<E> parent = null;
        while (node != null) {
            parent = node;
            if (node.key > key) {
                node = node.left;
            } else if (node.key < key) {
                node = node.right;
            } else {
                node.val = val;
            }
        }
        RedBlackNode<E> newNode = new RedBlackNode<>(key, val, parent);
        if (parent == null) {
            root = newNode;
        } else if (parent.key > key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        adjustmentColor(newNode);
    }

    private void adjustmentColor(RedBlackNode<E> node) {
        //case 1 插入节点是根节点
        if (node == root) {
            node.color = RedBlackColor.BLACK;
            return;
        }
        //case 2 插入节点父亲是黑色，无需调整
        if (isBlack(node.parent)) {
            return;
        }
        //case 3 当红红相邻时，叔叔为红
        /*
            需要将父节点、叔叔变黑、祖父变红，然后对祖父做递归处理
                - 父节点变为黑色、叔叔变为黑色
                - 祖父如果是黑色不变，会造成这颗子树黑色过多，因此祖父节点变为红色
                - 祖父如果变成红色，可能会红红相邻，因此需要递归调整
         */
        //父节点
        RedBlackNode<E> parent = node.parent;
        //叔叔
        RedBlackNode<E> uncle = parent.sibling();
        //祖父
        RedBlackNode<E> grandParent = parent.parent;
        if (isRed(uncle)) {
            parent.color = RedBlackColor.BLACK;
            uncle.color = RedBlackColor.BLACK;
            grandParent.color = RedBlackColor.RED;
            adjustmentColor(grandParent);
            return;
        }
        //case 4 叔叔为黑色
        /*
         1. 父节点为左孩子，插入节点也是左孩子 LL不平衡
         2. 父节点为右孩子，插入节点也是右孩子 RR不平衡
         3. 父节点为左孩子，插入节点也是右孩子 LR不平衡
         4. 父节点为右孩子，插入节点也是左孩子 RL不平衡
         */
        if (parent.ifLeftChild() && node.ifLeftChild()) {
            //1. 父节点为左孩子，插入节点也是左孩子 LL不平衡
            parent.color = RedBlackColor.BLACK;
            grandParent.color = RedBlackColor.RED;
            rightRotate(grandParent);
        } else if (parent.ifRightChild() && node.ifRightChild()) {
            //2. 父节点为右孩子，插入节点也是右孩子 RR不平衡
            parent.color = RedBlackColor.BLACK;
            grandParent.color = RedBlackColor.RED;
            leftRotate(grandParent);
        } else if (parent.ifLeftChild() && node.ifRightChild()) {
            //3. 父节点为左孩子，插入节点也是右孩子 LR不平衡
            leftRotate(parent);
            node.color = RedBlackColor.BLACK;
            parent.color = RedBlackColor.RED;
            rightRotate(grandParent);
        } else if (parent.ifRightChild() && node.ifLeftChild()) {
            //4. 父节点为右孩子，插入节点也是左孩子 RL不平衡
            rightRotate(parent);
            node.color = RedBlackColor.BLACK;
            parent.color = RedBlackColor.RED;
            leftRotate(grandParent);
        }
    }

    public void remove(int key) {
        RedBlackNode<E> deleteNode = getNode(key);
        if (deleteNode == null) {
            return;
        }
        remove(deleteNode);
    }

    /**
     * case1 删除的是根节点
     * - 删完了 直接将root = null
     * - 用剩余节点替换根节点的key value 根节点孩子 = null 颜色不变
     *
     * @param deleteNode
     */
    private void remove(RedBlackNode<E> deleteNode) {
        //先找到要删除节点的 子节点
        RedBlackNode<E> replaced = findReplaced(deleteNode);
        //没有孩子
        if (replaced == null) {
            //删完了 直接将root = null
            if (deleteNode == root) {
                root = null;
            } else {
                //删的是黑色
                if (isBlack(deleteNode)) {
                    fixDoubleBlack(deleteNode);
                } else {
                    //删的是红色节点 无需调整
                }
                if (deleteNode.ifLeftChild()) {
                    deleteNode.parent.left = null;
                } else {
                    deleteNode.parent.right = null;
                }
                deleteNode.parent = null;
            }
            return;
        }
        //有一个孩子
        if (deleteNode.left == null || deleteNode.right == null) {
            //用剩余节点替换根节点的key value 根节点孩子 = null 颜色不变
            if (deleteNode == root) {
                swap(root, replaced);
                root.left = root.right = null;
            } else {
                //不是叶子节点
                if (deleteNode.ifLeftChild()) {
                    deleteNode.parent.left = replaced;
                } else {
                    deleteNode.parent.right = replaced;
                }
                replaced.parent = deleteNode.parent;
                deleteNode.left = deleteNode.right = deleteNode.parent = null;
                //删除的是黑色 剩下的也是黑色
                if (isBlack(deleteNode) && isBlack(replaced)) {
                    fixDoubleBlack(replaced);
                } else {
                    //删除的是黑色  剩下的是红色， 剩下的这个节点变黑
                    replaced.color = RedBlackColor.BLACK;
                }
            }
            return;
        }
        //有两个孩子
        //将 删除节点的数据交换到后继节点中
        swap(deleteNode, replaced);
        remove(replaced);
    }

    /**
     * 删除节点和剩下节点都是黑，触发双黑，少了一个黑色
     * - 删除节点或剩余节点的兄弟为红，此时两个侄子为黑
     * - 删除节点或剩余与节点的兄弟，和兄弟孩子都为黑
     * - 删除节点的兄弟为黑，至少一个红色侄子
     *
     * @param node
     */
    private void fixDoubleBlack(RedBlackNode<E> node) {
        if (node == null) {
            return;
        }
        RedBlackNode<E> parent = node.parent;
        RedBlackNode<E> sibling = node.sibling();
        // case 3 兄弟节点是红色
        if (isRed(sibling)) {
            if (node.ifLeftChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            parent.color = RedBlackColor.RED;
            sibling.color = RedBlackColor.BLACK;
            fixDoubleBlack(node);
            return;
        }
        /*
        case 4: 被调整节点的兄弟为黑，两个侄子都为黑
                - 将兄弟节点设置为红色 目的是将删除节点和兄弟那边的黑色高度同时减少1
                - 如果父亲为红，则需将父亲变为黑，避免红红，此时路径黑色节点数目不变
                - 如果父亲为黑，说明这条路径少了一个黑，再次让父节点触发双黑
         */
        //兄弟是黑色
        //判断侄子是不是黑色
        if (sibling != null) {
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                sibling.color = RedBlackColor.RED;
                if (isRed(parent)) {
                    parent.color = RedBlackColor.BLACK;
                } else {
                    fixDoubleBlack(parent);
                }
                return;
            } else {
                //case 5 兄弟是黑色 侄子有红色
                if (sibling.ifLeftChild() && isRed(sibling.left)) {
                    // 兄弟是左孩子 左侄子是 红 LL不平衡
                    rightRotate(parent);
                    sibling.left.color = RedBlackColor.BLACK;
                    sibling.color = parent.color;
                } else if (sibling.ifLeftChild() && isRed(sibling.right)) {
                    // 兄弟是左孩子 右侄子是 红 LR不平衡
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                } else if (sibling.ifRightChild() && isRed(sibling.left)) {
                    // 兄弟是右孩子 左侄子是 红 RL不平衡
                    sibling.left.color = parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                } else {
                    // 兄弟是右孩子 右侄子是 红 RR不平衡
                    sibling.right.color = RedBlackColor.BLACK;
                    leftRotate(parent);
                    sibling.color = parent.color;
                }
                parent.color = RedBlackColor.BLACK;
            }
        } else {
            fixDoubleBlack(parent);
        }

    }

    private void swap(RedBlackNode<E> deleteNode, RedBlackNode<E> replaced) {
        int key = deleteNode.key;
        deleteNode.key = replaced.key;
        replaced.key = key;

        E val = deleteNode.val;
        deleteNode.val = replaced.val;
        replaced.val = val;
    }

    private RedBlackNode<E> getNode(int key) {
        RedBlackNode<E> node = root;
        while (node != null) {
            if (node.key > key) {
                node = node.left;
            } else if (node.key < key) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * 查找剩余节点
     *
     * @param node
     * @return
     */
    private RedBlackNode<E> findReplaced(RedBlackNode<E> node) {
        if (node.left == null && node.right == null) {
            return null;
        }
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }
        //后继节点
        return doMin(node.right);
    }

    private RedBlackNode<E> doMin(RedBlackNode<E> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return doMin(node.left);
    }
}
