package Tree;

/*
 * @Author: Jihan
 * @Date: 2022-05-05 17:15:27
 * @Description: 是否平衡二叉树
 */
public class Rec_IsBalanced {
	public static class Node {
		Node lChild;
		Node rChild;
		int value;

		public Node(int value) {
			this.value = value;
		}
	}

	public static class Info {
		boolean isBalanced;
		int height;

		public Info(boolean isBalanced, int height) {
			this.isBalanced = isBalanced;
			this.height = height;
		}
	}

	public static boolean isBalanced(Node root) {
		if (root == null) {
			return true;
		}
		return process(root).isBalanced;
	}

	public static Info process(Node node) {
		if (node == null) {
			return new Info(true, 0);
		}
		Info leftInfo = process(node.lChild);
		Info rightInfo = process(node.rChild);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		boolean isBalanced = true;
		if (!leftInfo.isBalanced || !rightInfo.isBalanced) {
			isBalanced = false;
		}
		if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
			isBalanced = false;
		}
		return new Info(isBalanced, height);
	}

	public static boolean isBalanced1(Node head) {
		boolean[] ans = new boolean[1];
		ans[0] = true;
		process1(head, ans);
		return ans[0];
	}

	public static int process1(Node head, boolean[] ans) {
		if (!ans[0] || head == null) {
			return -1;
		}
		int leftHeight = process1(head.lChild, ans);
		int rightHeight = process1(head.rChild, ans);
		if (Math.abs(leftHeight - rightHeight) > 1) {
			ans[0] = false;
		}
		return Math.max(leftHeight, rightHeight) + 1;
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
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isBalanced1(head) != isBalanced(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}