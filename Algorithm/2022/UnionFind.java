import java.util.HashMap;
import java.util.Stack;

/*
 * @Author: Jihan
 * @Date: 2022-05-09 09:55:57
 * @Description: 并查集
 */
public class UnionFind {
    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind_<V> {
        private HashMap<V, Node<V>> nodes;
        private HashMap<Node<V>, Node<V>> parents;
        private HashMap<Node<V>, Integer> sizeMap;

        public UnionFind_(V[] values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V v : values) {
                Node<V> node = new Node<>(v);
                nodes.put(v, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Node<V> getAncestor(Node<V> node) {
            Stack<Node<V>> path = new Stack<>();
            Node<V> parent = parents.get(node);
            while (node != parent) {
                path.push(node);
                node = parent;
                parent = parents.get(node);
            }
            while (!path.isEmpty()) {
                parents.put(path.pop(), node);
            }
            return node;
        }

        public void union(V a, V b) {
            Node<V> node1 = getAncestor(nodes.get(a));
            Node<V> node2 = getAncestor(nodes.get(b));
            if (node1 != node2) {
                int size1 = sizeMap.get(node1);
                int size2 = sizeMap.get(node2);
                Node<V> big = size1 > size2 ? node1 : node2;
                Node<V> small = big == node1 ? node2 : node1;
                parents.put(small, big);
                sizeMap.put(big, size1 + size2);
                sizeMap.remove(small);
            }
        }

        public boolean isSame(V a, V b) {
            return getAncestor(nodes.get(a)) == getAncestor(nodes.get(b));
        }

    }
}