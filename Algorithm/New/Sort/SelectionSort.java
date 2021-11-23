public class SelectionSort {

    public static void selectSort(int[] list) {
        if (list == null || list.length < 2) {
            return;
        }
        for (int i = list.length - 1; i > 0; i--) {
            int maxNum = i;
            for (int j = 0; j < i; j++) {
                if (list[j] > list[maxNum]) {
                    maxNum = j;
                }
            }
            swap(list, i, maxNum);
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
        int list[] = { 5, 4, 3, 2, 1 };
        selectSort(list);
        printList(list);
    }
}
