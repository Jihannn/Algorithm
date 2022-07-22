import java.util.PriorityQueue;

/*
 * @Author: Jihan
 * @Date: 2022-07-22 16:42:46
 * @Description: https://leetcode.cn/problems/merge-k-sorted-lists/
 */
public class _023_MergeKSortedLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.add(node);
            }
        }
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (!minHeap.isEmpty()) {
            ListNode next = minHeap.poll();
            cur.next = next;
            cur = next;
            if (next.next != null) {
                minHeap.add(next.next);
            }
        }
        return head.next;
    }
}
