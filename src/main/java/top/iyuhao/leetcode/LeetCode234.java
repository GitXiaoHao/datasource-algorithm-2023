package top.iyuhao.leetcode;

/**
 * @author yuhao
 * @time 2023/12/21 14:15
 * @desc 234. 回文链表
 **/
public class LeetCode234 {
    public boolean isPalindrome(LeetCode206.ListNode head) {
        LeetCode206.ListNode middle = middleNode(head);
        LeetCode206.ListNode tail = reverseList(middle);
        while (tail != null) {
            if (head.val != tail.val) {
                return false;
            }
            head = head.next;
            tail = tail.next;
        }
        return true;
    }
    public LeetCode206.ListNode reverseList(LeetCode206.ListNode head) {
        LeetCode206.ListNode pre = null;
        //3 2 1
        LeetCode206.ListNode cur = head;
        while (cur != null) {
            // 2
            LeetCode206.ListNode next = cur.next;
            // 3 -> null
            cur.next = pre;
            // null -> 3
            pre = cur;
            // 2 -> 1
            cur = next;
        }
        return pre;
    }

    /**
     * 找到中间节点
     * @param head
     * @return
     */
    private LeetCode206.ListNode middleNode(LeetCode206.ListNode head) {
        LeetCode206.ListNode slow = head;
        LeetCode206.ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 找中间点的同时反转前半个链表
     * 反转后的前半个链表 与 中间点开始的后半个链表逐一比较
     * @param head
     * @return
     */
    public boolean isPalindrome2(LeetCode206.ListNode head) {
        LeetCode206.ListNode p1 = head;
        LeetCode206.ListNode p2 = head;
        //反转前半个链表
        LeetCode206.ListNode newHead = null;
        //旧头
        LeetCode206.ListNode oldHead = head;
        //找中间点
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            // 反转链表
            oldHead.next = newHead;
            newHead = oldHead;
            oldHead = p1;
        }
        // 奇数个节点
        if (p2 != null) {
            p1 = p1.next;
        }
        // 偶数个节点
        // 比较
        while (newHead != null) {
            if (newHead.val != p1.val) {
                return false;
            }
            newHead = newHead.next;
            p1 = p1.next;
        }
        return true;
    }
}
