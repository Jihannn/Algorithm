package Graph_;

/*
 * @Author: Jihan
 * @Date: 2021-12-06 09:12:14
 * @Description:边的数据结构
 */
public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
