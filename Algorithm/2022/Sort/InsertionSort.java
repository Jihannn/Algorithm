package Sort;
/*
 * @Author: Jihan
 * @Date: 2022-04-20 20:13:58
 * @Description: 插入排序
 */
public class InsertionSort {
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArr(int[] arr){
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if(i != arr.length - 1){
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    public static void insertionSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j-1 >= 0 && arr[j] < arr[j-1] ; j--) {
                swap(arr, j, j-1);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,3,6,1,8,0,-2,-5,0,3,6};
        printArr(arr);
        insertionSort(arr);
        printArr(arr);
    }
}
