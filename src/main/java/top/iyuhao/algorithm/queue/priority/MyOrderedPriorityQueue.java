package top.iyuhao.algorithm.queue.priority;

import top.iyuhao.algorithm.queue.MyQueue;

/**
 * @author yuhao
 * @time 2024/1/16 11:05
 * 有序数组队列
 **/
public class MyOrderedPriorityQueue<E extends Priority> implements MyQueue<E> {
    private Priority[] array;
    private int size;
    private int capacity;

    public MyOrderedPriorityQueue(int capacity) {
        this.capacity = capacity;
        array = new Priority[capacity];
        size = 0;
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        insert(value);
        size++;
        return true;
    }

    private void insert(E value) {
        int i = size - 1;
        while (i >= 0 && value.priority() > array[i].priority()) {
            array[i + 1] = array[i];
            i--;
        }
        array[i + 1] = value;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E e = (E) array[size - 1];
        array[--size] = null;
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[size - 1];
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
