package com.jihan.algorithm.剑指offer;

/**
 * @author Jihan
 * @date 2019/8/10
 * <p>
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class _丑数 {

    final int[] fix = {2, 3, 5};

    public int GetUglyNumber_Solution(int index) {
        if (index == 0) {
            return 0;
        }
        int[] numIndex = {0, 0, 0};
        int[] count = {2, 3, 5};
        int[] now = new int[index];
        now[0] = 1;
        int nowCur = 1;
        while (nowCur < index) {
            int countCur = findMin(count[0], count[1], count[2]);
            if (now[nowCur - 1] < count[countCur]) {
                now[nowCur++] = count[countCur];
            }
            numIndex[countCur] += 1;
            count[countCur] = now[numIndex[countCur]] * fix[countCur];
        }
        return now[index - 1];
    }

    private int findMin(int index0, int index1, int index2) {
        if (index0 <= index1) {
            return index0 <= index2 ? 0 : 2;
        } else {
            return index1 <= index2 ? 1 : 2;
        }
    }
}
