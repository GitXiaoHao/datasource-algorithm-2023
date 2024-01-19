package top.iyuhao.algorithm.stack;

/**
 * @author yuhao
 * @time 2024/1/9 11:26
 **/
public interface MyStack<E> {
    /**
     *  向栈顶压入元素
     * @param value 待压入值
     * @return 是否压入成功
     */
    boolean push(E value);
    /**
     * 从栈顶弹出元素
     * @return 弹出的值
     *
     */
    E pop();
    /**
     * 查看栈顶元素
     * @return 栈顶元素的值
     */
    E peek();
    /**
     * 检查栈是否为空
     * @return 空返回true
     */
    boolean isEmpty();
    /**
     * 检查栈是否已满
     * @return 满返回true，否则返回false。
     */
    boolean isFull();

    /**
     * 栈大小
     * @return 栈大小，如果为空返回0。
     */
    int size();
}
