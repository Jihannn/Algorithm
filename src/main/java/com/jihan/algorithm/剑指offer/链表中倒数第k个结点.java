package com.jihan.algorithm.剑指offer;

import java.util.Stack;

/**
 * Created by Jihan on 2019/8/3
 *
 * 输入一个链表，输出该链表中倒数第k个结点。
 */
public class 链表中倒数第k个结点 {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k == 0) {
            return null;
        }
        Stack<ListNode> stack = new Stack<ListNode>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        for (int i = 1; i <= k; i++) {
            if (stack.isEmpty()) {
                return null;
            }
            head = stack.pop();
        }
        return head;
    }

    public ListNode FindKthToTail2(ListNode head, int k) {
        if (head == null || k == 0) {
            return null;
        }

        ListNode slowCur = head;
        ListNode fastCur = head;

        for (int i = 0; i < k; i++) {
            if (fastCur != null) {
                fastCur = fastCur.next;
            } else {
                return null;
            }
        }

        while (fastCur != null) {
            slowCur = slowCur.next;
            fastCur = fastCur.next;
        }

        return slowCur;
    }
}
