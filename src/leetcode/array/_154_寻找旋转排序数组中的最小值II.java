package leetcode.array;

/**
 * @author zhouxinghang
 * Created on 2021-02-26
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2]。最小值为0
 * 注意可以重复。
 */
public class _154_寻找旋转排序数组中的最小值II {


    public static void main(String[] args) {
        System.out.println(new _154_寻找旋转排序数组中的最小值II().findMin(new int[]{4,5,6,6,7,0,1,1,2}));
    }

    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length-1);
    }

    private int findMin(int[] nums, int start, int end) {
        if (start >= end) {
            return nums[start];
        }
        int mid = start + (end - start) / 2;
        // 增加一个判断，如果相等，就左移
        if (nums[mid] == nums[end]) {
            return findMin(nums, start, end-1);
        }
        if (nums[mid] > nums[end]) {
            return findMin(nums, mid+1, end);
        } else {
            return findMin(nums, start, mid);
        }
    }
}
