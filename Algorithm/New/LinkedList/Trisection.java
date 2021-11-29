package LinkedList;

/*
 * @Author: Jihan
 * 
 * @Date: 2021-11-29 13:37:52
 * 
 * @Description:把一个单向链表按照某个值，划分成左边小中间相等右边大
 */

public class Trisection<E extends Comparable> {
    private class Node {
        E value;
        Node next;

        Node(E value) {
            this.value = value;
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
        return head;
    }

    // 把链表转为数组排序完再转回链表，额外空间复杂度O(N)
    private void methodOne(E[] array, E num) {
        if (array == null || array.length < 2) {
            return;
        }
        partition(array, 0, array.length - 1, num);
    }

    private void partition(E[] array, int L, int R, E pivot) {
        int left = L - 1;
        int right = R + 1;
        int cur = L;
        while (cur < right) {
            if (array[cur].compareTo(pivot) < 0) {
                swap(array, cur++, ++left);
            } else if (array[cur].compareTo(pivot) > 0) {
                swap(array, cur, --right);
            } else {
                cur++;
            }
        }
    }

    // 用小于等于大于三个链表，再相互连接起来即可，额外空间复杂度O(1)
    private Node methodTwo(Node head, E num) {
        Node sH = null, sT = null, eH = null, eT = null, hH = null, hT = null;
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = null;
            if (cur.value.compareTo(num) < 0) {
                if (sH == null) {
                    sH = cur;
                    sT = cur;
                } else {
                    sT.next = cur;
                    sT = cur;
                }
            } else if (cur.value.compareTo(num) > 0) {
                if (hH == null) {
                    hH = cur;
                    hT = cur;
                } else {
                    hT.next = cur;
                    hT = cur;
                }
            } else {
                if (eH == null) {
                    eH = cur;
                    eT = cur;
                } else {
                    eT.next = cur;
                    eT = cur;
                }
            }
            cur = next;
        }
        // 三个链表相连
        if (sT != null) {
            sT.next = eH == null ? hH : eH;
            head = sH;
        }
        if (eT != null) {
            eT.next = hH;
            if (sT == null) {
                head = eH;
            }
        }
        if (sT == null && eT == null) {
            head = hH;
        }
        return head;
    }

    private void swap(E array[], int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void trisection1(E[] array, E num) {
        methodOne(array, num);
    }

    public void trisection2(E[] array, E num) {
        Node head = createLinkedList(array);
        head = methodTwo(head, num);
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] array = { 3, 8, 5, 2, 7 };
        Trisection<Integer> obj = new Trisection<>();
        // obj.trisection1(array, 6);
        // for (Integer integer : array) {
        // System.out.print(integer + ",");
        // }
        obj.trisection2(array, 1);
        obj.trisection2(array, 2);
        obj.trisection2(array, 9);
        obj.trisection2(array, 8);
        obj.trisection2(array, 6);
        obj.trisection2(array, 5);
    }
}