package com.jihan.algorithm;

import java.util.Stack;

/**
 * Created by Jihan on 2019/7/22
 */
public class ErgodicBinary {
    private class Node {
        private Node left;
        private Node right;
        private Integer data;

        private Node(Integer data) {
            this.data = data;
        }
    }

    public static void frontRecPrint(Node head) {
        if (head == null) {
            return;
        }

        System.out.println(head.data);
        frontRecPrint(head.left);
        frontRecPrint(head.right);
    }

    public static void middleRecPrint(Node head) {
        if (head == null) {
            return;
        }

        middleRecPrint(head.left);
        System.out.println(head.data);
        middleRecPrint(head.right);
    }

    public static void backRecPrint(Node head) {
        if (head == null) {
            return;
        }

        backRecPrint(head.left);
        backRecPrint(head.right);
        System.out.println(head.data);
    }

    public static void frontPrint(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.println(head.data);
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }
    }

    public static void middlePrint(Node head){
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while(!stack.isEmpty() || head != null){
            if(head != null){
                stack.push(head);
                head = head.left;
            }else{
                head = stack.pop();
                System.out.println(head.data);
                head = head.right;
            }
        }
    }

    public static void backPrint(Node head){
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Stack<Node> print = new Stack<>();
        stack.push(head);
        while(!stack.isEmpty()){
            head = stack.pop();
            print.push(head);
            if(head.left != null){
                stack.push(head.left);
            }
            if (head.right != null) {
                stack.push(head.right);
            }
        }
        while(!print.isEmpty()) {
            System.out.println(print.pop().data);
        }
    }
}
