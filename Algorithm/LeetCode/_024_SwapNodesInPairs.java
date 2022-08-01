/*
 * @Author: Jihan
 * @Date: 2022-08-01 15:44:28
 * @Description: https://leetcode.cn/problems/swap-nodes-in-pairs/
 */
public class _024_SwapNodesInPairs {

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

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode h = new ListNode(0);
        h.next = head;
        ListNode preP = h;
        ListNode cur1 = head;
        ListNode cur2 = head.next;
        ListNode next = cur2.next;
        while (cur2 != null) {
            next = cur2.next;
            cur1.next = next;
            cur2.next = cur1;
            preP.next = cur2;
            if (next == null || next.next == null) {
                break;
            }
            preP = cur1;
            cur1 = next;
            cur2 = next.next;
        }
        return h.next;
    }
}
