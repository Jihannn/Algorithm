import java.util.Comparator;
import java.util.PriorityQueue;

public class Others {

    /** 
     * 异或运算
     */

    //数组中，一个数出现奇数次，其它数都出现了偶数次，找到这一个数。 
    public static int findOneOddNum(int[] list){
        if(list == null){
            return -1; 
        }
        int number = 0;
        for (int i : list) {
            number ^= i;
        }
        return number;
    }

    //数组中，两个数出现奇数次，其它数都出现了偶数次，找到这两个数。
    //因为a^b != 0,所以二进制中至少有一位不同 
    //分为两组，一组为最后位数为0，一组最后位数为1
    public static int[] findTwoOddNum(int[] list){
        if(list == null){
            return null;
        }
        int number = 0;
        for (int i : list) {
            number ^= i;
        }
        int rightOne = number & (~number+1); //得到number最右侧的1
        int notOneNum = 0;
        for (int i : list) {
            if((i & rightOne) == 0){
                notOneNum ^= i;
            }
        }
        int oneNum = number ^ notOneNum;
        int result[] = {oneNum,notOneNum};
        return result;
    }

    /**
     * 比较器
     */ 

    public static class ACompare implements Comparator<Integer>{

        //负数在前，正数在后
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static void HeapTest(){
        //默认小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new ACompare());
        int[] arr = {6,4,2,1,73,89,3,7};
        for (int i : arr) {
            minHeap.add(i);
        }
        for (int i : arr) {
            maxHeap.add(i);
        }
        while(!minHeap.isEmpty()){
            System.out.print(minHeap.poll()+",");
        }
        System.out.println();
        while(!maxHeap.isEmpty()){
            System.out.print(maxHeap.poll()+",");
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
        // int list[] = {6,1,6,4,1,3,4,3,7};
        // int list2[] = {6,5,6,5,3,3,4,4,4,2,2,2,2,3};
        // System.out.println(findOneOddNum(list));
        // int list3[] = findTwoOddNum(list2);
        // System.out.println(list3[0]+"and"+list3[1]);
        // HeapTest();
    }
}
