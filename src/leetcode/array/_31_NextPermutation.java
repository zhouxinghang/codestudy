package leetcode.array;

/**
 * @author zhouxinghang
 * @date 2019-07-29
 * 给定一个数组，返回下一个字典序列数组（只能是下一个），如果没有，就返回最小序列（从小到大排列）
 * 必须原地修改，只能额外使用常数空间
 * eg:
 * 1,2,3 → 1,3,2 // 下一个更大序列
 * 3,2,1 → 1,2,3 // 无下一个更大序列，返回最小序=列
 * 1,1,5 → 1,5,1
 *
 */
public class _31_NextPermutation {

    /**
     * 从右边开始遍历，找打第一个比右边数小的数，记为 i，从右边遍历找到第一个比 i 大的数 记为j，先交换 i，j 的位置，然后将 i 左边的数翻转。
     * 如果查找，就返回最小序列
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i+1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length-1;
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start;
        int j = nums.length - 1;
        while (i < j) {
            swap(nums, i , j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,0,4,3,1};
        new _31_NextPermutation().nextPermutation(nums);
        for (int i : nums) {
            System.out.println(i);
        }

    }
}
