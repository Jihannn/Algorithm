package com.jihan.algorithm;

/**
 * Created by Jihan on 2019/7/22
 */
public class BinaryFirstAndLastPoint {
    private class Node {
        private Node parent;
        private Node right;
        private Node left;
        private Integer data;

        public Node(Integer data) {
            this.data = data;
        }
    }

    public static Node findNext(Node node) {
        if (node == null) {
            return null;
        }

        Node cur = null;

        if (node.right != null) {
            cur = node.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }

        cur = node.parent;
        while (cur != null && cur.left != node) {
            cur = cur.parent;
            node = node.parent;
        }
        return cur;
    }

    public static Node findPre(Node node) {
        if (node == null) {
            return null;
        }

        Node cur = null;

        if (node.left != null) {
            cur = node.left;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }

        cur = node.parent;
        while (cur != null && cur.right != node) {
            cur = cur.parent;
            node = node.parent;
        }
        return cur;
    }
}
