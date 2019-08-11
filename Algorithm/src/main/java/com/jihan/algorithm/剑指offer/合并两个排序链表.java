package com.jihan.algorithm.剑指offer;

/**
 * Created by Jihan on 2019/8/3
 *
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class 合并两个排序链表 {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    //递归

    public ListNode Merge1(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode res = null;
        if (list1.val <= list2.val) {
            res = list1;
            res.next = Merge(list1.next, list2);
        } else {
            res = list2;
            res.next = Merge(list1, list2.next);
        }
        return res;
    }

    //非递归

    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode smallCur = list1.val <= list2.val ? list1 : list2;
        ListNode bigCur = smallCur == list1 ? list2 : list1;
        ListNode returnCur = smallCur;
        smallCur = smallCur.next;

        while (smallCur != null && bigCur != null) {
            if (smallCur.val <= bigCur.val) {
                returnCur.next = smallCur;
                returnCur = returnCur.next;
                smallCur = smallCur.next;
            } else {
                returnCur.next = bigCur;
                returnCur = returnCur.next;
                bigCur = bigCur.next;
            }
        }

        while (smallCur != null) {
            returnCur.next = smallCur;
            returnCur = returnCur.next;
            smallCur = smallCur.next;
        }

        while (bigCur != null) {
            returnCur.next = bigCur;
            returnCur = returnCur.next;
            bigCur = bigCur.next;
        }

        return list1;
    }
}
