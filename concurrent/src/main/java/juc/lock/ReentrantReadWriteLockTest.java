package juc.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by 李恒名 on 2017/6/17.
 */
public class ReentrantReadWriteLockTest {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private String content = "Old";

    public void write() {
        lock.writeLock().lock();
        System.out.println(Thread.currentThread() +" LOCK");
        try {
            try {
                //模拟方法需要执行100毫秒
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            content = "New";
            System.out.println(Thread.currentThread() +" Write content to: " + content);
        } finally {
            System.out.println(Thread.currentThread() +" UNLOCK");
            lock.writeLock().unlock();
        }
    }

    public void read() {
        lock.readLock().lock();
        System.out.println(Thread.currentThread() +" LOCK");
        try {
            try {
                //模拟方法需要执行100毫秒
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() +" Read content is: " + content);
        } finally {
            System.out.println(Thread.currentThread() +" UNLOCK");
            lock.readLock().unlock();
        }
    }


    public static void main(String[] args) {
        final ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();
        // 使用Java 8 lambda 简化代码
        new Thread(() -> test.write()).start();
        new Thread(() -> test.read()).start();
        new Thread(() -> test.read()).start();

        /**
         输出：
             Thread[Thread-0,5,main] LOCK
             Thread[Thread-0,5,main] Write content to: New
             Thread[Thread-0,5,main] UNLOCK
             Thread[Thread-1,5,main] LOCK
             Thread[Thread-2,5,main] LOCK
             Thread[Thread-1,5,main] Read content is: New
             Thread[Thread-2,5,main] Read content is: New
             Thread[Thread-1,5,main] UNLOCK
             Thread[Thread-2,5,main] UNLOCK
         可以看到两个线程在读的时候可以同时获得锁
         **/
    }
}
