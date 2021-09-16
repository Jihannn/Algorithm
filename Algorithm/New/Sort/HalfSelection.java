public class HalfSelection {
    //有序数组二分查找
    public static int halfSelction(int[] list,int num){
        if(list == null){
            return -1;
        }
        int startIndex = 0;
        int endIndex = list.length - 1;
        int curIndex = 0;
        while(startIndex <= endIndex){
            curIndex = (startIndex + endIndex) / 2;
            if(list[curIndex] > num){
                endIndex = curIndex - 1;
            }else if(list[curIndex] < num){
                startIndex = curIndex + 1;
            }else{
                return curIndex;
            }
        }
        return -1;
    }

    //有序数组查找大于等于某数的最左侧位置
    public static int halfMaxNumIndex(int[] list,int num){
        if(list == null){
            return -1;
        }
        int startIndex = 0;
        int endIndex = list.length -1;
        int curIndex = 0;
        int leftMaxNum = -1;
        while(startIndex <= endIndex){
            curIndex = (startIndex + endIndex) / 2;
            if(list[curIndex] >= num){
                leftMaxNum = curIndex;
                endIndex = curIndex - 1;
            }else if(list[curIndex] < num){
                startIndex = curIndex + 1;
            }
        }
        return leftMaxNum;
    }

    //无序数组局部最小值,相邻数一定不等
    //定义：0 < 1 , n-1 < n-2 , i < i-1 && i < i+1
    //时间复杂度<O(N)
    public static int findPartialNum(int list[]){
        if(list == null || list.length < 2){
            return -1;
        }
        if(list[0] < list[1]){
            return 1;
        }else if(list[list.length-1] < list[list.length-2]){
            return list.length-1;
        }
        int startIndex = 1;
        int endIndex = list.length - 2;
        while(startIndex <= endIndex){
            int curIndex = (startIndex + endIndex) / 2;
            if(list[curIndex] < list[curIndex-1] && list[curIndex] < list[curIndex+1]){
                return curIndex;
            }else if(list[curIndex] > list[curIndex-1]){
                endIndex = curIndex -1;
            }else{
                startIndex = curIndex + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int orderList[] = {1,3,3,3,4,5,5,5,7,9};
        int disorderList[] = {6,3,2,1,0,6,5,9};
        System.out.println(halfMaxNumIndex(orderList, 6));
        System.out.println(findPartialNum(disorderList));
    }
}
