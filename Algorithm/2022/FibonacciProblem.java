/*
 * @Author: Jihan
 * @Date: 2022-05-29 19:10:29
 * @Description: 
 */
public class FibonacciProblem {
    // 斐波那契数列矩阵乘法方式的实现
    public static int fibonacci1(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci1(n - 1) + fibonacci1(n - 2);
    }

    public static int fibonacci2(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = { { 1, 1 }, { 1, 0 } };
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }

    private static int[][] matrixPower(int[][] base, int power) {
        int[][] res = new int[base.length][base[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] count = base;
        while (power > 0) {
            if ((power & 1) != 0) {
                res = matrixMulti(res, count);
            }
            count = matrixMulti(count, count);
            power >>= 1;
        }
        return res;
    }

    private static int[][] matrixMulti(int[][] a, int[][] b) {
        int aCbR = a[0].length;
        int aR = a.length;
        int bC = b[0].length;
        int[][] res = new int[aR][bC];
        for (int i = 0; i < aR; i++) {
            for (int j = 0; j < bC; j++) {
                for (int k = 0; k < aCbR; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }

    // 一个人可以一次往上迈1个台阶，也可以迈2个台阶，返回迈上N级台阶的方法数
    public static int getUpstairMethod(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[][] base = { { 1, 1 }, { 1, 0 } };
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    /**
     * 奶牛生小牛问题
     * 第一年农场有1只成熟的母牛A，往后的每年：
     * 1）每一只成熟的母牛都会生一只母牛
     * 2）每一只新出生的母牛都在出生的第三年成熟
     * 3）每一只母牛永远不会死
     * 返回N年后牛的数量
     */
    public static int getCowNumber(int n) {
        if (n <= 4) {
            return n;
        }
        int[][] base = {
                { 1, 1, 0 },
                { 0, 0, 1 },
                { 1, 0, 0 } };
        int[][] res = matrixPower(base, n - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }

    /**
     * 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串
     * 如果某个字符串，任何0字符的左边都有1紧挨着，认为这个字符串达标
     * 返回有多少达标的字符串
     */
    public static int getNumber1(int n) {
        if (n < 1) {
            return 0;
        }
        return process(1, n);
    }

    private static int process(int i, int n) {
        if (i == n - 1) {
            return 2;
        }
        if (i == n) {
            return 1;
        }
        return process(i + 1, n) + process(i + 2, n);
    }

    public static int getNumber2(int n) {
        if (n < 1) {
            return 0;
        }
        int[][] base = { { 1, 1 }, { 1, 0 } };
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    public static void main(String[] args) {
        System.out.println(fibonacci1(13));
        System.out.println(fibonacci2(13));
    }
}