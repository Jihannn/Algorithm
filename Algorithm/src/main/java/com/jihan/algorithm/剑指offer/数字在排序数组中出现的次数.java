package com.jihan.algorithm.剑指offer;

/**
 * @author Jihan
 * @date 2019/8/12
 *
 * 统计一个数字在排序数组中出现的次数。
 */
public class 数字在排序数组中出现的次数 {
    public int GetNumberOfK(int[] array, int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int firstIndex = getFirstIndex(array, 0, array.length - 1, k);
        int lastIndex = getLastIndex(array, 0, array.length - 1, k);

        if (firstIndex != -1 && lastIndex != -1) {
            return lastIndex - firstIndex + 1;
        }
        return 0;
    }

    public int getFirstIndex(int[] array, int head, int tail, int k) {
        if (tail < head) {
            return -1;
        }

        int mid = (head + tail) >> 1;
        if (array[mid] > k) {
            return getFirstIndex(array, head, mid - 1, k);
        } else if (array[mid] < k) {
            return getFirstIndex(array, mid + 1, tail, k);
        } else if (mid - 1 >= head && array[mid - 1] == k) {
            return getFirstIndex(array, head, mid - 1, k);
        } else {
            return mid;
        }
    }

    public int getLastIndex(int[] array, int head, int tail, int k) {
        int mid = (head + tail) >> 1;
        while (head <= tail) {
            if (array[mid] > k) {
                tail = mid - 1;
            } else if (array[mid] < k) {
                head = mid + 1;
            } else if (mid + 1 <= tail && array[mid + 1] == k) {
                head = mid + 1;
            } else {
                return mid;
            }
            mid = (head + tail) >> 1;
        }
        return -1;
    }
}
