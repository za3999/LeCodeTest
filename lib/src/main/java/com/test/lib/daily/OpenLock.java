package com.test.lib.daily;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * 752. 打开转盘锁
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * <p>
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * <p>
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * <p>
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 * 示例 2:
 * <p>
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 * 示例 3:
 * <p>
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：
 * 无法旋转到目标数字且不被锁定。
 * 示例 4:
 * <p>
 * 输入: deadends = ["0000"], target = "8888"
 * 输出：-1
 */

public class OpenLock {

    /**
     * 广度优先搜索
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")) {
            return -0;
        }
        Set<String> ignoreSet = new HashSet<>();
        for (String deadend : deadends) {
            if (deadend.equals("0000")) {
                return -1;
            }
            ignoreSet.add(deadend);
        }
        Queue<String> queue = new LinkedList<>();
        queue.add("0000");
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String status = queue.poll();
                List<String> nextStatuses = getNext(status);
                for (String nextStatus : nextStatuses) {
                    if (status.equals(target)) {
                        return step;
                    }
                    if (!ignoreSet.contains(nextStatus)) {
                        queue.add(nextStatus);
                        ignoreSet.add(nextStatus);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    /**
     * A* 算法 优先级队列
     */
    public int openLockV2(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }

        Set<String> dead = new HashSet();
        for (String deadend : deadends) {
            dead.add(deadend);
        }
        if (dead.contains("0000")) {
            return -1;
        }
        PriorityQueue<AStar> pq = new PriorityQueue<AStar>((a, b) -> a.allStep - b.allStep);
        pq.offer(new AStar("0000", target, 0));
        dead.add("0000");

        while (!pq.isEmpty()) {
            AStar node = pq.poll();
            for (String nextStatus : getNext(node.status)) {
                if (!dead.contains(nextStatus) && !dead.contains(nextStatus)) {
                    if (nextStatus.equals(target)) {
                        return node.currentStep + 1;
                    }
                    pq.offer(new AStar(nextStatus, target, node.currentStep + 1));
                    dead.add(nextStatus);
                }
            }
        }
        return -1;
    }

    public List<String> getNext(String status) {
        List<String> ret = new ArrayList();
        char[] array = status.toCharArray();
        for (int i = 0; i < 4; ++i) {
            char num = array[i];
            array[i] = num == '0' ? '9' : (char) (num - 1);
            ret.add(new String(array));
            array[i] = num == '9' ? '0' : (char) (num + 1);
            ret.add(new String(array));
            array[i] = num;
        }
        return ret;
    }

    class AStar {
        String status;
        int allStep, currentStep, lessStep;

        public AStar(String status, String target, int g) {
            this.status = status;
            this.currentStep = g;
            this.lessStep = getLess(status, target);
            this.allStep = this.currentStep + this.lessStep;
        }

        /**
         * 计算启发函数
         * 从status到target的最短步数
         */
        public int getLess(String status, String target) {
            int ret = 0;
            for (int i = 0; i < 4; ++i) {
                int dist = Math.abs(status.charAt(i) - target.charAt(i));
                ret += Math.min(dist, 10 - dist);
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        System.out.println("方度优先 result:" + new OpenLock().openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202"));
        System.out.println("A* 优先级队列 result:" + new OpenLock().openLockV2(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202"));
    }
}
