package Tree;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * @Author: Jihan
 * @Date: 2022-05-04 17:05:06
 * @Description: 二叉树序列化反序列化
 */
public class BinaryTreeSerial {
    public static class Node{
        Node lChild;
        Node rChild;
        int value;
        public Node(int value){
            this.value = value;
        }
    }

    public static Queue<String> preSerial(Node root){
        if(root == null){
            return null;
        }
        Stack<Node> stack = new Stack<>();
        Queue<String> strQueue = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            if(node == null){
                strQueue.add(null);
                continue;
            }else{
                strQueue.add(String.valueOf(node.value));
                stack.push(node.rChild);
                stack.push(node.lChild);
            }
        }
        return strQueue;
    }

    public static Node buildByPreSerial(Queue<String> queue){
        if(queue == null || queue.size() < 1){
            return null;
        }
        return preBuild(queue,queue.poll());
    }

    public static Node preBuild(Queue<String> queue,String val){
        if(val == null){
            return null;
        }
        Node node = new Node(Integer.valueOf(val));
        node.lChild = preBuild(queue,queue.poll());
        node.rChild = preBuild(queue,queue.poll());
        return node;
    }

    public static void preRec(Node node){
        if(node == null){
            return;
        }
        System.out.print(node.value+" ");
        preRec(node.lChild);
        preRec(node.rChild);
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
        Queue<String> queue = preSerial(root);
        for (String str : queue) {
            System.out.print(str + " ");
        }
        System.out.println();
        Node node = buildByPreSerial(queue);
        preRec(node);
    }
}
