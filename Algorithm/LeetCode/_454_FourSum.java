import java.util.HashMap;

/*
 * @Author: Jihan
 * @Date: 2022-08-02 23:58:10
 * @Description: https://leetcode.cn/problems/4sum-ii/
 */
public class _454_FourSum {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i : nums1) {
            for (int j : nums2) {
                map.put(i + j, map.getOrDefault(i + j, 0) + 1);
            }
        }
        for (int i : nums3) {
            for (int j : nums4) {
                res += map.getOrDefault(~(i + j) + 1, 0);
            }
        }
        return res;
    }
}
