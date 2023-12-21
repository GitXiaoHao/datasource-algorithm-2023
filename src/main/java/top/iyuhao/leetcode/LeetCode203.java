package top.iyuhao.leetcode;

/**
 * @author yuhao
 * @time 2023/11/1
 * 根据值删除节点
 **/
public class LeetCode203 {
    public LeetCode206.ListNode removeElements(LeetCode206.ListNode head, int val) {
        LeetCode206.ListNode dummy = new LeetCode206.ListNode(0, head);
        LeetCode206.ListNode prev = dummy;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return dummy.next;
    }

    /**
     * 方法二
     * @param p 链表头
     * @param val 目标值
     * @return
     */
    public LeetCode206.ListNode removeElement(LeetCode206.ListNode p, int val){
        if (p == null) {
            return  null;
        }
        if(p.val == val){
            return removeElement(p.next, val);
        }else{
            p.next = removeElement(p.next, val);
            return p; // 返回头结点，这样就可以实现链表的删除操作。
        }
    }

    public static void main(String[] args) {
        LeetCode206.ListNode head = new LeetCode206.ListNode(1, new LeetCode206.ListNode(2, new LeetCode206.ListNode(6, new LeetCode206.ListNode(3, new LeetCode206.ListNode(4, new LeetCode206.ListNode(5, new LeetCode206.ListNode(6)))))));
        head = new LeetCode206.ListNode(7, new LeetCode206.ListNode(7, new LeetCode206.ListNode(7, new LeetCode206.ListNode(7))));
        LeetCode206.ListNode listNode = new LeetCode203().removeElement(head, 7);
        System.out.println(listNode);
    }
}
