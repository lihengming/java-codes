package synchronizeds;

/**
 * Created by 李恒名 on 2017/6/14.
 *
 * 同步静态方法
 */
public class SynchronizedStaticMethodTest {

    public /*synchronized*/  static void method1() {
        try {
            //模拟方法需要执行100毫秒
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method1() execute!");

    }

    public /*synchronized*/ static void method2() {
        System.out.println("method2() execute!");
    }

    public static void main(String[] args) {
        final SynchronizedStaticMethodTest test1 = new SynchronizedStaticMethodTest();
        final SynchronizedStaticMethodTest test2 = new SynchronizedStaticMethodTest();
        // 使用Java 8 lambda 简化代码
        new Thread(() -> test1.method1()).start();
        new Thread(() -> test2.method2()).start();

        new Thread(() -> SynchronizedStaticMethodTest.method1()).start();
        new Thread(() -> SynchronizedStaticMethodTest.method2()).start();

        /**
         输出：
         method2() execute!
         method2() execute!
         method1() execute!
         method1() execute!

         使用synchronized修饰方法后：
         method1() execute!
         method2() execute!
         method1() execute!
         method2() execute!
         **/
    }
}

