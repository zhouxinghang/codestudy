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
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int res = 0;
        /**
         * 遍历，如果某个点，引用数量小于索引，
         * > res 变为 >= res + 1 就好理解些了。res + 1 表示索引因子，
         */
        while(res < citations.length && citations[citations.length - res - 1] > res) {
            res ++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new _274_HIndex().hIndex(new int[]{3,0,6,1,5}));
    }
}
