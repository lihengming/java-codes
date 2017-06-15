package volatiles;

/**
 * Created by 李恒名 on 2017/6/14.
 */
public class VolatileTest {

    private /*volatile*/ int sharedValue = 0;

    public static void main(String[] args) throws InterruptedException {
        VolatileTest test = new VolatileTest();
        new Thread(() -> test.listener()).start();
        new Thread(() -> test.increment()).start();

        /**
         输出：
             Value Incrementing：1
             Value Incrementing：2
             Value Incrementing：3
             Value Incrementing：4
             Value Incrementing：5
         使用 volatile 修饰 sharedValue后：
             Value Incrementing：1
             Value Changed：1
             Value Incrementing：2
             Value Changed：2
             Value Incrementing：3
             Value Changed：3
             Value Incrementing：4
             Value Changed：4
             Value Incrementing：5
             Value Changed：5
         */
    }

    public void listener() {
        int localValue = sharedValue;
        while (sharedValue < 5) {
            if (localValue != sharedValue) {
                System.out.println("Value Changed：" + sharedValue);
                localValue = sharedValue;
            }
        }
    }

    public void increment() {
        while (sharedValue < 5) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ++sharedValue;
            System.out.println("Value Incrementing：" + sharedValue);

        }
    }
}
