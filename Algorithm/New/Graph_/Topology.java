package Graph_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * @Author: Jihan
 * @Date: 2021-12-06 12:00:49
 * @Description:拓扑排序只能作用于有向无环图，排序具有依赖关系的图
 */
public class Topology {
    public static List<Node> sort(Graph graph) {
        // 所有结点入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 入度为零的结点
        Queue<Node> zeroInQueue = new LinkedList<>();
        // 收集每个结点的入度，并且把入度为零的入队
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        ArrayList<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node node = zeroInQueue.poll();
            result.add(node);
            // 把入度为零的出队，并且出度结点的入度数减一
            for (Node n : node.nexts) {
                int nowIn = inMap.get(n) - 1;
                inMap.put(n, nowIn);
                if (nowIn == 0) {
                    zeroInQueue.add(n);
                }
            }
        }
        return result;
    }
}
