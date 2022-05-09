package Sort;
/*
 * @Author: Jihan
 * @Date: 2022-04-20 20:07:47
 * @Description: 冒泡排序
 */
public class BubbleSort {
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

    public static void bubbleSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if(arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,3,6,1,8,0,-2,-5,0,3,6};
        printArr(arr);
        bubbleSort(arr);
        printArr(arr);
    }
}
