package Tree;
/*
 * @Author: Jihan
 * 
 * @Date: 2021-12-02 10:31:56
 * 
 * @Description: 检查是否为平衡二叉树
 */

public class CheckIsBT {
    public class ReturnType {
        int height;
        boolean isBT;

        ReturnType(boolean isBT, int height) {
            this.isBT = isBT;
            this.height = height;
        }
    }

    public boolean check(Node root) {
        if (root == null) {
            return true;
        }
        return checkRec(root).isBT;
    }

    private ReturnType checkRec(Node node) {
        if (node == null) {
            return new ReturnType(true, 0);
        }
        ReturnType leftReturn = checkRec(node.lChild);
        ReturnType rightReturn = checkRec(node.rChild);
        // 左右子树为平衡树并且高度差不大于一，则当前树为平衡树
        boolean isBT = leftReturn.isBT && rightReturn.isBT && Math.abs(leftReturn.height - rightReturn.height) < 2;
        int height = Math.max(leftReturn.height, rightReturn.height) + 1;
        return new ReturnType(isBT, height);
    }

    public static void main(String[] args) {
        int[] BT = { -1, 8, 6, 9, 4, 7, -1, 11, 3, 5 };
        int[] notBT = { -1, 8, 6, 9, 4, 7, -1, 11, 3, 5, -1, -1, -1, -1, -1, -1, 4 };
        Node root1 = CreateTree.create(BT);
        Node root2 = CreateTree.create(notBT);
        CheckIsBT obj = new CheckIsBT();
        System.out.println(obj.check(root1));
        System.out.println(obj.check(root2));
    }
}
