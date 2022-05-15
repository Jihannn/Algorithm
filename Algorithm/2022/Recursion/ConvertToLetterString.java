package Recursion;

/*
 * @Author: Jihan
 * @Date: 2022-05-15 13:13:03
 * @Description: 
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class ConvertToLetterString {
    public static int covert(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        char[] c = str.toCharArray();
        return process(0, c);
    }

    private static int process(int index, char[] str) {
        if (index == str.length) {
            return 1;
        }
        if (str[index] == '0') {
            return 0;
        }
        int p1 = process(index + 1, str);
        int p2 = 0;
        if (index + 1 < str.length && (str[index] - '0') * 10 + str[index + 1] - '0' <= 26) {
            p2 = process(index + 2, str);
        }
        return p1 + p2;
    }

    public static int dp(String str){
        if(str == null || str.length() < 1){
            return 0;
        }
        char[] s = str.toCharArray();
        int N = s.length;
        int[] dp = new int[N+1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if(s[i] != '0'){
                int p1 = dp[i + 1];
                int p2 = 0;
                if(i + 1 < N && (s[i] - '0') * 10 + (s[i+1] - '0') <= 26){
                    p2 = dp[i + 2];
                }
                dp[i] = p1 + p2;
            }
        }
        return dp[0];
    }

	public static String randomString(int len) {
		char[] str = new char[len];
		for (int i = 0; i < len; i++) {
			str[i] = (char) ((int) (Math.random() * 10) + '0');
		}
		return String.valueOf(str);
	}

	public static void main(String[] args) {
		int N = 30;
		int testTime = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * N);
			String s = randomString(len);
			int ans0 = covert(s);
			int ans1 = dp(s);
			if (ans0 != ans1) {
				System.out.println(s);
				System.out.println(ans0);
				System.out.println(ans1);
				System.out.println("Oops!");
				break;
			}
		}
		System.out.println("测试结束");
	}
}