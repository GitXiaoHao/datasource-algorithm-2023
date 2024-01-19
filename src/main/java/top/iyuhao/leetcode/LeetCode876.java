package top.iyuhao.leetcode;

/**
 * @author yuhao
 * @time 2023/12/21 14:10
 * @description: 链表的中间结点
 **/
public class LeetCode876 {
    /**
     * 快慢指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * <p>
     * @param head 链表头节点
     * @return 中间结点
     */
    public LeetCode206.ListNode middleNode(LeetCode206.ListNode head) {
        LeetCode206.ListNode slow = head;
        LeetCode206.ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
