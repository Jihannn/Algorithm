package Tree;

import java.util.HashMap;
import java.util.HashSet;

/*
 * @Author: Jihan
 * 
 * @Date: 2021-12-02 14:40:59
 * 
 * @Description:找到两结点的最小祖先结点
 */

public class LowestCommonAncestor {

    public Node method1(Node root, Node node1, Node node2) {
        if (root == null) {
            return null;
        }
        // 找到每个结点的父结点
        HashMap<Node, Node> parentMap = new HashMap<>();
        process(root, root, parentMap);
        // 找到node1的父路径
        HashSet<Node> nodeParentSet = new HashSet<>();
        Node cur = node1;
        while (cur != root) {
            nodeParentSet.add(cur);
            cur = parentMap.get(cur);
        }
        // 如果node2沿着父路径,第一个包含在node1中的结点即为最小祖先结点
        cur = node2;
        while (cur != root) {
            if (nodeParentSet.contains(cur)) {
                return cur;
            }
            cur = parentMap.get(cur);
        }
        // 如果上面没返回则说明祖先结点为根结点
        return root;
    }

    private void process(Node node, Node parent, HashMap<Node, Node> map) {
        if (node == null) {
            return;
        }
        map.put(node, parent);
        process(node.lChild, node, map);
        process(node.rChild, node, map);
    }

    // 第一种情况：node1/node2 即为对方的最小祖先结点
    // 第二种情况：node1/node2 都要往上追溯找到最小祖先结点
    public Node method2(Node node, Node node1, Node node2) {
        // 找到node1/node2直接返回,若是情况一即找到
        if (node == null || node1 == node || node2 == node) {
            return node;
        }
        // 左右子树中是否存在node1/node2
        Node left = method2(node.lChild, node1, node2);
        Node right = method2(node.rChild, node1, node2);
        // 第一次触发条件时的结点即为最小祖先结点，情况二
        if (left != null && right != null) {
            return node;
        }
        // 左右子树存在其一,往上返回
        return left == null ? right : left;
    }

    public static void main(String[] args) {
        int[] array = { -1, 1, 2, 5, 3, -1, 6, -1, 4, 5 };
        Node root = CreateTree.create(array);
        Node node1 = root.lChild.lChild.lChild;
        Node node2 = root.lChild.lChild.rChild;
        Node node3 = root.lChild.lChild;
        Node node4 = root.rChild;
        Node node5 = root.rChild.lChild;
        LowestCommonAncestor obj = new LowestCommonAncestor();
        System.out.println(obj.method1(root, node1, node2).data);
        System.out.println(obj.method1(root, node3, node2).data);
        System.out.println(obj.method1(root, node4, node5).data);
        System.out.println(obj.method2(root, node1, node2).data);
        System.out.println(obj.method2(root, node3, node2).data);
        System.out.println(obj.method2(root, node4, node5).data);
    }
}