/*
 * @Author: Jihan
 * @Date: 2022-05-12 11:32:41
 * @Description: 打印n层汉诺塔从最左边移动到最右边的全部过程
 */
package Recursion;

public class Hanoi {
    public static void hanoi(int n) {
        if (n > 0) {
            System.out.println("移动次数：" + rec(n, "Left", "Right", "Middle"));
        }
    }

    private static int rec(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println(from + "-to-" + to);
            return 1;
        } else {
            int times1 = rec(n - 1, from, other, to);
            System.out.println(from + "-to-" + to);
            int times2 = rec(n - 1, other, to, from);
            return times1 + times2 + 1;
        }
    }

    public static void main(String[] args) {
        hanoi(11);
    }
}
