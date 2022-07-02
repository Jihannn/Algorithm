/*
 * @Author: Jihan
 * @Date: 2022-04-22 09:35:58
 * @Description: 数组实现队列和栈
 */
public class ArrayToStackAndQueue {
    public static class ArrayToStack {
        int size = 0;
        int maxinum = 0;
        int[] arr = null;

        public ArrayToStack(int maxinum) {
            this.maxinum = maxinum;
            this.arr = new int[maxinum];
        }

        public void push(int num) {
            if (size == maxinum) {
                throw new RuntimeException("栈已满");
            }
            arr[size++] = num;
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("栈已空");
            }
            return arr[--size];
        }
    }

    public static class ArrayToQueue {
        int maxinum = 0;
        int size = 0;
        int addIndex = 0;
        int removeIndex = 0;
        int[] arr = null;

        public ArrayToQueue(int maxinum) {
            this.maxinum = maxinum;
            this.arr = new int[maxinum];
        }

        public void add(int num) {
            if (size == maxinum) {
                throw new RuntimeException("队列已满");
            }
            size++;
            arr[addIndex] = num;
            addIndex = findNextIndex(addIndex);
        }

        public int remove() {
            if (size == 0) {
                throw new RuntimeException("队列为空");
            }
            size--;
            int rtn = arr[removeIndex];
            removeIndex = findNextIndex(removeIndex);
            return rtn;
        }

        // 循环队列，找到下一个下标
        public int findNextIndex(int i) {
            return i < maxinum - 1 ? i + 1 : 0;
        }
    }

    public static void main(String[] args) {
        ArrayToStack stack = new ArrayToStack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        // stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(4);
        stack.push(5);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        // System.out.println(stack.pop());
        System.out.println("======");
        ArrayToQueue queue = new ArrayToQueue(3);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        // queue.add(4);
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        queue.add(4);
        queue.add(5);
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        // System.out.println(queue.remove());
    }
}