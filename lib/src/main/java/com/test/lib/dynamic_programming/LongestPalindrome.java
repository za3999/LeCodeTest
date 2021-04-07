package com.test.lib.dynamic_programming;

/**
 * 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class LongestPalindrome {

    public static String longestPalindrome(String s) {
        int length = s.length();
        int max = 0;
        String result = "";
        int k;
        if (length == 1) {
            return s;
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    k = (i - j) / 2;
                    boolean match = true;
                    for (int step = 0; step <= k; step++) {
                        if (i - step <= j + step) {
                            break;
                        }
                        if (s.charAt(i - step) != s.charAt(j + step)) {
                            match = false;
                            break;
                        }
                    }
                    if (match && max < i - j + 1) {
                        max = i - j + 1;
                        result = s.substring(j, i + 1);
                        break;
                    }
                } else if (max < 1) {
                    max = 1;
                    result = s.charAt(i) + "";
                }
            }
        }
        return result;
    }

    public static String longestPalindrome2(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else if (l == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println("result:" + longestPalindrome2("babad"));
    }
}
