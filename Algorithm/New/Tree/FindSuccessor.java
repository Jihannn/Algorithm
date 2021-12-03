package Tree;

/*
 * @Author: Jihan
 * 
 * @Date: 2021-12-02 17:22:13
 * 
 * @Description:在线索二叉树中找到结点的后继结点
 */

public class FindSuccessor {
    public class ThreadNode {
        int data;
        ThreadNode parent;
        ThreadNode lChild;
        ThreadNode rChild;

        ThreadNode(int data, ThreadNode parent) {
            this.data = data;
            this.parent = parent;
        }
    }

    // 情况一：结点存在右孩子,则右孩子的最左子树结点即为后继结点
    // 情况二：结点为父亲的右孩子，则往上回溯到结点为父结点的左孩子停止，则该父结点为后继结点
    // 情况三：结点为父亲的右孩子，若往上回溯到null，则该结点为树的最右侧结点
    public ThreadNode get(ThreadNode root) {
        if (root == null) {
            return null;
        }
        ThreadNode cur = root;
        // 情况一
        if (cur.rChild != null) {
            cur = cur.rChild;
            while (cur.lChild != null) {
                cur = cur.lChild;
            }
            return cur;
        }
        // 情况三/二
        while (cur.parent != null && cur == cur.parent.rChild) {
            cur = cur.parent;
        }
        return cur.parent;
    }
}