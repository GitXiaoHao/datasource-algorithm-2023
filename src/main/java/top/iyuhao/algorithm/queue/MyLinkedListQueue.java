package top.iyuhao.algorithm.queue;

import java.util.Iterator;

/**
 * @author yuhao
 * @time 2023/12/29 22:42
 **/
public class MyLinkedListQueue<E> implements MyQueue<E>, Iterable<E> {
    /**
     * 哨兵节点
     */
   private MyNode<E> head;
    private MyNode<E> tail;
    /**
     * 节点数
     */
    private int size;
    /**
     * 队列容量，默认为Integer.MAX_VALUE，表示无限大小队列，可以根据实际情况进行调整，
     */
    private int capacity;
    {
        head = new MyNode<E>(null, null);
        tail = head;
        this.tail.next = head;
        this.size = 0;
    }


    public MyLinkedListQueue() {
        this.capacity = Integer.MAX_VALUE;
    }
    public MyLinkedListQueue(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }
    public int size() {
        return this.size;
    }
    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            head.next = new MyNode<>(value, head);
            tail = head.next;
            size++;
            return true;  //如果是空队列，直接插入并返回true
        }
        //最后一个节点 next指向head tail指向这个节点 上一个节点指向这个节点 而tail指向上一个节点
        MyNode<E> addNode = new MyNode<>(value, head);
        tail.next = addNode;
        tail = addNode;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        MyNode<E> next = head.next;
        E value = next.value;
        head.next = next.next;
        size--;
        return value;
    }

    @Override
    public E peek() {
        return isEmpty() ? null : head.next.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return this.size == this.capacity;
    }

    private static class MyNode<E> {
        E value;
        MyNode<E> next;

        public MyNode(E value, MyNode<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    private class Itr implements java.util.Iterator<E> {
        MyNode<E> p = head.next;

        @Override
        public boolean hasNext() {
            return p != head;
        }

        @Override
        public E next() {
            E value = p.value;
            p = p.next;
            return value;
        }
    }
}
