package com.jihan.algorithm.剑指offer;

import java.util.Stack;

/**
 * Created by Jihan on 2019/8/4
 *
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 */
public class 包含min函数的栈 {

    private Stack<Integer> normal = new Stack<>();
    private Stack<Integer> min = new Stack<>();

    public void push(int node) {
        if(min.isEmpty()){
            normal.push(node);
            min.push(node);
            return;
        }
        normal.push(node);
        min.push(min.peek() <= node ? min.peek() : node);
    }

    public void pop() {
        if(!normal.isEmpty()){
            min.pop();
            normal.pop();
        }
    }

    public int top() {
        if(normal.isEmpty()){
            throw new RuntimeException("栈内无元素");
        }
        min.pop();
        return normal.pop();
    }

    public int min() {
        if(min.isEmpty()){
            throw new RuntimeException("栈内无元素");
        }
        return min.peek();
    }
}
