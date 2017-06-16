package thread;

/**
 * Created by 李恒名 on 2017/6/16.
 */
public class NotifyTest {

    public synchronized void work() {
        System.out.println("Begin Work");
        try {
            //等待唤醒
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work End");
    }

    public synchronized void continueWork() {
        notifyAll();
    }

    public static void main(String[] args) throws InterruptedException {
        NotifyTest test = new NotifyTest();
        new Thread(() -> test.work()).start();

        //等待3000毫秒后唤醒，继续工作。
        Thread.sleep(3000);
        test.continueWork();
    }
}
