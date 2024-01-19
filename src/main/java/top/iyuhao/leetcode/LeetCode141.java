package top.iyuhao.leetcode;

/**
 * @author yuhao
 * @time 2023/12/21 14:54
 * @desc 141. 环形链表
 **/
public class LeetCode141 {
    /**
     * 弗洛伊德龟兔问题
     * 两个指针，一个快，一个慢，如果相遇，则有环
     * @param head
     * @return
     */
    public boolean hasCycle(LeetCode206.ListNode head) {
        //兔子
        LeetCode206.ListNode rabbit = head;
        //乌龟
        LeetCode206.ListNode turtle = head;
        while (rabbit != null && rabbit.next != null) {
            rabbit = rabbit.next.next;
            turtle = turtle.next;
            if (rabbit == turtle) {
                return true;
            }
        }
        return false;
    }
}
