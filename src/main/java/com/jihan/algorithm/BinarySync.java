package com.jihan.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Jihan on 2019/7/22
 */
public class BinarySync {
    private class Node{
        private Node left;
        private Node right;
        private Object data;

        public Node(Object data) {
            this.data = data;
        }
    }

    public static String frontSerial(Node head){
        if(head == null){
            return "#_";
        }

        String tree = head.data.toString()+"_";
        tree += frontSerial(head.left);
        tree += frontSerial(head.right);

        return tree;
    }

    public Node frontParse(String tree){
        if(tree.isEmpty()){
            return null;
        }
        String[] str = tree.split("_");
        Queue<String> queue = new LinkedList<>();
        for (String s : str) {
            queue.add(s);
        }
        return parseStr(queue);
    }

    private Node parseStr(Queue<String> queue) {
        String str = queue.poll();

        if(str.equals("#")){
            return null;
        }

        Node head = new Node(str);
        head.left = parseStr(queue);
        head.right = parseStr(queue);

        return head;
    }
}
