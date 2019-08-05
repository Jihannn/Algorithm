package com.jihan.algorithm.剑指offer;

/**
 * Created by Jihan on 2019/8/5
 *
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。
 * 假设输入的数组的任意两个数字都互不相同。
 */
public class 二叉搜索树的后序遍历序列 {

    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        if (sequence.length == 1) {
            return true;
        }
        return judge(sequence, 0, sequence.length - 1);
    }

    private boolean judge(int[] sequence, int start, int end) {
        if (start >= end) {
            return true;
        }

        int i = start;
        while (sequence[i] < sequence[end]) {
            ++i;
        }

        for (int j = i; j < end; j++) {
            if (sequence[j] < sequence[end]) {
                return false;
            }
        }

        return judge(sequence, start, i - 1) & judge(sequence, i, end - 1);
    }
}
