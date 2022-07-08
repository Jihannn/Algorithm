/*
 * @Author: Jihan
 * @Date: 2022-07-08 09:52:36
 * @Description: https://leetcode.cn/problems/median-of-two-sorted-arrays/
 */
public class _004_MedianOfTwoSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        boolean even = (size & 1) == 0;
        if (nums1.length != 0 && nums2.length != 0) {
            if (even) {
                return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, size / 2 + 1)) / 2D;
            } else {
                return findKthNum(nums1, nums2, size / 2 + 1);
            }
        }
        if (nums1.length != 0) {
            if (even) {
                return (double) (nums1[size / 2] + nums1[(size - 1) / 2]) / 2;
            } else {
                return nums1[size / 2];
            }
        }
        if (nums2.length != 0) {
            if (even) {
                return (double) (nums2[size / 2] + nums2[(size - 1) / 2]) / 2;
            } else {
                return nums2[size / 2];
            }
        }
        return -1;
    }

    // 找到两数组中第K小的数
    private static int findKthNum(int[] arr1, int[] arr2, int kth) {
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = longs == arr1 ? arr2 : arr1;
        int l = longs.length;
        int s = shorts.length;
        // k小于短数组
        if (kth <= s) {
            return findUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
        }
        // k大于长数组
        if (kth > l) {
            // kth = 8
            // 1 2 3 4
            // x
            // 1 2 3 4 5 6
            // x x x
            // 4 + 3(findUpMedian) = 7
            // 手动去掉两个数，避免结果错误
            // 6 + 2 = 8
            if (shorts[kth - l - 1] >= longs[l - 1]) {
                return shorts[kth - l - 1];
            }
            if (longs[kth - s - 1] >= shorts[s - 1]) {
                return longs[kth - s - 1];
            }
            return findUpMedian(shorts, kth - l, s - 1, longs, kth - s, l - 1);
        }
        // k在两数组之间
        if (longs[kth - s - 1] >= shorts[s - 1]) {
            return longs[kth - s - 1];
        }
        return findUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);
    }

    // 两数组等长且同奇或同偶，查找上中位数
    private static int findUpMedian(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
        int m1 = 0;
        int m2 = 0;
        while (s1 < e1) {
            m1 = (e1 + s1) / 2;
            m2 = (e2 + s2) / 2;
            if (arr1[m1] == arr2[m2]) {
                return arr1[m1];
            }
            // 奇偶
            if (((e1 - s1 + 1) & 1) == 1) {
                // 1 2 3 4 5
                // x x x
                // 1 2 3 4 5
                // x x
                if (arr1[m1] > arr2[m2]) {
                    // 手动去掉一个数，保证两数组相同
                    if (arr2[m2] >= arr1[m1 - 1]) {
                        return arr2[m2];
                    }
                    e1 = m1 - 1;
                    s2 = m2 + 1;
                } else {
                    if (arr1[m1] >= arr2[m2 - 1]) {
                        return arr1[m1];
                    }
                    e2 = m2 - 1;
                    s1 = m1 + 1;
                }
            } else {
                // 1 2 3 4
                // x x
                // 1 2 3 4
                // x x
                if (arr1[m1] > arr2[m2]) {
                    e1 = m1;
                    s2 = m2 + 1;
                } else {
                    e2 = m2;
                    s1 = m1 + 1;
                }
            }
        }
        return Math.min(arr1[s1], arr2[s2]);
    }
}