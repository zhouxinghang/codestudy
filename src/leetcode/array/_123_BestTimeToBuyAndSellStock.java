package leetcode.array;

import java.util.Arrays;

/**
 * @author zhouxinghang
 * @date 2019-07-09
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 * 给定数组，第 i 个元素是第 i 天的股票价格。最多只允许完成两笔笔交易（即买入和卖出一支股票），计算最大利润。只能先买后卖
 */
public class _123_BestTimeToBuyAndSellStock {

    /**
     * 计算相邻元素的差值，如果差值 > 0，就融合为一个差值（可以当做一笔交易）。找出最大的两个差值，就是最大利润
     * 没有考虑到，两个正差值，中间隔着一个负差值，结果可能会比较大。比如 3,-2,5，这三个差值，可以合并成一个
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[] profitArr = new int[prices.length];
        int prevProfit = 0;
        int prevPrice = prices[0];
        int idx = 0;
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - prevPrice;
            prevPrice = prices[i];
            if (profit < 0 && prevProfit > 0) {
                profitArr[idx ++] = prevProfit;
                prevProfit = 0;
            }
            if (profit > 0) {
                prevProfit += profit;
            }
        }
        if (prevProfit > 0) {
            profitArr[idx ++] = prevProfit;
        }
        if (idx == 0) {
            return 0;
        }
        if (idx == 1) {
            return profitArr[0];
        }
        if (idx == 2) {
            return profitArr[0] + profitArr[1];
        }
        Arrays.sort(profitArr);
        return profitArr[profitArr.length - 1] + profitArr[profitArr.length - 2];
    }

    /**
     * 采用动态规划的方法，
     * dp[i][k][0 or 1]
     * 0 <= i <= n-1, 1 <= k <= K
     * n 为天数，大 K 为最多交易数
     * 此问题共 n × K × 2 种状态，全部穷举就能搞定。
     *
     * for 0 <= i < n:
     *     for 1 <= k <= K:
     *         for s in {0, 1}: // 1 表示持有股票，0 表示售卖了股票
     *             dp[i][k][s] = max(buy, sell, rest)
     *
     * 作者：labuladong
     * 链接：https://leetcode-cn.com/problems/two-sum/solution/yi-ge-tong-yong-fang-fa-tuan-mie-6-dao-gu-piao-wen/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * dp[3][2][1] 表示第三天已交易两次手中持有股票的所得利润
     * dp[3][3][0] 表示第三天已交易三次手中售出股票的所得利润
     * 可以得到下面的公式：
     * dp[i][k][0] = max (dp[i-1][k][1] + price[i], dp[i-1][k][0]) //max(售卖，保持不变)
     * dp[i][k][1] = max (dp[i-1][k-1][0] - price[i], dp[i-1][k][1]) //max(买入，保持不变)，只有再=在售卖的时候 k才-1
     * dp[-1][k][0] = dp[i][0][0] = 0
     * dp[-1][k][1] = dp[i][0][1] = -infinity // 还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[][][] dp = new int[prices.length][3][2];
        for (int i = 0; i < prices.length; i++) {
            for (int k = 2; k >= 1; k--) {
                if (i-1 < 0) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        }
        return dp[prices.length -1][2][0];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,4,2,5,7,2,4,9,0};
        System.out.println(new _123_BestTimeToBuyAndSellStock().maxProfit2(arr));
    }
}
