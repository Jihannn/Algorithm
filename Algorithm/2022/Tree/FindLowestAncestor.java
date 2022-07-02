package Tree;

/*
 * @Author: Jihan
 * @Date: 2022-05-07 09:32:11
 * @Description: 给定一棵二叉树的头节点head，和另外两个节点a和b，返回a和b的最低公共祖先
 */
public class FindLowestAncestor {
    public static class Node {
        Node lChild;
        Node rChild;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        boolean findA;
        boolean findB;
        Node ancestor;

        public Info(boolean findA, boolean findB, Node ancestor) {
            this.findA = findA;
            this.findB = findB;
            this.ancestor = ancestor;
        }
    }

    public static Node findLowestAncestor(Node root, Node A, Node B) {
        if (root == null) {
            return null;
        }
        return process(root, A, B).ancestor;
    }

    public static Info process(Node node, Node A, Node B) {
        if (node == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(node.lChild, A, B);
        Info rightInfo = process(node.rChild, A, B);
        boolean findA = node == A || leftInfo.findA || rightInfo.findA;
        boolean findB = node == B || leftInfo.findB || rightInfo.findB;
        Node ancestor = leftInfo.ancestor != null ? leftInfo.ancestor
                : (rightInfo.ancestor != null ? rightInfo.ancestor : (findA && findB ? node : null));
        return new Info(findA, findB, ancestor);
    }
}
