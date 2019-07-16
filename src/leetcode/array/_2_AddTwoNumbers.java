package leetcode.array;

/**
 * @author zhouxinghang
 * @date 2019-07-16
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 */
public class _2_AddTwoNumbers {

    /**
     * 将链表转为数字，然后相加，最后将结果转换为链表，这样如果链表过长的话，就会出问题
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long num1 = convertNumber(l1);
        long num2 = convertNumber(l2);
        long sum = num1 + num2;


        return converNode(sum);
    }

    /**
     * 直接迭代计算，考虑两数和超过 10 的 "进一" 处理
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int carried = 0;
        int sum = l1.val + l2.val;
        if (sum > 9) {
            carried = sum / 10;
            sum = sum - carried * 10;
        }
        ListNode result = new ListNode(sum);
        ListNode index = result;
        while (l1.next != null || l2.next != null) {
            int num1 = 0;
            int num2 = 0;
            if (l1.next != null) {
                num1 = l1.next.val;
                l1 = l1.next;
            }
            if (l2.next != null) {
                num2 = l2.next.val;
                l2 = l2.next;
            }
            sum = num1 + num2 + carried;
            if (sum > 9) {
                carried = sum / 10;
                sum = sum - carried * 10;
            } else {
                carried = 0;
            }
            index.next = new ListNode(sum);
            index = index.next;
        }
        // 最后还要考虑进的一位
        if (carried > 0) {
            index.next = new ListNode(carried);
        }
        return result;
    }

    private ListNode converNode(long num) {
        int temp = (int) (num % 10);
        num = num / 10;
        ListNode head = new ListNode(temp);
        ListNode index = head;
        while (num > 0) {
            temp = (int) (num % 10);
            num /= 10;
            index.next = new ListNode(temp);
            index = index.next;
        }
        return head;
    }

    private long convertNumber(ListNode line) {
        long num = line.val;
        long level = 1;
        while (line.next != null) {
            line = line.next;
            level = level * 10;
            num += line.val * level;
        }
        return num;
    }

    public static void main(String[] args) {
        ListNode line = new ListNode(1);
        line.next = new ListNode(0);
        line.next.next = new ListNode(0);
        line.next.next.next = new ListNode(0);
        line.next.next.next.next = new ListNode(0);
        line.next.next.next.next.next = new ListNode(0);
        line.next.next.next.next.next.next = new ListNode(1);
        ListNode line2 = new ListNode(5);
        line2.next = new ListNode(6);
        line2.next.next = new ListNode(4);
        _2_AddTwoNumbers addTwoNumbers = new _2_AddTwoNumbers();
        System.out.println(addTwoNumbers.convertNumber(line));
        System.out.println(addTwoNumbers.convertNumber(line2));
        System.out.println(addTwoNumbers.convertNumber(line) + addTwoNumbers.convertNumber(line2));
        System.out.println(addTwoNumbers.converNode(1000466));
        System.out.println(addTwoNumbers.addTwoNumbers2(line, line2));
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
