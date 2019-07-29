package com.jihan.algorithm;

import java.util.Stack;

/**
 * Created by Jihan on 2019/7/15
 */
public class PalindromeList {

    private class Node {
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }
    }

    public boolean checkPalindrome(Node head) {
        Stack<Node> stack = new Stack<>();
        Node index = head;

        while (index != null) {
            stack.push(index);
            index = index.next;
        }

        while (!stack.isEmpty()) {
            if (stack.pop() == head) {
                head = head.next;
            } else {
                return false;
            }
        }
        return true;
    }


}
