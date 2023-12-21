package top.iyuhao.leetcode;

/**
 * @author yuhao
 * @time 2023/12/21 11:07
 * @desc 21. 合并两个有序链表
 **/
public class LeetCode21 {
    /**
     * 哨兵
     * @param list1 第一个有序链表
     * @param list2 第二个有序链表
     * @return
     */
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
    public LeetCode206.ListNode mergeTwoLists2(LeetCode206.ListNode list1, LeetCode206.ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if(list1.val < list2.val){
            list1.next = mergeTwoLists2(list1.next, list2);
            return list1;
        }else {
            list2.next =  mergeTwoLists2(list1, list2.next);
            return list2;
        }
    }
}
