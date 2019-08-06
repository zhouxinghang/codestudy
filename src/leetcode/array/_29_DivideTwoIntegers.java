package leetcode.array;

/**
 * @author zhouxinghang
 * @date 2019-07-29
 * 给定两个整数，被除数 dividendL 和除数 divisorL。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回商。除数不能为0，均为32位有符号整数，除法结果溢出，则返回 2^31 − 1。
 *
 * Input: dividendL = 7, divisorL = -3
 * Output: -2
 */
public class _29_DivideTwoIntegers {

    /**
     * 使用long类型来处理，就不用担心会出现结果溢出的情况
     * @param divisor
     * @param dividend
     * @return
     */
    public int divide(int dividend, int divisor) {
        long dividendL = dividend;
        long divisorL = divisor;
        boolean sign = (dividendL > 0) ^ (divisorL > 0);
        dividendL = Math.abs(dividendL);
        divisorL = Math.abs(divisorL);
        int count = 0;
        long result = 0;
        while (dividendL >= divisorL) {
            divisorL <<= 1;
            count ++;
        }
        if (count == 0) {
            return 0;
        }
        while (count > 0) {
            divisorL >>= 1;
            count --;
            if (divisorL <= dividendL) {
                result += 1L<<count;
                dividendL -= divisorL;
            }
        }
        if (sign) {
            result = - result;
        }
        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) result;
    }

    /**
     * 给定两个整数，被除数 dividendL 和除数 divisorL。
     * 将两数相除，要求不使用乘法、除法和 mod 运算符。
     * 返回被除数 dividendL 除以除数 divisorL 得到的商
     * 除数倍增+迭代
     * @param dividendL
     * @param divisorL
     * @return
     */
    public int divide2(int dividendL, int divisorL) {
        //排除特殊情况
        if (dividendL == Integer.MIN_VALUE && divisorL == -1)
            return Integer.MAX_VALUE;
        //计数器
        int count = 0;
        //记录加倍前的doubledivisorL
        int tmpdivisorL = divisorL;
        //记录加倍后的doubledivisorL
        int doubledivisorL = tmpdivisorL;
        //记录加倍前的counttime
        int counttime = 1;
        //记录加倍前的counttime
        int doublecounttime = counttime;
        while (dividendL != 0) {
            //恢复初始情况
            tmpdivisorL = divisorL;
            doubledivisorL = tmpdivisorL;
            counttime = 1;
            doublecounttime = counttime;
            //这样判断可以避免溢出,不要用abs()
            if ((dividendL <= 0 && divisorL < 0 && dividendL <= divisorL) ||
                (dividendL >= 0 && divisorL > 0 && dividendL >= divisorL)||
                (dividendL <= 0 && divisorL > 0 && dividendL <= 0-divisorL) ||
                (dividendL >= 0 && divisorL < 0 && 0-dividendL <= divisorL)) {
                //除数和被除数同号的情况,防止溢出
                while ((dividendL <= 0 && divisorL < 0 && dividendL <= doubledivisorL && (Integer.MIN_VALUE-doubledivisorL)<=doubledivisorL) ||
                       (dividendL >= 0 && divisorL > 0 && dividendL >= doubledivisorL &&(Integer.MAX_VALUE-doubledivisorL)>=doubledivisorL) ||
                       (dividendL <= 0 && divisorL > 0 && dividendL <= 0-doubledivisorL && (Integer.MIN_VALUE+doubledivisorL)<=0-doubledivisorL) ||
                       (dividendL >= 0 && divisorL < 0 && 0-dividendL <= doubledivisorL) && (Integer.MAX_VALUE+doubledivisorL)>=0-doubledivisorL) {
                    //记录加倍前的doubledivisorL,counttime
                    tmpdivisorL = doubledivisorL;
                    counttime = doublecounttime;
                    //加倍,注意不能超过范围
                    doubledivisorL += doubledivisorL;
                    doublecounttime += doublecounttime;
                }
                //更新数值
                count += (dividendL <= 0 && divisorL < 0) ||
                         (dividendL >= 0 && divisorL > 0)?counttime:0-counttime;
                dividendL -= (dividendL <= 0 && divisorL < 0) ||
                            (dividendL >= 0 && divisorL > 0)? tmpdivisorL:0-tmpdivisorL;

            }else {
                return count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new _29_DivideTwoIntegers().divide(-2147483648 ,1));
        System.out.println(Math.abs(-2147483647));
        int a = Integer.MIN_VALUE;
        long al = a;
        System.out.println(al / -1L);
        System.out.println(-1<<31);
        System.out.println(1>>31 -1);
    }


}
