package Tree;

/*
 * @Author: Jihan
 * 
 * @Date: 2021-12-02 11:14:22
 * 
 * @Description:检查是否为满二叉树
 */

public class CheckIsFBT {
    public class ReturnType {
        int height;
        int nodes;

        ReturnType(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public boolean method(Node root) {
        if (root == null) {
            return true;
        }
        ReturnType rootType = process(root);
        // 满二叉树结点N与高度H的关系：N = 2^H - 1
        return rootType.nodes == Math.pow(2, rootType.height) - 1;
    }

    private ReturnType process(Node node) {
        if (node == null) {
            return new ReturnType(0, 0);
        }
        ReturnType leftReturn = process(node.lChild);
        ReturnType rightReturn = process(node.rChild);
        // 该结点高度 = 子树高度 + 1
        int height = Math.max(leftReturn.height, rightReturn.height) + 1;
        int nodes = leftReturn.nodes + rightReturn.nodes + 1;
        return new ReturnType(height, nodes);
    }
}