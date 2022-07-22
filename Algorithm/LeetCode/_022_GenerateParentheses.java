import java.util.ArrayList;
import java.util.List;

/*
 * @Author: Jihan
 * @Date: 2022-07-22 16:22:29
 * @Description: https://leetcode.cn/problems/generate-parentheses/submissions/
 */
public class _022_GenerateParentheses {
    private ArrayList<String> rtnList;

    public List<String> generateParenthesis(int n) {
        rtnList = new ArrayList<>();
        process(n, n, "");
        return rtnList;
    }

    private void process(int l, int r, String s) {
        if (l == 0 && r == 0) {
            rtnList.add(s);
            return;
        }
        if (l > 0) {
            process(l - 1, r, s + "(");
        }
        if (r > l) {
            process(l, r - 1, s + ")");
        }
    }
}
