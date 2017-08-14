package juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by 李恒名 on 2017/6/18.
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        WC wc = new WC();
        new Thread(() -> wc.use()).start();
        new Thread(() -> wc.use()).start();
        new Thread(() -> wc.use()).start();

        new Thread(() -> wc.use()).start();
        new Thread(() -> wc.use()).start();
        /**
         输出：
             Thread-1 正在使用卫生间
             Thread-2 正在使用卫生间
             Thread-0 正在使用卫生间
             Thread-0 使用完毕
             Thread-2 使用完毕
             Thread-1 使用完毕
             Thread-3 正在使用卫生间
             Thread-4 正在使用卫生间
             Thread-4 使用完毕
             Thread-3 使用完毕
         */
    }
}

class WC {
    private Semaphore semaphore = new Semaphore(3);//最大线程许可量

    public void use() {
        try {
            //获得许可
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() +" 正在使用卫生间");
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() +" 使用完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            //释放许可
            semaphore.release();
        }
    }
}
