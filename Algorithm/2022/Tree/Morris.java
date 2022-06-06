package Tree;

/*
 * @Author: Jihan
 * @Date: 2022-06-06 20:02:23
 * @Description: Morris遍历时间复杂度O(N)，额外空间复杂度O(1)
 */
public class Morris {
    public static class Node {
        Node lChild;
        Node rChild;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    private static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.lChild = generate(level + 1, maxLevel, maxValue);
        head.rChild = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.lChild;
            if (mostRight != null) {
                while (mostRight.rChild != null && mostRight.rChild != cur) {
                    mostRight = mostRight.rChild;
                }
                if (mostRight.rChild == null) {
                    System.out.print(cur.value + " ");
                    mostRight.rChild = cur;
                    cur = cur.lChild;
                    continue;
                } else {
                    mostRight.rChild = null;
                }
            } else {
                System.out.print(cur.value + " ");
            }
            cur = cur.rChild;
        }
        System.out.println();
    }

    private static void p(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        p(head.lChild);
        p(head.rChild);
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        Node head = generateRandomBST(maxLevel, maxValue);
        p(head);
        System.out.println();
        morris(head);
    }
}