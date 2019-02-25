package leetcode.array;


import java.util.Arrays;

/**
 * @author zhouxinghang
 * @date 2019/2/25
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Example:
 * Given nums = [1,1,1,2,2,3],
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It doesn't matter what you leave beyond the returned length.
 * <p>
 * 过滤后数组保证每个元素不超过2个，结果放在原来数组上，It doesn't matter what you leave beyond the returned length.
 */
public class _80_RemoveDuplicatesfromSortedArrayII {
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length < 3) {
            return nums.length;
        }
        int res = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[i - 2]) {
                nums[res++] = nums[i-2];
            }
        }
        nums[res++] = nums[nums.length-2];
        nums[res++] = nums[nums.length-1];
        System.out.println(Arrays.toString(nums));
        return res;
    }

    /**
     * 方法2
     * @param nums
     * @return
     */
    public static int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length < 3) {
            return nums.length;
        }
        int res = 2;
        for (int i = 2; i< nums.length; i++) {
            if (nums[i] != nums[res - 2]) {
                nums[res++] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicates2(nums));
    }
}
