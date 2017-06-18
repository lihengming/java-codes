package juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 李恒名 on 2017/6/17.
 */
public class ReentrantLockTest {

    private final Lock lock = new ReentrantLock();
    private String content = "Old";

    public void write() {
        lock.lock();
        //由于ReentrantLock是可重入锁，所以可以重复的加锁。
        //lock.lock();
        //lock.lock();
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
            lock.unlock();
            //进行多少次加锁操作，也需要对应多少次解锁操作。
            //lock.unlock();
            //lock.unlock();
        }
    }

    public void read() {
        lock.lock();
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
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        final ReentrantLockTest test = new ReentrantLockTest();
        // 使用Java 8 lambda 简化代码
        new Thread(() -> test.write()).start();
        new Thread(() -> test.read()).start();
        new Thread(() -> test.read()).start();

        /**
         不使用锁的输出：
             Thread[Thread-1,5,main] Read content is: Old
             Thread[Thread-2,5,main] Read content is: Old
             Thread[Thread-0,5,main] Write content to: New

         使用锁后：
             Thread[Thread-0,5,main] LOCK
             Thread[Thread-0,5,main] Write content to: New
             Thread[Thread-0,5,main] UNLOCK
             Thread[Thread-1,5,main] LOCK
             Thread[Thread-1,5,main] Read content is: New
             Thread[Thread-1,5,main] UNLOCK
             Thread[Thread-2,5,main] LOCK
             Thread[Thread-2,5,main] Read content is: New
             Thread[Thread-2,5,main] UNLOCK
         **/
    }
}
