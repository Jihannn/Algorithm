package com.jihan.algorithm.剑指offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Jihan
 * @date 2019/8/28
 *
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 */
public class 按之字顺序打印二叉树 {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public class Solution {
        public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
            ArrayList<ArrayList<Integer>> returnList = new ArrayList<>();
            ArrayList<Integer> levelList;
            if (pRoot == null) {
                return returnList;
            }
            Stack<TreeNode> toRight = new Stack<>();
            Stack<TreeNode> toLeft = new Stack<>();
            boolean isToRight = true;
            TreeNode node;
            toRight.push(pRoot);
            while (!toRight.isEmpty() || !toLeft.isEmpty()) {
                levelList = new ArrayList<>();
                if (isToRight) {
                    while (!toRight.isEmpty()) {
                        node = toRight.pop();
                        if (node != null) {
                            toLeft.push(node.left);
                            toLeft.push(node.right);
                            levelList.add(node.val);
                        }
                    }
                    isToRight = false;
                } else {
                    while (!toLeft.isEmpty()) {
                        node = toLeft.pop();
                        if (node != null) {
                            toRight.push(node.right);
                            toRight.push(node.left);
                            levelList.add(node.val);
                        }
                    }
                    isToRight = true;
                }
                if (levelList.size() != 0) {
                    returnList.add(levelList);
                }
            }
            return returnList;
        }

    }
}
