/*
 * @Author: Jihan
 * @Date: 2022-05-18 09:16:19
 * @Description: 
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 * 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 */
package Recursion;

public class CoinsWayNoLimit {
    public static int coinsWay(int[] arr, int aim) {
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
        int p1 = aim - arr[index] >= 0 ? process(index, aim - arr[index], arr) : 0;
        int p2 = process(index + 1, aim, arr);
        return p1 + p2;
    }

    public static int coinsWayDP(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 0; i <= arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int row = arr.length - 1; row >= 0; row--) {
            for (int rest = 1; rest <= aim; rest++) {
                int p1 = rest - arr[row] >= 0 ? dp[row][rest - arr[row]] : 0;
                int p2 = dp[row + 1][rest];
                dp[row][rest] = p1 + p2;
            }
        }
        return dp[0][aim];
    }

    // 为了测试
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

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = coinsWayDP(arr, aim);
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
