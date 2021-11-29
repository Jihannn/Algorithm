package LinkedList;

import java.util.HashMap;

/*
 * @Author: Jihan
 * 
 * @Date: 2021-11-29 15:41:37
 * 
 * @Description:复制含有随机指针结点的无环链表
 */

public class RandomPointer<E extends Comparable> {
    private class Node {
        E value;
        Node next;
        // 特殊结点，随机指向
        Node random;

        Node(E value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            Node node = (Node) obj;
            if (this.value != node.value) {
                return false;
            }
            if (this.random != null) {
                if (node.random == null || this.random.value != node.random.value) {
                    return false;
                }
            }
            if (this.next != null) {
                if (node.next == null || this.next.value != node.next.value) {
                    return false;
                }
            }
            return true;
        }
    }

    private Node createLinkedList(E[] array) {
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
        return initRandom(head);
    }

    private Node initRandom(Node head) {
        Node preNode = null;
        Node h = head;
        while (head != null) {
            int salt = (int) Math.random() * 10;
            if (salt < 2) {
                head.random = null;
            } else if (salt < 5) {
                if (head.next.next != null) {
                    head.random = head.next.next;
                } else {
                    head.random = preNode;
                }
            } else {
                if (head.next.next.next != null) {
                    head.random = head.next.next.next;
                } else {
                    head.random = preNode;
                }
            }
            head = head.next;
            preNode = head;
        }
        return h;
    }

    // 额外空间复杂度为O(N)
    private Node method1(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        // Map存储每个结点对应的克隆结点，random不保存
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        // 取克隆结点
        Node copy = map.get(head);
        cur = head;
        // 克隆结点的对应关系和原链表相等
        while (cur != null) {
            copy.next = map.get(cur.next);
            copy.random = map.get(cur.random);
            cur = cur.next;
            copy = copy.next;
        }
        return map.get(head);
    }

    // 额外空间复杂度为O(1)
    private Node method2(Node head) {
        // 把克隆结点连在原结点之后
        Node cur = head;
        while (cur != null) {
            Node copyNode = new Node(cur.value);
            Node temp = cur.next;
            cur.next = copyNode;
            copyNode.next = temp;
            cur = temp;
        }
        // 因为两个为一组，克隆结点的random即为原结点random的下一个结点
        cur = head;
        while (cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }
        // 原链表和克隆链表拆分
        Node copyHead = head.next;
        cur = head;
        Node copyCur = head.next;
        while (cur != null) {
            cur.next = cur.next.next;
            copyCur.next = copyCur.next == null ? null : copyCur.next.next;
            cur = cur.next;
            copyCur = copyCur.next;
        }
        return copyHead;
    }

    public boolean copyAndCompare(E[] array) {
        Node head = createLinkedList(array);
        Node copyHead = method2(head);
        while (head != null && copyHead != null) {
            if (head.value.compareTo(copyHead.value) != 0) {
                return false;
            }
            head = head.next;
            copyHead = copyHead.next;
        }
        // 有剩余说明不相同
        if (head != null || copyHead != null) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        Integer[] array1 = { 9, 2, 5, 6, 1 };
        Integer[] array2 = { 9, 5, 5, 6, 5 };
        Integer[] array3 = { 9 };
        Integer[] array4 = { 5, 5 };
        RandomPointer<Integer> obj = new RandomPointer<>();
        System.out.println(obj.copyAndCompare(array1));
        System.out.println(obj.copyAndCompare(array2));
        System.out.println(obj.copyAndCompare(array3));
        System.out.println(obj.copyAndCompare(array4));
    }
}