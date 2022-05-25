import java.util.ArrayDeque;

/*
 * @Author: Jihan
 * @Date: 2022-05-25 17:16:23
 * @Description: https://leetcode.cn/problems/maximal-rectangle/
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length < 1) {
            return 0;
        }
        int N = matrix[0].length;
        int[] heights = new int[N];
        int maxRec = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < N; col++) {
                heights[col] = matrix[row][col] == '1' ? heights[col] + 1 : 0;
            }
            maxRec = Math.max(maxRec, maxRectangle(heights));
        }
        return maxRec;
    }

    private int maxRectangle(int[] heights) {
        int N = heights.length;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int maxRec = 0;
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int index = stack.pop();
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                maxRec = Math.max(maxRec, (i - leftIndex - 1) * heights[index]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            maxRec = Math.max(maxRec, (heights.length - leftIndex - 1) * heights[index]);
        }
        return maxRec;
    }
}