package juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 李恒名 on 2017/6/18.
 */
public class ConditionTest {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();


    public void work() {
        lock.lock();
        try {
            try {
                System.out.println("Begin Work");
                condition.await();
                System.out.println("Begin End");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

    public void continueWork() {
        lock.lock();
        try {
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionTest test = new ConditionTest();
        new Thread(() -> test.work()).start();

        //等待3000毫秒后唤醒，继续工作。
        Thread.sleep(3000);
        test.continueWork();
    }
}
