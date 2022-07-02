package LinkedList;

import java.util.Stack;

/*
 * @Author: Jihan
 * @Date: 2022-04-29 11:09:27
 * @Description: 给定一个单链表的头节点head，请判断该链表是否为回文结构
 */
public class Palindrome {
    public static class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean palindrome1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 遍历链表入栈
        Stack<Node> stack = new Stack<>();
        Node node = head;
        while (node != null) {
            stack.add(node);
            node = node.next;
        }
        // 弹栈和遍历链表，如果有不同则不是回文
        node = head;
        while (!stack.isEmpty()) {
            if (stack.pop().value != node.value) {
                return false;
            }
        }
        return true;
    }

    public static boolean palindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 快慢指针，慢指针停留在中点
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 遍历链表，如果不相同则不是回文
        Node cur = head;
        while (slow != null) {
            if (slow.value != cur.value) {
                return false;
            }
            cur = cur.next;
            slow = slow.next;
        }
        return true;
    }

    // test
    public static Node createRandomLinkedList(int maxLen) {
        Node root = new Node(0);
        int len = (int) (Math.random() * maxLen);
        int value = 1;
        Node node = root;
        while (len > 0) {
            node.next = new Node(value++);
            node = node.next;
            len--;
        }
        return root;
    }

    public static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int listLen = 100;
        System.out.println("test start");
        for (int i = 0; i < testTime; i++) {
            Node head = createRandomLinkedList(listLen);
            boolean right = palindrome1(head);
            boolean test = palindrome(head);
            if (right != test) {
                System.out.println("Error");
                printLinkedList(head);
                break;
            }
        }
        System.out.println("test finish");
    }
}