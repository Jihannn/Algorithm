import java.util.PriorityQueue;

/*
 * @Author: Jihan
 * @Date: 2022-04-25 09:44:52
 * @Description: 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k,
 * k相对于数组长度来说是比较小的。请选择一个合适的排序策略，对这个数组进行排序。 
 */
public class SortedArrDistanceLessK {
    public static void printArr(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int L = 0;
        int R = 0;
        int len = 0;
        // R到L位置中间可以移动k步 => len = R - L + 1 = k
        // 先放入k+1个数,如果数量不够则有多少放多少
        while (len <= k && R < arr.length) {
            minHeap.add(arr[R++]);
            len++;
        }
        // 小根堆取出放到L位置
        // 如果右侧没数越界则跳出
        while (L < arr.length && R < arr.length) {
            arr[L++] = minHeap.poll();
            minHeap.add(arr[R++]);
        }
        // 把不满k步的数排序
        while (len > 0) {
            arr[L++] = minHeap.poll();
            len--;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, -8, 0, -2 };
        printArr(arr);
        sort(arr, 3);
        printArr(arr);
    }
}