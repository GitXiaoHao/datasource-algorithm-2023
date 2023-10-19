package top.iyuhao.algorithm;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author yuhao
 * @date 2023/10/18
 * 单向链表
 **/
public class SingleLinkedList<T> implements Iterable<T> {
    private final Node<T> head;
    private int size;

    public SingleLinkedList() {
        size = 0;
        head = new Node<>(null, null);
    }

    private Node<T> findLast() {
        Node<T> p = head;
        while (p.next != null) {
            p = p.next;
        }
        return p;
    }

    private void checkElement(Node<T> p) {
        Objects.requireNonNull(p, "index 不合法");
    }

    public void addFirst(T element) {
        head.next = new Node<>(element, head.next);
        size++;
    }

    public void addLast(T element) {
        Node<T> p = findLast();
        p.next = new Node<>(element, null);
        size++;
    }

    private Node<T> findByIndex(int index) {
        Objects.checkIndex(index, size);
        Node<T> p = head;
        while ((index--) >= 0 && p != null) {
            p = p.next;
        }
        return p;
    }

    public T get(int index) {
        Node<T> p = findByIndex(index);
        return p.value;
    }

    public void insert(int index, T e) {
        Objects.checkIndex(index, size + 1);
        if (index == size) {
            addLast(e);
        } else if (index == 0) {
            addFirst(e);
        }else {
            Node<T> p = findByIndex(index - 1);
            p.next = new Node<>(e, p.next);
            size++;
        }
    }
    public void removeFirst(){
        if (head.next == null) {
            throw new NoSuchElementException();
        }
        head.next = head.next.next;
        size--;
    }

    public void loop(Consumer<? super T> consumer) {
        Node<T> pointer = head;
        while ((pointer = pointer.next) != null) {
            consumer.accept(pointer.value);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new NodeIterator();
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private class NodeIterator implements Iterator<T> {
        Node<T> pointer = head;

        @Override
        public boolean hasNext() {
            return pointer.next != null;
        }

        @Override
        public T next() {
            pointer = pointer.next;
            return pointer.value;
        }
    }
}
