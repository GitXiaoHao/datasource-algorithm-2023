package top.iyuhao.algorithm.factorial;

import java.util.Arrays;

/**
 * @author yuhao
 * @time 2023/10/26
 * 递归求斐波那契第n项
 **/
public class FactorialFibonacci {
    public static void main(String[] args) {
        int n = 40;
        FactorialFibonacci fibonacci = new FactorialFibonacci();
        new Thread(() -> System.out.println("thread1 " + fibonacci.fibonacci(n))).start();
        new Thread(() -> System.out.println("thread2 " + fibonacci.fibonacci3(n))).start();
        System.out.println("main " + fibonacci.fibonacci2(n));
    }

    /**
     * 时间复杂度为 O(2^n)
     *
     * @param n 次数
     * @return 返回斐波那契数
     */
    public int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public int fibonacci2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 使用Memoization（记忆法）改进
     */
    public int fibonacci3(int n) {
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        cache[0] = 0;
        cache[1] = 1;
        return f(n,cache);
    }

    /**
     *
     * @param n 次数
     * @param cache 缓存
     * @return 返回斐波那契数
     */
    private static int f(int n, int[] cache) {
        if (cache[n] != -1) {
            return cache[n];
        }
        cache[n] = f(n - 1, cache) + f(n - 2, cache);
        return cache[n]; // return value for n
    }

}
