package top.iyuhao.algorithm.heap;

import java.util.Arrays;

/**
 * @author yuhao
 * @time 2024/1/24 16:43
 * 大顶堆
 **/
public class MyMaxHeap {
    private int[] array;
    private int size;
    private int capacity;

    public MyMaxHeap(int[] array) {
        this.array = array;
        size = capacity = array.length;
        heapify();
    }

    public MyMaxHeap(int capacity) {
        this.capacity = capacity;
        this.array = new int[capacity];
        this.size = 0;
    }

    public static void main(String[] args) {
        //int[] array = { 1, 2, 3, 4, 5, 6, 7 };
        int[] array = { 2, 3, 1, 7, 6, 4, 5 };
        MyMaxHeap myMaxHeap = new MyMaxHeap(array);
        myMaxHeap.sort();
        System.out.println(Arrays.toString(myMaxHeap.array));
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
        if (size == array.length) {
            return false;
        }
        up(offered);
        size++;
        return true;
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
            if (offered > array[parent]) {
                array[child] = array[parent];
            } else {
                break;
            }
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
        if (left < size && array[left] > array[max]) {
            max = left;
        }
        if (right < size && array[right] > array[max]) {
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

    public void sort() {
        int sizeSort = size;
        while (size > 1) {
            this.swap(0, --size);
            down(0);
        }
        size = sizeSort;
    }
}
