package Graph;

/*
 * @Author: Jihan
 * @Date: 2022-05-11 08:36:19
 * @Description: 
 */
public class Edge {
    Node from;
    Node to;
    int weight;

    public Edge(Node from,Node to){
        this.from = from;
        this.to = to;
        this.weight = 0;
    }

    public Edge(Node from,Node to,int weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}