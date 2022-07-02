/*
 * @Author: Jihan
 * @Date: 2022-07-01 17:06:55
 * @Description: https://leetcode.cn/problems/n-queens/submissions/
 */
package Recursion;

import java.util.ArrayList;
import java.util.List;

public class NQueens2 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (n == 1) {
            List<String> l = new ArrayList<String>();
            l.add("Q");
            result.add(l);
            return result;
        }
        if (n == 2 || n == 3) {
            return result;
        }
        int[] record = new int[n];
        process(0, record, n, result);
        return result;
    }

    private void process(int row, int[] record, int limit, List<List<String>> rtn) {
        if (row == limit) {
            ArrayList<String> ans = new ArrayList<>();
            for (int r = 0; r < limit; r++) {
                StringBuilder builder = new StringBuilder();
                for (int c = 0; c < limit; c++) {
                    if (record[r] == c) {
                        builder.append('Q');
                    } else {
                        builder.append('.');
                    }
                }
                ans.add(builder.toString());
            }
            rtn.add(ans);
        }
        for (int col = 0; col < limit; col++) {
            if (isValid(row, col, record)) {
                record[row] = col;
                process(row + 1, record, limit, rtn);
            }
        }
    }

    private boolean isValid(int row, int col, int[] record) {
        for (int i = 0; i < row; i++) {
            if (record[i] == col || Math.abs(row - i) == Math.abs(record[i] - col)) {
                return false;
            }
        }
        return true;
    }
}
