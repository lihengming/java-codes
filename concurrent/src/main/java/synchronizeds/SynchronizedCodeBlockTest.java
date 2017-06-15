package synchronizeds;

/**
 * Created by 李恒名 on 2017/6/14.
 * <p>
 * 同步代码块
 */
public class SynchronizedCodeBlockTest {
    private final Object lock = new Object();

    public void method1() {
        //需获得Class对象的锁方可执行
        //synchronized (this.getClass())
        //需获得lock对象的锁方可执行
        //synchronized (lock)
        //需获得当前对象的锁方可执行
        synchronized (this) {
            try {
                //模拟方法需要执行100毫秒
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method1() execute!");
        }
    }

    public void method2() {
        //需获得Class对象的锁方可执行
        //synchronized (this.getClass())
        //需获得lock对象的锁方可执行
        //synchronized (lock)
        //需获得当前对象的锁方可执行
        synchronized (this) {
            System.out.println("method2() execute!");
        }
    }

    public static void main(String[] args) {
        final SynchronizedCodeBlockTest test = new SynchronizedCodeBlockTest();
        // 使用Java 8 lambda 简化代码
        new Thread(() -> test.method1()).start();
        new Thread(() -> test.method2()).start();
        /**
         输出：
             method1() execute!
             method2() execute!

         */
    }
}
