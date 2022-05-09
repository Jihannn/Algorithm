package Tree;
/*
 * @Author: Jihan
 * @Date: 2022-05-07 08:43:50
 * @Description: 给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的头节点
 */
public class LargestBSTSubTreeNode {
    public static class Node {
        Node lChild;
        Node rChild;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        int max;
        int min;
        Node head;
        int nodes;
        int largest;

        public Info(Node head, int nodes, int largest, int max, int min) {
            this.head = head;
            this.nodes = nodes;
            this.largest = largest;
            this.max = max;
            this.min = min;
        }
    }

    public static Node getLargestSubTreeNode(Node root) {
        if (root == null) {
            return null;
        }
        return process(root).head;
    }

    public static Info process(Node node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.lChild);
        Info rightInfo = process(node.rChild);
        int max = node.value;
        int min = node.value;
        int nodes = 1;
        Node head;
        int largest;
        boolean isLeftMin = true;
        boolean isRightMax = true;
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
            nodes += leftInfo.nodes;
            isLeftMin = leftInfo.max <= node.value;
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
            nodes += rightInfo.nodes;
            isRightMax = node.value <= rightInfo.min;
        }
        if (isLeftMin && isRightMax) {
            largest = nodes;
            head = node;
        } else {
            largest = leftInfo != null ? leftInfo.largest : (rightInfo != null ? rightInfo.largest : 0);
            head = leftInfo != null ? leftInfo.head : (rightInfo != null ? rightInfo.head : null);
        }
        return new Info(head, largest, nodes, max, min);
    }

    // for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.lChild = generate(level + 1, maxLevel, maxValue);
		head.rChild = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	public static void main(String[] args) {
	}
}
