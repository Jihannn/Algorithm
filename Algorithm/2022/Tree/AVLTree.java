package Tree;

/*
 * @Author: Jihan
 * @Date: 2022-06-13 10:04:36
 * @Description: 
 */
public class AVLTree {
    public static class AVLNode<K extends Comparable<K>, V> {
        AVLNode<K, V> l;
        AVLNode<K, V> r;
        int h;
        K k;
        V v;

        public AVLNode(K key, V value) {
            h = 1;
            k = key;
            v = value;
        }
    }

    public static class AVLTreeMap<K extends Comparable<K>, V> {
        AVLNode<K, V> root;
        int size;

        public AVLTreeMap() {
            root = null;
            size = 0;
        }

        // 左旋
        private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> newCur = cur.r;
            cur.r = newCur.l;
            newCur.l = cur;
            cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
            newCur.h = Math.max(newCur.l != null ? newCur.l.h : 0, newCur.r != null ? newCur.r.h : 0) + 1;
            return newCur;
        }

        // 右旋
        private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> newCur = cur.l;
            cur.l = newCur.r;
            newCur.r = cur;
            cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
            newCur.h = Math.max(newCur.l != null ? newCur.l.h : 0, newCur.r != null ? newCur.r.h : 0) + 1;
            return newCur;
        }

        // 调整平衡
        private AVLNode<K, V> maintain(AVLNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int leftHeight = cur.l != null ? cur.l.h : 0;
            int rightHeight = cur.r != null ? cur.r.h : 0;
            // 左右子树高度差超过1
            if (Math.abs(leftHeight - rightHeight) > 1) {
                if (leftHeight > rightHeight) {
                    int leftLeftHeight = cur.l != null && cur.l.l != null ? cur.l.l.h : 0;
                    int leftRightHeight = cur.l != null && cur.l.r != null ? cur.l.r.h : 0;
                    // LL和LR高度相同，以LL的方式处理，若以LR方式处理则可能仍不平衡
                    if (leftLeftHeight >= leftRightHeight) {
                        cur = rightRotate(cur);
                    } else {
                        cur.l = leftRotate(cur.l);
                        cur = rightRotate(cur);
                    }
                } else {
                    int rightLeftHeight = cur.r != null && cur.r.l != null ? cur.r.l.h : 0;
                    int rightRightHeight = cur.r != null && cur.r.r != null ? cur.r.r.h : 0;
                    if (rightRightHeight >= rightLeftHeight) {
                        cur = leftRotate(cur);
                    } else {
                        cur.r = rightRotate(cur.r);
                        cur = leftRotate(cur);
                    }
                }
            }
            return cur;
        }

        private AVLNode<K, V> add(AVLNode<K, V> cur, K k, V v) {
            if (cur == null) {
                return new AVLNode<K, V>(k, v);
            }
            if (k.compareTo(cur.k) < 0) {
                cur.l = add(cur.l, k, v);
            } else {
                cur.r = add(cur.r, k, v);
            }
            cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
            return maintain(cur);
        }

        private AVLNode<K, V> delete(AVLNode<K, V> cur, K k) {
            if (k.compareTo(cur.k) > 0) {
                cur.r = delete(cur.r, k);
            } else if (k.compareTo(cur.k) < 0) {
                cur.l = delete(cur.l, k);
            } else {
                if (cur.l == null && cur.r == null) {
                    cur = null;
                } else if (cur.l != null && cur.r == null) {
                    cur = cur.l;
                } else if (cur.l == null && cur.r != null) {
                    cur = cur.r;
                } else {
                    // 取右子树中最小的key
                    AVLNode<K, V> newCur = cur.r;
                    while (newCur.l != null) {
                        newCur = newCur.l;
                    }
                    // 删除该节点并让新节点替代cur
                    cur.r = delete(cur.r, newCur.k);
                    newCur.l = cur.l;
                    newCur.r = cur.r;
                    cur = newCur;
                }
            }
            if (cur != null) {
                cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
            }
            return maintain(cur);
        }

        private AVLNode<K, V> getLastIndex(K k) {
            AVLNode<K, V> pre = root;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                pre = cur;
                if (k.compareTo(cur.k) == 0) {
                    break;
                } else if (k.compareTo(cur.k) > 0) {
                    cur = cur.r;
                } else {
                    cur = cur.l;
                }
            }
            return pre;
        }

        private boolean containsKey(K k) {
            if (k == null) {
                return false;
            }
            AVLNode<K, V> lastIndex = getLastIndex(k);
            return lastIndex != null && k.compareTo(lastIndex.k) == 0 ? true : false;
        }

        public void put(K k, V v) {
            if (k == null) {
                return;
            }
            AVLNode<K, V> lastIndex = getLastIndex(k);
            if (lastIndex != null && k.compareTo(lastIndex.k) == 0) {
                lastIndex.v = v;
            } else {
                size++;
                root = add(root, k, v);
            }
        }

        public void remove(K k) {
            if (k == null) {
                return;
            }
            if (containsKey(k)) {
                size--;
                root = delete(root, k);
            }
        }

        // test
        public boolean isBalanceAndHeight() {
            if (size == 0) {
                return true;
            }
            AVLNode<K, V> cur = root;
            return isSearch(cur) && isBalance(cur);
        }

        private boolean isBalance(AVLNode<K, V> cur) {
            if (cur == null) {
                return true;
            }
            int lHeight = cur.l != null ? cur.l.h : 0;
            int rHeight = cur.r != null ? cur.r.h : 0;
            if (Math.abs(lHeight - rHeight) > 1) {
                return false;
            }
            return isBalance(cur.l) && isBalance(cur.r);
        }

        private boolean isSearch(AVLNode<K, V> cur) {
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
        AVLTreeMap<Integer, Integer> avl = new AVLTreeMap<>();
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