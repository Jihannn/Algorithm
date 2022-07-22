/*
 * @Author: Jihan
 * @Date: 2022-07-22 21:50:37
 * @Description: https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 */
public class _026_RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 1){
            return nums == null ? 0 : 1;
        }
        int cur = 0;
        int checkIndex = 1;
        while(checkIndex != nums.length){
            if(nums[checkIndex] != nums[cur]){
                nums[++cur] = nums[checkIndex];
            }
            checkIndex++;
        }
        return cur+1;
    }
}
