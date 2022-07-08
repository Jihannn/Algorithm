/*
 * @Author: Jihan
 * @Date: 2022-07-03 17:28:50
 * @Description: https://leetcode.cn/problems/add-two-numbers/
 */
public class _002_AddTwoNums {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        // 是否进位，进位数只可能为1
        boolean carry = false;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        while (cur1 != null || cur2 != null) {
            int num1 = cur1 != null ? cur1.val : 0;
            int num2 = cur2 != null ? cur2.val : 0;
            // 如果之前有进位则加1
            int sum = num1 + num2 + (carry ? 1 : 0);
            cur.next = new ListNode(sum % 10);
            carry = sum >= 10 ? true : false;
            cur = cur.next;
            if (cur1 != null) {
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                cur2 = cur2.next;
            }
        }
        if (carry) {
            cur.next = new ListNode(1);
        }
        return head.next;
    }
}
