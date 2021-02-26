package leetcode.array;

/**
 * @author zhouxinghang
 * Created on 2021-02-26
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2]。最小值为0
 * 其实就是找到波谷。
 * 在百度面试过这个最难得题目154，确实很难理解。
 * 采用二分查找。
 *
 * 均分两份，其中一份一定是连续自增的，选取非连续自增那一部分
 */
public class _153_寻找旋转排序数组中的最小值 {
    public static void main(String[] args) {
        System.out.println(new _153_寻找旋转排序数组中的最小值().findMin(new int[]{4,5,6,7,0,1,2}));
        System.out.println(new _153_寻找旋转排序数组中的最小值().findMin(new int[]{1,2}));
    }

    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length-1);
    }

    private int findMin(int[] nums, int start, int end) {
        int mid = start + (end-start) /2 ;
        if (start >= end) {
            return nums[start];
        }
        if (nums[mid] > nums[end]) {
            return findMin(nums, mid+1, end);
        } else {
            return findMin(nums, start, mid);
        }
    }

    public int findMin2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
