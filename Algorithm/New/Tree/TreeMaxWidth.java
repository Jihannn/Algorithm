package Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
 * @Author: Jihan
 * 
 * @Date: 2021-11-30 17:04:27
 * 
 * @Description:获取二叉树最大宽度
 */

public class TreeMaxWidth {

    public int getTreeWidth1(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        // 存放结点的层数
        HashMap<Node, Integer> levelMap = new HashMap<>();
        // 当前层数
        int curLevel = 1;
        // 当前层数的结点数
        int curLevelNodes = 0;
        // 最长宽度
        int maxWidth = 0;
        queue.add(root);
        levelMap.put(root, curLevel);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            // 如果为当前层，结点数和宽度加一
            if (curLevel == levelMap.get(cur)) {
                curLevelNodes++;
                maxWidth++;
            } else {
                // 如果不是当前层，说明到下一层，结算上一层的最大宽度，并且初始化当前层数和结点
                maxWidth = Math.max(maxWidth, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
            if (cur.lChild != null) {
                queue.add(cur.lChild);
                levelMap.put(cur.lChild, curLevel + 1);
            }
            if (cur.rChild != null) {
                queue.add(cur.rChild);
                levelMap.put(cur.rChild, curLevel + 1);
            }
        }
        // 如果最后一层为最长宽度，还需比较一次
        return Math.max(maxWidth, curLevelNodes);
    }

    public int getMaxWidth2(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        // 当前层数最后的结点
        Node curEndNode = root;
        // 下一层最后的结点
        Node nextEndNode = null;
        // 最长宽度
        int maxWidth = 0;
        // 当前层数的结点数
        int curLevelNodes = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            // 如果当前结点为当前层最后的结点，结算宽度后跳转到下一层
            if (cur == curEndNode) {
                curLevelNodes++;
                maxWidth = Math.max(maxWidth, curLevelNodes);
                curEndNode = nextEndNode;
                nextEndNode = null;
                curLevelNodes = 0;
            } else {
                curLevelNodes++;
            }
            if (cur.lChild != null) {
                queue.add(cur.lChild);
                nextEndNode = cur.lChild;
            }
            if (cur.rChild != null) {
                queue.add(cur.rChild);
                nextEndNode = cur.rChild;
            }
        }
        return maxWidth;
    }
}