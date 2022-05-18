/*
 * @Author: Jihan
 * @Date: 2022-05-18 08:53:54
 * @Description: 
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 */
package Recursion;

public class CoinsWayEveryPaperDifferent {
    public static int getMethod(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 0) {
            return 0;
        }
        return process(0, aim, arr);
    }

    private static int process(int index, int aim, int[] arr) {
        if (aim == 0) {
            return 1;
        }
        if (index == arr.length) {
            return aim == 0 ? 1 : 0;
        }
        int p1 = aim - arr[index] >= 0 ? process(index + 1, aim - arr[index], arr) : 0;
        int p2 = process(index + 1, aim, arr);
        return p1 + p2;
    }

    public static int getMethodDP(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 0; i < arr.length + 1; i++) {
            dp[i][0] = 1;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                int p1 = j - arr[i] >= 0 ? dp[i + 1][j - arr[i]] : 0;
                int p2 = dp[i + 1][j];
                dp[i][j] = p1 + p2;
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = getMethod(arr, aim);
            int ans2 = getMethodDP(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
