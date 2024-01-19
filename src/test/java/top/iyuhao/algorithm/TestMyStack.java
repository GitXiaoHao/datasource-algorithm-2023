package top.iyuhao.algorithm;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import top.iyuhao.algorithm.stack.MyArrayStack;

import java.util.List;

/**
 * @author yuhao
 * @time 2024/1/9 11:43
 **/
public class TestMyStack {
    @Test
    public void test01(){
        MyArrayStack<Integer> stack = new MyArrayStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Assertions.assertFalse(stack.push(4));
        Assertions.assertIterableEquals(List.of(3,2,1),stack);
        System.out.println(stack.peek());
        Assertions.assertIterableEquals(List.of(3,2,1),stack);
        System.out.println(stack.pop());
        Assertions.assertIterableEquals(List.of(2,1),stack);
        System.out.println(stack.size());
    }
}
