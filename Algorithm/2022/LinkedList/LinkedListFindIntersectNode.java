package LinkedList;
/*
 * @Author: Jihan
 * @Date: 2022-05-01 11:36:29
 * @Description: 给定两个可能有环也可能无环的单链表，头节点head1和head2
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交返回null 
 * 要求如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)
 */

public class LinkedListFindIntersectNode {
    public static class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node h1In = findInCyclicNode(head1);
        Node h2In = findInCyclicNode(head2);
        return h1In == null && h2In == null ? bothNoCyclic(head1, head2)
                : (h1In != null && h2In != null ? bothIsCyclic(head1, h1In, head2, h2In) : null);
    }

    public static Node findInCyclicNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast != slow) {
            // 遇到null则无环
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static Node bothNoCyclic(Node head1, Node head2) {
        Node node1 = head1;
        Node node2 = head2;
        int n = 0;
        // 两个链表都遍历到末尾结点，并且记录结点个数
        while (node1.next != null) {
            node1 = node1.next;
            n++;
        }
        while (node2.next != null) {
            node2 = node2.next;
            n--;
        }
        // 如果结尾结点不同，则不相交
        if (node1 != node2) {
            return null;
        }
        // 长链表
        node1 = n > 0 ? head1 : head2;
        // 短链表
        node2 = node1 == head1 ? head2 : head1;
        n = Math.abs(n);
        // 因为相交部分长度一致，则让长链表的起点与短链表起点距离相同
        while (n != 0) {
            node1 = node1.next;
            n--;
        }
        // 相等处即为第一个相交结点
        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }

    public static Node bothIsCyclic(Node head1, Node n1, Node head2, Node n2) {
        // 如果相等则非环部分与bothNoCyclic一致
        if (n1 == n2) {
            Node node1 = head1;
            Node node2 = head2;
            int n = 0;
            while (node1 != n1) {
                node1 = node1.next;
                n++;
            }
            while (node2 != n2) {
                node2 = node2.next;
                n--;
            }
            node1 = n > 0 ? head1 : head2;
            node2 = node1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                node1 = node1.next;
                n--;
            }
            while (node1 != node2) {
                node1 = node1.next;
                node2 = node2.next;
            }
            return node1;
        }
        // 遍历一遍环，如果没遇到对方入环点则不相交
        Node node = n1.next;
        while (node != n1) {
            // 遇到了则随便返回一个入环点即为相交点
            if (node == n2) {
                return n1;
            }
            node = node.next;
        }
        return null;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);
    }
}