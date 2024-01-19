package top.iyuhao.algorithm.deque;

import top.iyuhao.utilclass.MyDeNode;

import java.util.Iterator;

/**
 * @author yuhao
 * @time 2024/1/15 16:00
 * @desc 链表双端队列 基于双向环型链表
 **/
public class LinkedListDeque<E> implements MyDeque<E>, Iterable<E> {
    private int capacity;
    private int size;
    private MyDeNode<E> sentinel;

    {
        sentinel = new MyDeNode<>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public LinkedListDeque(int capacity) {
        this.capacity = capacity;

    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            MyDeNode<E> p = sentinel.next;

            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public E next() {
                E val = p.val;
                p = p.next;
                return val;
            }
        };
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull()) {
            return false;
        }
        MyDeNode<E> f = sentinel;
        MyDeNode<E> l = sentinel.next;
        MyDeNode<E> addNode = new MyDeNode<>(e, f, l);
        f.next = addNode;
        l.prev = addNode;
        size++;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()) {
            return false;
        }
        MyDeNode<E> f = sentinel.prev;
        MyDeNode<E> l = sentinel;
        MyDeNode<E> addNode = new MyDeNode<>(e, f, l);
        f.next = addNode;
        l.prev = addNode;
        size++;
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        MyDeNode<E> prev = sentinel;
        MyDeNode<E> remove = sentinel.next;
        MyDeNode<E> next = remove.next;
        prev.next = next;
        next.prev = prev;
        size--;
        return remove.val;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        MyDeNode<E> next = sentinel;
        MyDeNode<E> remove = sentinel.prev;
        MyDeNode<E> prev = remove.prev;
        prev.next = next;
        next.prev = prev;
        size--;
        return remove.val;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.next.val;
    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.prev.val;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }
}
