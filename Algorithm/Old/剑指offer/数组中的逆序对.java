package com.jihan.algorithm.剑指offer;

/**
 * @author Jihan
 * @date 2019/8/12
 *
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。
 * 并将P对1000000007取模的结果输出。 即输出P%1000000007
 */
public class 数组中的逆序对 {
    public int InversePairs(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int[] copy = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        return process(array, copy, 0, array.length - 1);
    }

    public int process(int[] array, int[] copy, int head, int tail) {
        if (head == tail) {
            return 0;
        }

        int mid = (head + tail) >> 1;
        int leftCount = process(array, copy, head, mid);
        int rightCount = process(array, copy, mid + 1, tail);

        int tailCur = tail;
        int midCur = mid;
        int copyCur = tail;
        int count = 0;

        while (tailCur > mid && midCur >= head) {
            if (array[midCur] > array[tailCur]) {
                count += tailCur - mid;
                copy[copyCur--] = array[midCur--];
                if (count > 1000000007) {
                    count %= 1000000007;
                }
            } else {
                copy[copyCur--] = array[tailCur--];
            }
        }

        while (tailCur > mid) {
            copy[copyCur--] = array[tailCur--];
        }

        while (midCur >= head) {
            copy[copyCur--] = array[midCur--];
        }

        for (int i = head; i <= tail; i++) {
            array[i] = copy[i];
        }

        return (count + leftCount + rightCount) % 1000000007;
    }
}
