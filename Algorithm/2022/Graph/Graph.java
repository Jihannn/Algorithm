package Graph;

import java.util.HashMap;
import java.util.HashSet;

/*
 * @Author: Jihan
 * @Date: 2022-05-11 08:33:32
 * @Description: 
 */
public class Graph {
    HashMap<Integer, Node> nodes;
    HashSet<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}