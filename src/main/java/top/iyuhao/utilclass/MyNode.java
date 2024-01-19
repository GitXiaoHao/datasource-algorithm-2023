package top.iyuhao.utilclass;

/**
 * @author yuhao
 * @time 2024/1/9 11:30
 **/
public class MyNode <E>{
    public E value;
    public MyNode<E> next;

    public MyNode(E value, MyNode<E> next) {
        this.value = value;
        this.next = next;
    }
}
