package study;

/**
 * @author zhouxinghang
 * @date 2019-08-22
 * 选择一个基准，小于基准的放在左边，大于基准的放在右边。然后递归排序两边的部分
 * 画图比较容易理解
 * Arrays.sort()，对于普通类型使用的是快速排序，对于对象是归并排序，稳定，且不需要频繁的转换，适合对象这样的大内存类型，
 * 时间复杂度两者一致，归并排序需要一个额外的引用。
 *
 * https://tryenough.com/arithmetic-quitsort
 */
public class QuickSort {

    private int partition(int[] arr, int left, int right) {
        int temp = arr[left];
        while (right > left) {
            // 先遍历右边
            while (temp < arr[right] && left < right) {
                right--;
            }
            if (left < right) {
                arr[left] = arr[right];
                left++;
            }
            // 后遍历左边
            while (temp > arr[left] && left < right) {
                left++;
            }
            if (left < right) {
                arr[right] = arr[left];
                right--;
            }
        }
        arr[left] = temp;
        return left;
    }


    private void quickSort(int[] arr, int left, int right) {
        int dp;
        if (left < right) {
            dp = partition(arr, left, right);
            quickSort(arr, left, dp - 1);
            quickSort(arr, dp + 1, right);
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{3,4,2,1,5};
        new QuickSort().quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length ; i++) {
            System.out.println(arr[i]);
        }

    }
}
