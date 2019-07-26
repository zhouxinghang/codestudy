package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhouxinghang
 * @date 2019-07-26
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *  Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 *  A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class _15_3Sum {

    /**
     * 先排序，固定一个数，然后用左右指针开始遍历
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        int left, right;
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int sum;
        for (int i = 0 ; i < nums.length ; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            left = i + 1;
            right = nums.length -1;
            while (left < right) {
                sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(convertList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left+1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right-1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    private List<Integer> convertList(int a, int b, int c) {
        List<Integer> integers = new ArrayList<>();
        integers.add(a);
        integers.add(b);
        integers.add(c);
        return integers;
    }

    public static void main(String[] args) {
        System.out.println(new _15_3Sum().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
