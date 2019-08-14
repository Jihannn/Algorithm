package com.jihan.algorithm.剑指offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Jihan
 * @date 2019/8/12
 *
 * 输入一棵二叉树，求该树的深度。
 * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 */
public class 二叉树深度 {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = treeDepth(root.left);
        int right = treeDepth(root.right);

        return (left > right ? left : right) + 1;
    }

    public int treeDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        int count = 0;
        int nextCount = 1;
        TreeNode node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            ++count;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            if (count == nextCount) {
                nextCount = queue.size();
                count = 0;
                ++depth;
            }
        }
        return depth;
    }
}
