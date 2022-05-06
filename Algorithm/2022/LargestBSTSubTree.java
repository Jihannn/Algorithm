/*
 * @Author: Jihan
 * @Date: 2022-05-06 09:16:28
 * @Description:查找最大(节点最多)二叉搜索子树
 */
public class LargestBSTSubTree {
    public static class Node {
        Node lChild;
        Node rChild;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        int nodes;
        int max;
        int min;
        int largest;

        public Info(int nodes, int max, int min, int largest) {
            this.nodes = nodes;
            this.max = max;
            this.min = min;
            this.largest = largest;
        }
    }

    public static int largestBSTSubTree(Node root){
        if(root == null){
            return 0;
        }
        return process(root).largest;
    }

    public static Info process(Node node){
        if(node == null){
            return null;
        }
        Info leftInfo = process(node.lChild);
        Info rightInfo = process(node.rChild);
        int nodes = 1;
        int max = node.value;
        int min = node.value;
        boolean isLeftSmall = true;
        boolean isRightBig = true;
        if(leftInfo != null){
            nodes += leftInfo.nodes;
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            isLeftSmall = node.value >= leftInfo.max;
        }
        if(rightInfo != null){
            nodes += rightInfo.nodes;
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            isRightBig = node.value <= rightInfo.min;
        }
        int largest;
        if(isLeftSmall && isRightBig){
            largest = nodes;
        }else{
            largest = leftInfo != null ? leftInfo.largest : (rightInfo != null ? rightInfo.largest : 0);
        }
        return new Info(nodes, max, min, largest);
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.lChild = new Node(5);
        root.lChild.lChild = new Node(1);
        root.lChild.lChild.rChild = new Node(2);
        root.lChild.rChild = new Node(8);
        root.rChild = new Node(15);
        root.rChild.rChild = new Node(7);
        System.out.println(largestBSTSubTree(root));
    }
}