package top.iyuhao.algorithm;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author yuhao
 * @date 2023/10/18
 * 动态数组
 **/
public class DynamicArray<T> implements Iterable<T>, Serializable {
    /**
     * 数组当前大小
     */
    private int size = 0;
    /**
     * 数组容量
     */
    private int capacity;
    private Object[] arrayData;

    public DynamicArray() {
        capacity = 8;
    }

    public DynamicArray(int capacity) {
        this.capacity = capacity;
    }

    @SuppressWarnings("unchecked")
    T elementData(int index) {
        return (T) arrayData[index];
    }

    @SuppressWarnings("unchecked")
    <E> E elementAt(Object[] es, int index) {
        return (E) es[index];
    }

    public void add(int element) {
        add(size, element);
    }

    public void add(int index, int element) {
        Objects.checkIndex(index,size);
        //判断容量是否充足
        grow();
        //懒汉式
        if (index == 0) {
            arrayData = new Object[capacity];
        }
        //copy
        if (index != size) {
            System.arraycopy(arrayData, index, arrayData, index + 1, size - index);
        }
        //赋值
        arrayData[index] = element;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException("indexOutRoundError");
        }
        return elementData(index);
    }

    public T remove(int index) {
        @SuppressWarnings("unchecked")
        T oldValue = (T) arrayData[index];
        if (index != size - 1) {
            System.arraycopy(arrayData, index + 1, arrayData, index, size - index - 1);
        }
        arrayData[index] = null;
        return oldValue;
    }

    /**
     * 数组扩容
     */
    public void grow() {
        if (size >= capacity) {
            int newCapacity = Math.max(capacity + capacity >> 1, size + 1);
            arrayData = Arrays.copyOf(arrayData, newCapacity);
            capacity = newCapacity;
        }
    }

    /**
     * 遍历方式1
     *
     * @param action The action to be performed for each element
     */
    @Override
    public void forEach(Consumer<? super T> action) {
        final Object[] es = arrayData;
        for (int i = 0; i < size; i++) {
            action.accept(elementAt(es, i));
        }
    }

    @Override
    public String toString() {
        Object[] data = Arrays.copyOf(arrayData, size);
        return Arrays.toString(data);
    }

    /**
     * 遍历方式二 增强for循环
     *
     * @return Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public T next() {
                return elementData(i++);
            }
        };
    }

    /**
     * 遍历方式三 stream流
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public Stream<T> stream() {
        return (Stream<T>) Stream.of(Arrays.copyOfRange(arrayData, 0, size));
    }
}
