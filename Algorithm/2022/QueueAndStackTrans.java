import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * @Author: Jihan
 * @Date: 2022-04-22 10:29:47
 * @Description: 用队列/栈实现栈/队列
 */
public class QueueAndStackTrans {
    public static class QueueToStack{
        Queue<Integer> pushQueue = new LinkedList<>();
        Queue<Integer> popQueue = new LinkedList<>();

        public void push(int num){
            pushQueue.add(num);
        }

        // 保留队尾一个元素，其它元素进入另一个队列
        public int pop(){
            if(pushQueue.isEmpty()){
                return -1;
            }
            int size = pushQueue.size() - 1;
            for (int i = 0; i < size; i++) {
                popQueue.add(pushQueue.remove());
            }
            Queue<Integer> temp = pushQueue;
            pushQueue = popQueue;
            popQueue = temp;
            return popQueue.remove();
        }
    }
    public static class StackToQueue{
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        
        public void add(int num){
            stack1.push(num);
        }

        public int remove(){
            dumpElem();
            return stack2.pop();
        }

        public void dumpElem(){
            if(!stack2.isEmpty()){
                return;
            }
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
    }
    public static void main(String[] args) {
        QueueToStack stack = new QueueToStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop()); 
        System.out.println(stack.pop());
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop()); 
        System.out.println(stack.pop());
        // System.out.println(stack.pop());
        System.out.println("======");
        StackToQueue queue = new StackToQueue();
        queue.add(3);
        queue.add(2);
        queue.add(1);
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        queue.add(2);
        queue.add(1);
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());

    }
}
