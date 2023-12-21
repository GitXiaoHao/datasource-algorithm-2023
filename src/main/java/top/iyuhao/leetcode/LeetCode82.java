package top.iyuhao.leetcode;

/**
 * @author yuhao
 * @time 2023/12/21 10:56
 * @description 82. 删除排序链表中的重复元素 II
 **/
public class LeetCode82 {
    /**
     * 递归删除相同节点
     * @param p 链表头节点，也是删除节点的前一个节点，如果删除的是头节点，p为null，如果删除的是尾节点，p为尾节点
     * @return 删除相同节点后的链表头节点，如果链表为空，返回null
     */
    public LeetCode206.ListNode deleteDuplicates(LeetCode206.ListNode p) {
        if (p == null || p.next == null) {
            return p;
        }
        if (p.val == p.next.val) {
            // 循环删除相同的节点
            while (p.next != null && p.val == p.next.val) {
                p = p.next;
            }
            return deleteDuplicates(p.next);
        } else {
            p.next = deleteDuplicates(p.next);
            return p;
        }
    }

    /**
     * 迭代删除相同节点
     * @param head
     * @return
     */
    public LeetCode206.ListNode deleteDuplicates2(LeetCode206.ListNode head) {
        if (head == null || head.next == null) {
            return  head;
        }
        LeetCode206.ListNode p1 = new LeetCode206.ListNode(-1,head);
        LeetCode206.ListNode p2,p3;
        while ((p2 = p1.next) != null
        && (p3 = p2.next) != null) {
            if (p2.val != p3.val) {
                p1 = p1.next;
            } else {
                while (p3 != null && p2.val == p3.val) {
                    p3 = p3.next;
                }
                p1.next = p3;
            }
        }
        return p1.next;
    }
}
