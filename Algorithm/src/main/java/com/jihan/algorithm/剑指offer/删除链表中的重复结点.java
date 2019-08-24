package com.jihan.algorithm.剑指offer;

/**
 * @author Jihan
 * @date 2019/8/24
 * 
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class 删除链表中的重复结点 {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }

        ListNode mHead = new ListNode(0);
        mHead.next = pHead;

        ListNode preCur = mHead;
        ListNode nextCur = mHead.next;

        while (nextCur != null) {
            if (nextCur.next != null && nextCur.val == nextCur.next.val) {
                while (nextCur.next != null && nextCur.val == nextCur.next.val) {
                    nextCur = nextCur.next;
                }
                preCur.next = nextCur.next;
                nextCur = nextCur.next;
            } else {
                preCur = preCur.next;
                nextCur = nextCur.next;
            }
        }
        return mHead.next;
    }
}
