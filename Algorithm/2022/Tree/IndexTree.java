package Tree;

/*
 * @Author: Jihan
 * @Date: 2022-06-09 14:41:44
 * @Description: 
 * IndexTree
 * 1）支持区间查询
 * 2）没有线段树那么强，但是非常容易改成一维、二维、三维的结构
 * 3）只支持单点更新
 */
public class IndexTree {
    public static class ITree {
        int size;
        int[] tree;

        public ITree(int size) {
            this.size = size;
            tree = new int[size + 1];
        }

        public int sum(int index) {
            int rtn = 0;
            while (index > 0) {
                rtn += tree[index];
                index -= index & -index;
            }
            return rtn;
        }

        public void add(int index, int num) {
            while (index <= size) {
                tree[index] += num;
                index += index & -index;
            }
        }
    }
}