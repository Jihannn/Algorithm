package com.jihan.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jihan on 2019/7/18
 */
public class CopyRandomList {

    public class Node {
        private Integer data;
        private Node next;
        private Node random;

        public Node(Integer data) {
            this.data = data;
        }
    }

    public Node copyList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.next.data));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).random = map.get(cur.random);
            map.get(cur).next = map.get(cur.next);
            cur = cur.next;
        }
        return map.get(head);
    }

    public Node copyList2(Node head) {
        if (head == null) {
            return null;
        }

        Node cur = head;
        Node copyCur;
        Node curNext;
        while (cur != null) {
            curNext = cur.next;
            copyCur = new Node(cur.data);
            cur.next = copyCur;
            copyCur.next = curNext;
            cur = curNext;
        }

        cur = head;
        while (cur != null) {
            copyCur = cur.next;
            copyCur.random = cur.random != null ? cur.random.next : null;
            cur = copyCur.next;
        }

        Node copy = head.next;
        cur = head;
        copyCur = head.next;
        curNext = copyCur.next;
        while (curNext != null) {
            copyCur.next = curNext.next;
            cur.next = curNext;
            cur = curNext;
            copyCur = cur.next;
            curNext = copyCur.next;
        }
        return copy;
    }


}
