package com.jihan.algorithm;

/**
 * Created by Jihan on 2019/7/22
 */
public class CrossList {
    private class Node {
        private Integer data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static Node checkIntersect(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        Node loop1 = checkLoop(head1);
        Node loop2 = checkLoop(head2);
        if (loop1 == null && loop2 == null) {
            return checkNotLoop(head1, head2);
        }
        if (loop1 == null || loop2 == null) {
            return checkBothLoop(head1, loop1, head2, loop2);
        }

        return null;
    }

    private static Node checkBothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        if (loop1 == loop2) {
            Node cur1 = head1;
            Node cur2 = head2;
            int length = 0;
            while (cur1 != loop1) {
                cur1 = cur1.next;
                length++;
            }
            while (cur2 != loop2) {
                cur2 = cur2.next;
                length--;
            }
            cur1 = length > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            length = Math.abs(length);
            while (length != 0) {
                cur1 = cur1.next;
                length--;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            Node cur = loop1.next;
            while (cur != loop1) {
                cur = cur.next;
                if(head1 == loop2){
                    return loop1;
                }
            }
            return null;
        }

    }

    private static Node checkNotLoop(Node head1, Node head2) {
        if(head1.next == null || head2.next == null){
            return null;
        }
        Node cur1 = null;
        Node cur2 = null;
        int length = 0;
        while (head1.next != null) {
            cur1 = head1.next;
            length++;
        }
        while (head2.next != null) {
            cur2 = head2.next;
            length--;
        }
        if (cur1 != cur2) {
            return null;
        }
        cur1 = length > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        length = Math.abs(length);
        while (length > 0) {
            cur1 = cur1.next;
            length--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    private static Node checkLoop(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast != slow) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}
