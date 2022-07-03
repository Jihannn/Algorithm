import java.util.ArrayDeque;

/*
 * @Author: Jihan
 * @Date: 2022-05-25 16:24:03
 * @Description: https://leetcode.cn/problems/largest-rectangle-in-histogram/
 * 给定一个非负数组arr，代表直方图，返回直方图的最大长方形面积
 */
public class LargestRectangleInHistogram {
    public static int getLargestRec(int[] heights) {
        if (heights == null || heights.length < 1) {
            return 0;
        }
        int N = heights.length;
        int max = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int index = stack.pop();
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                max = Math.max(max, (i - leftIndex - 1) * heights[index]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            max = Math.max(max, (heights.length - leftIndex - 1) * heights[index]);
        }
        return max;
    }

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length < 1) {
            return -1;
        }
        int N = heights.length;
        int cur = 0;
        int rtn = Integer.MIN_VALUE;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        while (cur < N) {
            while (!stack.isEmpty() && heights[cur] <= heights[stack.peek()]) {
                int index = stack.pop();
                rtn = Math.max(rtn, stack.isEmpty() ? heights[index] * cur : heights[index] * (cur - stack.peek() - 1));
            }
            stack.push(cur);
            cur++;
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            rtn = Math.max(rtn, stack.isEmpty() ? heights[index] * N : heights[index] * (N - stack.peek() - 1));
        }
        return rtn;
    }
}