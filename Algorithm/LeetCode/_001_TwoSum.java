import java.util.HashMap;

/*
 * @Author: Jihan
 * @Date: 2022-07-03 16:50:38
 * @Description: https://leetcode.cn/problems/two-sum/
 */
public class _001_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int[] rtn = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        // O(N)
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                rtn[0] = map.get(nums[i]);
                rtn[1] = i;
                break;
            }
            // 放入余数，可能为另一个数
            map.put(target - nums[i], i);
        }
        return rtn;
    }
}