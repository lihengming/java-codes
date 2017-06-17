package thread;

/**
 * Created by 李恒名 on 2017/6/16.
 */
public class TimedJoinTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                //模拟子线程需要执行500毫秒
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " Work End");
        });
        thread.start();
        thread.join(100);//合并到主线程，主线程将等待该子线程执行完毕才会执行，只等待100毫秒，过时不在等。

        System.out.println("Main Thread Work End");

        /**
         输出：
             Main Thread Work End
             Thread-0 Work End

         删除Thread.sleep(500);或者降到100以内：
             Thread-0 Work End
             Main Thread Work End
         */
    }
}
