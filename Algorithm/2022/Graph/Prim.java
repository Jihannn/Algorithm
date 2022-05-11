package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/*
 * @Author: Jihan
 * @Date: 2022-05-11 10:56:00
 * @Description: Prim最小生成树
 */
public class Prim {
    public static ArrayList<Edge> prim(Graph graph) {
        HashSet<Node> pass = new HashSet<>();
        ArrayList<Edge> rtn = new ArrayList<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        for (Node node : graph.nodes.values()) {
            if (!pass.contains(node)) {
                pass.add(node);
                for (Edge edge : node.edges) {
                    minHeap.add(edge);
                }
                while (!minHeap.isEmpty()) {
                    Edge minEdge = minHeap.poll();
                    if (!pass.contains(minEdge.to)) {
                        pass.add(minEdge.to);
                        rtn.add(minEdge);
                        for (Edge e : minEdge.to.edges) {
                            minHeap.add(e);
                        }
                    }
                }
            }
        }
        return rtn;
    }
}
