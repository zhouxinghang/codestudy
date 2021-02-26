package leetcode.array;

import java.util.List;

/**
 * @author zhouxinghang
 * Created on 2020-06-22
 *
 * 7
 * 3 8
 * 8 1 0
 * 2 7 4 4
 * 4 5 2 6 5
 * 自顶向下找出最短路径和。
 */
public class _120_三角形最小路径和 {

    /**
     * dp[m][n] = min(dp[m-1,n-1], dp[m-1][n]) + triangle[m][n]
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(n^2)
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int column = triangle.get(row - 1).size();

        int[][] dp = new int[row][column];
        dp[0][0] = triangle.get(0).get(0);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < row; i++) {
            for (int j = 0; j <= i; j++) {
                // 最左侧
                if (j == 0) {
                    dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
                } else if(j == i) {
                    dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + triangle.get(i).get(j);
                }
            }
        }
        // dp最后一行记录了最小路径
        for (int i = 0; i < column; i++) {
            res = Math.min(res, dp[row - 1][i]);
        }
        return res;
    }

    // dp[m][n] = min(dp[m-1][n-1], dp[m-1][n]) + triangle[m][n]
    public int minimumTotal2(List<List<Integer>> triangle) {
        int row = triangle.size();
        int column = triangle.get(row-1).size();

        int[][] dp = new int[row+1][column+1];
        dp[1][1] = triangle.get(0).get(0);

        for (int i = 2; i <= row; i++) {
            for (int j = 1; j <= i; j++) {
                if (j == 1) {
                    dp[i][j] = dp[i-1][j] + triangle.get(i-1).get(j-1);
                } else if (i == j) {
                    dp[i][j] = dp[i-1][i-1] + triangle.get(i-1).get(j-1);
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + triangle.get(i-1).get(j-1);
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= column; i++) {
            res = Integer.min(res, dp[row][i]);
        }
        return res;
    }
}
