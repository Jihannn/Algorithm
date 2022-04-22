/*
 * @Author: Jihan
 * @Date: 2022-04-21 22:14:40
 * @Description: 链表实现栈和队列
 */
public class LinkedListToStackAndQueue {
    public static class Node{
        int data;
        Node next;
        public Node(int data){
            this.data = data;
        }
    }
    public static class DoubleNode{
        int data;
        DoubleNode pre;
        DoubleNode next;
        public DoubleNode(int data){
            this.data = data;
        }
    }
    public static class DoubleLinkedListToStack{
        DoubleNode head = null;
        
        public void push(DoubleNode node){
            if(head == null){
                head = node;
            }else{
                node.next = head;
                head.pre = node;
                head = node;
            }
        }

        public DoubleNode pop(){
            if(head == null){
                throw new RuntimeException("栈已空！");
            }
            DoubleNode cur = head;
            if(cur.next == null){
                head = null;
            }else{
                head = cur.next;
                head.pre = null;
                cur.next = null;
            }
            return cur;
        }

        public void print(){
            DoubleNode cur = head;
            while(cur != null){
                System.out.print(cur.data+"->");
                cur = cur.next;
            }
            System.out.println();
        }
    }

    public static class LinkedListToQueue{
        Node head = null;
        Node tail = null;
        
        public void add(Node node){
            if(head == null){
                head = node;
                tail = node;
            }else{
                tail.next = node;
                tail = node;
            }
        }

        public Node remove(){
            if(head == null){
                throw new RuntimeException("队列已空！");
            }
            Node rtn = head;
            head = head.next;
            rtn.next = null;
            return rtn;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedListToStack stack = new DoubleLinkedListToStack();
        stack.push(new DoubleNode(1));
        stack.push(new DoubleNode(2));
        stack.push(new DoubleNode(3));
        System.out.println(stack.pop().data); 
        System.out.println(stack.pop().data); 
        System.out.println(stack.pop().data); 
        System.out.println(stack.pop().data); 
        LinkedListToQueue queue = new LinkedListToQueue();
        queue.add(new Node(1));
        queue.add(new Node(2));
        queue.add(new Node(3));
        System.out.println(queue.remove().data); 
        System.out.println(queue.remove().data); 
        System.out.println(queue.remove().data); 
        System.out.println(queue.remove().data); 
    }
}