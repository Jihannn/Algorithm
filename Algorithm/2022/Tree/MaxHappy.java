package Tree;

import java.util.List;

/*
 * @Author: Jihan
 * @Date: 2022-05-07 16:55:06
 * @Description: 
 * 派对的最大快乐值
 * 员工信息的定义如下:
 * class Employee {
 *     public int happy; // 这名员工可以带来的快乐值
 *     List<Employee> subordinates; // 这名员工有哪些直接下级
 * }
 * 公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、 没有环的多叉树
 * 树的头节点是公司唯一的老板，除老板之外的每个员工都有唯一的直接上级
 * 叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外每个员工都有一个或多个直接下级
 * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.你的目标是让派对的整体快乐值尽量大
 * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
 */
public class MaxHappy {
    public static class Employee {
        public int happy; // 这名员工可以带来的快乐值
        List<Employee> subordinates; // 这名员工有哪些直接下级
    }

    public static class Info {
        int yes;
        int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public static int getMaxHappy(Employee root) {
        if (root == null) {
            return 0;
        }
        Info boss = process(root);
        return Math.max(boss.yes, boss.no);
    }

    public static Info process(Employee node) {
        if (node == null) {
            return new Info(0, 0);
        }
        int yes = node.happy;
        int no = 0;
        for (Employee sub : node.subordinates) {
            Info subInfo = process(sub);
            yes += subInfo.no;
            no += Math.max(subInfo.yes, subInfo.no);
        }
        return new Info(yes, no);
    }
}