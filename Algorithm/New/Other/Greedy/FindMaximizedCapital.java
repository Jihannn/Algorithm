package Other.Greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * @Author: Jihan
 * 
 * @Date: 2021-12-08 21:39:03
 * 
 * @Description:给一组（所需资金,纯利润）的工程，如何在有限次数使得收益最大化
 */
public class FindMaximizedCapital {
    private static class Project {
        int fund;
        int profit;
    }

    private static class MinFundComparator implements Comparator<Project> {

        @Override
        public int compare(Project o1, Project o2) {
            return o1.fund - o2.fund;
        }
    }

    private static class MaxProfitComparator implements Comparator<Project> {

        @Override
        public int compare(Project o1, Project o2) {
            return o2.profit - o2.profit;
        }
    }

    public static int getMaximized(Project[] projects, int initalFund, int times) {
        PriorityQueue<Project> minFund = new PriorityQueue<>(new MinFundComparator());
        PriorityQueue<Project> maxProfit = new PriorityQueue<>(new MaxProfitComparator());
        // 按小到大排序所需资金
        for (Project project : projects) {
            minFund.add(project);
        }
        int fund = initalFund;
        // 能做的次数
        for (int i = 0; i < times; i++) {
            while (!minFund.isEmpty() && minFund.peek().fund <= fund) {
                maxProfit.add(minFund.poll());
            }
            // 如果当前资金不够则提前结束
            if (maxProfit.isEmpty()) {
                break;
            }
            fund += maxProfit.poll().profit;
        }
        return fund;
    }
}