/*
 * @Author: Jihan
 * @Date: 2022-08-01 15:07:07
 * @Description: 
 */
public class _707_DesignLinkedList {
    class MyLinkedList {
        public class Node {
            int val;
            Node next;

            public Node() {
            }

            public Node(int val) {
                this.val = val;
            }
        }

        Node head;
        int size;

        public MyLinkedList() {
            head = new Node();
            size = 0;
        }

        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }
            int i = 0;
            Node cur = head.next;
            while (cur != null) {
                if (i == index) {
                    return cur.val;
                }
                cur = cur.next;
                i++;
            }
            return -1;
        }

        public void addAtHead(int val) {
            Node node = new Node(val);
            node.next = head.next;
            head.next = node;
            size++;
        }

        public void addAtTail(int val) {
            Node node = new Node(val);
            Node cur = head.next;
            Node pre = head;
            while (cur != null) {
                pre = cur;
                cur = cur.next;
            }
            pre.next = node;
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (index < 1) {
                addAtHead(val);
            } else if (size == index) {
                addAtTail(val);
            } else if (size < index) {
                return;
            } else {
                int i = 0;
                Node node = new Node(val);
                Node pre = head;
                Node cur = head.next;
                while (i != index) {
                    i++;
                    pre = cur;
                    cur = cur.next;
                }
                node.next = cur;
                pre.next = node;
                size++;
            }
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            int i = 0;
            Node pre = head;
            Node cur = head.next;
            while (i != index) {
                pre = cur;
                cur = cur.next;
                i++;
            }
            pre.next = cur.next;
            size--;
        }
    }
}