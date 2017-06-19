package juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by 李恒名 on 2017/6/18.
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(5);//工作队列容量5
        int corePoolSize = 1;//核心线程数1
        int maximumPoolSize = 2;//最大线程数2
        ThreadPoolExecutor executor =  new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 1, TimeUnit.MILLISECONDS,   workQueue);

        executor.execute(new TestTask());//执行任务，创建一个核心线程

        executor.execute(new TestTask());//核心线程忙，放入队列，队列内任务数+1
        executor.execute(new TestTask());//核心线程忙，放入队列，队列内任务数+1
        executor.execute(new TestTask());//核心线程忙，放入队列，队列内任务数+1
        executor.execute(new TestTask());//核心线程忙，放入队列，队列内任务数+1
        executor.execute(new TestTask());//核心线程忙，放入队列，队列内任务数+1
        System.out.println("WorkQueue Size：" + workQueue.size());//WorkQueue Size：5，队列满

        executor.execute(new TestTask());//核心线程忙，队列也满了，继续新的线程执行任务
        System.out.println("PoolSize Size：" + executor.getPoolSize());//PoolSize Size：2，达到最大线程数

        executor.execute(new TestTask());//继续执行任务，则抛出异常，拒绝服务

        executor.shutdown();

        /**
         输出：
         WorkQueue Size：5
         PoolSize Size：2
         Exception in thread "main" java.util.concurrent.RejectedExecutionException:
         Task juc.TestTask@45ee12a7 rejected  from java.util.concurrent.ThreadPoolExecutor@330bedb4
         [Running, pool size = 2, active threads = 1, queued tasks = 5, completed tasks = 0]
         */
    }

}

class TestTask implements Runnable{

    @Override
    public void run() {
        try {
            TimeUnit.DAYS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
