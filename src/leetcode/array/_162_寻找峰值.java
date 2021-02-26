package leetcode.array;

/**
 * @author zhouxinghang
 * Created on 2021-02-26
 * 给定数组 nums，找到峰值（只要是波峰，不需要最大的峰），返回峰值对应索引，如果有多个峰值，任意返回一个即可
 */
public class _162_寻找峰值 {

    public static void main(String[] args) {
        //System.out.println(new _162_寻找峰值().findPeakElement(new int[]{1,4,3,2}));
        System.out.println(new _162_寻找峰值().findPeakElement(new int[]{1,2}));
    }
    // 取中间节点 i，如果是 nums[i-1] < nums[i] < nums[i+1] 。表明波峰在右边
    public int findPeakElement(int[] nums) {
        return findPeakElement(nums, 0, nums.length-1);
    }

    private int findPeakElement(int[] nums, int left, int right) {
        if (left == right) {
            return left;
        }
        if (left+1 == right) {
            if (nums[left] > nums[right]) {
                return left;
            } else {
                return right;
            }
        }
        int mid = (left+right)/2;
        if (nums[mid-1] <= nums[mid] && nums[mid] >= nums[mid+1]) {
            return mid;
        } else if (nums[mid-1] < nums[mid] && nums[mid] < nums[mid+1]) {
            return findPeakElement(nums, mid, right);
        } else if (nums[mid-1] > nums[mid] && nums[mid] > nums[mid+1]){
            return findPeakElement(nums, left, mid);
        } else {
            return findPeakElement(nums, left, mid);
        }
    }
}
