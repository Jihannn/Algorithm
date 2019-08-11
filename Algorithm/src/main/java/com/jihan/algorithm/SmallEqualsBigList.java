package com.jihan.algorithm;

/**
 * Created by Jihan on 2019/7/16
 */
public class SmallEqualsBigList {

    private class Node {
        private Integer data;
        private Node next;

        private Node(Integer data) {
            this.data = data;
        }
    }

    public static Node keepStable(Node head, int num) {
        if (head == null) {
            return null;
        }

        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node bH = null;
        Node bT = null;
        Node next;

        while (head != null) {
            next = head.next;
            head.next = null;

            if (head.data < num) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sH.next = head;
                    sT = head;
                }
            } else if (head.data == num) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eH.next = head;
                    eT = head;
                }
            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bH.next = head;
                    bT = head;
                }
            }

            head = next;
        }

        if(sT != null){
            if(eT != null){
                sT.next = eH;
                eT.next = bH;
            }else{
                sT.next = bH;
            }
            return sT;
        }else{
            if(eT != null){
                eT.next = bH;
                return eH;
            }else{
                return bH;
            }
        }
    }

    public static Node listPartition(Node head, int num) {
        if (head == null) {
            return null;
        }

        int i = 0;
        Node cur = head;
        while (cur != null) {
            cur = cur.next;
            i++;
        }

        Node[] arrNode = new Node[i];
        cur = head;
        for (int j = 0; j < i; j++) {
            arrNode[j] = cur;
            cur = cur.next;
        }

        arrPartition(arrNode, num);

        for (int j = 0; j < arrNode.length - 1; j++) {
            arrNode[j].next = arrNode[j + 1];
        }
        arrNode[arrNode.length - 1].next = null;

        return arrNode[0];
    }

    private static void arrPartition(Node[] arr, int num) {
        int small = -1;
        int big = arr.length;
        int index = 0;
        while (index < big) {
            if (arr[index].data < num) {
                swap(arr, ++small, index++);
            } else if (arr[index].data > num) {
                swap(arr, --big, index);
            } else {
                index++;
            }
        }
    }

    private static void swap(Node[] arr, int i, int j) {
        Node temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }


}
