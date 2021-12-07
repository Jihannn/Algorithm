package Graph_;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/*
 * @Author: Jihan
 * @Date: 2021-12-06 15:11:22
 * @Description:Kurskal和Prim最小生成树算法
 */
public class Kurskal_Prim {
    // TODO:替换并查集
    private static class MySets {
        public HashMap<Node, ArrayList<Node>> map = new HashMap<>();

        public MySets(Graph graph) {
            // 每个结点自身为一个数据项
            for (Node node : graph.nodes.values()) {
                ArrayList<Node> list = new ArrayList<>();
                list.add(node);
                map.put(node, list);
            }
        }

        // 两结点是否在同一数据项集合
        public boolean isOne(Node from, Node to) {
            return map.get(from) == map.get(to);
        }

        // 两结点合并集合
        public void union(Node from, Node to) {
            ArrayList<Node> list = map.get(to);
            for (Node node : map.get(from)) {
                list.add(node);
                map.put(node, list);
            }
        }
    }

    private static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }

    // 以边优先
    public static ArrayList<Edge> kurskal(Graph graph) {
        // 每个结点一个数据项
        MySets sets = new MySets(graph);
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(new EdgeComparator());
        // 按权值排序
        for (Edge edge : graph.edges) {
            minHeap.add(edge);
        }
        ArrayList<Edge> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            // 找权值最小的边
            Edge minEdge = minHeap.poll();
            // 不成环则数据项合并
            if (!sets.isOne(minEdge.from, minEdge.to)) {
                sets.union(minEdge.from, minEdge.to);
                result.add(minEdge);
            }
        }
        return result;
    }

    // 以点优先
    public static HashSet<Edge> prim(Graph graph) {
        // 已经访问过的结点
        HashSet<Node> set = new HashSet<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(new EdgeComparator());
        HashSet<Edge> result = new HashSet<>();
        // 防止非连通图的情况
        for (Node node : graph.nodes.values()) {
            // 随机挑选一个结点
            if (!set.contains(node)) {
                set.add(node);
                // 关联的边权值排序
                for (Edge edge : node.edges) {
                    minHeap.add(edge);
                }
                while (!minHeap.isEmpty()) {
                    // 权值最小的边
                    Edge minEdge = minHeap.poll();
                    Node toNode = minEdge.to;
                    // 关联点加入集合
                    if (!set.contains(toNode)) {
                        set.add(toNode);
                        result.add(minEdge);
                        for (Edge edge : toNode.edges) {
                            minHeap.add(edge);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Graph graph = createGraph();
        ArrayList<Edge> result1 = kurskal(graph);
        HashSet<Edge> result2 = prim(graph);
        System.out.println();
    }

    private static Graph createGraph() {
        Graph graph = new Graph();
        HashMap<Integer, Node> nodes = new HashMap<>();
        HashSet<Edge> edges = new HashSet<>();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
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
        Edge e1 = new Edge(2, n1, n2);
        Edge e2 = new Edge(3, n2, n6);
        Edge e3 = new Edge(6, n2, n4);
        Edge e4 = new Edge(5, n1, n3);
        Edge e5 = new Edge(4, n4, n3);
        Edge e6 = new Edge(6, n3, n5);
        n1.edges.add(e1);
        n1.edges.add(e4);
        n2.edges.add(e2);
        n2.edges.add(e3);
        n3.edges.add(e6);
        n4.edges.add(e5);
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        edges.add(e5);
        edges.add(e6);
        graph.edges = edges;
        return graph;
    }
}
