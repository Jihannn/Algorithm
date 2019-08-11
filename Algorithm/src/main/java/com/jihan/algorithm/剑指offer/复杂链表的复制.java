package com.jihan.algorithm.剑指offer;

/**
 * Created by Jihan on 2019/8/5
 *
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
 * 另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class 复杂链表的复制 {

    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    public RandomListNode Clone(RandomListNode pHead)
    {
        if(pHead == null){
            return null;
        }

        //复制节点与原节点相连
        RandomListNode copy;
        RandomListNode cur = pHead;
        while(cur != null){
            copy =  new RandomListNode(cur.label);
            copy.next = cur.next;
            cur.next = copy;
            cur = copy.next;
        }

        //找复制节点的random
        cur = pHead;
        while(cur != null){
            copy = cur.next;
            if(cur.random != null){
                copy.random = cur.random.next;
            }
            cur = copy.next;
        }

        RandomListNode returnHead = pHead.next;

        //拆分
        cur = pHead;
        while(cur != null){
            copy = cur.next;
            cur.next = copy.next;
            if(cur.next != null){
                copy.next = cur.next.next;
            }
            cur = cur.next;
        }

        return returnHead;
    }
}
