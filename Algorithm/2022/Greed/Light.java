package Greed;

/*
 * @Author: Jihan
 * @Date: 2022-05-09 08:25:35
 * @Description: 
 * 给定一个字符串str，只由'X'和'.'两种字符构成
 * 'X'表示墙，不能放灯，也不需要点亮；'.'表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class Light {
    public static int light(String strs) {
        if (strs == null) {
            return 0;
        }
        int index = 0;
        int light = 0;
        char[] c = strs.toCharArray();
        while (index < c.length) {
            if (c[index] == 'X') {
                index++;
            } else {
                light++;
                if (index + 1 < c.length && c[index + 1] == '.') {
                    index = index + 3;
                } else {
                    index++;
                }
            }
        }
        return light;
    }
}
