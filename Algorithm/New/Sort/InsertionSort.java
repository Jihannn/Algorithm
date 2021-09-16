public class InsertionSort{
    
    public static void insertionSort(int[] list){
        if(list == null || list.length < 2){
            return;
        }
        for (int i = 1; i < list.length; i++) {
            for (int j = i;j > 0 && list[j] < list[j-1]; j--) {
                swap(list,j,j-1);
            }
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
        int list[] = {5,4,3,2,1};
        insertionSort(list);
        printList(list);
    }
}