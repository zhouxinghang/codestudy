package leetcode.array;
import	java.util.HashMap;
import	java.util.Map;


/**
 * @author zhouxinghang
 * @date 2019-07-15
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
 *
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 */
public class _219_ContainsDuplicatesII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i< nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new _219_ContainsDuplicatesII().containsNearbyDuplicate(new int[]{1,0,1,1}, 1));
    }
}
