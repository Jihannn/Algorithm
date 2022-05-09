package Sort;
/*
 * @Author: Jihan
 * @Date: 2022-04-22 22:21:10
 * @Description: 归并排序
 */
public class MergeSort {
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

    // 归并排序递归
    public static void mergeSortRec(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        process(arr,0,arr.length - 1);
    }

    // 归并排序迭代
    public static void MergeSortIter(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        int N = arr.length;
        // 步长
        int step = 1;
        while(step < N){
            int L = 0;
            while(L < N){
                // 剩下的数不够步长则放弃
                if(N - L < step){
                    break;
                }
                int M = L + step - 1;
                // 查看右边数是否够步长，不够则有多少算多少
                int R = M + Math.min(step,N - M - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            step <<= 1;
        } 
    }

    public static void process(int[] arr,int L,int R){
        // 递归结束条件
        if(L == R){
            return;
        }
        int mid = L + ((R - L) >> 1);
        // 使得左边有序，右边有序，然后合并
        process(arr, L, mid);
        process(arr, mid + 1,R);
        merge(arr,L,mid,R);
    }

    public static void merge(int[] arr,int L,int mid,int R){
        int leftIndex = L;
        int rightIndex = mid + 1;
        int[] helpArr = new int[R - L + 1];
        int helpCur = 0;
        // 按序复制到辅助数组，直至一边越界
        while(leftIndex <= mid && rightIndex <= R){
            helpArr[helpCur++] = arr[leftIndex] <= arr[rightIndex] ? arr[leftIndex++] : arr[rightIndex++];
        }
        // 下面两者只会走其一
        while(leftIndex <= mid){
            helpArr[helpCur++] = arr[leftIndex++];
        }
        while(rightIndex <= R){
            helpArr[helpCur++] = arr[rightIndex++];
        }
        // 辅助数组复制回原数组
        for (int i = 0; i < helpArr.length; i++) {
            arr[L + i] = helpArr[i];
        }
    }

    public static int[] generateRandomArray(int maxSize,int maxValue){
        int arr[] = new int[(int)(Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((maxValue + 1) * Math.random() - (int)(maxValue * Math.random()));
        }
        return arr;
    }

    public static int[] copyArr(int[] arr){
        if(arr == null){
            return null;
        }
        int[] arr2 = new int[arr.length];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = arr[i];
        }
        return arr2;
    }

    public static boolean isEqual(int[] arr1,int[] arr2){
        if((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)){
            return false;
        }
        if(arr1 == null && arr2 == null){
            return true;
        }
        if(arr1.length != arr2.length){
            return false;
        }
        for (int i = 0; i < arr2.length; i++) {
            if(arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }

    public static void test(){
        int testTimes = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test start!");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArray(maxSize,maxValue);
            int[] arr2 = copyArr(arr1);
            mergeSortRec(arr1);
            MergeSortIter(arr2);
            if(!isEqual(arr1,arr2)){
                System.out.println("error!");
                printArr(arr1);
                printArr(arr2);
                break;
            }
        }
        System.out.println("test end!");
    }

    public static void main(String[] args) {
        test();
    }
}
