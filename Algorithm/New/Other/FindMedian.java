package Other;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * @Author: Jihan
 * 
 * @Date: 2021-12-08 22:04:35
 * 
 * @Description:不停加入数字,能够快速找到中位数
 */
public class FindMedian {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    private class MaxHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

    public FindMedian() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(new MaxHeapComparator());
    }

    public void insert(int num) {
        if (minHeap.isEmpty() || num > minHeap.peek()) {
            minHeap.add(num);
        } else {
            maxHeap.add(num);
        }
        if (Math.abs(minHeap.size() - maxHeap.size()) >= 2) {
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            } else {
                minHeap.add(maxHeap.poll());
            }
        }
    }

    public double getMedian() {
        if ((minHeap.size() + maxHeap.size()) % 2 == 0) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
        return minHeap.size() > maxHeap.size() ? minHeap.peek() : maxHeap.peek();
    }

    public static void main(String[] args) {
        FindMedian obj = new FindMedian();
        obj.insert(5);
        obj.insert(3);
        obj.insert(2);
        obj.insert(4);
        obj.insert(1);
        System.out.println(obj.getMedian());
        obj.insert(6);
        System.out.println(obj.getMedian());
    }
}
