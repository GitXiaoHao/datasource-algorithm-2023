package top.iyuhao.algorithm.heap;

import java.util.Arrays;

/**
 * @author yuhao
 * @time 2024/1/25 10:40
 **/
public class MyHeap {
    public int[] array;
    private int size;
    private int capacity;
    /**
     * 判断是大顶堆还是小顶堆
     * 如果为true则为大顶堆
     * 如果为false 则为小顶堆
     */
    private boolean max;
    public MyHeap(int[] array,boolean max) {
        this.array = array;
        this.max = max;
        size = capacity = array.length;
        heapify();
    }

    public MyHeap(int capacity,boolean max) {
        this.capacity = capacity;
        this.max = max;
        this.array = new int[capacity];
        this.size = 0;
    }



    public int peek() {
        if (size == 0) {
            throw new IllegalArgumentException("数组无元素");
        }
        return array[0];
    }

    public int poll() {
        if (size == 0) {
            throw new IllegalArgumentException("数组无元素");
        }
        int top = array[0];
        swap(0, --size);
        down(0);
        return top;
    }

    public int poll(int index) {
        if (size == 0) {
            throw new IllegalArgumentException("数组无元素");
        }
        int del = array[index];
        swap(index, --size);
        down(index);
        return del;
    }

    private void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }

    /**
     * 替换堆顶元素
     *
     * @param replaced
     */
    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    public boolean offer(int offered) {
        if (size == capacity) {
            grow();
        }
        up(offered);
        size++;
        return true;
    }
    private void grow(){
        int newCapacity = Math.max(capacity + capacity >> 1, size + 1);
        array = Arrays.copyOf(array, newCapacity);
        capacity = newCapacity;
    }

    /**
     * 将inserted元素上浮；直至offered小于父元素或到堆顶
     *
     * @param offered
     */
    private void up(int offered) {
        int child = size;
        while (child > 0) {
            int parent = (child - 1) / 2;
            boolean cmp = this.max ? offered > array[parent] : offered < array[parent];
            if (!cmp) {
                break; //offered小于父元素或到堆顶，上浮结束。
            }
            array[child] = array[parent];//交换offered与父元素位置。
            child = parent;
        }
        array[child] = offered;
    }

    /**
     * 将parent索引处的元素下潜：与两个孩子较大者交换，直至没有孩子或孩子没他大
     *
     * @param parent
     */
    private void down(int parent) {
        //左孩子
        int left = parent * 2 + 1;
        int right = parent * 2 + 2;
        int max = parent;
        if(left < size && (this.max ? array[left] > array[max] : array[left] < array[max])){
            max = left;
        }
        if(right < size && (this.max ? array[right] > array[max] : array[right] < array[max])){
            max = right;
        }
        if (max != parent) {
            swap(max, parent);
            down(max);
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public boolean isFull(){
        return size == capacity;
    }

    public int size() {
        return size;
    }
}
