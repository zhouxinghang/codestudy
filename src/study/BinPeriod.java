package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author zhouxinghang
 * @date 2019-08-31
 */
public class BinPeriod {
    int solution(int n) {
        int[] d = new int[30];
        int l = 0;
        int p;
        while (n > 0) {
            d[l] = n % 2;
            n /= 2;
            l++;
        }
        System.out.println(new ArrayList<>(Collections.singletonList(d)));
        for (p = 1; p < 1 + l; ++p) {
            int i;
            boolean ok = true;
            for (i = 0; i < l - p; ++i) {
                if (d[i] != d[i + p]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return p;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new BinPeriod().solution(238));
    }
}
