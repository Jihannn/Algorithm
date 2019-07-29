package com.jihan.algorithm;

/**
 * Created by Jihan on 2019/7/27
 */
public class CompleteBinaryCount {

    public static class Node {
        private Node left;
        private Node right;
        private Integer value;

        public Node(Integer value) {
            this.value = value;
        }
    }

    public static int completeBinaryCount(Node head) {
        if (head == null) {
            return 1;
        }
        return process(head,1,findLeftMax(head,1));
    }

    private static int process(Node node, int level, int heightMax) {
        if(level == heightMax){
            return 1;
        }
        if(findLeftMax(node.right,level + 1) == heightMax){
            return (1 << (heightMax - level)) + process(node.right,level+1,heightMax);
        }else{
            return (1 << (heightMax - level - 1)) + process(node.left,level+1,heightMax);
        }
    }

    private static int findLeftMax(Node head, int level) {
        while (head.left != null) {
            level++;
            head = head.left;
        }
        return level;
    }

}
