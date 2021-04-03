package com.test.lib.double_pointer;

/**
 *「双指针」，当我们需要枚举数组中的两个元素时，如果我们发现随着第一个元素的递增，第二个元素是递减的，那么就可以使用双指针的方法，
 * 将枚举的时间复杂度从 O(N^2)O(N2) 减少至 O(N)O(N)。
 * 为什么是 O(N)O(N) 呢？
 * 这是因为在枚举的过程每一步中，「左指针」会向右移动一个位置（也就是题目中的 bb），
 * 而「右指针」会向左移动若干个位置，这个与数组的元素有关，但我们知道它一共会移动的位置数为 O(N)O(N)，
 * 均摊下来，每次也向左移动一个位置，因此时间复杂度为 O(N)O(N)。
 *
 *
 * 总结：根据序列本身序列的特征（升序or降序）使两个下标对序列进行扫描，可同向，也可以反向。
 */

public class AA_remark {

}
