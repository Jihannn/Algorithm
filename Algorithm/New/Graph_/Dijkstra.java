package Graph_;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/*
 * @Author: Jihan
 * @Date: 2021-12-07 20:45:39
 * @Description: 
 */
public class Dijkstra {

    public static HashMap<Node, Integer> dj(Node node) {
        // 源点到Node的距离
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        HashSet<Node> selectedSet = new HashSet<>();
        distanceMap.put(node, 0);
        // 拿到最短且未被锁定的结点
        Node minNode = getMinAndNoSelected(distanceMap, selectedSet);
        while (minNode != null) {
            // 源点到该结点的距离
            int distance = distanceMap.get(minNode);
            // 遍历该结点关联的边
            for (Edge edge : minNode.edges) {
                // 如果之前没有路径到达，则新增
                if (!distanceMap.containsKey(edge.to)) {
                    distanceMap.put(edge.to, distance + edge.weight);
                } else {
                    // 如果之前已有路径，则判断路径长度
                    distanceMap.put(edge.to, Math.min(distance + edge.weight, distanceMap.get(edge.to)));
                }
            }
            selectedSet.add(minNode);
            minNode = getMinAndNoSelected(distanceMap, selectedSet);
        }
        return distanceMap;
    }

    private static Node getMinAndNoSelected(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedSet) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
            if (!selectedSet.contains(entry.getKey()) && entry.getValue() < minDistance) {
                minDistance = entry.getValue();
                minNode = entry.getKey();
            }
        }
        return minNode;
    }
}
