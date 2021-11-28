import java.util.PriorityQueue;

public class HeapSort {

    public static void heapSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int heapSize = array.length;
        // 若结点依次添加，则用heapInsert建立堆
        // for (int i = 0; i < heapSize; i++) {
        // heapInsert(array, i);
        // }

        // 若已给出全部数组，则可用heapify建立堆
        // 从第一个非叶子结点开始
        for (int i = (heapSize - 1) / 2; i >= 0; i--) {
            heapify(array, i, heapSize);
        }
        // 每次排序好最后一个位置
        swap(array, 0, --heapSize);
        while (heapSize > 0) {
            heapify(array, 0, heapSize);
            swap(array, 0, --heapSize);
        }
    }

    private static void heapInsert(int[] array, int index) {
        while (array[index] > array[(index - 1) / 2]) {
            swap(array, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void heapify(int[] array, int index, int heapSize) {
        int leftIndex = index * 2 + 1;
        while (leftIndex < heapSize) {
            // 比较左右孩子
            int largestIndex = leftIndex + 1 < heapSize && array[leftIndex + 1] > array[leftIndex] ? leftIndex + 1
                    : leftIndex;
            largestIndex = array[index] > array[largestIndex] ? index : largestIndex;
            if (largestIndex == index) {
                break;
            }
            // 结点下沉
            swap(array, index, largestIndex);
            index = largestIndex;
            leftIndex = index * 2 + 1;
        }
    }

    // 相关题目：一个几乎有序的数组，每个元素移动不超过k，完全排好序。
    public static void almostArrSort(int arr[], int k) {
        // 优先队列不传比较器则默认小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int index = 0;
        // 0~k-1位置上的数加入小根堆
        for (; index < k; index++) {
            minHeap.add(arr[index]);
        }
        int cur = 0;
        // 最小数出队，解决一个位置
        for (; index < arr.length; index++, cur++) {
            minHeap.add(arr[index]);
            arr[cur] = minHeap.poll();
        }
        // 数组全部入队跳出上面循环，把剩余元素依次出队
        while (!minHeap.isEmpty()) {
            arr[cur++] = minHeap.poll();
        }
    }

    public static void swap(int array[], int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void printArray(int array[]) {
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                System.out.print("[");
            } else if (i == array.length - 1) {
                System.out.print(array[i] + "]");
                break;
            }
            System.out.print(array[i] + ",");
        }
    }

    public static void main(String[] args) {
        int[] array = { 6, 4, 2, 1, 4, 8, 93, 13, 7 };
        heapSort(array);
        printArray(array);
        // int[] almostarray = { 1, 3, 5, 8, 2, 4, 6 };
        // almostArrSort(almostarray, 3);
        // printArray(almostarray);
    }
}