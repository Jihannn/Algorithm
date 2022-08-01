/*
 * @Author: Jihan
 * @Date: 2022-07-20 16:57:31
 * @Description: https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class _019_RemoveNthNodeFromEndOfList {
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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        while (n-- > 0) {
            fast = fast.next;
        }
        ListNode slow = dummy;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }
        // 快慢指针
        ListNode slow = head;
        ListNode fast = head.next;
        int cur = 1;
        int count = 2;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            cur++;
        }
        // 根据奇偶性得到总结点数
        count = fast == null ? cur * 2 - 1 : cur * 2;
        // 删除最开头结点
        if (count == n) {
            return head.next;
        } else {
            // 正序第n个，即需要删除结点的前一个结点
            int pre = count - n;
            // 如果在中点左边，则从头开始
            if (pre < cur) {
                slow = head;
                cur = 1;
            }
            // 让指针移动到pre位置
            while (pre != cur) {
                slow = slow.next;
                cur++;
            }
            slow.next = slow.next.next;
        }
        return head;
    }
}
