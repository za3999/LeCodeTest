package com.test.lib.daily;
import com.test.lib.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * <p>
 * <p>
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *  
 * <p>
 * 限制：
 * <p>
 * 1 <= s 的长度 <= 8
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Permutation {
    ArrayList<String> results = new ArrayList<>();
    char[] c;

    public String[] permutation(String s) {
        c = s.toCharArray();
        dfs(0);
        return results.toArray(new String[results.size()]);
    }

    private void dfs(int index) {
        if (index == c.length) {
            results.add(String.valueOf(c)); // 退出条件，将结果添加到集合中
            return;
        }
        Set<Character> set = new HashSet<>(); // 标记已经处理过的char,去重复
        for (int i = index; i < c.length; i++) {
            if (set.contains(c[i])) {
                continue;
            }
            set.add(c[i]);
            Util.swap(c, i, index); //交换位置
            dfs(index + 1);  // 递归处理下一级
            Util.swap(c, i, index); //回逆交换过的位置
        }
    }

    public static void main(String[] args) {
        Util.print(new Permutation().permutation("abc"));
    }
}
