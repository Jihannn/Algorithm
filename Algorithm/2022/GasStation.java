import java.util.LinkedList;

/*
 * @Author: Jihan
 * @Date: 2022-05-24 09:44:20
 * @Description: https://leetcode.cn/problems/gas-station
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 */
public class GasStation {
    public static int gasStation(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length != cost.length) {
            return -1;
        }
        int N = gas.length;
        int M = N << 1;
        int[] sum = new int[M];
        for (int i = 0; i < N; i++) {
            sum[i] = gas[i] - cost[i];
            sum[i + N] = gas[i] - cost[i];
        }
        for (int i = 1; i < sum.length; i++) {
            sum[i] += sum[i - 1];
        }
        LinkedList<Integer> queue = new LinkedList<>();
        int R = 0;
        for (int L = 0; L < N; L++) {
            while (R < L + N) {
                while (!queue.isEmpty() && sum[R] <= sum[queue.peekLast()]) {
                    queue.pollLast();
                }
                queue.addLast(R);
                R++;
            }
            int index = queue.peekFirst();
            // 改良可以实现从每个站点是否能环绕一圈
            if (L == 0) {
                if (sum[index] >= 0) {
                    return L;
                }
            } else {
                if (sum[index] - sum[L - 1] >= 0) {
                    return L;
                }
            }
            if (index == L) {
                queue.pollFirst();
            }
        }
        return -1;
    }
}