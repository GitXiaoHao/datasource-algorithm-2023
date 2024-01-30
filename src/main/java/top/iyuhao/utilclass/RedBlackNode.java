package top.iyuhao.utilclass;

/**
 * @author yuhao
 * @time 2024/1/29 11:28
 **/
public class RedBlackNode<E> {
    public int key;
    public E val;
    public RedBlackNode<E> left;
    public RedBlackNode<E> right;
    public RedBlackNode<E> parent;
    public RedBlackColor color = RedBlackColor.RED;

    public RedBlackNode(int key) {
        this.key = key;
    }

    public RedBlackNode(int key, E val) {
        this.key = key;
        this.val = val;
    }

    public RedBlackNode(int key, E val, RedBlackNode<E> left, RedBlackNode<E> right, RedBlackNode<E> parent, RedBlackColor color) {
        this.key = key;
        this.val = val;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.color = color;
    }

    public RedBlackNode(int key, E val, RedBlackNode<E> left, RedBlackNode<E> right, RedBlackNode<E> parent) {
        this.key = key;
        this.val = val;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public RedBlackNode(int key, E val, RedBlackNode<E> parent) {
        this.key = key;
        this.val = val;
        this.parent = parent;
    }

    /**
     * 是否是左孩子
     * @return
     */
    public boolean ifLeftChild(){
        return parent != null && parent.left == this; // 如果是左孩子，返回true，否则返回false
    }
    public boolean ifRightChild(){
        return parent != null && parent.right == this; // 如果是右孩子，返回true，否则返回false
    }

    /**
     * 叔叔
     * @return
     */
    public RedBlackNode<E> uncle(){
        if(parent == null || parent.parent == null){
            return null;
        }
        if(parent.ifLeftChild()){
            return parent.parent.right;
        }
        return parent.parent.left; // 叔叔是右孩子或者左孩子
    }
    public RedBlackNode<E> sibling(){
        if (parent == null) {
            return null;
        }
        if (ifLeftChild()) {
            return parent.right;
        }
        return parent.left; // 兄弟是右孩子或者左孩子
    }
}
