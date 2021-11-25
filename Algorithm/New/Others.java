import java.util.Comparator;
import java.util.PriorityQueue;

public class Others {

    /**
     * 异或运算
     */

    // 数组中，一个数出现奇数次，其它数都出现了偶数次，找到这一个数。
    public static int findOneOddNum(int[] array) {
        int eor = 0;
        for (int num : array) {
            eor ^= num;
        }
        return eor;
    }

    // 数组中，两个数出现奇数次，其它数都出现了偶数次，找到这两个数。
    public static int[] findTwoOddNum(int[] array) {
        int eor = 0;
        // 异或遍历得到 eor = a ^ b
        for (int num : array) {
            eor ^= num;
        }
        // 因为a!=b,所以它们二进制至少有一位不同
        // 找到eor二进制最右侧的1
        int rightDig = eor & (~eor + 1);
        int resultA = 0;
        for (int num : array) {
            // 因为a!=b,可以分成right相同与不同的两组,a和b各在一组.
            // 偶数次的数会抵消,结果剩下a或b
            if ((num & rightDig) == 0) {
                resultA ^= num;
            }
        }
        int resultB = eor ^ resultA;
        return new int[] { resultA, resultB };
    }

    /**
     * 比较器
     */

    public static class ACompare implements Comparator<Integer> {

        // 负数在前，正数在后
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static void HeapTest() {
        // 默认小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new ACompare());
        int[] arr = { 6, 4, 2, 1, 73, 89, 3, 7 };
        for (int i : arr) {
            minHeap.add(i);
        }
        for (int i : arr) {
            maxHeap.add(i);
        }
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + ",");
        }
        System.out.println();
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + ",");
        }
    }

    public static void swap(int list[], int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    public static void printList(int list[]) {
        for (int i = 0; i < list.length; i++) {
            if (i == 0) {
                System.out.print("[");
            } else if (i == list.length - 1) {
                System.out.print(list[i] + "]");
                break;
            }
            System.out.print(list[i] + ",");
        }
    }

    public static void main(String[] args) {
        // int list[] = {6,1,6,4,1,3,4,3,7};
        // int list2[] = {6,5,6,5,3,3,4,4,4,2,2,2,2,3};
        // System.out.println(findOneOddNum(list));
        // int list3[] = findTwoOddNum(list2);
        // System.out.println(list3[0]+"and"+list3[1]);
        // HeapTest();
    }
}
