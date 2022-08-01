/*
 * @Author: Jihan
 * @Date: 2022-08-01 08:58:46
 * @Description: 
 */
public class _059_SpiralMatrix {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int colCur = 0;
        int rowCur = 0;
        int leftBound = -1;
        int rightBound = n;
        int topBound = -1;
        int bottomBound = n;
        int now = 1;
        int goal = 1;
        while (now <= n * n) {
            if (goal % 4 == 1) {
                res[rowCur][colCur++] = now++;
                if (colCur + 1 == rightBound) {
                    topBound++;
                    goal++;
                }
            } else if (goal % 4 == 2) {
                res[rowCur++][colCur] = now++;
                if (rowCur + 1 == bottomBound) {
                    rightBound--;
                    goal++;
                }
            } else if (goal % 4 == 3) {
                res[rowCur][colCur--] = now++;
                if (colCur - 1 == leftBound) {
                    bottomBound--;
                    goal++;
                }
            } else {
                res[rowCur--][colCur] = now++;
                if (rowCur - 1 == topBound) {
                    leftBound++;
                    goal++;
                }
            }
        }
        return res;
    }
}