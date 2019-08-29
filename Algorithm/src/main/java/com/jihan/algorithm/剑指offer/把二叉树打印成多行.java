package com.jihan.algorithm.剑指offer;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Jihan
 * @date 2019/8/28
 *
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 */
public class 把二叉树打印成多行 {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public class Solution {
        ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
            ArrayList<ArrayList<Integer>> returnList = new ArrayList<>();
            ArrayList<Integer> levelList;
            if (pRoot == null) {
                return returnList;
            }
            LinkedList<TreeNode> queue1 = new LinkedList<>();
            LinkedList<TreeNode> queue2 = new LinkedList<>();
            queue1.add(pRoot);
            while (!queue1.isEmpty() || !queue2.isEmpty()) {
                levelList = fallQueue(queue1, queue2);
                if (levelList.size() != 0) {
                    returnList.add(levelList);
                }
                levelList = fallQueue(queue2, queue1);
                if (levelList.size() != 0) {
                    returnList.add(levelList);
                }
            }
            return returnList;
        }

        public ArrayList<Integer> fallQueue(LinkedList<TreeNode> queue, LinkedList<TreeNode> queue2) {
            ArrayList<Integer> levelList = new ArrayList<>();
            TreeNode node;
            while (!queue.isEmpty()) {
                node = queue.pop();
                levelList.add(node.val);
                if (node.left != null) {
                    queue2.add(node.left);
                }
                if (node.right != null) {
                    queue2.add(node.right);
                }
            }
            return levelList;
        }
    }
}
