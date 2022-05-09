package Tree;
/*
 * @Author: Jihan
 * @Date: 2022-05-06 08:51:40
 * @Description: 是否为满二叉树
 */
public class Rec_IsFull {
    public static class Node {
        Node lChild;
        Node rChild;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }
    
    public static class Info{
        boolean isFull;
        int height;
        public Info(boolean isFull,int height){
            this.isFull =isFull;
            this.height = height;
        }
    }

    public static boolean isFull(Node root){
        if(root == null){
            return true;
        }
        return process(root).isFull;
    }

    public static Info process(Node node){
        if(node == null){
            return new Info(true, 0);
        }
        Info leftInfo = process(node.lChild);
        Info rightInfo = process(node.rChild);
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        int height = leftInfo.height + 1;
        return new Info(isFull, height);
    }

	public static void main(String[] args) {
	}
}
