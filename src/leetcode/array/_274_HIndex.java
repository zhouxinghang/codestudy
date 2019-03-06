package leetcode.array;

import java.util.Arrays;

/**
 * @author zhouxinghang
 * @date 2019/3/5
 * 给定一个数组，值为论文的被引用数量，求出其 HIndex
 * 假设一个研究者的 h 因子为 10，则表明该研究者被引用次数大于等于 10 的文章数量也应大于等于 10。
 *
 * Example:
    Input: citations = [3,0,6,1,5]
    Output: 3
    0,1,3,5,6
    0,1,2,5,6
 * Note:
 *  If there are several possible values for h, the maximum one is taken as the h-index.
 *
 */
public class _274_HIndex {
    /**
     * 从前往后查找排序后的列表，直到某篇论文的序号大于该论文被引次数。所得序号减一即为H指数。
     * 遍历，如果某个点，引用数量小于索引，
     * > res 变为 >= res + 1 就好理解些了。res + 1 表示索引因子，
     *
     * time : O(nlogn) space : O(1)
     */
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int res = 0;
        while(res < citations.length && citations[citations.length - res - 1] > res) {
            res ++;
        }
        return res;
    }

    /**
     * 使用一个大小为 n+1 的数组count统计引用数，对于count[i]表示的是引用数为 i 的文章数量。
     * time : O(n) space : O(n)
     *
     * @param citations
     * @return
     */
    public int hIndex2(int[] citations) {
        int n = citations.length;
        int[] count = new int[n + 1];
        for (int c : citations) {
            if (c >= n) {
                // 如果引用数量超过 n，就记在
                count[n] += 1;
            } else {
                count[c] += 1;
            }
        }
        for (int i = n; i > 0; i--) {
            if(count[i] >= i) {
                return i;
            }
            count[i - 1] += count[i];
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new _274_HIndex().hIndex2(new int[]{3,0,6,1,5}));
    }
}
