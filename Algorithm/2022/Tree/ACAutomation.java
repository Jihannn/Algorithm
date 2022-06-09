/*
 * @Author: Jihan
 * @Date: 2022-06-09 15:08:27
 * @Description: AC自动机
 * 解决在一个大字符串中，找到多个候选字符串的问题
 * 1）把所有匹配串生成一棵前缀树
 * 2）前缀树节点增加fail指针
 * 3）fail指针的含义：如果必须以当前字符结尾，当前形成的路径是str，剩下哪一个字符串的前缀和str的后缀
 *    拥有最大的匹配长度。fail指针就指向那个字符串的最后一个字符所对应的节点
 */
package Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ACAutomation {
    public static class Node {
        private String end;
        private Node[] nexts;
        private boolean endUsed;
        private Node fail;

        public Node() {
            end = null;
            nexts = new Node[26];
            endUsed = false;
            fail = null;
        }
    }

    public static class ACAutoM {
        private Node root;

        public ACAutoM() {
            root = new Node();
        }

        public void insert(String s) {
            char[] str = s.toCharArray();
            Node cur = root;
            for (int i = 0; i < str.length; i++) {
                int c = str[i] - 'a';
                if (cur.nexts[c] == null) {
                    cur.nexts[c] = new Node();
                }
                cur = cur.nexts[c];
            }
            cur.end = s;
        }

        public void build() {
            Queue<Node> queue = new ArrayDeque<>();
            Node cur = root;
            Node cFail = cur.fail;
            queue.add(cur);
            while (!queue.isEmpty()) {
                cur = queue.poll();
                for (int i = 0; i < cur.nexts.length; i++) {
                    if (cur.nexts[i] != null) {
                        cur.nexts[i].fail = root;
                        cFail = cur.fail;
                        while (cFail != null) {
                            if (cFail.nexts[i] != null) {
                                cur.nexts[i].fail = cFail.nexts[i];
                                break;
                            }
                            cFail = cFail.fail;
                        }
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }

        public List<String> containWords(String content) {
            char[] str = content.toCharArray();
            Node cur = root;
            Node follow = null;
            int index = 0;
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                while (cur != root && cur.nexts[index] == null) {
                    cur = cur.fail;
                }
                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                follow = cur;
                while (follow != root) {
                    if (follow.endUsed) {
                        break;
                    }
                    if (follow.end != null) {
                        ans.add(follow.end);
                        follow.endUsed = true;
                    }
                    follow = follow.fail;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        ACAutoM ac = new ACAutoM();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("abcdheks");
        // 设置fail指针
        ac.build();

        List<String> contains = ac.containWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
        for (String word : contains) {
            System.out.println(word);
        }
    }
}
