package com.test.lib.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 * <p>
 * 注意：
 * 总人数少于1100人。
 * <p>
 * 示例
 * <p>
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * <p>
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class ReconstructQueue {

    public static int[][] reconstructQueue(int[][] people) {
        //从高到低排序,高度相同，按位置从小到大排序(从前往后)
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        List<int[]> list = new LinkedList<>();
        for (int[] p : people) {
            //依次按照前面的人数插入
            //因为是从高到低排序的，高的人会往后挤，但是新插入的低的人不会影响高的人前面的人数
            list.add(p[1], p);
        }
        return list.toArray(people);
    }

    public static void main(String[] args) {
        int[][] result = reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}});
        Arrays.stream(result).forEach(ints -> {
            System.out.print("[");
            Arrays.stream(ints).forEach(System.out::print);
            System.out.print("]");
        });
    }
}
