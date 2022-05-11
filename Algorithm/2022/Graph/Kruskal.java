package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

/*
 * @Author: Jihan
 * @Date: 2022-05-11 09:27:31
 * @Description: Kruskal最小生成树
 */
public class Kruskal {
    public static ArrayList<Edge> kruskal(Graph graph) {
        UnionFind unionFind = new UnionFind(graph.nodes);
        PriorityQueue<Edge> minHeap = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        for (Edge edge : graph.edges) {
            minHeap.add(edge);
        }
        ArrayList<Edge> rtn = new ArrayList<>();
        while(!minHeap.isEmpty()){
            Edge cur = minHeap.poll();
            if(!unionFind.isSameSet(cur.from, cur.to)){
                rtn.add(cur);
                unionFind.union(cur.from, cur.to);
            }
        }
        return rtn;
    }

    public static class UnionFind {
        HashMap<Node, Node> parents;
        HashMap<Node, Integer> sizes;
        int sets;

        public UnionFind(HashMap<Integer, Node> nodes) {
            this.parents = new HashMap<>();
            this.sizes = new HashMap<>();
            this.sets = nodes.size();
            for (Node node : nodes.values()) {
                parents.put(node, node);
                sizes.put(node, 1);
            }
        }

        private Node findAncestor(Node node) {
            Stack<Node> path = new Stack<>();
            while (node != parents.get(node)) {
                path.push(node);
                node = parents.get(node);
            }
            while (!path.isEmpty()) {
                parents.put(path.pop(), node);
            }
            return node;
        }

        public void union(Node a, Node b) {
            Node aAncestor = findAncestor(a);
            Node bAncestor = findAncestor(b);
            if (aAncestor != bAncestor) {
                int aSize = sizes.get(aAncestor);
                int bSize = sizes.get(bAncestor);
                Node big = aSize > bSize ? aAncestor : bAncestor;
                Node small = aAncestor == big ? bAncestor : aAncestor;
                parents.put(small, big);
                sizes.put(big, aSize + bSize);
                sizes.remove(small);
                sets--;
            }
        }

        public boolean isSameSet(Node a, Node b) {
            return findAncestor(a) == findAncestor(b);
        }

    }
}