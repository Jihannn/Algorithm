import java.util.PriorityQueue;

public class HeapSort {

    public static void heapSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        // for (int i = 0; i < array.length; i++) {
        // heapInsert(array, i);
        // }

        int heapSize = array.length;
        // 构建大根堆，从第一个非叶子结点开始
        for (int i = (heapSize - 1) / 2; i >= 0; i--) {
            heapify(array, i, heapSize);
        }
        // 构建小根堆
        swap(array, 0, --heapSize);
        while (heapSize > 0) {
            heapify(array, 0, heapSize);
            swap(array, 0, --heapSize);
        }
    }

    public static void heapInsert(int[] array, int index) {
        while (array[index] > array[(index - 1) / 2]) {
            swap(array, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] array, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 左右孩子中比较大的
            int largest = left + 1 < heapSize && array[left + 1] > array[left] ? left + 1 : left;
            // 比较结点和孩子
            largest = array[index] > array[largest] ? index : largest;
            if (largest == index) {
                break;
            }
            // 大的结点下沉
            swap(array, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    // 相关题目：一个几乎有序的数组，每个元素移动不超过k，完全排好序。
    public static void almostArrSort(int arr[], int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int index = 0;
        for (; index < Math.min(arr.length, k); index++) {
            minHeap.add(arr[index]);
        }
        int cur = 0;
        for (; index < arr.length; index++, cur++) {
            minHeap.add(arr[index]);
            arr[cur] = minHeap.poll();
        }
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