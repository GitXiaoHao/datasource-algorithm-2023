package top.iyuhao.algorithm.factorial;


/**
 * @author yuhao
 * @time 2023/10/23
 * 递归
 **/
public class Factorial {
    public int f(int n){
        if (n == 1) {
            return 1;
        }
        return n * f(n - 1);
    }
    /**
     * 递归反向打印字符串
     */
    public void reversePrint(String str){
        if (str.isEmpty()) {
            return;
        }
        reversePrint(str.substring(1));
        System.out.print(str.charAt(0));
    }
}
