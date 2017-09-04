package thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by 李恒名 on 2017/8/29.
 *
 *  死锁测试，线程1获得A锁（monitor）之后尝试获得B锁，线程2获得B锁后尝试获得A锁，线程1和线程2即产生死锁。
 */
public class DeadlockTest {
    public static void main(String[] args) {
        Object lockA = new Object();
        Object lockB = new Object();
        new Thread(() -> {
            //线程1获得A锁
            synchronized (lockA){
                System.out.println("Thread 1 get lock A .");
                //增加死锁发生几率
                try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {}
                //尝试获得B锁
                System.out.println("Thread 1 try get lock B, waiting...");
                synchronized (lockB){
                    //永远无法获得B锁，所以不会打印出来
                    System.out.println("Thread 1 get lock B .");
                }

            }
        }, "Thread 1").start();
        new Thread(() -> {
            //线程2获得B锁
            synchronized (lockB){
                System.out.println("Thread 2 get lock B .");
                //增加死锁发生几率
                try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {}
                //尝试获得A锁
                System.out.println("Thread 2 try get lock A, waiting...");
                synchronized (lockA){
                    //永远无法获得A锁，所以不会打印出来
                    System.out.println("Thread 2 get lock A .");
                }

            }
        }, "Thread 2").start();

        /**
         > jstack [pid]

         "Thread 2":
         waiting to lock monitor 0x0000000002cc7338 (object 0x000000076b7b31d8, a java.lang.Object),
         which is held by "Thread 1"
         "Thread 1":
         waiting to lock monitor 0x0000000002cc9bc8 (object 0x000000076b7b31e8, a java.lang.Object),
         which is held by "Thread 2"

         */
    }
}
