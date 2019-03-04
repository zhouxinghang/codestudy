package leetcode.array;

import java.util.Arrays;

/**
 * @author zhouxinghang
 * @date 2019/2/25
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * Example 1:
    Input: [1,2,3,4,5,6,7] and k = 3
    Output: [5,6,7,1,2,3,4]
    Explanation:
    rotate 1 steps to the right: [7,1,2,3,4,5,6]
    rotate 2 steps to the right: [6,7,1,2,3,4,5]
    rotate 3 steps to the right: [5,6,7,1,2,3,4]

 */
public class _189_RotateArray {
    public static void rotate1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) {
            return;
        }
        int length = nums.length;
        int[] nums2 = new int[length];
        k = k % length;
        for (int i = 0; i< k; i++) {
            nums2[i] = nums[length - k + i];
        }
        for (int i = 0;i < length -k; i++) {
            nums2[i+k] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums2[i];
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void rotate2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) {
            return;
        }
        int[] nums2 = new int[nums.length];
        for (int i= 0; i< nums.length;i++) {
            nums2[i % nums.length] = nums[(nums.length - k + i) % nums.length];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums2[i];
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        rotate2(nums, 3);
    }
}
