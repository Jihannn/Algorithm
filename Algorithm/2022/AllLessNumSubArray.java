import java.util.LinkedList;

/*
 * @Author: Jihan
 * @Date: 2022-05-24 09:17:38
 * @Description: 
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 */
public class AllLessNumSubArray {
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int subArray(int[] arr, int num) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int n = arr.length;
        int rtn = 0;
        LinkedList<Integer> minQueue = new LinkedList<>();
        LinkedList<Integer> maxQueue = new LinkedList<>();
        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r < n) {
                while (!maxQueue.isEmpty() && arr[r] >= arr[maxQueue.peekLast()]) {
                    maxQueue.pollLast();
                }
                while (!minQueue.isEmpty() && arr[r] <= arr[minQueue.peekLast()]) {
                    minQueue.pollLast();
                }
                maxQueue.add(r);
                minQueue.add(r);
                if (arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()] > num) {
                    break;
                }
                r++;
            }
            rtn += r - l;
            if (maxQueue.peekFirst() == l) {
                maxQueue.pollFirst();
            }
            if (minQueue.peekFirst() == l) {
                minQueue.pollFirst();
            }
        }
        return rtn;
    }

    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = subArray(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
