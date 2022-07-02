/*
 * @Author: Jihan
 * @Date: 2022-04-20 20:19:32
 * @Description: 1.二分查找 2.查找值最左或最右的位置
 */
public class HalfSelection {
    public static int halfSeletion(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] > value) {
                R = mid - 1;
            } else if (arr[mid] < value) {
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return arr[L] == value ? L : -1;
    }

    public static int findLeftmost(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        int lastIndex = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] > value) {
                R = mid - 1;
            } else if (arr[mid] < value) {
                L = mid + 1;
            } else {
                lastIndex = mid;
                R = mid - 1;
            }
        }
        return lastIndex;
    }

    public static int findRightmost(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        int lastIndex = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] < value) {
                L = mid + 1;
            } else if (arr[mid] > value) {
                R = mid - 1;
            } else {
                lastIndex = mid;
                L = mid + 1;
            }
        }
        return lastIndex;
    }

    public static void main(String[] args) {
        int[] arr = { -6, -3, -3, -1, 0, 0, 3, 7, 7, 9, 12 };
        System.out.println(halfSeletion(arr, -1));
        System.out.println(findLeftmost(arr, -6));
        System.out.println(findLeftmost(arr, -3));
        System.out.println(findLeftmost(arr, 12));
        System.out.println(findLeftmost(arr, 13));
        System.out.println();
        System.out.println(findRightmost(arr, -6));
        System.out.println(findRightmost(arr, -3));
        System.out.println(findRightmost(arr, 12));
        System.out.println(findRightmost(arr, 13));
    }
}
