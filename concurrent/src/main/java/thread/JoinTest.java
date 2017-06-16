package thread;

/**
 * Created by 李恒名 on 2017/6/16.
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " Work End"));
        thread1.start();
        thread1.join();//合并到主线程，主线程将等待该子线程执行完毕才会执行

        Thread thread2 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " Work End"));
        thread2.start();
        thread1.join();//合并到主线程，主线程将等待该子线程执行完毕才会执行

        System.out.println("Main Thread Work End");

        /**
         输出：
             Thread-0 Work End
             Thread-1 Work End
             Main Thread Work End

         不使用join()：
             Main Thread Work End
             Thread-0 Work End
             Thread-1 Work End
         */
    }
}
