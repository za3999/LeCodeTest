package com.test.lib.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1239. 串联字符串的最大长度
 * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 * <p>
 * 请返回所有可行解 s 中最长长度。
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = ["un","iq","ue"]
 * 输出：4
 * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
 * 示例 2：
 * <p>
 * 输入：arr = ["cha","r","act","ers"]
 * 输出：6
 * 解释：可能的解答有 "chaers" 和 "acters"。
 * 示例 3：
 * <p>
 * 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
 * 输出：26
 */
public class MaxLength {

    int res;
    List<Integer> masks = new ArrayList<Integer>();

    public int maxLength(List<String> arr) {
        for (String s : arr) {
            int mask = 0;
            for (int i = 0; i < s.length(); ++i) {
                int ch = s.charAt(i) - 'a';
                if (((mask >> ch) & 1) != 0) {// 若 mask 已有 ch，则说明 s 含有重复字母，无法构成可行解
                    mask = 0;
                    break;
                }
                mask |= 1 << ch; // 将 ch 加入 mask 中
            }
            if (mask > 0) {
                masks.add(mask);
            }
        }
        dfs(0, 0);
        return getCount(res);
    }

    private void dfs(int i, int position) {
        if (position == masks.size()) {
            res = getCount(res) > getCount(i) ? res : i;
            return;
        }
        if ((i & masks.get(position)) == 0) {
            dfs(i | masks.get(position), position + 1);
        }
        dfs(i, position + 1);
    }

    private int getCount(int res) {
        int length = 0;
        for (int i = 0; i < 30; i++) {
            if (((res >> i) & 1) != 0) {
                length++;
            }
        }
        return length;
    }

    public static void main(String[] args) {
        int result = new MaxLength().maxLength(Arrays.asList("cha", "r", "act", "ers"));
        System.out.println("length:" + result);
        new HammingWeight();
    }
}
