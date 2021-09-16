public class QuickSort {

    //荷兰国旗
    public static void hollandTwo(int[] list,int num){
        if(list == null || list.length < 2){
            return;
        }
        hollandTwoProcess(list,0,list.length-1,num);
    }

    private static void hollandTwoProcess(int[] list, int L, int R,int num) {
        int left = L - 1;
        int cur = L;
        while (cur <= R){
            if(list[cur] <= num){
                swap(list, cur, ++left);
            }
            cur++;
        }
    }

    //荷兰国旗（三等分）
    public static void hollandThree(int[] list,int num){
        if(list == null || list.length < 2){
            return;
        }
        hollandThreeProcess(list,0,list.length-1,num);
    }

    private static void hollandThreeProcess(int[] list, int L, int R, int num) {
        int left = L - 1;
        int right = R + 1;
        int cur = L;
        while(cur < right){
            if(list[cur] < num){
                swap(list, cur++, ++left);
            }else if(list[cur] > num){
                swap(list,cur,--right);
            }else{
                cur++;
            }
        }
    }

    //快排1
    public static void quickSort1(int[] list){
        if(list == null || list.length < 2){
            return;
        }
        quickProcess1(list, 0, list.length-1);
    }

    public static void quickProcess1(int[] list,int L,int R){
        if(L < R){
            int equal = partition1(list,L,R);
            quickProcess1(list,L,equal-1);
            quickProcess1(list,equal+1,R);
        }
    }

    private static int partition1(int[] list, int L, int R) {
        int left = L - 1;
        int right = R;
        int cur = L;
        while(cur < right){
            if(list[cur] <= list[R]){
                swap(list,cur++,++left);
            }else{
                swap(list,cur,--right);
            }
        }
        swap(list,cur,R);
        return cur;
    }

    //快排2
    public static void quickSort2(int[] list){
        if(list == null || list.length < 2){
            return;
        }
        quickProcess2(list,0,list.length-1);
    }

    public static void quickProcess2(int[] list,int L,int R){
        if(L < R){
            int equals[] = partition2(list,L,R);
            quickProcess2(list, L, equals[0]-1);
            quickProcess2(list, equals[1]+1, R);
        }
    }

    public static int[] partition2(int[] list,int L,int R){
        int left = L - 1;
        int right = R;
        int cur = L;
        while(cur < right){
            if(list[cur] < list[R]){
                swap(list,cur++,++left);
            }else if(list[cur] > list[R]){
                swap(list,cur,--right);
            }else{
                cur++;
            }
        }
        swap(list,cur,R);
        return new int[]{left+1,cur};
    }


    //随机快排
    public static void quickRandomSort(int[] list){
        if(list == null || list.length < 2){
            return;
        }
        quickRandomProcess(list, 0, list.length-1);
    }

    public static void quickRandomProcess(int[] list,int L,int R){
        if(L < R){
            swap(list,R,L + (int)Math.random()*(R-L+1));
            int equals[] = partition3(list,L,R);
            quickRandomProcess(list, L, equals[0]-1);
            quickRandomProcess(list, equals[1]+1, R);
        }
    }

    private static int[] partition3(int[] list, int L, int R) {
        int left = L - 1;
        int right = R;
        int cur = L;
        while(cur < right){
            if(list[cur] < list[R]){
                swap(list,cur++,++left);
            }else if(list[cur] > list[R]){
                swap(list,cur,--right);
            }else{
                cur++;
            }
        }
        swap(list,cur,R);
        return new int[]{left+1,cur};
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
        int[] hollandList = {4,2,1,3,5,1,7,8,4};
        hollandTwo(hollandList,4);
        printList(hollandList);
        int[] hollandList2 = {4,2,1,3,5,4,41,7,8,4};
        hollandThree(hollandList2, 5);
        printList(hollandList2);
        int[] quickList1 = {5,4,3,2,1,5,4,3,6,8};
        quickSort1(quickList1);
        printList(quickList1);
        int[] quickList2 = {5,4,3,2,1,5,4,3,6,8};
        quickSort2(quickList2);
        printList(quickList2);
        int[] quickList3 = {5,4,3,2,1,5,4,3,6,8};
        quickRandomSort(quickList3);
        printList(quickList3);
    }
}
