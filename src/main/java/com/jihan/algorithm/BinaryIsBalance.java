package com.jihan.algorithm;

/**
 * Created by Jihan on 2019/7/23
 */
public class BinaryIsBalance {
    private static class Node {
        private Node left;
        private Node right;
        private Object data;

        public Node(Object data) {
            this.data = data;
        }
    }

    public static boolean treeIsB(Node head) {
        return process(head).isB;
    }

    private static class NodeData {
        private boolean isB;
        private int height;

        public NodeData(boolean isB, int height) {
            this.isB = isB;
            this.height = height;
        }
    }

    public static NodeData process(Node head) {
        if (head == null) {
            return new NodeData(false, 0);
        }
        NodeData leftData = process(head.left);
        if (!leftData.isB) {
            return new NodeData(false, 0);
        }
        NodeData rightData = process(head.right);
        if (!rightData.isB) {
            return new NodeData(false, 0);
        }
        return new NodeData(true, Math.abs(leftData.height - rightData.height) + 1);
    }
}
