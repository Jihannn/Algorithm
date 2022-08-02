import java.util.HashSet;

/*
 * @Author: Jihan
 * @Date: 2022-08-02 23:57:18
 * @Description: https://leetcode.cn/problems/happy-number/
 */
public class _202_HappyNumber {
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        int count = 0;
        while (n != 1) {
            while (n != 0) {
                count += Math.pow(n % 10, 2);
                n /= 10;
            }
            if (set.contains(count)) {
                return false;
            }
            set.add(count);
            n = count;
            count = 0;
        }
        return true;
    }
}