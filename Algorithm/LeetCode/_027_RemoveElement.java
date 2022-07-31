/*
 * @Author: Jihan
 * @Date: 2022-07-31 22:24:52
 * @Description: https://leetcode.cn/problems/remove-element
 */
public class _027_RemoveElement {
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 0;
        while (fast != nums.length) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        return slow;
    }

    // 会保留原数
    public int removeElement2(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int last = nums.length - 1;
        int cur = 0;
        while (cur <= last) {
            if (nums[cur] == val) {
                int temp = nums[cur];
                nums[cur] = nums[last];
                nums[last--] = temp;
            } else {
                cur++;
            }
        }
        return last + 1;
    }
}