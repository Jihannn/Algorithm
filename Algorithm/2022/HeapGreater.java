import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/*
 * @Author: Jihan
 * @Date: 2022-04-27 08:30:22
 * @Description: 加强堆
 */
public class HeapGreater<T> {
    ArrayList<T> heapArr;
    // 反向索引,查找value的位置
    HashMap<T, Integer> indexMap;
    Comparator<T> c;
    int size;

    public HeapGreater(Comparator<T> c) {
        this.heapArr = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.size = 0;
        this.c = c;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean contain(T value) {
        return indexMap.containsKey(value);
    }

    public void add(T value) {
        indexMap.put(value, size);
        heapArr.add(value);
        heapInsert(size++);
    }

    public void remove(T value) {
        // 要删除的位置
        int delIndex = indexMap.get(value);
        // 最后一个元素
        T replace = heapArr.get(size - 1);
        // 删除该元素的索引
        indexMap.remove(value);
        // 删除最后一个元素
        heapArr.remove(--size);
        // 如果要删除的就是最后一个元素则结束
        if (replace != value) {
            // 在该位置放之前的最后一个元素并重新建立索引
            heapArr.set(delIndex, replace);
            indexMap.put(replace, delIndex);
            // 做一个位置调整,让其变成堆
            adjust(delIndex);
        }
    }

    private void adjust(int index) {
        // 两者只会走其一
        heapify(index);
        heapInsert(index);
    }

    private void heapify(int index) {
        int leftCIndex = index * 2 + 1;
        while (leftCIndex < size) {
            int best = leftCIndex + 1 < size && compare(heapArr.get(leftCIndex + 1), heapArr.get(leftCIndex))
                    ? leftCIndex + 1
                    : leftCIndex;
            best = compare(heapArr.get(best), heapArr.get(index)) ? best : index;
            if (best == index) {
                break;
            }
            swap(best, index);
            index = best;
            leftCIndex = best * 2 + 1;
        }
    }

    private void heapInsert(int index) {
        while (compare(heapArr.get(index), heapArr.get((index - 1) / 2))) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private boolean compare(T i, T j) {
        return c.compare(i, j) < 0;
    }

    private void swap(int i, int j) {
        T iValue = heapArr.get(i);
        T jValue = heapArr.get(j);
        heapArr.set(i, jValue);
        heapArr.set(j, iValue);
        // 交换反向索引位置
        indexMap.put(iValue, j);
        indexMap.put(jValue, i);
    }

    public static class Test{
        int value;
        public Test(int value){
            this.value = value;
        }
    }

    public static class TestComparator implements Comparator<Test>{
        @Override
        public int compare(Test t1, Test t2) {
            return t1.value-t2.value;
        }
    }

    public static void main(String[] args) {
        HeapGreater<Test> heap = new HeapGreater<>(new TestComparator());
        Test t1 = new Test(2);
        Test t2 = new Test(0);
        heap.add(new Test(6));
        heap.add(new Test(4));
        heap.add(new Test(8));
        heap.add(t1);
        heap.add(new Test(-6));
        heap.add(t2);
        heap.add(new Test(6));
        heap.remove(t1);
        heap.remove(t2);
        System.out.println();
    }
}