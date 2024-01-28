package top.iyuhao.algorithm.queue.block;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yuhao
 * @time 2024/1/19 21:28
 **/
@SuppressWarnings("all")
public class MyBlockingQueueImpl<E> implements MyBlockingQueue<E>{
    private final E[] array;
    private AtomicInteger size;
    private int head;
    private int tail;
    private final int capacity;
    private final ReentrantLock headLock;
    private final ReentrantLock tailLock;
    private final Condition headWaits;
    private final Condition tailWaits;
    public MyBlockingQueueImpl(int capacity) {
        this.capacity = capacity;
        array = (E[]) new Object[capacity];
        size = new AtomicInteger();
        head = 0;
        tail = 0;
        headLock = new ReentrantLock();
        tailLock = new ReentrantLock();
        headWaits = headLock.newCondition();
        tailWaits = tailLock.newCondition();
    }

    @Override
    public void offer(E e) throws InterruptedException {
        tailLock.lockInterruptibly();
        //添加前的元素个数
        int num;
        try {
            while (isFull()) {
                tailWaits.await();
            }
            array[tail] = e;
            tail = (tail + 1) % capacity;
            num = size.getAndIncrement();
            if (num + 1 < capacity) {
                tailWaits.signal();
            }
        }finally {
            tailLock.unlock();
        }
        if (num == 0) {
            //唤醒等待非空线程
            headLock.lock();
            try {
                headWaits.signal();
            } finally {
                headLock.unlock();
            }
        }
    }

    @Override
    public boolean offer(E e, long milliseconds) throws InterruptedException {
        tailLock.lockInterruptibly();
        //添加前的元素个数
        int num;
        try {
            long nanos = TimeUnit.MILLISECONDS.toNanos(milliseconds);
            while (isFull()) {
                if (nanos <= 0) {
                    return false;
                }
                nanos = tailWaits.awaitNanos(nanos);
            }
            array[tail] = e;
            tail = (tail + 1) % capacity;
            num = size.getAndIncrement();
            if (num + 1 < capacity) {
                tailWaits.signal();
            }
        }finally {
            tailLock.unlock();
        }
        if (num == 0) {
            //唤醒等待非空线程
            headLock.lock();
            try {
                headWaits.signal();
            } finally {
                headLock.unlock();
            }
        }
        return true;
    }

    @Override
    public E poll() throws InterruptedException {
        headLock.lockInterruptibly();
        E e;
        //计数
        int num;
        try {
            while (isEmpty()) {
                headWaits.await();
            }
            e = array[head];
            array[head] = null;
            head = (head + 1) % capacity;
            num = size.decrementAndGet();
            if (num > 0) {
                headWaits.signal();
            }
        }finally {
            headLock.unlock();
        }
        //队列从满 -> 不满时，由poll唤醒等待
        if (num == capacity - 1) {
            //唤醒等待非满线程
            tailLock.lock();
            try {
                tailWaits.signal();
            } finally {
                tailLock.unlock();
            }
        }
        return e;
    }
    private boolean isEmpty(){
        return size.get() == 0;
    }
    private boolean isFull(){
        return size.get() == capacity;
    }
}
