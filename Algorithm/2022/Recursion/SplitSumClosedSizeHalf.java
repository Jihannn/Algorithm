package Recursion;

/*
 * @Author: Jihan
 * @Date: 2022-05-23 08:54:54
 * @Description:     
 * 给定一个正数数组arr，请把arr中所有的数分成两个集合
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 * 请尽量让两个集合的累加和接近
 * 返回最接近的情况下，较小集合的累加和
 */
public class SplitSumClosedSizeHalf {
    public static int sumClosed(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        sum /= 2;
        if ((arr.length & 1) != 1) {
            return process(0, arr.length / 2, sum, arr);
        } else {
            return Math.max(process(0, arr.length / 2, sum, arr), process(0, arr.length / 2 + 1, sum, arr));
        }
    }

    private static int process(int index, int limit, int rest, int[] arr) {
        if (index == arr.length) {
            return limit == 0 ? 0 : -1;
        }
        int p1 = process(index + 1, limit, rest, arr);
        int p2 = -1;
        int next = -1;
        if (rest - arr[index] >= 0) {
            next = process(index + 1, limit - 1, rest - arr[index], arr);
        }
        if (next != -1) {
            p2 = arr[index] + next;
        }
        return Math.max(p1, p2);
    }

    public static int sumClosedDP(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        int N = arr.length;
        int M = (N + 1) / 2;
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        sum /= 2;
        int[][][] dp = new int[N + 1][M + 1][sum + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        for (int i = 0; i <= sum; i++) {
            dp[N][0][i] = 0;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int limit = 0; limit <= M; limit++) {
                for (int rest = 0; rest <= sum; rest++) {
                    int p1 = dp[index + 1][limit][rest];
                    int p2 = -1;
                    int next = -1;
                    if (limit - 1 >= 0 && rest - arr[index] >= 0) {
                        next = dp[index + 1][limit - 1][rest - arr[index]];
                    }
                    if (next != -1) {
                        p2 = arr[index] + next;
                    }
                    dp[index][limit][rest] = Math.max(p1, p2);
                }
            }
        }
        if ((arr.length & 1) != 1) {
            return dp[0][arr.length / 2][sum];
        } else {
            return Math.max(dp[0][arr.length / 2][sum], dp[0][arr.length / 2 + 1][sum]);
        }
    }

    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
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
