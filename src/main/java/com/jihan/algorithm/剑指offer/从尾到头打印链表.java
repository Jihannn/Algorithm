package com.jihan.algorithm.剑指offer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Jihan on 2019/7/30
 *
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 */
public class 从尾到头打印链表 {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    //翻转链表

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        ListNode preCur = null;
        ListNode cur = listNode;
        ListNode nextCur = null;
        while (cur != null) {
            nextCur = cur.next;
            cur.next = preCur;
            preCur = cur;
            cur = nextCur;
        }

        while (preCur != null) {
            list.add(preCur.val);
            preCur = preCur.next;
        }
        return list;
    }

    //压栈

    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        Stack<Integer> stack = new Stack<Integer>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }
}
