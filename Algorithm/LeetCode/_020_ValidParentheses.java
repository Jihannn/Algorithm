import java.util.ArrayDeque;

/*
 * @Author: Jihan
 * @Date: 2022-07-20 17:38:41
 * @Description: https://leetcode.cn/problems/valid-parentheses/
 */
public class _020_ValidParentheses {
    public static boolean isValid(String s) {
        char[] str = s.toCharArray();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(' || str[i] == '{' || str[i] == ']') {
                stack.push(str[i]);
            } else {
                if (stack.isEmpty() || !check(str[i], stack.pop())) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean check(char right, char left) {
        switch (right) {
            case ')':
                return left == '(';
            case '}':
                return left == '{';
            case ']':
                return left == '[';
            default:
                return false;
        }
    }
}
