package Tree;

import java.util.ArrayList;
import java.util.Stack;

/*
 * @Author: Jihan
 * 
 * @Date: 2021-12-01 16:58:17
 * 
 * @Description: 检查是否为搜索二叉树
 */
public class CheckIsBST {

    // 中序递归遍历,检查是否为升序
    public boolean method1(Node root) {
        if (root == null) {
            return true;
        }
        // 先存储中序遍历的结果
        ArrayList<Node> list = new ArrayList<>();
        inOrderRec(root, list);
        Node preNode = list.get(0);
        // 非升序即不为搜索二叉树
        for (Node node : list) {
            if (node.data < preNode.data) {
                return false;
            }
            preNode = node;
        }
        return true;
    }

    private void inOrderRec(Node node, ArrayList<Node> list) {
        if (node == null) {
            return;
        }
        inOrderRec(node.lChild, list);
        list.add(node);
        inOrderRec(node.rChild, list);
    }

    // 不借助ArrayList,直接返回结果
    public boolean method2(Node root) {
        if (root == null) {
            return true;
        }
        return inOrderRec2(root, Integer.MIN_VALUE);
    }

    private boolean inOrderRec2(Node node, int preNodeData) {
        if (node == null) {
            return true;
        }
        boolean isLeftBST = inOrderRec2(node.lChild, preNodeData);
        if (!isLeftBST || node.data <= preNodeData) {
            return false;
        } else {
            preNodeData = node.data;
        }
        return inOrderRec2(node.rChild, preNodeData);
    }

    // 非递归遍历,检查是否升序
    public boolean inOrderNoRec(Node root) {
        if (root == null) {
            return false;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        int preNodeData = Integer.MIN_VALUE;
        while (!stack.isEmpty() || cur != null) {
            if (cur.lChild != null) {
                stack.push(cur.lChild);
            } else {
                cur = stack.pop();
                // 如果不为升序则不为搜索树
                if (preNodeData > cur.data) {
                    return false;
                }
                preNodeData = cur.data;
                stack.push(cur.rChild);
            }
        }
        return true;
    }

    // 递归套路
    public class ReturnType {
        int min;
        int max;
        boolean isBST;

        ReturnType(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    public boolean method3(Node root) {
        if (root == null) {
            return true;
        }
        return process(root).isBST;
    }

    private ReturnType process(Node node) {
        if (node == null) {
            return null;
        }
        ReturnType leftReturn = process(node.lChild);
        ReturnType rightReturn = process(node.rChild);
        int min = node.data;
        int max = node.data;
        boolean isBST = true;
        if (leftReturn != null) {
            min = Math.min(min, leftReturn.min);
            max = Math.max(max, leftReturn.max);
        }
        if (rightReturn != null) {
            min = Math.min(min, rightReturn.min);
            max = Math.max(max, rightReturn.max);
        }
        // 左子树最大值没有比当前结点小，右子树最小值没有比当前结点大，左右子树不都是BST，那么当前树不为BST
        if ((leftReturn != null && (!leftReturn.isBST || leftReturn.max > node.data))
                || (rightReturn != null && (!rightReturn.isBST || rightReturn.min < node.data))) {
            isBST = false;
        }
        return new ReturnType(isBST, min, max);
    }

    public static void main(String[] args) {
        int[] BST = { -1, 8, 6, 9, 4, 7, -1, 11, 3, 5 };
        int[] notBST = { -1, 8, 6, 9, 9, 7, -1, 11, 3, 5 };
        Node root1 = CreateTree.create(BST);
        Node root2 = CreateTree.create(notBST);
        CheckIsBST obj = new CheckIsBST();
        System.out.println(obj.method1(root1));
        System.out.println(obj.method1(root2));
        System.out.println(obj.method2(root1));
        System.out.println(obj.method2(root2));
        System.out.println(obj.method3(root1));
        System.out.println(obj.method3(root2));
    }
}
