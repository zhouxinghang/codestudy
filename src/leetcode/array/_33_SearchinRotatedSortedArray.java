package leetcode.array;

/**
 * @author zhouxinghang
 * @date 2019-08-24
 * 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 给定目标值，返回其索引，若不存在就返回-1
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class _33_SearchinRotatedSortedArray {

    /**
     * 第一类 2 3 4 5 6 7 1这种，也就是nums[start] <= nums[mid]。此例子中就是2 <= 5
     * 这种情况下，前半部分有序。因此如果 nums[start] <=target<nums[mid]。则在前半部分找，
     * 否则去后半部分找。
     * 第二类 6 7 1 2 3 4 5这种，也就是nums[start] > nums[mid]。此例子中就是6 > 2
     * 这种情况下，后半部分有序。因此如果 nums[mid] <target<=nums[end]。则在后半部分找，
     * 否则去前半部分找。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length-1;
        int mid;
        while (start <= end) {
            mid = start + (end-start)/2;
            if (nums[mid] == target) {
                return mid;
            }
            // 前半部分有序
            if (nums[start] <= nums[mid]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target <= nums[end] && target > nums[mid]) {
                    start = mid + 1;
                } else {
                    end = mid -1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {5,1,3};
        System.out.println(new _33_SearchinRotatedSortedArray().search(arr, 3));
    }
}
