/*
 * @Author: Jihan
 * @Date: 2022-07-28 18:33:11
 * @Description: https://leetcode.cn/problems/divide-two-integers/
 */
public class _029_DivideTwoIntegers {

    private int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            // 无进位相加
            sum = a ^ b;
            // 进位信息
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    // 相反数
    private int negNum(int a) {
        // ~a + 1
        return add(~a, 1);
    }

    private int minus(int a, int b) {
        return add(a, negNum(b));
    }

    private int multiply(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) == 1) {
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    private int divi(int a, int b) {
        // 只允许正数相除
        int x = a < 0 ? negNum(a) : a;
        int y = b < 0 ? negNum(b) : b;
        int res = 0;
        for (int i = 31; i >= 0; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return (a < 0 && b > 0) || (a > 0 && b < 0) ? negNum(res) : res;
    }

    public int divide(int dividend, int divisor) {
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == negNum(1)) {
                return Integer.MAX_VALUE;
            }
            int res = divi(add(dividend, 1), divisor);
            return add(res, divi(minus(dividend, multiply(res, divisor)), divisor));
        }
        return divi(dividend, divisor);
    }
}
