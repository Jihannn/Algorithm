package LinkedList;

import java.util.Stack;

/*
 * @Author: Jihan
 * 
 * @Date: 2021-11-29 11:34:54
 * 
 * @Description:判断一个链表是否为回文结构
 * 回文结构：1->2->3->2->1
 */
public class JudgeIsPalindrome<E> {

    private class Node {
        E value;
        Node next;

        Node(E value) {
            this.value = value;
        }
    }

    private Node createLinkedList(E[] array) {
        if (array == null) {
            return null;
        }
        Node head = new Node(array[0]);
        Node pre = head;
        for (int i = 1; i < array.length; i++) {
            Node node = new Node(array[i]);
            pre.next = node;
            pre = node;
        }
        return head;
    }

    // 额外空间复杂度为O(N)
    private boolean method1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Stack<Node> stack = new Stack<>();
        // 遍历链表，全部入栈
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        // 依次弹出即为逆序
        while (!stack.isEmpty()) {
            if (!stack.pop().value.equals(cur.value)) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    // 额外空间复杂度为O(N/2)
    private boolean method2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 快慢指针找中点
        Node slowP = head;
        Node fastP = head;
        while (fastP.next != null && fastP.next.next != null) {
            slowP = slowP.next;
            fastP = fastP.next.next;
        }
        // 把链表后半段入栈
        slowP = slowP.next;
        Stack<Node> stack = new Stack<>();
        while (slowP != null) {
            stack.push(slowP);
            slowP = slowP.next;
        }
        Node cur = head;
        while (!stack.isEmpty()) {
            if (!stack.pop().value.equals(cur.value)) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    // 额外空间复杂度为O(1)
    private boolean method3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 快慢指针找到中点
        Node slowP = head;
        Node fastP = head;
        while (fastP.next != null && fastP.next.next != null) {
            slowP = slowP.next;
            fastP = fastP.next.next;
        }
        // 后半段逆序
        Node cur = slowP.next;
        slowP.next = null;
        Node temp;
        while (cur != null) {
            temp = cur.next;
            cur.next = slowP;
            slowP = cur;
            cur = temp;
        }
        // 逆序完slowP指向队尾，从两端向中点遍历
        cur = head;
        // 当比较完中点后结束
        while (cur != null) {
            if (!slowP.value.equals(cur.value)) {
                return false;
            }
            cur = cur.next;
            slowP = slowP.next;
        }
        return true;
    }

    public boolean judge(E[] array) {
        Node head = createLinkedList(array);
        return method3(head);
    }

    public static void main(String[] args) {
        Integer[] palindrome = { 1, 2, 3, 2, 1 };
        Integer[] palindrome2 = { 1, 2, 3, 3, 3, 2, 1 };
        Integer[] palindrome3 = { 1, 2, 2, 1 };
        Integer[] palindrome4 = { 1, 1 };
        Integer[] array = { 1, 2, 3, 4, 2, 1 };
        Integer[] array2 = { 1, 2, 3, 3, 1 };
        JudgeIsPalindrome<Integer> obj = new JudgeIsPalindrome<>();
        System.out.println(obj.judge(palindrome));
        System.out.println(obj.judge(palindrome2));
        System.out.println(obj.judge(palindrome3));
        System.out.println(obj.judge(palindrome4));
        System.out.println(obj.judge(array));
        System.out.println(obj.judge(array2));
    }
}
