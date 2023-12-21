package top.iyuhao.algorithm;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author yuhao
 * {@code @date} 2023/10/22
 * 双向链表(带哨兵)
 **/
public class DoubleLinkedListSentinel implements Iterable<Integer> {
    private MyNode head;
    private MyNode tail;
    private int size;

    public DoubleLinkedListSentinel() {
        head = new MyNode(null, null, null);
        tail = new MyNode(null, null, null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    private MyNode findByIndex(int index) {
        MyNode p = head;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p;
    }

    public void addFirst(Integer e) {
        MyNode p = new MyNode(null, e, head.next);
        head.next.prev = p;
        head.next = p;
        size++;
    }

    public void addLast(Integer e) {
        MyNode p = new MyNode(tail.prev, e, tail);
        tail.prev.next = p;
        tail.prev = p;
        size++;
    }

    public void insert(int index, Integer e) {
        Objects.checkIndex(index, size + 1);
        MyNode p = findByIndex(index);
        MyNode newMyNode = new MyNode(p.prev, e, p);
        p.prev.next = newMyNode;
        p.prev = newMyNode;
        size++;
    }

    public void removeFirst() {
        if (head.next == tail || head.next == null) {
            throw new NoSuchElementException();
        }
        head.next = head.next.next;
        head.next.prev = head;
        size--;
    }

    public void removeLast() {
        if(tail.prev == head || tail.prev == null){
            throw new NoSuchElementException();
        }
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        size--;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            MyNode p = findByIndex(index);
            p.prev.next = p.next;
            p.next.prev = p.prev;
            size--;
            p.prev = null;
            p.next = null;
            p.value = null;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            MyNode p = head;

            @Override
            public boolean hasNext() {
                return p.next != tail;
            }

            @Override
            public Integer next() {
                p = p.next;
                return p.value;
            }
        };
    }

    private static class MyNode {
        MyNode prev;
        Integer value;
        MyNode next;

        public MyNode(MyNode prev, Integer value, MyNode next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}
