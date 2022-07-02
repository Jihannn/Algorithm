package Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * @Author: Jihan
 * @Date: 2022-05-02 13:54:27
 * @Description: 二叉树前中后序遍历
 */
public class BinaryTreePreInLastLevel {
    public static class Node {
        Node lChild;
        Node rChild;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void preRec(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        preRec(node.lChild);
        preRec(node.rChild);
    }

    public static void inRec(Node node) {
        if (node == null) {
            return;
        }
        inRec(node.lChild);
        System.out.print(node.value + " ");
        inRec(node.rChild);
    }

    public static void lastRec(Node node) {
        if (node == null) {
            return;
        }
        lastRec(node.lChild);
        lastRec(node.rChild);
        System.out.print(node.value + " ");
    }

    public static void levelRec(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        process(queue);
    }

    public static void process(Queue<Node> queue) {
        if (queue.isEmpty()) {
            return;
        }
        Node node = queue.poll();
        System.out.print(node.value + " ");
        if (node.lChild != null) {
            queue.add(node.lChild);
        }
        if (node.rChild != null) {
            queue.add(node.rChild);
        }
        process(queue);
    }

    public static void preUnRec(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.value + " ");
            if (node.rChild != null) {
                stack.push(node.rChild);
            }
            if (node.lChild != null) {
                stack.push(node.lChild);
            }
        }
    }

    public static void inUnRec(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.lChild;
            } else {
                cur = stack.pop();
                System.out.print(cur.value + " ");
                cur = cur.rChild;
            }
        }
    }

    public static void lastUnRec(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            stack2.push(node);
            if (node.lChild != null) {
                stack1.push(node.lChild);
            }
            if (node.rChild != null) {
                stack1.push(node.rChild);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().value + " ");
        }
    }

    public static void levelUnRec(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.value + " ");
            if (node.lChild != null) {
                queue.add(node.lChild);
            }
            if (node.rChild != null) {
                queue.add(node.rChild);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.lChild = new Node(2);
        root.rChild = new Node(3);
        root.lChild.lChild = new Node(4);
        root.lChild.rChild = new Node(5);
        root.rChild.lChild = new Node(6);
        root.lChild.lChild.rChild = new Node(7);
        root.lChild.rChild.lChild = new Node(8);
        root.rChild.lChild.rChild = new Node(9);
        preRec(root);
        System.out.println();
        inRec(root);
        System.out.println();
        lastRec(root);
        System.out.println();
        levelRec(root);
        System.out.println();
        System.out.println();
        preUnRec(root);
        System.out.println();
        inUnRec(root);
        System.out.println();
        lastUnRec(root);
        System.out.println();
        levelUnRec(root);
    }
}