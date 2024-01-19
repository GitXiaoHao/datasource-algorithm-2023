package top.iyuhao.leetcode;

/**
 * @author yuhao
 * @time 2023/12/21 14:57
 * @desc 142. 环形链表 II
 **/
public class LeetCode142 {
    /**
     * 首先判断是否为环
     * 如果是 就让乌龟返回头节点 兔子和乌龟再次相遇 就是入口
     * @param head
     * @return
     */
    public LeetCode206.ListNode detectCycle(LeetCode206.ListNode head) {
        //兔子
        LeetCode206.ListNode rabbit = head;
        //乌龟
        LeetCode206.ListNode turtle = head;
        while (rabbit != null && rabbit.next != null) {
            rabbit = rabbit.next.next;
            turtle = turtle.next;
            if (rabbit == turtle) {
                //进入环形 找入口
                // 乌龟回到头节点
                turtle = head;
                while (rabbit != turtle) {
                    rabbit = rabbit.next;
                    turtle = turtle.next;
                    if (rabbit == turtle) {
                        break;
                    }
                }
                return rabbit;
            }
        }
        return null;
    }
}
