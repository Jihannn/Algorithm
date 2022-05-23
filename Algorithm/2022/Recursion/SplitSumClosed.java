package Recursion;

/*
 * @Author: Jihan
 * @Date: 2022-05-23 08:37:24
 * @Description: 
 * 给定一个正数数组arr，
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回最接近的情况下，较小集合的累加和
 */
public class SplitSumClosed {
    public static int sumClosed(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return process(0, sum / 2, arr);
    }

    private static int process(int index, int rest, int[] arr) {
        if (index == arr.length) {
            return 0;
        }
        int p1 = process(index + 1, rest, arr);
        int p2 = 0;
        if (rest - arr[index] >= 0) {
            p2 = arr[index] + process(index + 1, rest - arr[index], arr);
        }
        return Math.max(p1, p2);
    }

    public static int sumClosedDP(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int N = arr.length;
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        sum /= 2;
        int[][] dp = new int[N + 1][sum + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= sum; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                if (rest - arr[index] >= 0) {
                    p2 = arr[index] + dp[index + 1][rest - arr[index]];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum];
    }

    // test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = sumClosed(arr);
            int ans2 = sumClosedDP(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}