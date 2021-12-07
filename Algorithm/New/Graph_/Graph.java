package Graph_;

import java.util.HashMap;
import java.util.HashSet;

/*
 * @Author: Jihan
 * @Date: 2021-12-06 09:08:32
 * @Description: 图的数据结构
 */
public class Graph {
    // 顶点的集合，<下标,顶点>
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}