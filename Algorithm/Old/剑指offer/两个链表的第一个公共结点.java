package com.jihan.algorithm.剑指offer;

import java.util.HashSet;

/**
 * @author Jihan
 * @date 2019/8/12
 *
 * 输入两个链表，找出它们的第一个公共结点。
 */
public class 两个链表的第一个公共结点 {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }

        int count1 = 0;
        int count2 = 0;
        ListNode pHeadCopy1 = pHead1;
        ListNode pHeadCopy2 = pHead2;

        while (pHeadCopy1 != null) {
            pHeadCopy1 = pHeadCopy1.next;
            ++count1;
        }

        while (pHeadCopy2 != null) {
            pHeadCopy2 = pHeadCopy2.next;
            ++count2;
        }

        pHeadCopy1 = pHead1;
        pHeadCopy2 = pHead2;

        int dValue;
        if (count1 > count2) {
            dValue = count1 - count2;
            while (dValue != 0) {
                pHeadCopy1 = pHeadCopy1.next;
                dValue--;
            }
        } else {
            dValue = count2 - count1;
            while (dValue != 0) {
                pHeadCopy2 = pHeadCopy2.next;
                dValue--;
            }
        }

        while (pHeadCopy1 != pHeadCopy2) {
            pHeadCopy1 = pHeadCopy1.next;
            pHeadCopy2 = pHeadCopy2.next;
        }
        return pHeadCopy1;
    }

    public ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }

        HashSet<ListNode> set = new HashSet<>();

        while (pHead1 != null) {
            set.add(pHead1);
            pHead1 = pHead1.next;
        }

        while (!set.contains(pHead2) && pHead2 != null) {
            pHead2 = pHead2.next;
        }

        return pHead2;
    }
}
