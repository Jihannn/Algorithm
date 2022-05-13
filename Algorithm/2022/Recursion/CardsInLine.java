/*
 * @Author: Jihan
 * @Date: 2022-05-13 09:45:02
 * @Description: 
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数
 */
package Recursion;

public class CardsInLine {
    public static int getPoint1(int[] arr) {
        if (arr == null) {
            return 0;
        }
        int first = first1(arr, 0, arr.length - 1);
        int last = last1(arr, 0, arr.length - 1);
        return Math.max(first, last);
    }

    private static int first1(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int choseL = arr[L] + last1(arr, L + 1, R);
        int choseR = arr[R] + last1(arr, L, R - 1);
        return Math.max(choseL, choseR);
    }

    private static int last1(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int choseL = first1(arr, L + 1, R);
        int choseR = first1(arr, L, R - 1);
        return Math.min(choseL, choseR);
    }

    public static int getPoint2(int[] arr) {
        if (arr == null) {
            return 0;
        }
        int N = arr.length;
        int[][] firstCache = new int[N][N];
        int[][] lastCache = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                firstCache[i][j] = -1;
                lastCache[i][j] = -1;
            }
        }
        return Math.max(first2(arr, 0, N - 1, firstCache, lastCache), last2(arr, 0, N - 1, firstCache, lastCache));
    }

    private static int first2(int[] arr, int L, int R, int[][] firstCache, int[][] lastCache) {
        if (firstCache[L][R] != -1) {
            return firstCache[L][R];
        }
        if (L == R) {
            firstCache[L][R] = arr[L];
            return arr[L];
        }
        int choseL = arr[L] + last2(arr, L + 1, R, firstCache, lastCache);
        int choseR = arr[R] + last2(arr, L, R - 1, firstCache, lastCache);
        int max = Math.max(choseL, choseR);
        firstCache[L][R] = max;
        return max;
    }

    private static int last2(int[] arr, int L, int R, int[][] firstCache, int[][] lastCache) {
        if (lastCache[L][R] != -1) {
            return lastCache[L][R];
        }
        if (L == R) {
            lastCache[L][R] = 0;
            return 0;
        }
        int choseL = first2(arr, L + 1, R, firstCache, lastCache);
        int choseR = first2(arr, L, R - 1, firstCache, lastCache);
        int min = Math.min(choseL, choseR);
        lastCache[L][R] = min;
        return min;
    }

    public static int getPoint3(int[] arr) {
        if (arr == null) {
            return 0;
        }
        int N = arr.length;
        int[][] firstTable = new int[N][N];
        int[][] lastTable = new int[N][N];
        for (int i = 0; i < N; i++) {
            firstTable[i][i] = arr[i];
        }
        int R = 1;
        while (R < N) {
            for (int L = 0; L < N - R; L++) {
                firstTable[L][R + L] = Math.max(arr[R + L] + lastTable[L][R + L - 1],
                        arr[L] + lastTable[L + 1][R + L]);
                lastTable[L][R + L] = Math.min(firstTable[L][R + L - 1], firstTable[L + 1][R + L]);
            }
            R++;
        }
        return Math.max(firstTable[0][N - 1], lastTable[0][N - 1]);
    }

    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(getPoint1(arr));
        System.out.println(getPoint2(arr));
        System.out.println(getPoint3(arr));
    }
}