package Sort;

/*
 * @Author: Jihan
 * @Date: 2022-04-25 08:54:11
 * @Description: 堆排序
 */
public class HeapSort {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

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

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // O(N*logN)
        int heapSize = arr.length;
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        // O(N)
        // for (int i = arr.length - 1; i >= 0; i++) {
        // heapify(arr, i, heapSize);
        // }
        // 每次排序好最后一个结点
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    public static void heapInsert(int[] arr, int index) {
        // 结点上浮
        while (arr[(index - 1) / 2] < arr[index]) {
            swap(arr, (index - 1) / 2, index);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        // 结点下沉
        int leftChild = index * 2 + 1;
        while (leftChild < heapSize) {
            int largest = leftChild + 1 < heapSize && arr[leftChild + 1] > arr[leftChild]
                    ? leftChild + 1
                    : leftChild;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            leftChild = largest * 2 + 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = { -5, 2, 7, 0, 7, 12, -4 };
        printArr(arr);
        heapSort(arr);
        printArr(arr);
    }
}