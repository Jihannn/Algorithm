/*
 * @Author: Jihan
 * @Date: 2022-06-16 09:49:25
 * @Description: SizeBalanceTree
 */
package Tree;

public class SBTree {
    public static class SBNode<K extends Comparable<K>, V> {
        SBNode<K, V> l;
        SBNode<K, V> r;
        int size;
        K k;
        V v;

        public SBNode(K k, V v) {
            size = 0;
            this.k = k;
            this.v = v;
        }
    }

    public static class SizeBalanceTree<K extends Comparable<K>, V> {
        private SBNode<K, V> root;

        public SizeBalanceTree() {
            root = null;
        }

        private SBNode<K, V> leftRotate(SBNode<K, V> cur) {
            SBNode<K, V> newCur = cur.r;
            cur.r = newCur.l;
            newCur.l = cur;
            newCur.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return newCur;
        }

        private SBNode<K, V> rightRotate(SBNode<K, V> cur) {
            SBNode<K, V> newCur = cur.l;
            cur.l = newCur.r;
            newCur.r = cur;
            newCur.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return newCur;
        }

        private SBNode<K, V> maintain(SBNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.l != null ? cur.l.size : 0;
            int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            int rightSize = cur.r != null ? cur.r.size : 0;
            int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            // 平衡策略：侄子节点数比叔叔节点数多则需要调整
            // 旋转后需要调整子节点被改变的节点，以确保平衡
            if (leftLeftSize > rightSize) {
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (leftRightSize > rightSize) {
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            } else if (rightRightSize > leftSize) {
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            } else if (rightLeftSize > leftSize) {
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.r = maintain(cur.r);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            }
            return cur;
        }

        private SBNode<K, V> add(SBNode<K, V> cur, K k, V v) {
            if (cur == null) {
                return new SBNode<K, V>(k, v);
            }
            cur.size++;
            if (k.compareTo(cur.k) < 0) {
                cur.l = add(cur.l, k, v);
            } else {
                cur.r = add(cur.r, k, v);
            }
            return maintain(cur);
        }

        private SBNode<K, V> delete(SBNode<K, V> cur, K k) {
            cur.size--;
            if (k.compareTo(cur.k) < 0) {
                cur.l = delete(cur.l, k);
            } else if (k.compareTo(cur.k) > 0) {
                cur.r = delete(cur.r, k);
            } else {
                if (cur.l == null && cur.r == null) {
                    cur = null;
                } else if (cur.l == null && cur.r != null) {
                    cur = cur.r;
                } else if (cur.l != null && cur.r == null) {
                    cur = cur.l;
                } else {
                    SBNode<K, V> pre = null;
                    SBNode<K, V> newCur = cur.r;
                    newCur.size--;
                    while (newCur.l != null) {
                        pre = newCur;
                        newCur = newCur.l;
                        newCur.size--;
                    }
                    if (pre != null) {
                        pre.l = newCur.r;
                        newCur.r = cur.r;
                    }
                    newCur.l = cur.l;
                    newCur.size = newCur.l.size + (newCur.r != null ? newCur.r.size : 0) + 1;
                    cur = newCur;
                }
            }
            return maintain(cur);
        }

        private SBNode<K, V> getLastNode(K k) {
            SBNode<K, V> cur = root;
            SBNode<K, V> pre = root;
            while (cur != null) {
                pre = cur;
                if (k.compareTo(cur.k) == 0) {
                    break;
                } else if (k.compareTo(cur.k) < 0) {
                    cur = cur.l;
                } else {
                    cur = cur.r;
                }
            }
            return pre;
        }

        private boolean isContainsKey(K k) {
            SBNode<K, V> lastNode = getLastNode(k);
            return lastNode != null && lastNode.k.compareTo(k) == 0;
        }

        public void put(K k, V v) {
            if (k == null) {
                return;
            }
            SBNode<K, V> lastNode = getLastNode(k);
            if (lastNode != null && lastNode.k.compareTo(k) == 0) {
                lastNode.v = v;
            } else {
                root = add(root, k, v);
            }
        }

        public void remove(K k) {
            if (k == null) {
                return;
            }
            if (isContainsKey(k)) {
                root = delete(root, k);
            }
        }

        // test
        public boolean isBalanceAndHeight() {
            SBNode<K, V> cur = root;
            return isSearch(cur) && isBalance(cur);
        }

        private boolean isBalance(SBNode<K, V> cur) {
            if (cur == null) {
                return true;
            }
            int leftSize = cur.l != null ? cur.l.size : 0;
            int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            int rightSize = cur.r != null ? cur.r.size : 0;
            int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            if (leftLeftSize > rightSize || leftRightSize > rightSize || rightLeftSize > leftSize
                    || rightRightSize > leftSize) {
                return false;
            }
            return isBalance(cur.l) && isBalance(cur.r);
        }

        private boolean isSearch(SBNode<K, V> cur) {
            if (cur == null) {
                return true;
            }
            if ((cur.l != null && cur.l.k.compareTo(cur.k) > 0)
                    || (cur.r != null && cur.r.k.compareTo(cur.k) < 0)) {
                return false;
            }
            return isSearch(cur.l) && isSearch(cur.r);
        }
    }

    public static void main(String[] args) {
        SizeBalanceTree<Integer, Integer> avl = new SizeBalanceTree<>();
        int maxK = 500;
        int maxV = 50000;
        int testTime = 100000;
        System.out.println("test start");
        for (int i = 0; i < testTime; i++) {
            int addK = (int) (Math.random() * maxK);
            int addV = (int) (Math.random() * maxV);
            avl.put(addK, addV);
            if (!avl.isBalanceAndHeight()) {
                System.out.println("oop1");
                break;
            }
            int removeK = (int) (Math.random() * maxK);
            avl.remove(removeK);
            if (!avl.isBalanceAndHeight()) {
                System.out.println("oop2");
                break;
            }
        }
        System.out.println("test end");
    }
}
