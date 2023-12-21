package top.iyuhao.leetcode;

/**
 * leet 83
 *
 * @author yuhao
 * @time 2023/12/21 10:45
 **/
public class LeetCode83 {
    /**
     * 删除排序链表中的重复元素
     *
     * @param head
     * @return
     */
    public LeetCode206.ListNode deleteDuplicates(LeetCode206.ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //使用双指针
        LeetCode206.ListNode cur = head;
        LeetCode206.ListNode next = cur.next;
        //当前节点和下一个节点的值相等，则将下一个节点的next指向下一个节点的next，直到不相等为止
        while (next != null) {
            if (cur.val == next.val) {
                cur.next = next.next;
            } else {
                cur = cur.next;
            }
            next = next.next;
        }
        return head;
    }

    public LeetCode206.ListNode deleteDuplicates2(LeetCode206.ListNode p) {
        if (p == null || p.next == null) {
            return p;
        }
        // 当前节点和下一个节点的值相等，则将下一个节点的next指向下一个节点的next，直到不相等为止
        if (p.val == p.next.val) {
            return deleteDuplicates2(p.next);
        } else {
            // 不相等，则将当前节点的next指向下一个节点的next，并返回当前节点。
            p.next = deleteDuplicates2(p.next);
            return p;
        }
    }
}
