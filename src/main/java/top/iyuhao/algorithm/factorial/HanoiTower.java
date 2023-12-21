package top.iyuhao.algorithm.factorial;


import java.util.LinkedList;

/**
 * @author yuhao
 * @time 2023/10/28
 * 汉诺塔问题
 **/
public class HanoiTower {
    /**
     * 柱子
     */
    private static LinkedList<Integer> a;
    private static LinkedList<Integer> b;
    private static LinkedList<Integer> c;

    public HanoiTower() {
        a = new LinkedList<>();
        b = new LinkedList<>();
        c = new LinkedList<>();
    }

    public void init(int n) {
        for (int i = n; i > 0; i--) {
            a.push(i);
        }
    }

    /**
     *
     * @param n 圆盘个数
     * @param from 原柱子
     * @param to 目标柱子
     * @param other 其他柱子
     */
    private static void move(int n, LinkedList<Integer> from, LinkedList<Integer> to, LinkedList<Integer> other) {
        if (n == 0) {
            return;
        }
        //将n-1个盘子从from移动到other
        move(n - 1, from, other, to);
        //中间
        to.push(from.pop());
        //将n-1个盘子从other移动到to
        move(n - 1, other, to, from);
    }
}
