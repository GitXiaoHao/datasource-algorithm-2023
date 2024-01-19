package top.iyuhao.algorithm.queue;

/**
 * @author yuhao
 * @time 2023/12/21 15:42
 **/
public interface MyQueue<E> {
    /**
     * 向队列尾部插入值
     * @param value 待插入值
     * @return 插入成功返回true
     */
    boolean offer(E value);

    /**
     * 从队列中取出值
     * @return 值，如果队列为空则返回null
     */
    E poll();

    /**
     * 查看队列头部的值，不删除值
     * @return 值，如果队列为空则返回null
     */
    E peek();

    /**
     * 检查队列是否为空
     * @return 空返回true
     */
    boolean isEmpty();

    /**
     *  检查队列是否已满，不能再插入值时返回true
     * @return 满返回true，否则返回false。
     */
    boolean isFull();
}
