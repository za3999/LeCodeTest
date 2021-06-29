package com.test.lib.niubo;

/**
 * JZ1 二维数组中的查找
 * 描述
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * [
 *   [1,2,8,9],
 *   [2,4,9,12],
 *   [4,7,10,13],
 *   [6,8,11,15]
 * ]
 * 给定 target = 7，返回 true。
 *
 * 给定 target = 3，返回 false。
 *
 * 示例1
 * 输入：
 * 7,[[1,2,8,9],[2,4,9,12],[4,7,10,13],[6,8,11,15]]
 * 返回值：
 * true
 * 说明：
 * 存在7，返回true
 *
 * 示例2
 * 输入：
 * 3,[[1,2,8,9],[2,4,9,12],[4,7,10,13],[6,8,11,15]]
 * 返回值：
 * false
 * 说明：
 * 不存在3，返回false
 * https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e?tpId=13&&tqId=11154&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class Find {
    public boolean find(int target, int[][] array) {
        if (array == null) {
            return false;
        }
        int rowLength = array.length;
        if (rowLength <= 0) {
            return false;
        }
        int colLength = array[0].length;
        if (colLength <= 0) {
            return false;
        }

        if (array[0][0] > target || array[rowLength - 1][colLength - 1] < target) {
            return false;
        }
        int r = rowLength - 1;
        int c = 0;
        while (r >= 0 && c < colLength) {
            if (array[r][c] == target) {
                return true;
            }
            if (array[r][c] < target) {
                c++;
            } else {
                r--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("result:" + new Find().find(16, new int[][]{}));
    }
}
