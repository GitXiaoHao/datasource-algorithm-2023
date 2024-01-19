package top.iyuhao.algorithm.deque;

/**
 * @author yuhao
 * @time 2024/1/15 16:06
 **/
public interface MyDeque <E>{
    boolean offerFirst(E e);
    boolean offerLast(E e);
    E pollFirst();
    E pollLast();
    E peekFirst();
    E peekLast();
    int size();
    boolean isEmpty();
    boolean isFull();
}
