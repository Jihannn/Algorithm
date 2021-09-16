import java.util.PriorityQueue;

public class HeapSort {

    public static void heapSort(int[] list){
        if(list == null || list.length <2){
            return;
        }
        // for (int i = 0; i < list.length; i++) {
        //     heapInsert(list, i);
        // }
        for(int i = list.length - 1;i >= 0;i--){
            heapify(list, i, list.length);
        }

        int heapSize = list.length;
        swap(list,0,--heapSize);
        while(heapSize > 0){
            heapify(list,0,heapSize);
            swap(list,0,--heapSize);
        }
    }

    public static void heapInsert(int[] list,int index){
        while(list[index] > list[(index-1)/2]){
            swap(list,index,(index-1)/2);
            index = (index-1) / 2; 
        }
    }

    public static void heapify(int[] list,int index,int heapSize){
        int left = index * 2 + 1;
        while(left < heapSize){
            int largest = left + 1 < heapSize && list[left+1] > list[left] ? left+1 : left;
            largest = list[index] > list[largest] ? index : largest;
            if(largest == index){
                break;
            }
            swap(list,largest,index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    //一个几乎有序的数组，每个元素移动不超过k，完全排好序。
    public static void almostArrSort(int arr[],int k){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int index = 0;
        for (; index < Math.min(arr.length, k); index++) {
            minHeap.add(arr[index]);
        }
        int cur = 0;
        for (; index < arr.length; index++,cur++) {
            minHeap.add(arr[index]);
            arr[cur] = minHeap.poll();
        }
        while(!minHeap.isEmpty()){
            arr[cur++] = minHeap.poll();
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
        int[] list = {6,4,2,1,4,8,93,13,7};
        heapSort(list);
        printList(list);
        int[] almostList = {1,3,5,8,2,4,6};
        almostArrSort(almostList, 3);
        printList(almostList);
    }   
}