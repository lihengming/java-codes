package juc.aqs;

/**
 * Created by 李恒名 on 2017/6/22.
 */
public class MutexLockTest {
    public static void main(String[] args) {
        TestTask test = new TestTask();
        new Thread(() -> test.method1()).start();
        new Thread(() -> test.method2()).start();

        /**
         输出：
         method1() execute!
         method2() execute!

         不加锁的话：
         method2() execute!
         method1() execute!
         */
    }

    static class TestTask {
        private MutexLock lock = new MutexLock();

        public void method1() {
            lock.lock();
            try {
                //模拟方法需要执行100毫秒
                Thread.sleep(100);
                System.out.println("method1() execute!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void method2() {
            lock.lock();
            try {
                System.out.println("method2() execute!");
            } finally {
                lock.unlock();
            }
        }
    }
}
