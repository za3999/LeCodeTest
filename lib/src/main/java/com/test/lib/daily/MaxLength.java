package com.test.lib.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    }
}
