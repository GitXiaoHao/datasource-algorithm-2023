package top.iyuhao.leetcode;

/**
 * @author yuhao
 * @time 2023/12/21 10:31
 * leet19
 **/
public class LeetCode19 {
    public LeetCode206.ListNode removeNthFromEnd(LeetCode206.ListNode head, int n) {
        LeetCode206.ListNode s = new LeetCode206.ListNode(-1,head);
        recursion(s, n);
        return s.next;
    }

    /**
     * 递归调用 返回下一个节点的倒数位置
     *
     * @param p
     * @param n
     * @return
     */
    private int recursion(LeetCode206.ListNode p, int n) {
        if (p == null) {
            return 0;
        }
        //下一个节点的倒数位置
        int nth = recursion(p.next, n);
        if (nth == n) {
            p.next = p.next.next;
        }
        return nth + 1;
    }

    /**
     * 方法二：双指针
     * @param head  头节点  用于返回头节点  不用于删除节点  用于返回头节点  不用于删除节点  用于返回头节点
     * @param n  删除节点的倒数第n个节点  n=1 删除倒数第一个节点  n=2 删除倒数第二个节点  n=3 删除倒数第三个节点  n=4 删�
     * @return  返回头节点  不用于删除节点  用于返回头节点  不用于删除节点  用于返回头节点  不用于删除节点
     */
    public LeetCode206.ListNode removeNthFromEnd2(LeetCode206.ListNode head, int n) {
        LeetCode206.ListNode s = new LeetCode206.ListNode(-1,head);
        LeetCode206.ListNode p = s;
        LeetCode206.ListNode q = s;
        while (n-- > 0) {
            q = q.next;
        }
        while (q.next != null) {
            p = p.next;
            q = q.next;
        }
        p.next = p.next.next;
        return s.next;
    }
    public static void main(String[] args) {
        LeetCode19 leetCode19 = new LeetCode19();
        LeetCode206.ListNode head = new LeetCode206.ListNode(1, new LeetCode206.ListNode(2, new LeetCode206.ListNode(6, new LeetCode206.ListNode(3, new LeetCode206.ListNode(4, new LeetCode206.ListNode(5, new LeetCode206.ListNode(6)))))));
        head = new LeetCode206.ListNode(7, new LeetCode206.ListNode(7, new LeetCode206.ListNode(7, new LeetCode206.ListNode(7))));
        LeetCode206.ListNode listNode = leetCode19.removeNthFromEnd(head, 7);
        System.out.println(listNode);
        System.out.println(listNode.next.next.next.next.next.next);
        System.out.println(listNode.next.next.next.next.next.next.next);

    }
}
