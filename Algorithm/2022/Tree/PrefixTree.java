package Tree;

import java.util.HashMap;

/*
 * @Author: Jihan
 * @Date: 2022-04-28 09:58:13
 * @Description: 前缀树
 */
public class PrefixTree {
    public static class Node {
        int pass;
        int end;
        // 如果字符量大，可以使用HashMap
        Node[] child;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.child = new Node[26];
        }
    }

    private Node root;

    public PrefixTree() {
        this.root = new Node();
    }

    public void insert(String str) {
        if (str == null) {
            return;
        }
        char[] cArr = str.toCharArray();
        Node node = root;
        node.pass++;
        for (int i = 0; i < cArr.length; i++) {
            int cIndex = cArr[i] - 'a';
            if (node.child[cIndex] == null) {
                node.child[cIndex] = new Node();
            }
            node = node.child[cIndex];
            node.pass++;
        }
        node.end++;
    }

    // 查找字符出现过几次
    public int search(String str) {
        if (str == null) {
            return 0;
        }
        char[] cArr = str.toCharArray();
        Node node = root;
        for (int i = 0; i < cArr.length; i++) {
            int cIndex = cArr[i] - 'a';
            if (node.child[cIndex] == null) {
                return 0;
            }
            node = node.child[cIndex];
        }
        return node.end;
    }

    public void delete(String str) {
        if (search(str) != 0) {
            char[] cArr = str.toCharArray();
            Node node = root;
            node.pass--;
            for (int i = 0; i < cArr.length; i++) {
                int cIndex = cArr[i] - 'a';
                if (--node.child[cIndex].pass == 0) {
                    node.child[cIndex] = null;
                    return;
                }
                node = node.child[cIndex];
            }
            node.end--;
        }
    }

    // 查找有多少数以str为前缀
    public int prefixNum(String str) {
        if (str == null) {
            return 0;
        }
        char[] cArr = str.toCharArray();
        Node node = root;
        for (int i = 0; i < cArr.length; i++) {
            int cIndex = cArr[i] - 'a';
            if (node.child[cIndex] == null) {
                return 0;
            }
            node = node.child[cIndex];
        }
        return node.pass;
    }

    // test
    public static class Right {

        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            PrefixTree trie1 = new PrefixTree();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans3) {
                        System.out.println(ans1);
                        System.out.println(ans3);
                        System.out.println("Oops1!");
                    }
                } else {
                    int ans1 = trie1.prefixNum(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans3) {
                        System.out.println(ans1);
                        System.out.println(ans3);
                        System.out.println("Oops2!");
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}
