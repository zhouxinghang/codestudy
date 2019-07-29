package leetcode.array;

/**
 * @author zhouxinghang
 * @date 2019-07-29
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 两两交换相邻节点意思是：如果有5个节点--》 swap(1,2) + swap(3,4)
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class _24_SwapNodesinPairs {

    /**
     * 采用递归的方式。多设置两个节点prev 和 tail，prev.next = head, tail = next.next
     * 对于每一个单元，我们要做的是交换 head 和 head.next，返回 prev.next
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }
}
