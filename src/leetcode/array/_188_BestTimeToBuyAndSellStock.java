package leetcode.array;

/**
 * @author zhouxinghang
 * @date 2019-07-09
 * 给定数组，第 i 个元素是第 i 天的股票价格。最多只允许完成K笔交易（即买入和卖出一支股票），计算最大利润。只能先买后卖
 * 动态规划，和 123题类似 123题 k=2，
 */
public class _188_BestTimeToBuyAndSellStock {
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        // k 超过 天数的一半，说明天天再交易，将k当做最大值计算
        if (k > prices.length / 2) {
            return maxProfitAnyK(prices);
        }
        int[][][] dp = new int[prices.length][k+1][2];
        for (int i = 0; i < prices.length; i++) {
            for (int t = k; t >= 1; t--) {
                if (i-1 < 0) {
                    dp[i][t][0] = 0;
                    dp[i][t][1] = -prices[i];
                    continue;
                }
                dp[i][t][0] = Math.max(dp[i-1][t][0], dp[i-1][t][1] + prices[i]);
                dp[i][t][1] = Math.max(dp[i-1][t][1], dp[i-1][t-1][0] - prices[i]);
            }
        }
        return dp[prices.length -1][k][0];
    }

    public int maxProfitAnyK(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int maxProfit = 0;
        int prev = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prev) {
                maxProfit += prices[i] - prev;
            }
            prev = prices[i];
        }
        return maxProfit;
    }
}
