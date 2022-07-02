import java.util.Stack;

/*
 * @Author: Jihan
 * @Date: 2022-04-22 10:11:37
 * @Description: 栈结构实现得到栈内最小元素
 */
public class SpecialGetMinStack {
    public static class GetMinStack {
        Stack<Integer> normalStack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();

        public void push(int num) {
            normalStack.push(num);
            if (minStack.isEmpty()) {
                minStack.push(num);
            } else {
                if (num < minStack.peek()) {
                    minStack.push(num);
                } else {
                    minStack.push(minStack.peek());
                }
            }
        }

        public int pop() {
            minStack.pop();
            return normalStack.pop();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        GetMinStack stack = new GetMinStack();
        stack.push(3);
        System.out.println(stack.getMin());
        stack.push(2);
        System.out.println(stack.getMin());
        stack.push(1);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        stack.push(1);
        System.out.println(stack.getMin());
    }
}