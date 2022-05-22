package Recursion;

/*
 * @Author: Jihan
 * @Date: 2022-05-20 10:23:23
 * @Description: 
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 */
public class MinCoinsNoLimit {
    public static int minCoin(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 1) {
            return -1;
        }
        return process(0, aim, arr);
    }

    private static int process(int index, int rest, int[] arr) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int ways = Integer.MAX_VALUE;
        for (int zhang = 0; (rest - arr[index] * zhang) >= 0; zhang++) {
            int next = process(index + 1, rest - arr[index] * zhang, arr);
            if (next != Integer.MAX_VALUE) {
                ways = Math.min(ways, zhang + next);
            }
        }
        return ways;
    }

    public static int minCoinDP(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 1) {
            return -1;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int col = 1; col <= aim; col++) {
            dp[N][col] = Integer.MAX_VALUE;
        }
        for (int row = N - 1; row >= 0; row--) {
            for (int col = 0; col <= aim; col++) {
                dp[row][col] = dp[row + 1][col];
                if (col - arr[row] >= 0 && dp[row][col - arr[row]] != Integer.MAX_VALUE) {
                    dp[row][col] = Math.min(dp[row][col], dp[row][col - arr[row]] + 1);
                }
            }
        }
        return dp[0][aim];
    }

    // test
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    // test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // test
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoin(arr, aim);
            int ans2 = minCoinDP(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                break;
            }
        }
        System.out.println("功能测试结束");
    }
}