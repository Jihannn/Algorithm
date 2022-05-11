package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
 * @Author: Jihan
 * @Date: 2022-05-11 09:06:16
 * @Description: 拓扑排序
 */
public class Topology {
    public static ArrayList<Node> topology(Graph graph){
        if(graph == null){
            return null;
        }
        HashMap<Integer,Node> nodes = graph.nodes;
        ArrayList<Node> rtn = new ArrayList<>();
        while(rtn.size() !=  nodes.size()){
            for (Node node : nodes.values()) {
                if(node.in == 0){
                    rtn.add(node);
                    for (Node next : node.nexts) {
                        next.in--;
                    }
                }
            }
        }
        return rtn;
    }

    public static ArrayList<Node> topologySort(Graph graph){
        if(graph == null){
            return null;
        }
        Queue<Node> zeroQueue = new LinkedList<>();
        HashMap<Node,Integer> inMap = new HashMap<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if(node.in == 0){
                zeroQueue.add(node);
            }
        }
        ArrayList<Node> rtn = new ArrayList<>();
        while(!zeroQueue.isEmpty()){
            Node cur = zeroQueue.poll();
            rtn.add(cur);
            for (Node next : cur.nexts) {
                int nowIn = inMap.get(next) - 1;
                inMap.put(next, nowIn);
                if(nowIn == 0){
                    zeroQueue.add(next);
                }
            }
        }
        return rtn;
    }
}
