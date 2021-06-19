package com.test.lib.daily;

public class SubsetXORSum {

    int res;
    int n;

    void dfs(int val, int idx, int[] nums) {
        if (idx == n) {
            res += val;
            return;
        }
        dfs(val ^ nums[idx], idx + 1, nums);
        dfs(val, idx + 1, nums);
    }

    int subsetXORSum(int[] nums) {
        res = 0;
        n = nums.length;
        dfs(0, 0, nums);
        return res;
    }

    public static void main(String[] args) {
        int res = new SubsetXORSum().subsetXORSum(new int[]{1, 2, 3});
        System.out.println("res:" + res);
    }
//0-1（true）
//    0-1（true)-2(true)
//        0-1（true)-2(true) -3(true)
//        0-1（true)-2(true) -3(false)
//    0-1（true)-2(false)
//        0-1（true)-2(false) -3(true)
//        0-1（true)-2(false) -3(false)
//0-1 （false）
//    0-1 （false）-2(true)
//         0-1 （false）-2(true)  -3(true)
//         0-1 （false）-2(true)  -3(false)
//    0-1 （false）-(false)
//         0-1 （false）-2(false)  -3(true)
//         0-1 （false）-2(false)  -3(false)
}
