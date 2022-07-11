/*
 * @Author: Jihan
 * @Date: 2022-07-11 15:18:42
 * @Description: https://leetcode.cn/problems/reverse-integer/
 */
public class _007_ReverseInteger {
    public int reverse(int x) {
        // 转为负数计算
        boolean neg = (x >>> 31) == 1;
        x = neg ? x : -x;
        int rtn = 0;
        // 用于越界判断
        int min = Integer.MIN_VALUE / 10;
        int remainder = Integer.MIN_VALUE % 10;
        while (x != 0) {
            // 越界处理
            // rtn > min 则下面 rtn * 10 导致越界
            // rtn == min 则下面 (x % 10) 导致越界
            if (rtn < min || (rtn == min && x % 10 < remainder)) {
                return 0;
            }
            rtn = rtn * 10 + (x % 10);
            x /= 10;
        }
        return neg ? rtn : Math.abs(rtn);
    }

    // 空间复杂度高
    public int reverse2(int x) {
        if (x == 0) {
            return 0;
        }
        int digit = getDigit(x);
        if (digit == 1) {
            return x;
        }
        boolean isMinus = false;
        if (x < 0) {
            isMinus = true;
            x = ~x + 1;
        }
        char[] xArr = String.valueOf(x).toCharArray();
        if (digit == 10) {
            char[] maxArr = String.valueOf(Integer.MAX_VALUE).toCharArray();
            maxArr[9] += isMinus ? 1 : 0;
            int xCur = 9;
            int mCur = 0;
            while (xCur >= 0) {
                if (xArr[xCur] < maxArr[mCur]) {
                    break;
                } else if (xArr[xCur] == maxArr[mCur]) {
                    xCur--;
                    mCur++;
                    continue;
                } else {
                    return 0;
                }
            }
        }
        int left = 0;
        int right = xArr.length - 1;
        while (right > left) {
            char temp = xArr[right];
            xArr[right] = xArr[left];
            xArr[left] = temp;
            right--;
            left++;
        }
        int rtn = Integer.valueOf(String.valueOf(xArr));
        return isMinus ? ~rtn + 1 : rtn;
    }

    private int getDigit(int x) {
        int digit = 0;
        while (x != 0) {
            x /= 10;
            digit++;
        }
        return digit;
    }
}
