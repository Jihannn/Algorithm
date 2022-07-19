/*
 * @Author: Jihan
 * @Date: 2022-07-19 13:55:54
 * @Description: https://leetcode.cn/problems/container-with-most-water/
 */
public class _011_ContainerWithMostWater {
    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int l = 0;
        int r = height.length - 1;
        int max = 0;
        while (l != r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            // 短的向内移动
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }
}
