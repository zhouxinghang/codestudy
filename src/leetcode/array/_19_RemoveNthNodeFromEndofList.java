package leetcode.array;

/**
 * @author zhouxinghang
 * @date 2019-07-26
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 */
public class _19_RemoveNthNodeFromEndofList {

    /**
     * 采用两个指针，间隔 n+1 步，后面的节点的下一个节点就是要删除的节点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode left = preHead, right = preHead;
        for (int i = 0; i < n+1; i++) {
            right = right.next;
        }
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        // 有可能删除的是第一个节点
        return preHead.next;
    }
}


class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}