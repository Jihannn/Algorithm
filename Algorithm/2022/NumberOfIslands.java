/*
 * @Author: Jihan
 * @Date: 2022-05-10 09:10:55
 * @Description: https://leetcode.cn/problems/number-of-islands/
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 */
public class NumberOfIslands {
    public int numIslands1(char[][] grid) {
        int islands = 0;
        // 遍历每个数
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                // 如果为岛屿则开始感染
                if (grid[row][col] == '1') {
                    infect(grid, row, col);
                    islands++;
                }
            }
        }
        return islands;
    }

    public void infect(char[][] grid, int row, int col) {
        // 不越界，并且是岛屿
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[row].length || grid[row][col] != '1') {
            return;
        }
        // 改值避免无限循环
        grid[row][col] = '2';
        // 感染上下左右
        infect(grid, row - 1, col);
        infect(grid, row + 1, col);
        infect(grid, row, col - 1);
        infect(grid, row, col + 1);
    }

    // 并查集
    public int numIslands2(char[][] grid) {
        UnionFind unionFind = new UnionFind(grid);
        int rowLen = grid.length;
        int colLen = grid[0].length;
        // 处理第一行
        for (int col = 1; col < colLen; col++) {
            if (grid[0][col] == '1' && grid[0][col - 1] == '1') {
                unionFind.union(0, col, 0, col - 1);
            }
        }
        // 处理第一列
        for (int row = 1; row < rowLen; row++) {
            if (grid[row][0] == '1' && grid[row - 1][0] == '1') {
                unionFind.union(row, 0, row - 1, 0);
            }
        }
        // 处理剩下数,上方操作避免做边界判断
        for (int row = 1; row < rowLen; row++) {
            for (int col = 1; col < colLen; col++) {
                if (grid[row][col] == '1') {
                    if (grid[row - 1][col] == '1') {
                        unionFind.union(row, col, row - 1, col);
                    }
                    if (grid[row][col - 1] == '1') {
                        unionFind.union(row, col, row, col - 1);
                    }
                }
            }
        }
        return unionFind.sets();
    }

    public class UnionFind {
        // index:当前数的父节点
        int[] parents;
        // index:当前数的子节点总和(包含自己)
        int[] sizes;
        int[] help;
        // 集合总数
        int sets;
        int colLen;

        public UnionFind(char[][] grid) {
            int size = grid.length * grid[0].length;
            parents = new int[size];
            sizes = new int[size];
            help = new int[size];
            colLen = grid[0].length;
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length; col++) {
                    if (grid[row][col] == '1') {
                        int index = findIndex(row, col);
                        parents[index] = index;
                        sizes[index] = 1;
                        sets++;
                    }
                }
            }
        }

        // 二维数组映射到一维数组的下标
        public int findIndex(int row, int col) {
            return row * colLen + col;
        }

        private int findAncestor(int index) {
            int cur = 0;
            // 找到index的最祖先节点
            while (parents[index] != index) {
                help[cur] = index;
                index = parents[index];
            }
            // 把路径上的节点放到祖先节点下
            for (--cur; cur >= 0; cur--) {
                parents[cur] = index;
            }
            return index;
        }

        public void union(int aR, int aC, int bR, int bC) {
            int aIndex = findIndex(aR, aC);
            int bIndex = findIndex(bR, bC);
            int aAncestor = findAncestor(aIndex);
            int bAncestor = findAncestor(bIndex);
            // 如果祖先节点不相同,则不为同一个集合
            if (aAncestor != bAncestor) {
                int big = aAncestor > bAncestor ? aAncestor : bAncestor;
                int small = big == aAncestor ? bAncestor : aAncestor;
                parents[small] = big;
                sizes[big] += sizes[small];
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }
}
