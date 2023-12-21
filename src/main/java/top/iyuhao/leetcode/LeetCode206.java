package top.iyuhao.leetcode;

/**
 * @author yuhao
 * @time 2023/10/28
 * 反转链表
 **/
public class LeetCode206 {


    public static void main(String[] args) throws CloneNotSupportedException {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
        LeetCode206 code206 = new LeetCode206();
        //System.out.println(code206.reverseList1(o1.clone()));
        //System.out.println(code206.reverseList2(o1.clone()));
        //System.out.println(code206.reverseList3(o1.clone()));
        //System.out.println(code206.reverseList4(o1));
        System.out.println(code206.reverseList5(o1));
    }

    public ListNode reverseList1(ListNode head) {
        ListNode newNode = null;
        ListNode temp = head;
        while (temp != null) {
            newNode = new ListNode(temp.val, newNode);
            temp = temp.next;
        }
        return newNode;
    }

    public ListNode reverseList2(ListNode head) {
        MyNode myNode = new MyNode(head);
        MyNode newNode = new MyNode(null);
        while (true){
            ListNode listNode = myNode.removeFirst();
            if (listNode == null) {
                break;
            }
            newNode.addFirst(listNode);
        }
        return newNode.head;
    }

    public ListNode reverseList3(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode listNode = reverseList3(node.next);
        // 反转链表
        node.next.next = node;
        node.next = null;
        // 返回链表头
        return listNode;
    }

    public ListNode reverseList4(ListNode node) {
        if(node == null || node.next == null){
            return node;
        }
        // 返回链表头
        ListNode old2 = node.next;
        // 反转链表
        ListNode new1 = node;
        // 反转链表
        while (old2 != null) {
            // 1 2 3 4 5
            node.next = old2.next;
            old2.next = new1;
            new1 = old2;
            old2 = node.next;
        }
        return new1;
    }
    public ListNode reverseList5(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode newHead = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = newHead;
            newHead = node;
            node = next;
        }
        return newHead; // 返回链表头
    }


    private static class MyNode {
        ListNode head;

        public MyNode(ListNode head) {
            this.head = head;
        }

        public void addFirst(ListNode node) {
            node.next = head;
            head = node;
        }

        public ListNode removeFirst() {
            ListNode first = head;
            if (first == null) {
                return null;
            }
            head = head.next;
            return first;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }



        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("[");
            ListNode node = this;
            while (node != null) {
                sb.append(node.val).append(",");
                node = node.next;
            }
            sb.deleteCharAt(sb.length() - 1); // 删除最后一个逗号
            sb.append("]");
            return sb.toString();
        }
    }
}
