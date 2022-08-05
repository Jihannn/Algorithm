import java.util.HashMap;

/*
 * @Author: Jihan
 * @Date: 2022-08-05 22:34:02
 * @Description: https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class _106_BuildTree {
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

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return process(0, inorder.length - 1, 0, postorder.length - 1, inorder, postorder, map);
    }

    private TreeNode process(int inLeft, int inRight, int pLeft, int pRight, int[] inorder, int[] postorder,
            HashMap<Integer, Integer> map) {
        if (inLeft > inRight || pLeft > pRight) {
            return null;
        }
        TreeNode node = new TreeNode(postorder[pRight]);
        int index = map.get(node.val);
        int leftNum = index - inLeft;
        node.left = process(inLeft, index - 1, pLeft, pLeft + leftNum - 1, inorder, postorder, map);
        node.right = process(index + 1, inRight, pLeft + leftNum, pRight - 1, inorder, postorder, map);
        return node;
    }
}
