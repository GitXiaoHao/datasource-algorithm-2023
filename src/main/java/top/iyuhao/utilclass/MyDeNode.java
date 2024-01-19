package top.iyuhao.utilclass;

/**
 * @author yuhao
 * @time 2024/1/15 16:00
 **/
public class MyDeNode<E> {
    public E val;
    public MyDeNode<E> next;
    public MyDeNode<E> prev;

    public MyDeNode(E val) {
        this.val = val;
    }

    public MyDeNode(E val, MyDeNode<E> next, MyDeNode<E> prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }

    public MyDeNode() {
    }

    @Override
    public String toString() {
        return "MyDeNode{" +
                "val=" + val +
                ", next=" + next +
                ", prev=" + prev +
                '}';
    }

    public E getVal() {
        return val;
    }

    public void setVal(E val) {
        this.val = val;
    }

    public MyDeNode<E> getNext() {
        return next;
    }

    public void setNext(MyDeNode<E> next) {
        this.next = next;
    }

    public MyDeNode<E> getPrev() {
        return prev;
    }
}
