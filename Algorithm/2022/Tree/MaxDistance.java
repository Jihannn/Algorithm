package Tree;
/*
 * @Author: Jihan
 * @Date: 2022-05-06 11:17:54
 * @Description: 二叉树两节点最大距离
 */
public class MaxDistance {
    public static class Node {
        Node lChild;
        Node rChild;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        int maxDistance;
        int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public static int maxDistance(Node root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxDistance;
    }

    public static Info process(Node node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(node.lChild);
        Info rightInfo = process(node.rChild);
        int height = Math.max(leftInfo.height , rightInfo.height) + 1;
        int maxDistance = leftInfo.height + rightInfo.height + 1;
        maxDistance = Math.max(leftInfo.maxDistance, maxDistance);
        maxDistance = Math.max(rightInfo.maxDistance, maxDistance);
        return new Info(maxDistance, height);
    }

	public static void main(String[] args) {
	}
}
