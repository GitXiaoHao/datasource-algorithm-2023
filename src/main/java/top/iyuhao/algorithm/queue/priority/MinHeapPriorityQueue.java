package top.iyuhao.algorithm.queue.priority;

import top.iyuhao.algorithm.queue.MyQueue;

/**
 * @author yuhao
 * @time 2024/1/16 20:11
 * 小顶堆
 **/
public class MinHeapPriorityQueue<E extends Priority> implements MyQueue<E> {
    private Priority[] array;
    private int size;
    private int capacity;
    public MinHeapPriorityQueue(int capacity) {
        this.capacity = capacity;
        array = new Priority[capacity];
        size = 0;
    }
    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        int child = size++;
        int parent = (child - 1) / 2;
        while (child > 0 && value.priority() < array[parent].priority()) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = value;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        swap(0, --size);
        Priority priority = array[size];
        array[size] = null;
        //下潜
        sink(0);
        return (E) priority;
    }
    private void sink(int parent) {
        //左孩子
        int left = 2 * parent + 1;
        //右孩子
        int right = 2 * parent + 2;
        //假设父元素优先级最小
        int min = parent;
        if (left < size && array[left].priority() < array[min].priority()) {
            min = left;
        }
        if (right < size && array[right].priority() < array[min].priority()) {
            min = right;
        }
        if (min != parent) {
            swap(min, parent);
            sink(min); //递归下潜，直至父元素优先级最高，或者没有孩子为止。
        }
    }
    /**
     * 交换两个元素位置，并且更新堆结构
     * @param i 下标为i的元素交换位置，下标为j的元素交换位置
     * @param j 下标为i的元素交换位置，下标为j的元素交换位置
     */
    private void swap(int i, int j) {
        Priority temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public E peek() {
        return null;
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
