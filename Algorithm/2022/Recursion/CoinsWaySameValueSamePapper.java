package Recursion;

import java.util.HashMap;
import java.util.Map.Entry;

/*
 * @Author: Jihan
 * @Date: 2022-05-18 09:34:09
 * @Description: 
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 */
public class CoinsWaySameValueSamePapper {
    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }
        int[] coins = new int[map.size()];
        int[] nums = new int[map.size()];
        int cur = 0;
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            coins[cur] = entry.getKey();
            nums[cur] = entry.getValue();
            cur++;
        }
        return process(0, aim, coins, nums);
    }

    private static int process(int index, int rest, int[] coins, int[] nums) {
        if (rest == 0) {
            return 1;
        }
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int num = 0; num <= nums[index] && rest - (coins[index] * num) >= 0; num++) {
            ways += process(index + 1, rest - (coins[index] * num), coins, nums);
        }
        return ways;
    }

    public static int coinsWayDP(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }
        int[] coins = new int[map.size()];
        int[] nums = new int[map.size()];
        int cur = 0;
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            coins[cur] = entry.getKey();
            nums[cur] = entry.getValue();
            cur++;
        }
        int[][] dp = new int[coins.length + 1][aim + 1];
        for (int row = 0; row < dp.length; row++) {
            dp[row][0] = 1;
        }
        for (int index = coins.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = dp[index + 1][rest];
                if (rest - coins[index] >= 0) {
                    ways += dp[index][rest - coins[index]];
                    if (rest - ((nums[index] + 1) * coins[index]) >= 0) {
                        ways -= dp[index + 1][rest - ((nums[index] + 1) * coins[index])];
                    }
                }
                dp[index][rest] = ways;
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
        int maxLen = 10;
        int maxValue = 20;
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