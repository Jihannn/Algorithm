package Other.Greedy;

import java.util.Arrays;
import java.util.Comparator;

/*
 * @Author: Jihan
 * 
 * @Date: 2021-12-08 17:10:19
 * 
 * @Description:安排会议，有开始结束时间，怎么样安排使得开会议次数最多
 */
public class BestArrange {
    public static class Program {
        public int start;
        public int end;
    }

    private static class ProgramComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static int bestArrange(Program[] programs, int timePoint) {
        Arrays.sort(programs, new ProgramComparator());
        int result = 0;
        for (Program program : programs) {
            if (timePoint <= program.start) {
                result++;
                timePoint = program.end;
            }
        }
        return result;
    }
}