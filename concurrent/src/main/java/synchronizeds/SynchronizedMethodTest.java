package synchronizeds;

/**
 * Created by 李恒名 on 2017/6/14.
 * <p>
 * 同步成员方法
 */
public class SynchronizedMethodTest {

    public /*synchronized*/ void method1() {
        try {
            //模拟方法需要执行100毫秒
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method1() execute!");

    }

    public /*synchronized*/ void method2() {
        System.out.println("method2() execute!");
    }

    public static void main(String[] args) {
        final SynchronizedMethodTest test = new SynchronizedMethodTest();
        // 使用Java 8 lambda 简化代码
        new Thread(() -> test.method1()).start();
        new Thread(() -> test.method2()).start();

        /**
         输出：
             method2() execute!
             method1() execute!

         使用synchronized修饰方法后：
             method1() execute!
             method2() execute!
         **/
    }
}

