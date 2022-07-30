import java.util.ArrayList;
import java.util.List;

/*
 * @Author: Jihan
 * @Date: 2022-07-30 20:17:01
 * @Description: https://leetcode.cn/problems/permutations
 */
public class _046_Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> rtnList = new ArrayList<>();
        process(0, nums, new ArrayList<>(), rtnList);
        return rtnList;
    }

    private void process(int index, int[] nums, List<Integer> list, List<List<Integer>> rtnList) {
        if (index == nums.length) {
            rtnList.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            swap(nums, i, index);
            process(index + 1, nums, list, rtnList);
            list.remove(index);
            swap(nums, i, index);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
