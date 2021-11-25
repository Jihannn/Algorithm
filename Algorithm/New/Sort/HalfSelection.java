public class HalfSelection {
    // 有序数组二分查找
    public static int halfSelction(int[] array, int num) {
        if (array == null) {
            return -1;
        }
        int startIndex = 0;
        int endIndex = array.length - 1;
        int curIndex = 0;
        while (startIndex <= endIndex) {
            curIndex = (startIndex + endIndex) / 2;
            if (array[curIndex] > num) {
                endIndex = curIndex - 1;
            } else if (array[curIndex] < num) {
                startIndex = curIndex + 1;
            } else {
                return curIndex;
            }
        }
        return -1;
    }

    // 有序数组查找大于等于某数的最左侧位置
    public static int halfMaxNumIndex(int[] array, int num) {
        if (array == null) {
            return -1;
        }
        // 左右边界指针
        int startIndex = 0;
        int endIndex = array.length - 1;
        int curIndex = 0;
        // 当前最大数指针
        int leftMaxNumIndex = -1;
        while (startIndex <= endIndex) {
            curIndex = (startIndex + endIndex) / 2;
            if (array[curIndex] >= num) {
                leftMaxNumIndex = curIndex;
                endIndex = curIndex - 1;
            } else if (array[curIndex] < num) {
                startIndex = curIndex + 1;
            }
        }
        return leftMaxNumIndex;
    }

    // 求无序数组局部最小值。相邻数一定不等。
    // 局部最小值定义：左:0 < 1,右:n-2 > n-1,中间: i-1 > i && i < i+1
    public static int findPartialNum(int array[]) {
        if (array == null || array.length < 2) {
            return -1;
        }
        // 左右边界是局部最小直接返回
        if (array[0] < array[1]) {
            return 0;
        } else if (array[array.length - 1] < array[array.length - 2]) {
            return array.length - 1;
        }
        // 因为左右边界都是向下的趋势且相邻数不等，所以中间必有局部最小值。
        int startIndex = 1;
        int endIndex = array.length - 2;
        while (startIndex <= endIndex) {
            int curIndex = (startIndex + endIndex) / 2;
            // 如果curIndex符合局部最小则返回
            if (array[curIndex] < array[curIndex - 1] && array[curIndex] < array[curIndex + 1]) {
                return curIndex;
            } else if (array[curIndex] > array[curIndex - 1]) {
                endIndex = curIndex - 1;
            } else {
                startIndex = curIndex + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int orderarray[] = { 1, 3, 3, 3, 4, 5, 5, 5, 7, 9 };
        int disorderarray[] = { 6, 3, 2, 1, 0, 6, 5, 9 };
        System.out.println(halfMaxNumIndex(orderarray, 6));
        System.out.println(findPartialNum(disorderarray));
    }
}
