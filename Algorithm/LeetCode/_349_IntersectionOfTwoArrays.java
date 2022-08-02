import java.util.HashSet;

/*
 * @Author: Jihan
 * @Date: 2022-08-02 23:56:19
 * @Description: https://leetcode.cn/problems/intersection-of-two-arrays/
 */
public class _349_IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[] {};
        }
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> res = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        for (int i : nums2) {
            if (set.contains(i)) {
                res.add(i);
            }
        }
        return res.stream().mapToInt(x -> x).toArray();
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[] {};
        }
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        int N = 0;
        for (int i : nums2) {
            if (set.contains(i) && !set2.contains(i)) {
                N++;
            }
            set2.add(i);
        }
        int[] res = new int[N];
        int cur = 0;
        for (int i : set) {
            if (set2.contains(i)) {
                res[cur++] = i;
            }
        }
        return res;
    }
}
