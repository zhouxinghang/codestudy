package leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhouxinghang
 * @date 2019-08-26
 * 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 */
public class _49_GroupAnagrams {

    /**
     * 方法1，排序。时间复杂度 O(NKlogK)，
     * 方法2，计数方式，记录每个字母出现的次数，作为key。 key = "0#0#0#0#0#"，数字分别代表 abcde 出现的次数，# 用来分割。这样的话，"abb" 就映射到了 "1#2#0#0#0"。
     *      时间复杂度 O(NK)，空间复杂度 O(NK)
     * 方法3，正整数的唯一分解定力，用一个数组存储质数 prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103}。
     *      每个prime表示一个字母的权重值，计算其权重积，作为key。比如 abc = 2*3*5 = 30.
     *      缺点是：容易造成越界
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<Integer, List<String>> hash = new HashMap<>();
        //每个字母对应一个质数
        int[] prime = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103 };
        for (int i = 0; i < strs.length; i++) {
            int key = 1;
            //累乘得到 key
            for (int j = 0; j < strs[i].length(); j++) {
                key *= prime[strs[i].charAt(j) - 'a'];
            }
            if (hash.containsKey(key)) {
                hash.get(key).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                hash.put(key, temp);
            }

        }
        return new ArrayList<List<String>>(hash.values());
    }

    public static void main(String[] args) {
        System.out.println(new _49_GroupAnagrams().groupAnagrams(new String[]{"abcc", "ccc"}));
    }
}
