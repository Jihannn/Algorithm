/*
 * @Author: Jihan
 * @Date: 2022-05-11 09:00:05
 * @Description: 宽度优先遍历
 */
package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSeach {
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> pass = new HashSet<>();
        queue.add(node);
        pass.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            visit(cur);
            for (Node next : cur.nexts) {
                if (!pass.contains(next)) {
                    queue.add(next);
                    pass.add(next);
                }
            }
        }
    }

    private static void visit(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
    }
}
