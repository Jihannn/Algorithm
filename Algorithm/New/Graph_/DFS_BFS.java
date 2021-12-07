package Graph_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * @Author: Jihan
 * @Date: 2021-12-06 10:59:01
 * @Description:广度优先遍历和深度优先遍历
 */
public class DFS_BFS {
    public static void DFS(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        visit(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            for (Node n : node.nexts) {
                if (!set.contains(n)) {
                    set.add(n);
                    // 把node压回去，下次弹出会走另一条路
                    stack.push(node);
                    stack.push(n);
                    visit(n);
                    break;
                }
            }
        }
    }

    public static void BFS(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            visit(node);
            for (Node n : node.nexts) {
                if (!set.contains(n)) {
                    set.add(n);
                    queue.add(n);
                }
            }
        }
    }

    private static void visit(Node node) {
        if (node != null) {
            System.out.print(node.value + "->");
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        HashMap<Integer, Node> nodes = new HashMap<>();
        HashSet<Edge> edges = new HashSet<>();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        n1.nexts = new ArrayList<>();
        n2.nexts = new ArrayList<>();
        n3.nexts = new ArrayList<>();
        n4.nexts = new ArrayList<>();
        n5.nexts = new ArrayList<>();
        n1.nexts.add(n2);
        n1.nexts.add(n3);
        n2.nexts.add(n4);
        n2.nexts.add(n6);
        n4.nexts.add(n3);
        n3.nexts.add(n5);
        nodes.put(1, n1);
        nodes.put(2, n2);
        nodes.put(3, n3);
        nodes.put(4, n4);
        nodes.put(5, n5);
        nodes.put(6, n6);
        graph.nodes = nodes;
        BFS(graph.nodes.get(1));
        System.out.println();
        DFS(graph.nodes.get(1));
    }
}