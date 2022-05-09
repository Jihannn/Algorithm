package LinkedList;
import java.util.ArrayList;

/*
 * @Author: Jihan
 * @Date: 2022-04-29 11:26:48
 * @Description: 给定一个单链表的头节点head，给定一个整数n，将链表按n划分成左边<n、中间==n、右边>n
 */
public class SplitLinkedList {
    public static class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node right(Node head, int num) {
        if (head == null) {
            return head;
        }
        // 拷贝到数组
        ArrayList<Node> list = new ArrayList<>();
        Node node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        Node[] arr = new Node[list.size()];
        list.toArray(arr);
        // 数组中做划分
        partition(arr,num);
        // 根据数组的排序串联链表
        int i = 0;
        while (i < arr.length - 1) {
            arr[i].next = arr[++i];
        }
        arr[i].next = null;
        return arr[0];
    }

    public static void partition(Node[] arr,int num) {
        int left = -1;
        int right = arr.length;
        int cur = 0;
        while (cur < right) {
            if (arr[cur].value > num) {
                swap(arr, cur, --right);
            } else if (arr[cur].value < num) {
                swap(arr, cur++, ++left);
            } else {
                cur++;
            }
        }
    }

    public static Node splitLinkedList(Node head,int num){
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node bH = null;
        Node bT = null;
        while(head != null){
            if(head.value < num){
                if(sH == null){
                    sH = sT = head;
                }else{
                    sT.next = head;
                    sT = head;
                }
            }else if(head.value == num){
                if(eH == null){
                    eH = eT = head;
                }else{
                    eT.next = head;
                    eT = head;
                }
            }else{
                if(bH == null){
                    bH = bT = head;
                }else{
                    bT.next = head;
                    bT = head;
                }
            }
            head = head.next;
        }
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
		if (sT != null) { // 如果有小于区域
			sT.next = eH;
			eT = eT == null ? sT : eT; // 下一步，谁去连大于区域的头，谁就变成eT
		}
		// 下一步，一定是需要用eT 去接 大于区域的头
		// 有等于区域，eT -> 等于区域的尾结点
		// 无等于区域，eT -> 小于区域的尾结点
		// eT 尽量不为空的尾巴节点
		if (eT != null) { // 如果小于区域和等于区域，不是都没有
			eT.next = bH;
		}
		return sH != null ? sH : (eH != null ? eH : bH);
    }

    public static void swap(Node[] arr, int i, int j) {
        Node temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static Node createRandomLinkedList(int maxLen,int maxValue) {
        Node root = new Node(0);
        int len = (int) (Math.random() * maxLen);
        Node node = root;
        while (len > 0) {
            node.next = new Node((int) (Math.random() * maxValue));
            node = node.next;
            len--;
        }
        return root;
    }

    public static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node head = createRandomLinkedList(100, 100);
        Node node1 = right(head,50);
        printLinkedList(node1);
        Node node2 = splitLinkedList(head,50);
        printLinkedList(node2);
    }
}