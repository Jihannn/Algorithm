package Recursion;

import java.util.PriorityQueue;

/*
 * @Author: Jihan
 * @Date: 2022-05-17 09:34:11
 * @Description: 
 * 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
 * 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 * 只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 假设所有人拿到咖啡之后立刻喝干净，
 * 返回从开始等到所有咖啡机变干净的最短时间
 * 三个参数：int[] arr、int N，int a、int b
 */
public class Coffee {

    public static class Machine {
        int timePoint;
        int waitTime;

        public Machine(int timePoint, int waitTime) {
            this.timePoint = timePoint;
            this.waitTime = waitTime;
        }
    }

    public static int minDrink(int[] arr, int N, int a, int b) {
        PriorityQueue<Machine> minHeap = new PriorityQueue<>(
                (o1, o2) -> (o1.timePoint + o1.waitTime) - (o2.timePoint + o2.waitTime));
        for (int waitTime : arr) {
            minHeap.add(new Machine(0, waitTime));
        }
        int[] timePoints = new int[N];
        for (int i = 0; i < N; i++) {
            Machine m = minHeap.poll();
            m.timePoint += m.waitTime;
            timePoints[i] = m.timePoint;
            minHeap.add(m);
        }
        return bestTime(timePoints, 0, a, b, 0);
    }

    private static int bestTime(int[] timePoints, int index, int wash, int air, int free) {
        if (index == timePoints.length) {
            return 0;
        }

        int washTime = Math.max(timePoints[index], free) + wash;
        int restTime1 = bestTime(timePoints, index + 1, wash, air, washTime);
        int p1 = Math.max(washTime, restTime1);

        int airTime = timePoints[index] + air;
        int restTime2 = bestTime(timePoints, index + 1, wash, air, free);
        int p2 = Math.max(airTime, restTime2);

        return Math.min(p1, p2);
    }

    public static int minDrink2(int[] arr, int N, int a, int b) {
        PriorityQueue<Machine> minHeap = new PriorityQueue<>(
                (o1, o2) -> (o1.timePoint + o1.waitTime) - (o2.timePoint + o2.waitTime));
        for (int waitTime : arr) {
            minHeap.add(new Machine(0, waitTime));
        }
        int[] timePoints = new int[N];
        for (int i = 0; i < N; i++) {
            Machine m = minHeap.poll();
            m.timePoint += m.waitTime;
            timePoints[i] = m.timePoint;
            minHeap.add(m);
        }
        return bestTimeDP(timePoints, a, b);
    }

    public static int bestTimeDP(int[] timePoints, int wash, int air) {
        int N = timePoints.length;
        int maxFree = 0;
        for (int i = 0; i < N; i++) {
            maxFree = Math.max(maxFree, timePoints[i]) + wash;
        }
        int[][] dp = new int[N + 1][maxFree + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int free = 0; free <= maxFree; free++) {
                int washTime = Math.max(timePoints[index], free) + wash;
                if (washTime > maxFree) {
                    continue;
                }
                int restTime1 = dp[index + 1][washTime];
                int p1 = Math.max(washTime, restTime1);

                int airTime = timePoints[index] + air;
                int restTime2 = dp[index + 1][free];
                int p2 = Math.max(airTime, restTime2);

                dp[index][free] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }

    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans2 = minDrink(arr, n, a, b);
            int ans3 = minDrink2(arr, n, a, b);
            if (ans2 != ans3) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans2 + " , " + ans3);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");

    }

}