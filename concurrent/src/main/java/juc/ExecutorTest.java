package juc;

import java.util.concurrent.*;

/**
 * Created by 李恒名 on 2017/6/18.
 */
public class ExecutorTest {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future future = es.submit(new Task());
        try {
            System.out.println("Calculate Completed Sum：" + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        es.shutdown();
    }
}

class Task implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " Started");
        //求和
        return 1 + 1;
    }
}

