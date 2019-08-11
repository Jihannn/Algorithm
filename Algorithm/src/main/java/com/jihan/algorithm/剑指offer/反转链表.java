package com.jihan.algorithm.剑指offer;

/**
 * Created by Jihan on 2019/8/3
 *
 * 输入一个链表，反转链表后，输出新链表的表头。
 */
public class 反转链表 {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode preCur = null;
        ListNode nowCur = head;
        ListNode nextCur;
        while (nowCur != null) {
            nextCur = nowCur.next;
            nowCur.next = preCur;
            preCur = nowCur;
            nowCur = nextCur;
        }

        return preCur;
    }
}
