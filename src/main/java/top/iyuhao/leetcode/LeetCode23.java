package top.iyuhao.leetcode;

/**
 * @author yuhao
 * @time 2023/12/21 11:19
 * @desc 合并K个升序链表
 **/
public class LeetCode23 {
    public LeetCode206.ListNode mergeTwoLists(LeetCode206.ListNode list1, LeetCode206.ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        LeetCode206.ListNode s = new LeetCode206.ListNode(-1, null);
        LeetCode206.ListNode p = s;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }
        if (list1 != null) {
            p.next = list1;
        }
        if (list2 != null) {
            p.next = list2;
        }
        return s.next;
    }

    public LeetCode206.ListNode mergeKLists(LeetCode206.ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return split(lists, 0, lists.length - 1);
    }

    /**
     * 返回合并后的链表
     *
     * @param lists
     * @param i     左边界
     * @param j     右边界
     * @return
     */
    private LeetCode206.ListNode split(LeetCode206.ListNode[] lists, int i, int j) {
        if (i == j) {
            return lists[i]; // 只剩一个链表，直接返回该链表。
        }
        int m = (i + j) >>> 1;
        LeetCode206.ListNode left = split(lists, i, m);
        LeetCode206.ListNode right = split(lists, m + 1, j);
        return mergeTwoLists(left, right);
    }
}
