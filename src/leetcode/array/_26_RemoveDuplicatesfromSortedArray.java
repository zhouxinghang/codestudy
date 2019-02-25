package leetcode.array;

import java.util.Arrays;

/**
 * @author zhouxinghang
 * @date 2019/2/25
 * <p>
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Example:
 * Given nums = [0,0,1,1,1,2,2,3,3,4],
 * Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
 * 过滤重复元素，将过滤后的结果放在原来的数组中，比如过滤后结果有5个，那数组的前5个就是过滤后的结果
 */
public class _26_RemoveDuplicatesfromSortedArray {

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[res++] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(nums));
    }
}
