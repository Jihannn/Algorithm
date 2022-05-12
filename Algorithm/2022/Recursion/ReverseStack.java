package Recursion;

import java.util.Stack;

/*
 * @Author: Jihan
 * @Date: 2022-05-12 13:37:14
 * @Description: 给定一个栈，请逆序这个栈，不能申请额外的数据结构，只能使用递归函数
 */
public class ReverseStack {
    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int last = getLast(stack);
        reverse(stack);
        stack.push(last);
    }

    private static Integer getLast(Stack<Integer> stack){
        int rtn = stack.pop();
        if(stack.isEmpty()){
            return rtn;
        }
        int last = getLast(stack);
        stack.push(rtn);
        return last;
    }
}
