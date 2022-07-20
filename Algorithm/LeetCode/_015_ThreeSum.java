import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @Author: Jihan
 * @Date: 2022-07-19 23:37:13
 * @Description: https://leetcode.cn/problems/3sum/
 */
public class _015_ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<List<Integer>>();
        }
        // 排序
        Arrays.sort(nums);
        ArrayList<List<Integer>> result = new ArrayList<>();
        // 选择第一个
        for (int i = 0; i < nums.length - 1; i++) {
            // 如果重复则不执行
            if (i == 0 || nums[i - 1] != nums[i]) {
                // 选择其余两个数
                List<List<Integer>> rtnList = twoSum(nums, i + 1, nums.length - 1, -nums[i]);
                for (List<Integer> l : rtnList) {
                    l.add(0, nums[i]);
                    result.add(l);
                }
            }
        }
        return result;
    }

    // 数组从begin到end范围内，找到两数之和能得到target
    private List<List<Integer>> twoSum(int[] nums, int begin, int end, int target) {
        int l = begin;
        int r = end;
        ArrayList<List<Integer>> rtnList = new ArrayList<>();
        while (l < r) {
            if (nums[l] + nums[r] > target) {
                r--;
            } else if (nums[l] + nums[r] < target) {
                l++;
            } else {
                if (l == begin || nums[l - 1] != nums[l]) {
                    ArrayList<Integer> rtn = new ArrayList<>();
                    rtn.add(nums[l]);
                    rtn.add(nums[r]);
                    rtnList.add(rtn);
                }
                l++;
            }
        }
        return rtnList;
    }
}