package juc;

import java.util.concurrent.*;

/**
 * Created by 李恒名 on 2017/6/18.
 */
public class ExecutorTest {
    public static void main(String[] args) {
        //创建固定线程数的线程池
        ExecutorService es = Executors.newFixedThreadPool(2);
        //执行传统Runnable任务
        es.execute(new RunnableTask());
        //执行Callable任务并获得任务结果Future
        Future future = es.submit(new CallableTask());
        try {
            System.out.println("Calculate Completed Sum：" + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        //关键线程池
        es.shutdown();
        /**
         输出：
         pool-1-thread-1 Started By Runnable
         pool-1-thread-2 Started By Callable
         Calculate Completed Sum：2
         */
    }
}
class CallableTask implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " Started By Callable");
        //求和
        return 1 + 1;
    }
}
class RunnableTask implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Started By Runnable");

    }
}


