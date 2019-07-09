package leetcode.array;

/**
 * @author zhouxinghang
 * @date 2019-07-09
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * 给定数组，第 i 个元素是第 i 天的股票价格。最多只允许完成一笔交易（即买入和卖出一支股票），计算最大利润。只能先买后卖
 * Example 1:
 * // Input: [7, 1, 5, 3, 6, 4]
 * // Output: 5
 * Example 2:
 * // Input: [7, 6, 4, 3, 1]
 * // Output: 0
 */

public class _121_BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = Integer.MIN_VALUE;
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return Math.max(0, maxProfit);
    }

    public int maxProfit2(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > minPrice) {
                maxProfit = Math.max(prices[i] - minPrice, maxProfit);
            } else {
                minPrice = prices[i];
            }
        }
        return maxProfit;
    }

    /**
     * 通过动态代理开发
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        for (int i = 0; i < prices.length; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][1] + prices[i], dp[i-1][0]);
            // dp[i][1] = Math.max(dp[i-1][0] - prices[i], dp[i-1][1]);
            dp[i][1] = Math.max(0 - prices[i], dp[i-1][1]);
        }
        return dp[prices.length - 1][0];
    }

    public static void main(String[] args) {
        System.out.println(new _121_BestTimeToBuyAndSellStock().maxProfit3(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
