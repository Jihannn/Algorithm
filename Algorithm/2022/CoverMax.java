import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * @Author: Jihan
 * @Date: 2022-04-27 09:44:44
 * @Description: 
 * 给定很多线段，每个线段都有两个数[start, end]，表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>1
 * 返回：
 * 线段最多重合区域中，包含了几条线段
 */
public class CoverMax {
    public static int coverMax1(int[][] lines) {
        if (lines == null || lines.length < 2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // 求出线段的范围
        for (int[] l : lines) {
            min = Math.min(min, l[0]);
            max = Math.max(max, l[1]);
        }
        int maxCoverCount = 0;
        for (double i = min + 0.5; i < max; i += 1) {
            int coverCount = 0;
            for (int[] s : lines) {
                if (s[0] < i && s[1] > i) {
                    coverCount++;
                }
            }
            maxCoverCount = Math.max(coverCount, maxCoverCount);
        }
        return maxCoverCount;
    }

    public static class Line {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class LineComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static int coverMax2(int[][] lines) {
        if (lines == null || lines.length < 2) {
            return 0;
        }
        // 对线段进行排序
        Line[] lineArr = new Line[lines.length];
        for (int i = 0; i < lines.length; i++) {
            lineArr[i] = new Line(lines[i][0], lines[i][1]);
        }
        Arrays.sort(lineArr, new LineComparator());
        // 上面代码也可以使用非类的方式排序
        // Arrays.sort(lines,(a,b) -> (a[0] - b[0]));
        int maxCoverCount = 0;
        // 小根堆中放线段的end
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (Line line : lineArr) {
            while (!minHeap.isEmpty() && minHeap.peek() <= line.start) {
                minHeap.poll();
            }
            minHeap.add(line.end);
            maxCoverCount = Math.max(minHeap.size(), maxCoverCount);
        }
        return maxCoverCount;
    }

    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("test start");
        int N = 100;
        int L = 2;
        int R = 255;
        int times = 100000;
        for (int i = 0; i < times; i++) {
            int[][] linesArr = generateLines(N, L, R);
            int cover1 = coverMax1(linesArr);
            int cover2 = coverMax2(linesArr);
            if (cover1 != cover2) {
                System.out.println("error");
                System.out.println(cover1);
                System.out.println(cover2);
                break;
            }
        }
        System.out.println("test end");
    }
}