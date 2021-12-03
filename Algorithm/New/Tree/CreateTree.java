package Tree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * @Author: Jihan
 * 
 * @Date: 2021-12-02 15:38:01
 * 
 * @Description:创建用于测试的二叉树
 */
public class CreateTree {

    // 数组按层序遍历的顺序，无结点则为-1，零位置置空
    public static Node create(int[] array) {
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(array[1]);
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int leftIndex = 2 * index;
            int rightIndex = 2 * index + 1;
            if (leftIndex < array.length) {
                Node node = new Node(array[leftIndex]);
                queue.add(node);
                if (node.data != -1) {
                    cur.lChild = node;
                }
            }
            if (rightIndex < array.length) {
                Node node = new Node(array[rightIndex]);
                queue.add(node);
                if (node.data != -1) {
                    cur.rChild = node;
                }
            }
            index++;
        }
        return root;
    }
}
