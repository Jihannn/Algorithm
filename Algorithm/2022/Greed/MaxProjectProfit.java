package Greed;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * @Author: Jihan
 * @Date: 2022-05-09 09:04:51
 * @Description: 
 * 输入正数数组costs、正数数组profits、正数K和正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明：每做完一个项目，马上获得的收益，可以支持你去做下一个项目，不能并行的做项目。
 * 输出：最后获得的最大钱数
 */
public class MaxProjectProfit {
    public static class Project {
        int cost;
        int profit;
    }

    public static class CostMinComparator implements Comparator<Project> {

        @Override
        public int compare(Project o1, Project o2) {
            return o1.cost - o2.cost;
        }

    }

    public static class ProfitMaxComparator implements Comparator<Project> {

        @Override
        public int compare(Project o1, Project o2) {
            return o2.profit - o1.profit;
        }

    }

    public static int projectProfit(Project[] projects, int k, int m) {
        PriorityQueue<Project> minHeap = new PriorityQueue<>(new CostMinComparator());
        PriorityQueue<Project> maxHeap = new PriorityQueue<>(new ProfitMaxComparator());
        for (Project project : projects) {
            minHeap.add(project);
        }
        while (!minHeap.isEmpty() && k > 0) {
            while (minHeap.peek().cost < m) {
                maxHeap.add(minHeap.poll());
            }
            if (maxHeap.isEmpty()) {
                break;
            }
            m += maxHeap.poll().profit;
            k--;
        }
        return m;
    }
}
