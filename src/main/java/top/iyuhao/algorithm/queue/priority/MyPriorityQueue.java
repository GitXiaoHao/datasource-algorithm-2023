package top.iyuhao.algorithm.queue.priority;

import top.iyuhao.algorithm.queue.MyQueue;

/**
 * @author yuhao
 * @time 2024/1/15 19:36
 * @description 实现优先队列 基于无序数组实现
 * 必须实现Priority接口
 **/
public class MyPriorityQueue<E extends Priority> implements MyQueue<E> {
    private Priority[] array;
    private int size;
    private int capacity;

    public MyPriorityQueue(int capacity) {
        this.capacity = capacity;
        array = new Priority[capacity];
        size = 0;
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[size++] = value;
        return true;
    }

    /**
     * 返回优先级最高的索引值
     *
     * @return
     */
    private int selectMax() {
        int max = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].priority() > array[max].priority()) {
                max = i;
            }
        }
        return max;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E e = (E) array[selectMax()];
        remove(selectMax());
        return e;
    }

    private void remove(int index) {
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
        }
        array[--size] = null;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[selectMax()];
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
