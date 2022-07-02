package LinkedList;

import java.util.HashMap;

/*
 * @Author: Jihan
 * @Date: 2022-04-30 15:03:41
 * @Description: https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * 返回复制链表的头节点。
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 */
public class CopyListWithRandomPointer {
    public class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        // return method1(head);
        return method2(head);
    }

    public Node method1(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node node = head;
        // 每个结点与拷贝结点对应上
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }
        node = head;
        while (node != null) {
            Node copyNode = map.get(node);
            copyNode.next = map.get(node.next);
            copyNode.random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }

    public Node method2(Node head) {
        if (head == null) {
            return null;
        }
        // 在每个结点后面添加拷贝结点
        Node node = head;
        while (node != null) {
            Node temp = node.next;
            Node copyNode = new Node(node.val);
            node.next = copyNode;
            copyNode.next = temp;
            node = temp;
        }
        // 根据特性可以找到随机指针的指向
        node = head;
        while (node != null) {
            Node copyNode = node.next;
            copyNode.random = node.random == null ? null : node.random.next;
            node = copyNode.next;
        }
        // 断开连接
        node = head;
        Node rtn = node.next;
        while (node != null) {
            Node copyNode = node.next;
            node.next = copyNode.next;
            copyNode.next = copyNode.next == null ? null : copyNode.next.next;
            node = node.next;
        }
        return rtn;
    }
}
