package Tree;

import java.util.ArrayList;

/*
 * @Author: Jihan
 * @Date: 2022-06-16 14:04:02
 * @Description: 跳表
 */
public class SkipListMap {
    public static class SKNode<K extends Comparable<K>, V> {
        ArrayList<SKNode<K, V>> nextNodes;
        int level;
        K k;
        V v;

        public SKNode(K k, V v) {
            this.k = k;
            this.v = v;
            level = 0;
            nextNodes = new ArrayList<>();
        }

        // true : key比otherKey小
        public boolean isKeyLess(K otherKey) {
            return otherKey != null && (k == null || k.compareTo(otherKey) < 0);
        }

        public boolean isKeyEqual(K otherKey) {
            return (otherKey == null && k == null) || (k != null && otherKey != null && otherKey.compareTo(k) == 0);
        }

    }

    public static class SKMap<K extends Comparable<K>, V> {
        private static final float POSSIBILITY = 0.5f;
        int maxLevel;
        int size;
        SKNode<K, V> head;

        public SKMap(K k, V v) {
            head = new SKNode<K, V>(null, null);
            head.nextNodes.add(null);
            maxLevel = 0;
            size = 0;
        }

        // 找到比k小且最近的位置
        private SKNode<K, V> findRightLessNodeInMap(K k) {
            if (k == null) {
                return null;
            }
            SKNode<K, V> curNode = head;
            int curLevel = maxLevel;
            while (curLevel >= 0) {
                curNode = findRightLessNodeInLevel(k, curNode, curLevel--);
            }
            return curNode;
        }

        private SKNode<K, V> findRightLessNodeInLevel(K k, SKNode<K, V> cur, int level) {
            SKNode<K, V> pre = cur;
            SKNode<K, V> next = cur.nextNodes.get(level);
            while (next != null && next.isKeyLess(k)) {
                pre = next;
                next = next.nextNodes.get(level);
            }
            return pre;
        }

        public void put(K k, V v) {
            if (k == null) {
                return;
            }
            SKNode<K, V> lastNode = findRightLessNodeInMap(k);
            SKNode<K, V> next = lastNode.nextNodes.get(0);
            if (next != null && next.isKeyEqual(k)) {
                next.v = v;
            } else {
                size++;
                int curLevel = 0;
                SKNode<K, V> curNode = new SKNode<>(k, v);
                curNode.nextNodes.add(null);
                // 随机选择层数
                while (Math.random() < POSSIBILITY) {
                    curLevel++;
                    curNode.nextNodes.add(null);
                }
                while (curLevel >= maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                int level = maxLevel;
                SKNode<K, V> pre = head;
                while (level >= 0) {
                    pre = findRightLessNodeInLevel(k, pre, level);
                    if (level <= curLevel) {
                        curNode.nextNodes.set(level, pre.nextNodes.get(level));
                        pre.nextNodes.set(level, curNode);
                    }
                    level--;
                }
            }
        }

        public V get(K k) {
            if (k == null) {
                return null;
            }
            SKNode<K, V> lastNode = findRightLessNodeInMap(k);
            SKNode<K, V> next = lastNode.nextNodes.get(0);
            return next != null && next.isKeyEqual(k) ? next.v : null;
        }

        public void remove(K k) {
            if (isContainsKey(k)) {
                size--;
                int level = maxLevel;
                SKNode<K, V> node = head;
                while (level >= 0) {
                    node = findRightLessNodeInLevel(k, node, level);
                    SKNode<K, V> next = node.nextNodes.get(level);
                    if (next != null && next.isKeyEqual(k)) {
                        node.nextNodes.set(level, next.nextNodes.get(level));
                    }
                    if (level != 0 && node == head && node.nextNodes.get(level) == null) {
                        head.nextNodes.remove(level);
                        maxLevel--;
                    }
                    level--;
                }
            }
        }

        private boolean isContainsKey(K k) {
            if (k == null) {
                return false;
            }
            SKNode<K, V> lastNode = findRightLessNodeInMap(k);
            SKNode<K, V> next = lastNode.nextNodes.get(0);
            return next != null && next.isKeyEqual(k);
        }
    }
}