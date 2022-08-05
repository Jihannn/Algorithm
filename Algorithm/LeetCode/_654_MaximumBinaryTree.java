/*
 * @Author: Jihan
 * @Date: 2022-08-05 22:34:58
 * @Description: https://leetcode.cn/problems/maximum-binary-tree/
 */
public class _654_MaximumBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return process(0, nums.length - 1, nums);
    }

    private TreeNode process(int l, int r, int[] nums) {
        if (l > r) {
            return null;
        }
        int maxIndex = l;
        for (int i = l; i <= r; i++) {
            if (nums[maxIndex] < nums[i]) {
                maxIndex = i;
            }
        }
        TreeNode node = new TreeNode(nums[maxIndex]);
        node.left = process(l, maxIndex - 1, nums);
        node.right = process(maxIndex + 1, r, nums);
        return node;
    }
}
