package Tree;

import java.util.Stack;

/*
 * @Author: Jihan
 * @Date: 2021-11-30 15:35:08
 * @Description: 前中后序非递归遍历
 */
public class PreInPostBinaryTraversal {

    // 前序遍历
    public void preOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.print(cur.data + ",");
            if (cur.rChild != null) {
                stack.push(cur.rChild);
            }
            if (cur.lChild != null) {
                stack.push(cur.lChild);
            }
        }
        System.out.println();
    }

    // 中序遍历
    public void inOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (!stack.isEmpty() || cur != null) {
            // 一直入栈到左边界
            if (cur != null) {
                stack.push(cur.lChild);
                cur = cur.lChild;
            } else {
                cur = stack.pop();
                System.out.println(cur.data + ",");
                cur = cur.rChild;
            }
        }
    }

    // 后序遍历
    public void postOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node cur = stack1.pop();
            stack2.push(cur);
            if (cur.lChild != null) {
                stack1.push(cur.lChild);
            }
            if (cur.rChild != null) {
                stack1.push(cur.rChild);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().data + ",");
        }
    }

    public static void main(String[] arg) {
    }
}
