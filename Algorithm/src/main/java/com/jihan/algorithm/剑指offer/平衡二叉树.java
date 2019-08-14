package com.jihan.algorithm.剑指offer;

/**
 * @author Jihan
 * @date 2019/8/14
 *
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 */
public class 平衡二叉树 {
    private class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        return getDepth(root) != -1;
    }

    private int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = getDepth(node.left);
        if (left == -1) {
            return -1;
        }

        int right = getDepth(node.right);
        if (right == -1) {
            return -1;
        }

        return Math.abs(left - right) > 1 ? -1 : Math.max(left, right) + 1;
    }
}
