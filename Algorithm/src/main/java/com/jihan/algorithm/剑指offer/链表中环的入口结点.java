package com.jihan.algorithm.剑指offer;

/**
 * @author Jihan
 * @date 2019/8/24
 *
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 */
public class 链表中环的入口结点 {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null) {
            return null;
        }

        ListNode low = pHead;
        ListNode fast = pHead;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            low = low.next;
            if (fast == low) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        low = pHead;

        while (low != fast) {
            low = low.next;
            fast = fast.next;
        }

        return low;
    }
}
