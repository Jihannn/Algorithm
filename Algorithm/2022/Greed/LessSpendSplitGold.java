package Greed;
import java.util.PriorityQueue;

/*
 * @Author: Jihan
 * @Date: 2022-05-09 08:42:32
 * @Description: 
 * 一块金条切成两半，是需要花费和长度数值一样的铜板
 * 比如长度为20的金条，不管怎么切都要花费20个铜板，一群人想整分整块金条，怎么分最省铜板? 
 * 例如，给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
 * 如果先把长度60的金条分成10和50，花费60；再把长度50的金条分成20和30，花费50；一共花费110铜板
 * 但如果先把长度60的金条分成30和30，花费60；再把长度30金条分成10和20，花费30；一共花费90铜板
 * 输入一个数组，返回分割的最小代价
 */
public class LessSpendSplitGold {

    public static int splitGold(int[] nums){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (Integer num : nums) {
            minHeap.add(num);
        }
        int spend = 0;
        while(minHeap.size() > 1){
            int s = minHeap.poll() + minHeap.poll();
            spend += s;
            minHeap.add(s);
        }
        return spend;
    }
    
}
