package top.iyuhao.algorithm;

import java.util.LinkedList;
import java.util.Timer;

/**
 * @author yuhao
 * @date 2023/10/18
 * 单向链表
 **/
public class SingleLinkedList<T> {
    private Node<T> head;

    public SingleLinkedList() {
        head = new Node<>(null,null);
    }

    public void addFirst(T node){
        head.next = new Node<>(node,head.next);
    }
    private static class Node<T>{
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
