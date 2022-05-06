import java.util.LinkedList;
import java.util.Queue;

/*
 * @Author: Jihan
 * @Date: 2022-05-05 17:25:27
 * @Description: 是否为完全二叉树
 */
public class Rec_IsCBT {
    public static class Node {
        Node lChild;
        Node rChild;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        boolean isCBT;
        boolean isFull;
        int height;

        public Info(boolean isCBT,boolean isFull,int height) {
            this.isCBT = isCBT;
            this.isFull = isFull;
            this.height = height;
        }
    }

    public static boolean isCBT(Node root){
        if(root == null){
            return true;
        }
        return process(root).isCBT;
    }

    public static Info process(Node node){
        if(node == null){
            return new Info(true, true, 0);
        }
        Info leftInfo = process(node.lChild);
        Info rightInfo = process(node.rChild);
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isCBT = false;
        if(leftInfo.isFull && rightInfo.isFull && leftInfo.height - rightInfo.height == 1){
            isCBT = true;
        }
        if(leftInfo.isCBT && rightInfo.isFull && leftInfo.height - rightInfo.height == 1){
            isCBT = true;
        }
        if(leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height){
            isCBT = true;
        }
        if(leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height){
            isCBT = true;
        }
        return new Info(isCBT, isFull, height);
    }

    public static boolean isCBT1(Node root){
        if(root == null){
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean leaf = false;
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if(node.rChild != null && node.lChild == null){
                return false;
            }
            if(leaf && (node.lChild != null || node.rChild != null)){
                return false;
            }
            if(node.lChild != null){
                queue.add(node.lChild);
            }
            if(node.rChild != null){
                queue.add(node.rChild);
            }
            // 如果孩子不全,那么后面结点必须为叶子结点,否则不是完全二叉树
            if(node.lChild == null || node.rChild == null){
                leaf = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.lChild = new Node(2);
        root.rChild = new Node(3);
        root.lChild.lChild = new Node(4);
        root.rChild.lChild = new Node(5);
        System.out.println(isCBT(root));
        System.out.println(isCBT1(root));
    }
}