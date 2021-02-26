package leetcode.array;

/**
 * @author zhouxinghang
 * Created on 2021-02-26
 * 查找二维矩阵 m*n 中是否含有数字 target。从左到右，从上到下递增
 */
public class  _240_搜索二维矩阵II {

    public static void main(String[] args) {
        // [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]
        int[][] matrix = new int[][]{
                new int[]{1,4,7,11,15},
                new int[]{2,5,8,12,19},
                new int[]{3,6,9,16,22},
                new int[]{10,13,14,17,24},
                new int[]{18,21,23,26,30}
        };
        System.out.println(new _240_搜索二维矩阵II().searchMatrix(matrix, 5));
    }
    /**
     * 1、暴力方式，对每一行进行二分查找
     * 2、从最右侧那个点开始查找，如果大于就向下，小于就向左
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int column = matrix[0].length-1;
        while (row < matrix.length && column >= 0) {
            if (matrix[row][column] < target) {
                row++;
            } else if (matrix[row][column] > target) {
                column--;
            } else {
                return true;
            }
        }
        return false;
    }
}
