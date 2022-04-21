/*
 * @Author: Jihan
 * @Date: 2022-04-21 13:46:23
 * @Description: 异或运算：相同为0，不同为1(无进位相加)
 * 1.不用额外变量交换两个数
 * 2.一个数组中有一种数出现了奇数次，其它种数都出现了偶数次，求解奇数次的数
 * 3.一个数组中有两种数出现了奇数次，其它种数都出现了偶数次，求解奇数次的数
 * 4.一个数组中有一种数出现K次，其它种数出现M次，求出现K次的数。M > 1,K < M,空间复杂度O(1),时间复杂度O(N)
 */
public class ExcusiveOR {
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

    // i和j的内存地址不能相同
    public static void swap(int[] arr,int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    // 2.
    public static void findOddNum1(int arr[]){
        int eor = 0;
        for (int i : arr) {
            eor ^= i;
        }
        System.out.println(eor);
    }

    // 3.
    public static void findOddNum2(int arr[]){
        // 数组中所有数组异或，eor结果为两奇数a^b
        int eor = 0;
        for (int i : arr) {
            eor ^= i;
        }
        // 根据异或运算定义，因为a^b二进制位中有1，说明两数的相同位的数必然不同
        // 获得二进制位最右侧的1
        int rightOne = eor & (~eor + 1);
        // 根据rightOne将数组中分为两类，一类为有1的，一类为无1的，即可将a,b区分开
        int eor2 = 0;
        for (int i : arr) {
            if((i & rightOne) != 0){
                eor2 ^= i;
            }
        }
        System.out.println(eor2+","+(eor^eor2));
    }

    // 4.
    public static void findKNum(int arr[],int k,int m){
        // 整型二进制位为32位
        int[] digits = new int[32];
        // 如果二进制位上为1则累加到digits
        for (int i : arr) {
            for (int j = 0; j < 32; j++) {
                digits[j] += (i >> j) & 1;
            }
        }
        int num = 0;
        // 二进制位上的数%m存在余数，这说明出现k次数的数在该位置上为1
        for (int i = 0;i < digits.length;i++) {
            if(digits[i] % m != 0){
                num |= (1 << i);
            }
        }
        System.out.println(num);
    }

    public static void main(String[] args) {
        int arr[] = {7,-2,-2,3,3,7,7};
        findOddNum1(arr);
        int arr2[] = {7,-2,-2,3,3,7,7,8,3,8};
        findOddNum2(arr2);
        int arr3[] = {7,8,3,8,7,3,3,7,8,7,8,3,-2,-2};
        findKNum(arr3, 2, 4);
    }
}
