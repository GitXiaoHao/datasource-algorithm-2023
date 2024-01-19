package top.iyuhao.algorithm.stack;

import java.util.Iterator;

/**
 * @author yuhao
 * @time 2024/1/9 11:51
 **/
public class MyArrayStack <E> implements MyStack<E>,Iterable<E>{
    private E[] array;
    private int capacity;
    private int size;
    @SuppressWarnings("all")
    public MyArrayStack(int capacity) {
        this.capacity = capacity;
        this.array = (E[]) new Object[capacity];
    }
    {
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = size;
            @Override
            public boolean hasNext() {
                return p != 0;
            }

            @Override
            public E next() {
                return array[--p];
            }
        };
    }

    @Override
    public boolean push(E value) {
        if (isFull()) {
            return false;
        }
        array[size++] = value;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E value = array[--size];
        array[size] = null;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public int size() {
        return size;
    }
}
