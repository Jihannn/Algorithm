package Greed;
import java.util.Arrays;
import java.util.Comparator;

/*
 * @Author: Jihan
 * @Date: 2022-05-09 08:53:33
 * @Description: 
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲，给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多，返回最多的宣讲场次
 */
public class BestArrange {
    public static class Meeting{
        int start;
        int end;
    }

    public static class MeetingComparator implements Comparator<Meeting>{
        @Override
        public int compare(BestArrange.Meeting o1, BestArrange.Meeting o2) {
            return o1.end - o2.end;
        }
    }

    public static int arrange(Meeting[] meetings){
        if(meetings == null || meetings.length < 1){
            return 0;
        }
        Arrays.sort(meetings,new MeetingComparator());
        int lastEnd = 0;
        int times = 0;
        for (int i = 0; i < meetings.length; i++) {
            if(meetings[i].start >= lastEnd){
                lastEnd = meetings[i].end;
                times++;
            }
        }
        return times;
    }

    public static void main(String[] args) {
        
    }
}
