/*
 * @Author: Jihan
 * @Date: 2021-12-08 08:26:49
 * @Description: 前缀树
 */
package Tree;

public class TrieTree {
    public class TrieNode {
        // 被经过次数
        int pass;
        // 以此为结点的次数
        int end;
        // 下一个结点
        TrieNode[] nexts;

        TrieNode() {
            pass = 0;
            end = 0;
            nexts = new TrieNode[25];
        }
    }

    private TrieNode root;

    public TrieTree() {
        root = new TrieNode();
    }

    public void insert(String words) {
        char[] word = words.toCharArray();
        // 根结点先经过一次
        root.pass++;
        TrieNode cur = root;
        for (int i = 0; i < word.length; i++) {
            // 找到对应下标,26个字母
            int index = word[i] - 'a';
            if (cur.nexts[index] == null) {
                cur.nexts[index] = new TrieNode();
            }
            cur.nexts[index].pass++;
            cur = cur.nexts[index];
        }
        cur.end++;
    }

    public boolean search(String words) {
        char[] word = words.toCharArray();
        TrieNode cur = root;
        for (int i = 0; i < word.length; i++) {
            int index = word[i] - 'a';
            if (cur.nexts[index] == null) {
                return false;
            }
            cur = cur.nexts[index];
        }
        return cur.end != 0;
    }

    public void delete(String words) {
        if (search(words)) {
            char[] word = words.toCharArray();
            root.pass--;
            TrieNode cur = root;
            for (int i = 0; i < word.length; i++) {
                int index = word[i] - 'a';
                // 如果pass=0则销毁该结点和后续结点
                if (--cur.nexts[index].pass == 0) {
                    cur.nexts[index] = null;
                    return;
                }
                cur = cur.nexts[index];
            }
            cur.end--;
        }
    }
}
