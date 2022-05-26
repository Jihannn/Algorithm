import java.util.ArrayDeque;

/*
 * @Author: Jihan
 * @Date: 2022-05-25 17:42:20
 * @Description: https://leetcode.cn/problems/count-submatrices-with-all-ones/
 * 给你一个 m x n 的二进制矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1
 */
public class CountSubmatricesWithAllOnes {
    public int numSubmat(int[][] mat) {
        if (mat == null || mat.length < 1 || mat[0].length < 1) {
            return 0;
        }
        int N = mat[0].length;
        int[] heights = new int[N];
        int count = 0;
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < N; col++) {
                heights[col] = mat[row][col] == 1 ? heights[col] + 1 : 0;
            }
            count += getSubRectangle(heights);
        }
        return count;
    }

    private static int getSubRectangle(int[] heights) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int N = heights.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                int index = stack.pop();
                if (heights[i] < heights[index]) {
                    int left = stack.isEmpty() ? -1 : stack.peek();
                    int n = i - left - 1;
                    int down = left == -1 ? heights[i] : Math.max(heights[left], heights[i]);
                    count += (heights[index] - down) * ((n * (n + 1)) >> 1);
                }
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int n = heights.length - left - 1;
            int down = left == -1 ? 0 : heights[left];
            count += (heights[index] - down) * ((n * (n + 1)) >> 1);
        }
        return count;
    }
}