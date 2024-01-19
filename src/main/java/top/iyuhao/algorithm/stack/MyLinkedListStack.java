package top.iyuhao.algorithm.stack;

import top.iyuhao.utilclass.MyNode;

import java.util.Iterator;

/**
 * @author yuhao
 * @time 2024/1/9 11:29
 **/
public class MyLinkedListStack <E> implements MyStack<E> ,Iterable<E> {
    private int capacity;
    private int size;
    private MyNode<E> head;
    {
        head = new MyNode<>(null,null);
    }

    public MyLinkedListStack(int capacity) {
        this.capacity = capacity;
    }

    public MyLinkedListStack() {
        this.capacity = Integer.MAX_VALUE;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            MyNode<E> p = head.next;
            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    @Override
    public boolean push(E value) {
        if (isFull()) {
            return false;
        }
        head.next = new MyNode<>(value,head.next);
        size++;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E value = head.next.value;
        head.next = head.next.next;
        size--;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.next.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public int size() {
        return size;
    }
}
