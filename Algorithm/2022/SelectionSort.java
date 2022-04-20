/*
 * @Author: Jihan
 * @Date: 2022-04-20 19:47:35
 * @Description: 选择排序
 */
public class SelectionSort {
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

    public static void selectionSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minValue = i;
            for (int j = i + 1; j < arr.length; j++) {
                minValue = arr[j] < arr[minValue] ? j : minValue;
            }
            swap(arr,i,minValue);
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,3,6,1,8,0,-2,-5,0,3,6};
        printArr(arr);
        selectionSort(arr);
        printArr(arr);
    }
}