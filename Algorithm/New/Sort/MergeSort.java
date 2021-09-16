public class MergeSort {

    public static void mergeSort(int list[]){
        if(list == null || list.length < 2){
            return;
        }
        process(list,0,list.length-1);
    }

    public static void process(int list[],int L,int R){
        if(L == R){
            return;
        }
        int M = L + (R - L >> 1);
        process(list,L,M);
        process(list,M+1,R);
        merge(list,L,M,R);
    }

    public static void merge(int list[],int L,int M,int R){
        int tempList[] = new int[R-L+1];
        int left = L;
        int right = M+1;
        int cur = 0;
        while(left <= M && right <= R){
            tempList[cur++] = list[left] <= list[right] ? list[left++] : list[right++];
        }
        while(left <= M){
            tempList[cur++] = list[left++];
        }
        while(right <= R){
            tempList[cur++] = list[right++];
        }
        // for (int i : tempList) {
        //     list[L++] = i; 
        // }
        for (int i = 0; i < tempList.length; i++) {
            list[L + i] = tempList[i];
        }
    }

    //小和问题，一个数左边比它小的数的总和
    public static int smallSum(int[] list){
        if(list == null || list.length < 2){
            return 0;
        }
        return smallSumProcess(list,0,list.length-1);
    }
    
    private static int smallSumProcess(int[] list, int L, int R) {
        if(L == R){
            return 0;
        }
        int M = L + ((R - L) >> 1);
        int leftSum = smallSumProcess(list, L, M);
        int rightSum = smallSumProcess(list, M+1, R);
        int sum = smallSumMerge(list,L,M,R);
        return leftSum + rightSum + sum;
    }

    private static int smallSumMerge(int[] list, int L, int M, int R) {
        int tempList[] = new int[R-L+1];
        int left = L;
        int right = M + 1;
        int cur = 0;
        int sum = 0;
        while(left <= M && right <= R){
            sum += list[left] < list[right] ? (R - right + 1) * list[left] : 0;
            tempList[cur++] = list[left] < list[right] ? list[left++] : list[right++];
        }
        while(left <= M){
            tempList[cur++] = list[left++];
        }
        while(right <= R){
            tempList[cur++] = list[right++];
        }
        for (int i = 0; i < tempList.length; i++) {
            list[L + i] = tempList[i];
        }
        return sum;
    }

    //打印逆序对 数组左边比右边大为一个逆序对
    public static void printReverseOrder(int[] list){
        if(list == null || list.length < 2){
            return;
        }
        reverseProcess(list, 0, list.length-1);
    }

    public static void reverseProcess(int[] list,int L,int R){
        if(L==R){
            return;
        }
        int M = L + ((R - L) >> 1);
        reverseProcess(list,L,M);
        reverseProcess(list,M+1,R);
        reverseMerge(list,L,M,R);
    }
    
    public static void reverseMerge(int[] list,int L,int M,int R){
        int tempList[] = new int[R-L+1];
        int left = L;
        int right = M + 1;
        int cur = 0;
        while(left <= M && right <= R){
            if(list[left] > list[right]){
                System.out.println("["+list[left]+","+list[right]+"]");
            }
            tempList[cur++] = list[left] < list[right] ? list[left++] : list[right++];
        }
        while(left <= M){
            tempList[cur++] = list[left++];
        }
        while(right <= R){
            tempList[cur++] = list[right++];
        }
        for (int i = 0; i < tempList.length; i++) {
            list[L + i] = tempList[i];
        }
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
        int list[] = {5,7,8,4,3,2,1};
        int sumList[] = {1,2,3,4,5,6}; // 5+8+9+8+5 = 35 
        // mergeSort(list);
        // printList(list);
        int reverseOrderList[] = {7,5,4,3};
        // System.out.println(smallSum(sumList));

        // TODO:有问题
        printReverseOrder(reverseOrderList);
    }
}
