package Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/*
 * @Author: Jihan
 * @Date: 2022-05-12 09:47:44
 * @Description:Dijkstra源点到其它节点最小距离
 */
public class Dijkstra {
    public static HashMap<Node, Integer> dj1(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        HashSet<Node> selectedSet = new HashSet<>();
        distanceMap.put(from, 0);
        Node minNode = getMinAndUnSelected(distanceMap, selectedSet);
        while (minNode != null) {
            for (Edge edge : minNode.edges) {
                int preDistance = distanceMap.get(minNode);
                if (!distanceMap.containsKey(edge.to)) {
                    distanceMap.put(edge.to, preDistance + edge.weight);
                } else {
                    distanceMap.put(edge.to, Math.min(distanceMap.get(edge.to), preDistance + edge.weight));
                }
            }
            selectedSet.add(minNode);
            minNode = getMinAndUnSelected(distanceMap, selectedSet);
        }
        return distanceMap;
    }

    private static Node getMinAndUnSelected(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedSet) {
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

    public static HashMap<Node, Integer> dj2(Node from, int size) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        HeapGreater minHeap = new HeapGreater(size);
        minHeap.addAndUpdateAndIgnore(from, 0);
        while (!minHeap.isEmpty()) {
            NodeRecord record = minHeap.pop();
            for (Edge edge : record.node.edges) {
                minHeap.addAndUpdateAndIgnore(edge.to, record.distance + edge.weight);
            }
            distanceMap.put(record.node, record.distance);
        }
        return distanceMap;
    }

    public static class NodeRecord {
        Node node;
        int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class HeapGreater {
        Node[] nodes;
        HashMap<Node, Integer> indexMap;
        HashMap<Node, Integer> distanceMap;
        int size;

        public HeapGreater(int n) {
            nodes = new Node[n];
            indexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void addAndUpdateAndIgnore(Node node, int distance) {
            if (!indexMap.containsKey(node)) {
                nodes[size] = node;
                distanceMap.put(node, distance);
                indexMap.put(node, size);
                heapInsert(size++);
            }
            if (indexMap.get(node) != -1) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                heapInsert(indexMap.get(node));
            }
        }

        public NodeRecord pop() {
            if (isEmpty()) {
                return null;
            }
            Node node = nodes[0];
            int distance = distanceMap.get(node);
            NodeRecord record = new NodeRecord(node, distance);
            swap(0, --size);
            heapify(0);
            distanceMap.remove(node);
            indexMap.put(node, -1);
            return record;
        }

        private void heapInsert(int index) {
            while (index > 0 && distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index) {
            while (index * 2 + 1 < size) {
                int leftIndex = index * 2 + 1;
                int small = leftIndex + 1 < size
                        && distanceMap.get(nodes[leftIndex + 1]) < distanceMap.get(nodes[leftIndex]) ? leftIndex + 1
                                : leftIndex;
                small = distanceMap.get(nodes[index]) <= distanceMap.get(nodes[small]) ? index : small;
                if (small == index) {
                    break;
                }
                swap(index, small);
                index = small;
            }
        }

        private void swap(int i, int j) {
            indexMap.put(nodes[i], j);
            indexMap.put(nodes[j], i);
            Node temp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = temp;
        }
    }
}
