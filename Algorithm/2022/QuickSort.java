import java.util.Stack;

/*
 * @Author: Jihan
 * @Date: 2022-04-24 09:50:36
 * @Description: 快速排序
 */
public class QuickSort {
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

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // 随机使得最差情况期望为O(N*LogN)
        int randomIndex = L + (int) Math.random() * (R - L + 1);
        swap(arr, randomIndex, R);
        // 相等区域闭区间
        int[] boundarys = partition(arr, L, R);
        process(arr, L, boundarys[0] - 1);
        process(arr, boundarys[1] + 1, R);
    }

    public static int[] partition(int[] arr, int L, int R) {
        int left = L - 1;
        int right = R;
        int cur = L;
        while (cur < right) {
            if (arr[cur] < arr[R]) {
                swap(arr, ++left, cur++);
            } else if (arr[cur] > arr[R]) {
                swap(arr, --right, cur);
            } else {
                cur++;
            }
        }
        swap(arr, cur, R);
        return new int[] { left + 1, cur };
    }

    public static class MyStackData {
        int leftIndex;
        int rightIndex;

        public MyStackData(int leftIndex, int rightIndex) {
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;
        }
    }

    public static void quickSortUnRec(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Stack<MyStackData> stack = new Stack<>();
        int L = 0;
        int R = arr.length - 1;
        int randomIndex = L + (int) Math.random() * (R - L + 1);
        swap(arr, R, randomIndex);
        stack.push(new MyStackData(L, R));
        while (!stack.isEmpty()) {
            MyStackData data = stack.pop();
            L = data.leftIndex;
            R = data.rightIndex;
            if (L >= R) {
                continue;
            }
            int[] boundarys = partition(arr, L, R);
            stack.push(new MyStackData(L, boundarys[0] - 1));
            stack.push(new MyStackData(boundarys[1] + 1, R));
        }
    }

    public static void main(String[] args) {
        int[] arr = { -6, 8, 2, 0, 0, 2, -5, 8 };
        printArr(arr);
        quickSortUnRec(arr);
        printArr(arr);
    }
}