import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * @Author: Jihan
 * @Date: 2022-05-25 08:35:33
 * @Description: 单调栈
 */
public class MonotonousStack {

    public static int[][] getNearLess2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        int N = arr.length;
        int cur = 0;
        int[][] rtn = new int[N][2];
        ArrayDeque<List<Integer>> stack = new ArrayDeque<>();
        while (cur < N) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[cur]) {
                List<Integer> list = stack.pop();
                List<Integer> preList = stack.peek();
                for (Integer i : list) {
                    rtn[i][0] = preList == null ? -1 : preList.get(preList.size() - 1);
                    rtn[i][1] = cur;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[cur]) {
                stack.peek().add(cur);
            } else {
                ArrayList<Integer> newList = new ArrayList<>();
                newList.add(cur);
                stack.push(newList);
            }
            cur++;
        }
        while (!stack.isEmpty()) {
            List<Integer> list = stack.pop();
            List<Integer> preList = stack.peek();
            for (Integer i : list) {
                rtn[i][0] = preList == null ? -1 : preList.get(preList.size() - 1);
                rtn[i][1] = -1;
            }
        }
        return rtn;
    }

    public static int[][] getNearLess(int[] arr) {
        if (arr == null) {
            return null;
        }
        int N = arr.length;
        int[][] rtn = new int[N][2];
        ArrayDeque<LinkedList<Integer>> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek().getLast()]) {
                LinkedList<Integer> list = stack.pop();
                if (arr[i] == arr[list.peekLast()]) {
                    list.addLast(i);
                    stack.push(list);
                    break;
                } else {
                    while (!list.isEmpty()) {
                        int index = list.removeLast();
                        rtn[index][0] = stack.isEmpty() ? -1 : stack.peek().getLast();
                        rtn[index][1] = i;
                    }
                }
            }
            if (stack.isEmpty() || arr[i] != arr[stack.peek().getLast()]) {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            LinkedList<Integer> list = stack.pop();
            while (!list.isEmpty()) {
                int index = list.removeLast();
                rtn[index][0] = stack.isEmpty() ? -1 : stack.peek().getLast();
                rtn[index][1] = -1;
            }
        }
        return rtn;
    }

    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void printArray2(int[][] arr) {
        System.out.println("=======");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + "=>" + arr[i][0] + "|" + arr[i][1]);
        }
        System.out.println("=======");
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr2 = getRandomArray(size, max);
            int[][] ans1 = getNearLess(arr2);
            int[][] ans2 = rightWay(arr2);
            int[][] ans3 = getNearLess2(arr2);
            if (!isEqual(ans1, ans2) || !isEqual(ans1, ans3)) {
                System.out.println("Oops!");
                printArray(arr2);
                printArray2(ans1);
                printArray2(ans2);
                printArray2(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }
}