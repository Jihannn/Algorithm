package LinkedList;

/*
 * @Author: Jihan
 * @Date: 2022-04-29 08:37:20
 * @Description: 快慢指针边界
 * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 */
public class FastSlowPoint {
    public static class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node oddMidEvenPreMid(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node oddMidEvenLastMid(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node oddMidPreAndEvenPreMidPre(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node fast = head.next.next;
        Node slow = head.next;
        Node pre = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return pre;
    }

    public static Node oddMidPreAndEvenLastMidPre(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node fast = head.next.next;
        Node slow = head.next;
        Node pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return pre;
    }

    // test
    public static Node createRandomLinkedList(int maxLen) {
        Node root = new Node(0);
        int len = (int) (Math.random() * maxLen);
        int value = 1;
        Node node = root;
        while (len > 0) {
            node.next = new Node(value++);
            node = node.next;
            len--;
        }
        return root;
    }

    // test
    public static Node right(Node head, int type) {
        if (head == null || head.next == null) {
            return head;
        }
        int size = 0;
        Node node = head;
        while (node != null) {
            size++;
            node = node.next;
        }
        int mid = 0;
        switch (type) {
            // 偶上中,奇中
            case 1:
                if (size % 2 == 0) {
                    mid = size / 2 - 1;
                } else {
                    mid = size / 2;
                }
                break;
            case 2:
                // 偶下中,奇中
                mid = size / 2;
                break;
            // 偶上中前,奇中前
            case 3:
                if (size % 2 == 0) {
                    mid = size / 2 - 2;
                } else {
                    mid = size / 2 - 1;
                }
                break;
            // 偶下中前,奇中前
            case 4:
                mid = size / 2 - 1;
                break;
        }
        node = head;
        for (int i = 0; i < mid; i++) {
            node = node.next;
        }
        return node;
    }

    public static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int listLen = 100;
        System.out.println("test start");
        for (int i = 0; i < testTime; i++) {
            Node head = createRandomLinkedList(listLen);
            // int right = right(head,1).value;
            // int rtn = oddMidEvenPreMid(head).value;
            // int right = right(head,2).value;
            // int rtn = oddMidEvenLastMid(head).value;
            // int right = right(head,3).value;
            // int rtn = oddMidPreAndEvenPreMidPre(head).value;
            int right = right(head, 4).value;
            int rtn = oddMidPreAndEvenLastMidPre(head).value;
            if (right != rtn) {
                System.out.println("Error");
                System.out.println(right);
                System.out.println(rtn);
                printLinkedList(head);
                break;
            }
        }
        System.out.println("test finish");
    }
}
