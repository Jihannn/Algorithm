package com.jihan.algorithm.剑指offer;

/**
 * Created by Jihan on 2019/8/3
 *
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class 树的子结构 {

    public class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        boolean result = false;

        if (root1.val == root2.val) {
            result = checkIsChild(root1, root2);
        }

        if (!result) {
            result = HasSubtree(root1.left, root2);
        }

        if (!result) {
            result = HasSubtree(root1.right, root2);
        }

        return result;
    }

    public boolean checkIsChild(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        }

        if (root1 == null) {
            return false;
        }

        if (root1.val != root2.val) {
            return false;
        }

        return checkIsChild(root1.left, root2.left) && checkIsChild(root1.right, root2.right);
    }
}
