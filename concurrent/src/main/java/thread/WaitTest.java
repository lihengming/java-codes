package thread;

/**
 * Created by 李恒名 on 2017/6/16.
 */
public class WaitTest {

    public synchronized void work() {
        System.out.println("Begin Work");
        try {
            //等待1000毫秒后，自动唤醒继续执行
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work End");
    }


    public static void main(String[] args) {
        WaitTest test = new WaitTest();

        new Thread(() -> test.work()).start();
    }
}
