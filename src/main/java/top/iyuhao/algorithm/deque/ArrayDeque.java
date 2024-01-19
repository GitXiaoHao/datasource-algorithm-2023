package top.iyuhao.algorithm.deque;

import java.util.Iterator;

/**
 * @author yuhao
 * @time 2024/1/15 16:27
 * @description 双端队列
 **/
public class ArrayDeque<E> implements MyDeque<E>,Iterable<E>{
    private E[] array;
    private int head;
    private int tail;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayDeque(int size) {
        this.size = 0;
        array = (E[]) new Object[size + 1];
    }

    /**
     * 数组下表自增
     * @param i 当前下标
     * @param length 数组长度
     * @return +1下标
     */
    private static int inc(int i, int length){
        if(i +1 >= length){
            return 0;
        }
        return i + 1;
    }

    /**
     * 数组下表自减
     * @param i 当前下标
     * @param length 数组长度(队列长度) 下标从0开始，队列长度为length - 1 下标从1开始，队列
     * @return -1下标
     */
    private static int dec(int i, int length){
        if(i - 1 < 0){
            return length - 1;
        }
        return i - 1;
    }

    /**
     * 先head-- 再添加元素
     * @param e
     * @return
     */
    @Override
    public boolean offerFirst(E e) {
        if (isFull()) {
            return  false;
        }
        head = dec(head,array.length);
        array[head] = e;
        size++;
        return true;
    }

    /**
     * 先添加元素 再tail++
     * @param e
     * @return
     */
    @Override
    public boolean offerLast(E e) {
        if (isFull()) {
            return false;
        }
        array[tail] = e;
        tail = inc(tail,array.length);
        size++;
        return true;
    }

    /**
     * 先取元素 再head++
     * @return
     */
    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        E e = array[head];
        array[head] = null; // 释放内存，防止GC引起的内存泄露，如果不释放，队列中的元素会一直存
        head = inc(head,array.length);
        size--;
        return e;
    }

    /**
     * 先tail-- 再获取要移除的值
     * @return
     */
    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        tail = dec(tail,array.length);
        E e = array[tail];
        array[tail] = null;
        size--;
        return e;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return array[head];
    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return array[dec(tail,array.length)];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        if (tail > head) {
            return (tail - head) == array.length - 1;
        } else if (head > tail) {
            return head - tail == 1;
        }
        return false;
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
                E e = array[index];
                index = inc(index,array.length);
                return e;
            }
        };
    }
}
