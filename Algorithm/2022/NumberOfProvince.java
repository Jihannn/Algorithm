/*
 * @Author: Jihan
 * @Date: 2022-05-10 08:29:24
 * @Description: https://leetcode.cn/problems/number-of-provinces/
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 */
public class NumberOfProvince {

    public int findCircleNum(int[][] isConnected) {
        int row = isConnected.length;
        int col = isConnected[0].length;
        UnionFind unionFind = new UnionFind(row);
        for (int i = 0; i < row; i++) {
            for (int j = i + 1; j < col; j++) {
                if(isConnected[i][j] == 1){
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets();
    }

    public static class UnionFind {
        int[] sizes;
        int[] parent;
        int[] help;
        int sets;

        public UnionFind(int N) {
            sizes = new int[N];
            parent = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                sizes[i] = 1;
            }
        }

        private int findAncestor(int i) {
            int cur = 0;
            while (parent[i] != i) {
                help[cur++] = i;
                i = parent[i];
            }
            for (--cur; cur >= 0; cur--) {
                parent[help[cur]] = i;
            }
            return i;
        }

        public void union(int a, int b) {
            int aAncestor = findAncestor(a);
            int bAncestor = findAncestor(b);
            if (aAncestor != bAncestor) {
                int big = sizes[aAncestor] > sizes[bAncestor] ? aAncestor : bAncestor;
                int small = big == aAncestor ? bAncestor : aAncestor;
                parent[small] = big;
                sizes[big] += sizes[small];
                sets--;
            }
        }

        public int sets(){
            return sets;
        }
    }
}