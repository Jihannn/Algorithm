package Tree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * @Author: Jihan
 * 
 * @Date: 2021-12-01 17:00:54
 * 
 * @Description:检查是否为完全二叉树
 */
public class CheckIsCBT {
    // 1.如果左子树为空右子树不为空则不为完全二叉树
    // 2.如果该结点的左右子树缺一，后面的结点如果不是叶子结点则不为完全二叉树

    public boolean check(Node root) {
        if (root == null) {
            return false;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean isLeafNext = false;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            // 情况1和情况2
            if ((cur.lChild == null && cur.rChild != null)
                    || (isLeafNext && (cur.lChild != null || cur.rChild != null))) {
                return false;
            }
            if (cur.lChild != null) {
                queue.add(cur.lChild);
            }
            if (cur.rChild != null) {
                queue.add(cur.rChild);
            }
            // 进入情况2
            if (cur.lChild == null || cur.rChild == null) {
                isLeafNext = true;
            }
        }
        return true;
    }
}