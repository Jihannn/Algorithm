import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/*
 * @Author: Jihan
 * @Date: 2022-05-24 08:32:28
 * @Description: 
 * 窗口内最大值或最小值更新结构的实现
 * 假设一个固定大小为W的窗口，依次划过arr，
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 */
public class SlidingWindowMaxArray {
    public static int[] slidingWindow(int[] arr, int w) {
        if (arr == null || arr.length < w) {
            return new int[0];
        }
        int N = arr.length;
        int[] rtn = new int[N - w + 1];
        int cur = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        int right = 0;
        while (right < N) {
            while (!queue.isEmpty() && arr[queue.peekLast()] < arr[right]) {
                queue.pollLast();
            }
            queue.addLast(right);
            if (queue.peekFirst() == right - w) {
                queue.pollFirst();
            }
            if (right + 1 >= w) {
                rtn[cur++] = arr[queue.peekFirst()];
            }
            right++;
        }
        return rtn;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 3, 5, 4, 3, 3, 6, 7 };
        for (int i : slidingWindow(arr, 3)) {
            System.out.print(i + ",");
        }
    }
}
