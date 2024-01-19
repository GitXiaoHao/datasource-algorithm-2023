package top.iyuhao.algorithm.queue;

import java.util.Iterator;

/**
 * @author yuhao
 * @time 2024/1/5 19:25
 * 环型队列
 **/
public class MyCircularQueue<E> implements MyQueue<E>,Iterable<E>{
    private E[] array;
    private int head;
    private int tail;
    private int capacity;
    public MyCircularQueue(int capacity) {
        this.capacity = capacity;
        array = (E[]) new Object[capacity + 1];

    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = head;
            @Override
            public boolean hasNext() {
                return index != tail;
            }

            @Override
            public E next() {
                E value = array[index];
                index = (index + 1) % capacity;
                return value;
            }
        };
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail] = value;
        tail = (tail + 1) % this.capacity;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head];
        head = (head + 1) % this.capacity;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return (tail + 1) % this.capacity  == head;
    }
}
