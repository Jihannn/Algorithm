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
            return false;
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
}
