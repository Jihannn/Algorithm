package Graph;
/*
 * @Author: Jihan
 * @Date: 2022-05-11 08:32:27
 * @Description: 深度优先遍历
 */

import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch {
    public void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> pass = new HashSet<>();
        stack.push(node);
        pass.add(node);
        visit(node);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!pass.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    pass.add(next);
                    visit(next);
                    break;
                }
            }
        }
    }

    private void visit(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.value + " ");
    }
}
