public class RadixSort {

    public static void radixSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        radixProcess(arr,0,arr.length-1,getDigtial(arr));
    }

    private static int getDigtial(int[] arr) {
        int num = 0;
        int maxNum = Integer.MIN_VALUE;
        for (int i : arr) {
            maxNum = Math.max(i, maxNum);
        }
        while(maxNum != 0){
            num++;
            maxNum /= 10;
        }
        return num;
    }

    public static void radixProcess(int[] arr,int L,int R,int maxDig){
        final int RADIX = 10;
        int[] bucket = new int[R-L+1];
        for (int i = 1; i <= maxDig; i++) {
            int[] count = new int[RADIX];
            for (int j = L; j <= R; j++) {
                count[getDigNum(arr[j],i)]++;
            }
            for (int j = 1; j < RADIX; j++) {
                count[j] += count[j-1];
            }
            for (int j = R; j >= L; j--) {
                bucket[--count[getDigNum(arr[j],i)]] = arr[j];
            }
            int bCur = 0,aCur = L;
            while(aCur <= R){
                arr[aCur++] = bucket[bCur++];
            }
        }
    }

    public static int getDigNum(int num,int dig){
        return (num / (int)Math.pow(10,dig-1)) % 10;
    }
    
    public static void swap(int list[],int i,int j){
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    public static void printList(int list[]){
        for (int i = 0; i < list.length; i++) {
            if(i == 0){
                System.out.print("["); 
            }else if(i == list.length-1){
                System.out.print(list[i]+"]");
                break;
            }
            System.out.print(list[i]+",");
        }
    }

    public static void main(String[] args) {
        int[] arr = {8,7,6,4,16,15,11,5,7,8,23,4};
        radixSort(arr);
        printList(arr);
    }
}
