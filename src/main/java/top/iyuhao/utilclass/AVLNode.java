package top.iyuhao.utilclass;

/**
 * @author yuhao
 * @time 2024/1/28 18:06
 **/
public class AVLNode<E> {
    public E value;
    public AVLNode<E> left;
    public AVLNode<E> right;
    public int height;

    public AVLNode(E value) {
        this.value = value;
    }

    public AVLNode(E value, int height) {
        this.value = value;
        this.height = height;
    }

    public AVLNode(E value, AVLNode<E> left, AVLNode<E> right, int height) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.height = height;
    }

    public AVLNode(E value, AVLNode<E> left, AVLNode<E> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }


}
