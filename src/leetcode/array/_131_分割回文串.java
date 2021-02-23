package leetcode.array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author zhouxinghang
 * Created on 2021-02-22
 * 给定一个字符串s，将s分割成一些子串，使每个子串都是回文串。返回所有分割方案
 * aab -> [[a,a,b],[aa,b]]
 */
public class _131_分割回文串 {

    public static void main(String[] args) {
        System.out.println("abc".substring(0));
        System.out.println("[" + "abc".substring(0,0) + "]");
        _131_分割回文串 obj = new _131_分割回文串();
        System.out.println(obj.partition("aab"));
    }

    /**
     * 使用回溯法
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        Deque<String> path = new ArrayDeque<>();
        backtrack(res, path, s, 0, s.length());
        return res;
    }

    /**
     *
     * @param res 结果
     * @param path 探索路径
     * @param s 探索对象，变量
     * @param start 变量，开始位置
     * @param len 变量，长度
     */
    public void backtrack(List<List<String>> res, Deque<String> path, String s, int start, int len) {
        if (start == len) {
            res.add(new ArrayList<>(path));
        }
        for (int i = start; i< len; i++) {
            if (!isPalindrome(s, start, i)) {
                continue;
            }
            // start, i 都是可以取到的，截取的时候要 i+1
            path.add(s.substring(start, i+1));
            backtrack(res, path, s, i+1, len);
            path.removeLast();
        }
    }

    private boolean isPalindrome(String str, int left, int right) {
        // 这一步不会走到
//        if (left == right) {
//            return false;
//        }
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
