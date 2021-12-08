package Other.Greedy;

import java.util.PriorityQueue;

/*
 * @Author: Jihan
 * 
 * @Date: 2021-12-08 17:25:08
 * 
 * @Description:分金条,让切割的消耗最小。
 */
public class LessMoneySplitGold {

    public static int split(int[] golds) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i : golds) {
            minHeap.add(i);
        }
        int cost = 0;
        while (minHeap.size() > 1) {
            int newNode = minHeap.poll() + minHeap.poll();
            cost += newNode;
            minHeap.add(newNode);
        }
        return cost;
    }
}
