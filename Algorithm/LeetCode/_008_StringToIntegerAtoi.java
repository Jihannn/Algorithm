/*
 * @Author: Jihan
 * @Date: 2022-07-11 15:54:30
 * @Description: https://leetcode.cn/problems/string-to-integer-atoi/
 */
public class _008_StringToIntegerAtoi {
    public static int myAtoi(String s) {
		if (s == null || s.equals("")) {
			return 0;
		}
		s = removeHeadZero(s.trim());
		if (s == null || s.equals("")) {
			return 0;
		}
		char[] str = s.toCharArray();
		if (!isValid(str)) {
			return 0;
		}
		// str 是符合日常书写的，正经整数形式
		boolean posi = str[0] == '-' ? false : true;
        // 防止越界
		int minq = Integer.MIN_VALUE / 10;
		int minr = Integer.MIN_VALUE % 10;
		int res = 0;
		int cur = 0;
		for (int i = (str[0] == '-' || str[0] == '+') ? 1 : 0; i < str.length; i++) {
            // 负数形式，使得-2147483648能够正常存储
			cur = '0' - str[i];
            // 防止越界
			if ((res < minq) || (res == minq && cur < minr)) {
				return posi ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
			res = res * 10 + cur;
		}
		// 如果入参为"2147483648"
		if (posi && res == Integer.MIN_VALUE) {
			return Integer.MAX_VALUE;
		}
		return posi ? -res : res;
	}

	public static String removeHeadZero(String str) {
        // 开头字符是否为正负
		boolean r = (str.startsWith("+") || str.startsWith("-"));
        // 如果是则从1位置开始，找到最开始非0字符
		int s = r ? 1 : 0;
		for (; s < str.length(); s++) {
			if (str.charAt(s) != '0') {
				break;
			}
		}
		int e = -1;
        // 从右往左找到最左侧的非数字字符
		for (int i = str.length() - 1; i >= (r ? 1 : 0); i--) {
			if (str.charAt(i) < '0' || str.charAt(i) > '9') {
				e = i;
			}
		}
		return (r ? String.valueOf(str.charAt(0)) : "") + str.substring(s, e == -1 ? str.length() : e);
	}

	public static boolean isValid(char[] chas) {
        // 如果开头非+-且非数字
		if (chas[0] != '-' && chas[0] != '+' && (chas[0] < '0' || chas[0] > '9')) {
			return false;
		}
        // 如果开头是+-且只有本身
		if ((chas[0] == '-' || chas[0] == '+') && chas.length == 1) {
			return false;
		}
		// 上面0位置判断完毕
        // 从1位置开始如果非数字则违规
		for (int i = 1; i < chas.length; i++) {
			if (chas[i] < '0' || chas[i] > '9') {
				return false;
			}
		}
		return true;
	}
}