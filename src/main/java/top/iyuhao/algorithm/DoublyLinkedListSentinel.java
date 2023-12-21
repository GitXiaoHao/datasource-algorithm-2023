package top.iyuhao.algorithm;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author yuhao
 * @time 2023/10/23
 * 环形链表
 **/
public class DoublyLinkedListSentinel implements Iterable<Integer> {
    private Node sentinel;
    private int size;

    public DoublyLinkedListSentinel() {
        sentinel = new Node(0);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(int val) {
        //当前哨兵
        Node now = sentinel;
        Node p = sentinel.next;
        Node newNode = new Node(val, p, now);
        now.next = newNode;
        p.prev = newNode;
        size++;
    }

    public void addLast(int val) {
        Node now = sentinel.prev;
        Node p = sentinel;
        Node newNode = new Node(val, p, now);
        now.next = newNode;
        p.prev = newNode;
        size++;
    }
    public void removeFirst() {
        if (sentinel.next == sentinel) {
            throw new NoSuchElementException();
        }
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
    }
    public void removeLast() {
        if (sentinel.prev == sentinel) {
            throw new NoSuchElementException();
        }
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
    }
    public void removeByValue(int val) {
        Node p = findByValue(val);
        if (p == null) {
            throw new NoSuchElementException();
        }
        p.prev.next = p.next;
        p.next.prev = p.prev;
        size--;
        p.prev = null;
        p.next = null;
        p.val = 0;
        System.gc(); //回收垃圾，避免内存泄漏
    }
    private Node findByValue(int val) {
        Node p = sentinel;
        while (p.next != sentinel) {
            if (p.next.val == val) {
                return p.next;
            }
            p = p.next;
        }
        return null;
    }
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            DoublyLinkedListSentinel.Node p = sentinel;

            @Override
            public boolean hasNext() {
                return p.next != sentinel;
            }

            @Override
            public Integer next() {
                p = p.next;
                return p.val;
            }
        };
    }

    private static class Node {
        int val;
        Node next;
        Node prev;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }
}
