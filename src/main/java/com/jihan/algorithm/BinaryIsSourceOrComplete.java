package com.jihan.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Jihan on 2019/7/27
 */
public class BinaryIsSourceOrComplete {

    public static class Node {
        private Node left;
        private Node right;
        private Integer value;

        public Node(Integer value) {
            this.value = value;
        }
    }


    public static boolean isBinarySearchTree(Node head) {
        if (head == null) {
            return false;
        }
        Stack<Node> stack = new Stack<>();
        Node nowCur = head;
        Node preCur = null;
        while (!stack.isEmpty() || nowCur != null) {
            if (nowCur != null) {
                stack.push(nowCur);
                nowCur = nowCur.left;
            } else {
                nowCur = stack.pop();
                if (preCur != null && preCur.value > nowCur.value) {
                    return false;
                }
                preCur = nowCur;
                nowCur = nowCur.right;
            }
        }
        return true;
    }

    public static boolean isCompleteBinaryTree(Node head) {
        if (head == null) {
            return false;
        }
        boolean leaf = false;
        Node left;
        Node right;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            left = head.left;
            right = head.right;
            if ((left == null && right != null)
                    || (leaf && (left != null || right != null))) {
                return false;
            }
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            } else {
                leaf = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        System.out.println("===============test for BST================");
        System.out.println("---------test1--------------true");
        Node head = new Node(1);
        System.out.println(isBinarySearchTree(head));

        System.out.println("---------test2--------------true");
        head = new Node(2);
        head.left = new Node(1);
        head.right = new Node(3);
        System.out.println(isBinarySearchTree(head));

        System.out.println("---------test3--------------false");
        head = new Node(2);
        head.left = new Node(1);
        head.right = new Node(0);
        System.out.println(isBinarySearchTree(head));

        System.out.println();

        System.out.println("===============test for CBT================");
        System.out.println("---------test1--------------false");
        head = new Node(1);
        head.left = new Node(2);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.left.left.left = new Node(8);
        head.left.left.right = new Node(9);
        head.left.right.right = new Node(10);
        head.right = new Node(3);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        System.out.println(isCompleteBinaryTree(head));

        System.out.println("---------test2--------------true");
        head = new Node(1);
        head.left = new Node(2);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.left.left.left = new Node(8);
        head.left.left.right = new Node(9);
        head.left.right.left = new Node(10);
        head.right = new Node(3);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        System.out.println(isCompleteBinaryTree(head));

        System.out.println("---------test3--------------true");
        head = new Node(1);
        head.left = new Node(2);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.left.left.left = new Node(8);
        head.left.left.right = new Node(9);
        head.right = new Node(3);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        System.out.println(isCompleteBinaryTree(head));
    }
}
