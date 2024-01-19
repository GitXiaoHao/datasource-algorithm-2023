package top.iyuhao.algorithm.queue.priority;

import top.iyuhao.algorithm.queue.MyQueue;

/**
 * @author yuhao
 * @time 2024/1/16 14:20
 * 大顶堆优先级队列
 **/
@SuppressWarnings("all")
public class LargeTopReactorPriorityQueue<E extends Priority> implements MyQueue<E> {
    private Priority[] array;
    private int size;
    private int capacity;
    public LargeTopReactorPriorityQueue(int capacity) {
        this.capacity = capacity;
        array = new Priority[capacity];
        size = 0;
    }

    /**
     * 入堆新元素，加入到数组末尾（索引位置child）
     *  不断比较新加元素与它父节点（parent）优先级
     *      - 如果父节点优先级低，则向下移动，并找到下一个parent
     *      - 直至父节点优先级更高或child == 0为止
     * @param value 待插入值
     * @return
     */
    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        int child = size++;
        int parent = (child - 1) / 2;
        while (child > 0 && value.priority() > array[parent].priority()) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = value;
        return true;
    }

    /**
     * 1. 交换堆顶和尾部元素，让尾部元素出队
     * 2.（下潜）
     *      - 从堆顶开始，将父元素与两个孩子较大者交换
     *      - 直到父元素大于两个孩子，或者没有孩子为止
     * @return
     */
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
        //假设父元素优先级最高
        int max = parent;
        if (left < size && array[left].priority() > array[max].priority()) {
            max = left;
        }
        if (right < size && array[right].priority() > array[max].priority()) {
            max = right;
        }
        if (max != parent) {
            swap(max, parent);
            sink(max); //递归下潜，直至父元素优先级最高，或者没有孩子为止。
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
        if (isEmpty()) {
            return null;
        }
        return (E) array[0];
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
