import java.util.ArrayList;

/*
 * @Author: Jihan
 * @Date: 2022-05-05 16:34:03
 * @Description: 是否二叉搜索树
 */
public class Rec_IsBST {
    public static class Node {
        Node lChild;
        Node rChild;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static boolean isBST(Node root) {
        if (root == null) {
            return true;
        }
        return process(root).isBST;
    }

    public static Info process(Node node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.lChild);
        Info rightInfo = process(node.rChild);
        int max = node.value;
        int min = node.value;
        boolean isBST = true;
        if(rightInfo != null){
            max = Math.max(node.value, rightInfo.max);
            if(!rightInfo.isBST || rightInfo.min <= node.value){
                isBST = false;
            }
        }
        if(leftInfo != null){
            min = Math.min(node.value, leftInfo.min);
            if(!leftInfo.isBST || leftInfo.max >= node.value){
                isBST = false;
            }
        }
        return new Info(isBST, max, min);
    }


    // test
    public static boolean isBST1(Node head) {
		if (head == null) {
			return true;
		}
		ArrayList<Node> arr = new ArrayList<>();
		in(head, arr);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i).value <= arr.get(i - 1).value) {
				return false;
			}
		}
		return true;
	}

	public static void in(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		in(head.lChild, arr);
		arr.add(head);
		in(head.rChild, arr);
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
        int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isBST1(head) != isBST(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
    }
}
