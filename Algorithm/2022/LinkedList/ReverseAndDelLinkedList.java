package LinkedList;
/*
 * @Author: Jihan
 * @Date: 2022-04-21 21:03:38
 * @Description: 单链表、双链表反转和删除指定值
 */
public class ReverseAndDelLinkedList {
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
    public static void printLinkedList(Node head){
        while(head != null){
            System.out.print(head.data+"->");
            head = head.next;
        }
        System.out.println();
    }
    public static void printDoubleLinkedList(DoubleNode head){
        while(head != null){
            System.out.print(head.data+"->");
            head = head.next;
        }
        System.out.println();
    }
    public static Node createLinkedList(int[] arr){
        Node head = new Node(arr[0]);
        Node cur = head;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new Node(arr[i]);
            cur = cur.next;
        }
        return head;
    }
    public static DoubleNode createDoubleLinkedList(int[] arr){
        DoubleNode head = new DoubleNode(arr[0]);
        DoubleNode cur = head;
        DoubleNode pre = null;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new DoubleNode(arr[i]);
            cur.pre = pre;
            pre = cur;
            cur = cur.next;
        }
        return head;
    }
    public static Node reverseLinkedList(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node pre = null;
        Node next = null;
        Node cur = head;
         while(cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    public static DoubleNode reverseDoubleLinkedList(DoubleNode head){
        if(head == null || head.next == null){
            return head;
        }
        DoubleNode pre = null;
        DoubleNode cur = head;
        while(cur != null){
            cur.pre = cur.next;
            cur.next = pre;
            pre = cur;
            cur = cur.pre;
        }
        return pre;
    }
    public static Node delLinkedList(Node head,int num){
        // 若需要删除的值为头结点，则先找到非该值的结点以便可以返回新头结点
        while(head != null){
            if(head.data != num){
                break;
            }
            head = head.next;
        }
        Node cur = head;
        Node pre = head;
        while(cur != null){
            if(cur.data != num){
                pre = cur;
            }else{
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int arr[] = {6,5,4,6,3,2,2,4,1};
        Node head = createLinkedList(arr);
        printLinkedList(head);
        // printLinkedList(reverseLinkedList(head));
        printLinkedList(delLinkedList(head,6));

        DoubleNode head2 = createDoubleLinkedList(arr);
        printDoubleLinkedList(head2);
        printDoubleLinkedList(reverseDoubleLinkedList(head2));
    }
}