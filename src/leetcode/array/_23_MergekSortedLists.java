package leetcode.array;

/**
 * @author zhouxinghang
 * @date 2019-07-29
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class _23_MergekSortedLists {

    /**
     * 1：第一个想到的方法，依次比较每个链表的头结点，取最小的那一个
     * 2：分治，两两合并
     * 这里采用第二种方法
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return doMerge(lists, 0, lists.length - 1);
    }

    private ListNode doMerge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = (left + right) / 2;
        ListNode l1 = doMerge(lists, left, mid);
        ListNode l2 = doMerge(lists, mid+1, right);
        return mergeTwoList(l1, l2);
    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoList(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoList(l1, l2.next);
            return l2;
        }
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}


