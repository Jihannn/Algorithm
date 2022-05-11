package Graph;
/*
 * @Author: Jihan
 * @Date: 2022-05-11 08:35:55
 * @Description: 
 */

import java.util.ArrayList;

public class Node {
    int in;
    int to;
    int value;
    ArrayList<Node> nexts;
    ArrayList<Edge> edges;

    public Node(){
        in = 0;
        to = 0;
        value = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
