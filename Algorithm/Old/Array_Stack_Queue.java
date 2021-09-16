package com.jihan.algorithm;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * Created by Jihan on 2019/7/8
 */
public class Array_Stack_Queue {

    public class ArrayToStack {

        private int[] list;
        private int index = 0;

        public ArrayToStack(int stackSize) {
            if (stackSize < 0) {
                throw new IllegalArgumentException("初始化长度小于0");
            }
            list = new int[stackSize];
        }

        public void push(int num) {
            if (index == list.length) {
                throw new ArrayIndexOutOfBoundsException("栈已满");
            }
            list[index++] = num;
        }

        public int pop() {
            if (list.length == 0) {
                throw new ArrayIndexOutOfBoundsException("栈内无数字");
            }
            return list[--index];
        }

        public int peek() {
            if (list.length == 0) {
                throw new ArrayIndexOutOfBoundsException("栈内无数字");
            }
            return list[index - 1];
        }
    }

    public class ArrayToQueue {

        private int[] list;
        private int head = 0;
        private int tail = 0;
        private int listSize = 0;
        private int queueSize;

        public ArrayToQueue(int queueSize) {
            if(queueSize < 0){
                throw new IllegalArgumentException("初始化长度小于0");
            }
            list = new int[queueSize];
            this.queueSize = queueSize;
        }

        public void add(int num) {
            if (listSize == queueSize) {
                throw new ArrayIndexOutOfBoundsException("队列已满");
            }
            list[head] = num;
            head = head == queueSize - 1 ? 0 : head++;
            listSize++;
        }

        public int poll() {
            if (listSize == 0) {
                throw new ArrayIndexOutOfBoundsException("队列内无数字");
            }
            int temp = list[tail];
            tail = tail == queueSize - 1 ? 0 : tail++;
            listSize--;
            return temp;
        }

        public int peek() {
            if (listSize == 0) {
                throw new ArrayIndexOutOfBoundsException("队列内无数字");
            }
            return list[tail];
        }
    }

    public class StackToQueue {

        private Stack<Integer> data;
        private Stack<Integer> help;
        private int queueSize;

        public StackToQueue(int queueSize) {
            data = new Stack<>();
            help = new Stack<>();
            data.setSize(queueSize);
            help.setSize(queueSize);
            this.queueSize = queueSize;
        }

        public void add(int num) {
            if (data.size() == queueSize) {
                throw new ArrayIndexOutOfBoundsException("队列已满");
            }
            data.push(num);
        }

        public int poll() {
            if (data.isEmpty()) {
                throw new ArrayIndexOutOfBoundsException("队列为空");
            }
            swap();
            return data.pop();
        }

        public int peek() {
            if (data.isEmpty()) {
                throw new ArrayIndexOutOfBoundsException("队列为空");
            }
            swap();
            return data.peek();
        }

        private void swap() {
            if (help.isEmpty() && !data.isEmpty()) {
                while (data.size() > 1) {
                    help.push(data.pop());
                }
            }
        }
    }

    public class QueueToStack{

        private Queue<Integer> data;
        private Queue<Integer> help;

        public QueueToStack(){
            data = new LinkedList<>();
            help = new LinkedList<>();
        }

        public void push(int num){
            data.add(num);
        }

        public int peek(){
            if(data.isEmpty()){
                throw new ArrayIndexOutOfBoundsException("队列为空");
            }
            while(data.size() != 1){
                help.add(data.poll());
            }
            int temp = data.poll();
            help.add(temp);
            swap();
            return temp;
        }

        public int pop(){
            if(data.isEmpty()){
                throw new ArrayIndexOutOfBoundsException("队列为空");
            }
            while(data.size() != 1){
                help.add(data.poll());
            }
            int temp = data.poll();
            swap();
            return temp;
        }

        private void swap() {
            Queue<Integer> temp = help;
            help = data;
            data = temp;
        }
    }
}
