package Tree;

/*
 * @Author: Jihan
 * @Date: 2022-05-06 09:16:28
 * @Description:给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的头节点
 */
public class LargestBSTSubTree {
    public static class Node {
        Node lChild;
        Node rChild;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        int maxBSTSize;
        int max;
        int min;
        Node maxBSTHead;

        public Info(int maxBSTSize, int max, int min, Node maxBSTHead) {
            this.maxBSTSize = maxBSTSize;
            this.max = max;
            this.min = min;
            this.maxBSTHead = maxBSTHead;
        }
    }

    public static Node maxBSTSubHead(Node root) {
        if (root == null) {
            return null;
        }
        return process(root).maxBSTHead;
    }

    public static Info process(Node node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.lChild);
        Info rightInfo = process(node.rChild);
        int maxBSTSize = 0;
        Node maxBSTHead = null;
        int max = node.value;
        int min = node.value;
        boolean isLeftSmall = true;
        boolean isRightBig = true;
        if (leftInfo != null) {
            maxBSTHead = leftInfo.maxBSTHead;
            maxBSTSize = leftInfo.maxBSTSize;
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            isLeftSmall = node.value > leftInfo.max;
        }
        if (rightInfo != null) {
            if (rightInfo.maxBSTSize > maxBSTSize) {
                maxBSTHead = rightInfo.maxBSTHead;
                maxBSTSize = rightInfo.maxBSTSize;
            }
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            isRightBig = node.value < rightInfo.min;
        }
        if (isLeftSmall && isRightBig && (leftInfo != null ? leftInfo.maxBSTHead == node.lChild : true)
                && (rightInfo != null ? rightInfo.maxBSTHead == node.rChild : true)) {
            maxBSTHead = node;
            maxBSTSize = 1 + (leftInfo != null ? leftInfo.maxBSTSize : 0)
                    + (rightInfo != null ? rightInfo.maxBSTSize : 0);
        }
        return new Info(maxBSTSize, max, min, maxBSTHead);
    }
}