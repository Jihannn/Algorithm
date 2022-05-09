package Tree;
import java.util.LinkedList;
import java.util.Queue;
/*
 * @Author: Jihan
 * @Date: 2022-05-04 21:09:44
 * @Description: 求最大宽度
 */
public class BinaryTreeWidth{
    public static class Node{
        Node lChild;
        Node rChild;
        int value;
        public Node(int value){
            this.value = value;
        }
    }

    public static int getWidth(Node root){
        if(root == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        Node nowLevel = root;
        Node nextLevel = null;
        int maxWidth = 0;
        int curWidth = 0;
        queue.add(root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if(node.lChild != null){
                nextLevel = node.lChild;
                queue.add(node.lChild);
            }
            if(node.rChild != null){
                nextLevel = node.rChild;
                queue.add(node.rChild);
            }
            curWidth++;
            if(nowLevel == node){
                maxWidth = Math.max(maxWidth, curWidth);
                curWidth = 0;
                nowLevel = nextLevel;
                nextLevel = null;
            }
        }
        return maxWidth;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.lChild = new Node(2);
        root.rChild = new Node(3);
        root.lChild.lChild = new Node(4);
        root.lChild.rChild = new Node(5);
        root.rChild.lChild = new Node(6);
        root.lChild.lChild.rChild = new Node(7);
        root.lChild.rChild.lChild = new Node(8);
        root.rChild.lChild.rChild = new Node(9);
        System.out.println(getWidth(root));
    }
}