package Graph_;

import java.util.ArrayList;

/*
 * @Author: Jihan
 * @Date: 2021-12-06 09:10:00
 * @Description: 顶点的数据结构
 */
public class Node {
    public int value;
    // 入度数
    public int in;
    // 出度数
    public int out;
    // 出度的顶点
    public ArrayList<Node> nexts;
    // 关联的边
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
