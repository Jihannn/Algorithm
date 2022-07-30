/*
 * @Author: Jihan
 * @Date: 2022-07-28 20:46:20
 * @Description: https://leetcode.cn/problems/trapping-rain-water/
 */
public class _042_TrappingRainWater {
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int N = height.length;
        int L = 1;
        int R = N - 2;
        int leftMax = height[0];
        int rightMax = height[N - 1];
        int res = 0;
        while (L <= R) {
            if (leftMax < rightMax) {
                res += Math.max(0, leftMax - height[L]);
                leftMax = Math.max(leftMax, height[L++]);
            } else {
                res += Math.max(0, rightMax - height[R]);
                rightMax = Math.max(rightMax, height[R--]);
            }
        }
        return res;
    }
}
