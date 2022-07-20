import java.util.ArrayList;
import java.util.List;

/*
 * @Author: Jihan
 * @Date: 2022-07-20 16:13:11
 * @Description: https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 */
public class _017_LetterCombinationsOfAPhoneNumber {

    public static final char[][] map = new char[][] {
            { 'a', 'b', 'c' },
            { 'd', 'e', 'f' },
            { 'g', 'h', 'i' },
            { 'j', 'k', 'l' },
            { 'm', 'n', 'o' },
            { 'p', 'q', 'r', 's' },
            { 't', 'u', 'v' },
            { 'w', 'x', 'y', 'z' } };

    public List<String> letterCombinations(String digits) {
        ArrayList<String> rtnList = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return rtnList;
        }
        char[] str = digits.toCharArray();
        StringBuilder sb = new StringBuilder();
        process(0, str, rtnList, sb);
        return rtnList;
    }

    private void process(int index, char[] str, ArrayList<String> rtnList, StringBuilder sb) {
        if (index == str.length) {
            rtnList.add(sb.toString());
            return;
        }
        char[] letter = map[str[index] - '2'];
        for (int i = 0; i < letter.length; i++) {
            process(index + 1, str, rtnList, sb.append(letter[i]));
            sb.deleteCharAt(index);
        }
    }

}
