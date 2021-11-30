package LinkedList;

/*
 * @Author: Jihan
 * 
 * @Date: 2021-11-29 17:52:18
 * 
 * @Description:给两个可能有环或无环的单向链表。相交则返回相交结点，不相交则返回空。
 */
public class Intersection {
    public class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node createLinkedList(int[] array) {
        if (array == null) {
            return null;
        }
        Node head = new Node(array[0]);
        Node pre = head;
        for (int i = 1; i < array.length; i++) {
            Node node = new Node(array[i]);
            pre.next = node;
            pre = node;
        }
        return head;
    }

    private void addNode(Node first, Node... nodes) {
        if (first == null) {
            return;
        }
        while (first.next != null) {
            first = first.next;
        }
        for (Node node : nodes) {
            first.next = node;
            first = node;
        }
    }

    // 没环则返回null,有环则返回入环处结点
    private Node haveRound(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slowP = head.next;
        Node fastP = head.next.next;
        while (slowP != fastP) {
            // 走到尽头说明无环
            if (fastP == null) {
                return null;
            }
            slowP = slowP.next;
            fastP = fastP.next.next;
        }
        // 相遇后从头开始步长为1,再相遇必定在入环处(数学证明)
        fastP = head;
        while (fastP != slowP) {
            slowP = slowP.next;
            fastP = fastP.next;
        }
        return fastP;
    }

    private Node noRound(Node head1, Node head2) {
        int length = 0;
        // 求两个链表的长度
        Node cur1 = head1;
        while (cur1.next != null) {
            length++;
            cur1 = cur1.next;
        }
        Node cur2 = head2;
        while (cur2.next != null) {
            length--;
            cur2 = cur2.next;
        }
        // 如果最后一个结点不一样则没相交 ||
        if (cur1 != cur2) {
            return null;
        }
        // 求出两者的长度
        cur1 = length > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        length = Math.abs(length);
        // 长的先走长度差值
        while (length != 0) {
            length--;
            cur1 = cur1.next;
        }
        // 然后两者同时走，相等时就为交点 Y
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    private Node bothRound(Node head1, Node entrance1, Node head2, Node entrance2) {
        Node cur1 = head1;
        Node cur2 = head2;
        // 相交圆 YO
        if (entrance1 == entrance2) {
            int length = 0;
            while (cur1.next != entrance1) {
                length++;
                cur1 = cur1.next;
            }
            while (cur2.next != entrance2) {
                length--;
                cur2 = cur2.next;
            }
            cur1 = length > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            length = Math.abs(length);
            while (length != 0) {
                length--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }
        cur1 = entrance1.next;
        // 绕着环一圈找另一个入环点，没遇到说明不相交，遇到说明相交
        while (cur1 != entrance1) {
            // -O-
            if (cur1 == entrance2) {
                // 返回任一入环点都是相交点
                return entrance1;
            }
            cur1 = cur1.next;
        }
        // 66
        return null;
    }

    public void intersectionProcess(Node head1, Node head2) {
        Node entrance1 = haveRound(head1);
        Node entrance2 = haveRound(head2);
        // 两个都无环
        if (entrance1 == null && entrance2 == null) {
            Node result = noRound(head1, head2);
            System.out.println("都无环的相交点为：" + (result == null ? null : result.value));
            return;
        }
        if (entrance1 != null && entrance2 != null) {
            Node result = bothRound(head1, entrance1, head2, entrance2);
            System.out.println("都有环的相交点为：" + (result == null ? null : result.value));
            return;
        }
        System.out.println("一环一非环无相交点");
    }

    public static void main(String[] args) {
        Intersection obj = new Intersection();
        Node node1 = obj.new Node(111);
        Node node2 = obj.new Node(222);
        Node node3 = obj.new Node(333);
        Node node4 = obj.new Node(444);
        Node node5 = obj.new Node(555);
        Node node6 = obj.new Node(666);
        Node node7 = obj.new Node(777);
        // 不相交无环链表 ||
        // Node list1 = obj.createLinkedList(new int[] { 1, 2, 3, 4, 5 });
        // Node list2 = obj.createLinkedList(new int[] { 1, 2, 3, 4, 5 });
        // 相交无环链表 Y
        // Node list3 = obj.createLinkedList(new int[] { 1, 2, 3, 4 });
        // Node list4 = obj.createLinkedList(new int[] { 1, 2 });
        // obj.addNode(list3, node1, node2, node3);
        // obj.addNode(list4, node1);
        // 不相交一环一非环 |O
        // Node list5 = obj.createLinkedList(new int[] { 1, 2, 3, 4 });
        // Node list6 = obj.createLinkedList(new int[] { 1, 2, 3, 4 });
        // obj.addNode(list6, node1, node2, node3, node1);
        // 不相交两环 OO
        // Node list7 = obj.createLinkedList(new int[] { 1, 2, 3, 4 });
        // Node list8 = obj.createLinkedList(new int[] { 1, 2, 3, 4 });
        // obj.addNode(list7, node1, node2, node3, node4, node1);
        // obj.addNode(list8, node5, node6, node7, node5);
        // 相交环 YO
        // Node list9 = obj.createLinkedList(new int[] { 1, 2, 3, 4, 5, 6 });
        // Node list10 = obj.createLinkedList(new int[] { 1, 2, 3, 4 });
        // obj.addNode(list9, node1, node2, node3, node4, node5, node6, node3);
        // obj.addNode(list10, node1);
        // 相交环 -O-
        Node list11 = obj.createLinkedList(new int[] { 1, 2, 3, 4, 5, 6 });
        Node list12 = obj.createLinkedList(new int[] { 1, 2, 3, 4 });
        obj.addNode(list11, node1, node2, node3, node4, node1);
        obj.addNode(list12, node4);
        System.out.println();
        obj.intersectionProcess(list11, list12);
    }
}
