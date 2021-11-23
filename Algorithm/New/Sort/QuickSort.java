public class QuickSort {

    // 荷兰国旗
    public static void hollandTwo(int[] array, int num) {
        if (array == null || array.length < 2) {
            return;
        }
        // 以num为轴，左小右大排序
        hollandTwoProcess(array, 0, array.length - 1, num);
    }

    private static void hollandTwoProcess(int[] array, int L, int R, int num) {
        int left = L - 1;
        int cur = L;
        while (cur <= R) {
            if (array[cur] <= num) {
                swap(array, cur, ++left);
            }
            cur++;
        }
    }

    // 荷兰国旗（三等分）
    public static void hollandThree(int[] array, int num) {
        if (array == null || array.length < 2) {
            return;
        }
        // 以num为轴，左小中等右大排序
        hollandThreeProcess(array, 0, array.length - 1, num);
    }

    private static void hollandThreeProcess(int[] array, int L, int R, int num) {
        int left = L - 1;
        int right = R + 1;
        int cur = L;
        while (cur < right) {
            if (array[cur] < num) {
                swap(array, cur++, ++left);
            } else if (array[cur] > num) {
                swap(array, cur, --right);
            } else {
                cur++;
            }
        }
    }

    // 快排1(二等分)
    public static void quickSort1(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        quickProcess1(array, 0, array.length - 1);
    }

    private static void quickProcess1(int[] array, int L, int R) {
        // 如果相等则说明只有一个元素，该子序列无需排序
        if (L < R) {
            // 返回轴心,递归左右子序列
            int equal = partition1(array, L, R);
            quickProcess1(array, L, equal - 1);
            quickProcess1(array, equal + 1, R);
        }
    }

    private static int partition1(int[] array, int L, int R) {
        int left = L - 1;
        int right = R;
        int cur = L;
        // 以R为轴. 小于等于[left])[cur]......([right]大于
        while (cur < right) {
            if (array[cur] <= array[R]) {
                swap(array, cur++, ++left);
            } else {
                swap(array, cur, --right);
            }
        }
        // cur == right,把轴放在中间.
        // 小于等于...[left])([cur/right]...大于...R
        swap(array, cur, R);
        return cur;
    }

    // 快排2(三等分)
    public static void quickSort2(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        quickProcess2(array, 0, array.length - 1);
    }

    private static void quickProcess2(int[] array, int L, int R) {
        if (L < R) {
            int equals[] = partition2(array, L, R);
            quickProcess2(array, L, equals[0] - 1);
            quickProcess2(array, equals[1] + 1, R);
        }
    }

    private static int[] partition2(int[] array, int L, int R) {
        int left = L - 1;
        int right = R;
        int cur = L;
        // 小于[left])...等于[cur]...([right]大于...R
        while (cur < right) {
            if (array[cur] < array[R]) {
                swap(array, cur++, ++left);
            } else if (array[cur] > array[R]) {
                swap(array, cur, --right);
            } else {
                cur++;
            }
        }
        swap(array, cur, R);
        // 返回相等的轴序列的左右边界
        return new int[] { left + 1, cur };
    }

    // 随机快排
    public static void quickRandomSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        quickRandomProcess(array, 0, array.length - 1);
    }

    private static void quickRandomProcess(int[] array, int L, int R) {
        if (L < R) {
            // 把轴随机替换,以免极端情况.
            swap(array, R, L + (int) Math.random() * (R - L));
            // 其余与快排2相同
            int equals[] = partition3(array, L, R);
            quickRandomProcess(array, L, equals[0] - 1);
            quickRandomProcess(array, equals[1] + 1, R);
        }
    }

    private static int[] partition3(int[] array, int L, int R) {
        int left = L - 1;
        int right = R;
        int cur = L;
        while (cur < right) {
            if (array[cur] < array[R]) {
                swap(array, cur++, ++left);
            } else if (array[cur] > array[R]) {
                swap(array, cur, --right);
            } else {
                cur++;
            }
        }
        swap(array, cur, R);
        return new int[] { left + 1, cur };
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
        int[] hollandarray = { 4, 2, 1, 3, 5, 1, 7, 8, 4 };
        hollandTwo(hollandarray, 4);
        printArray(hollandarray);
        int[] hollandarray2 = { 4, 2, 1, 3, 5, 4, 41, 7, 8, 4 };
        hollandThree(hollandarray2, 5);
        printArray(hollandarray2);
        int[] quickarray1 = { 5, 4, 3, 2, 1, 5, 4, 3, 6, 8 };
        quickSort1(quickarray1);
        printArray(quickarray1);
        int[] quickarray2 = { 5, 4, 3, 2, 1, 5, 4, 3, 6, 8 };
        quickSort2(quickarray2);
        printArray(quickarray2);
        int[] quickarray3 = { 5, 4, 3, 2, 1, 5, 4, 3, 6, 8 };
        quickRandomSort(quickarray3);
        printArray(quickarray3);
    }
}
