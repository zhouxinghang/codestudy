package leetcode.array;

import java.util.Arrays;

/**
 * @author zhouxinghang
 * @date 2019/2/25
 *
 * https://leetcode.com/problems/remove-element/
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 *
 * Example:
 * Given nums = [0,1,2,2,3,0,4,2], val = 2,
 * Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.
 * 返回remove后的长度length，且数组的前length个不能包含给定的值val，顺序无所谓
 */
public class _27_RemoveElement {

    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[res++] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,2,3,0,4,2};
        removeElement(nums,2);
    }
}
