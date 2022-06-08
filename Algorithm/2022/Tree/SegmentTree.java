/*
 * @Author: Jihan
 * @Date: 2022-06-08 13:57:56
 * @Description: 线段树
 */
package Tree;

public class SegmentTree {

    public static class STree {

        private int maxLen;
        private int[] arr;
        private int[] sum;
        private int[] lazy;
        private int[] update;
        private boolean[] change;

        public STree(int[] original) {
            maxLen = original.length + 1;
            arr = new int[maxLen << 2];
            sum = new int[maxLen << 2];
            lazy = new int[maxLen << 2];
            update = new int[maxLen << 2];
            change = new boolean[maxLen << 2];
            for (int i = 1; i < maxLen; i++) {
                arr[i] = original[i - 1];
            }
        }

        public void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = l + ((r - l) >> 1);
            // int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }

        public void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        public void pushDown(int rt, int ln, int rn) {
            if (change[rt]) {
                change[rt << 1] = true;
                change[rt << 1 | 1] = true;
                update[rt << 1] = update[rt];
                update[rt << 1 | 1] = update[rt];
                lazy[rt << 1] = 0;
                lazy[rt << 1 | 1] = 0;
                sum[rt << 1] = update[rt] * ln;
                sum[rt << 1 | 1] = update[rt] * rn;
                change[rt] = false;
            }
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;
                sum[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }

        public void add(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                lazy[rt] += C;
                sum[rt] += C * (r - l + 1);
                return;
            }
            int mid = l + ((r - l) >> 1);
            // int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                update[rt] = C;
                change[rt] = true;
                lazy[rt] = 0;
                sum[rt] = C * (r - l + 1);
                return;
            }
            int mid = l + ((r - l) >> 1);
            // int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }
            int mid = l + ((r - l) >> 1);
            // int mid = (l + r) >> 1;
            int rtn = 0;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                rtn += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                rtn += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return rtn;
        }
    }
}
