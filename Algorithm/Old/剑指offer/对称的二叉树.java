package com.jihan.algorithm.剑指offer;

import java.util.Stack;

/**
 * @author Jihan
 * @date 2019/8/26
 *
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
public class 对称的二叉树 {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

        public boolean isSymmetrical(TreeNode pRoot) {
            if (pRoot == null) {
                return true;
            }
            return process(pRoot.left, pRoot.right);
        }

        public boolean process(TreeNode left, TreeNode right) {
            if (left == null) {
                return right == null;
            }
            if (right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            return process(left.left, right.right) && process(left.right, right.left);
        }
    }

    boolean isSymmetrical2(TreeNode pRoot){
        if(pRoot == null){
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(pRoot.left);
        stack.push(pRoot.right);
        while(!stack.isEmpty()){
            TreeNode right = stack.pop();
            TreeNode left = stack.pop();
            if(right == null && left == null){
                continue;
            }
            if(right == null || left == null){
                return false;
            }
            if(right.val != left.val){
                return false;
            }
            stack.push(left.left);
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
        }
        return true;
    }
}