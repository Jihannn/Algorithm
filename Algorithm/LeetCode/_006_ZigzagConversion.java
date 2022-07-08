/*
 * @Author: Jihan
 * @Date: 2022-07-08 20:20:07
 * @Description: https://leetcode.cn/problems/zigzag-conversion/
 */
public class _006_ZigzagConversion {
    // 1         11         21
    // 2      10 12       20
    // 3     9   13     19
    // 4   8     14   18
    // 5 7       15 17 
    // 6         16
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() < numRows) {
            return s;
        }
        // 找规律，numRows和最大间隔关系为 (numRows - 1) * 2
        int maxGap = (numRows - 1) << 1;
        char[] originArr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int curGap = maxGap;
        int curIndex = 0;
        for (int row = 0; row < numRows; row++) {
            // 根据规律，每行最初间隔依次减少2，直到最后一行
            curGap = row == numRows - 1 ? maxGap : maxGap - 2 * row;
            curIndex = row;
            // 添加每行开头的字符
            sb.append(originArr[curIndex]);
            // 越界则到下一行
            while (curIndex + curGap < originArr.length) {
                sb.append(originArr[curIndex + curGap]);
                curIndex += curGap;
                // 根据规律，除了开头和结尾之外 每行的间隔变换为 curGap -> maxGap - curGap -> curGap
                curGap = curGap == maxGap ? maxGap : maxGap - curGap;
            }
        }
        return sb.toString();
    }
}
