package top.iyuhao.algorithm;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import top.iyuhao.algorithm.queue.MyLinkedListQueue;

import java.util.List;

/**
 * @author yuhao
 * @time 2023/12/29 22:59
 **/

public class TestLinkedListQueue {
    @Test
    public void testOffer(){
        MyLinkedListQueue<Integer> queue = new MyLinkedListQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.poll();
        Assertions.assertIterableEquals(List.of(2, 3, 4), queue);
    }
}
