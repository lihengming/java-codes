package unsafe;

/**
 * Created by 李恒名 on 2017/6/21.
 */
public class UnsafeLockTest {

    public static void main(String[] args) {
        TestTask test = new TestTask();
        new Thread(() -> test.method1()).start();
        new Thread(() -> test.method2()).start();

        /**
         输出：
         method1() execute!
         method2() execute!

         不加锁保持同步：
         method2() execute!
         method1() execute!
         */
    }

    static class TestTask {
        private UnsafeLock lock = new UnsafeLock();

        public void method1() {
            lock.lock(this);
            try {
                //模拟方法需要执行100毫秒
                Thread.sleep(100);
                System.out.println("method1() execute!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(this);
            }
        }

        public void method2() {
            lock.lock(this);
            System.out.println("method2() execute!");
            lock.unlock(this);
        }
    }
}
