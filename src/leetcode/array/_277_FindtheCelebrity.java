package leetcode.array;

/**
 * @author zhouxinghang
 * @date 2019/2/25
 * Suppose you are at a party with n people (labeled from 0 ton - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the othern - 1 people know him/her but he/she does not know any of them.
 * Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 * You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a functionint findCelebrity(n), your function should minimize the number of calls toknows.
 * Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return-1.
 *
 */
public class _277_FindtheCelebrity {

    /**
     * time : O(n)
     * space : O(1)
     * @param n
     * @return
     */
    public int findCelebrity(int n) {
        if (n < 2) {
            return -1;
        }
        // 第一个循环判断可能是名人的人。假如名人是0，从1~j，0都不认识，所以排除了1~j，如果0认识j，排除了0是名人，j可能是名人
        int possible = 0;
        for (int i = 1; i < n; i++) {
            if (knows(possible, i)) {
                possible = i;
            }
        }
        // 第二个循环check 找出的名人是否正确
        for (int i = 0; i < n; i++) {
            if (possible != i && ((knows(possible, i) || !knows(i, possible)))) {
                return -1;
            }
        }
        return possible;
    }

    public boolean knows(int a, int b) {
        return true;
    }
}
