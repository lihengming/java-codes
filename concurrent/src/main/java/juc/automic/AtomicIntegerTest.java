package juc.automic;

import org.junit.Assert;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 李恒名 on 2017/6/18.
 */
public class AtomicIntegerTest {

    public static AtomicInteger sharedValue = new AtomicInteger();

    //每次将sharedValue的值增加10
    public static void increment() {
        for (int i = 0; i < 10; i++) {
            sharedValue.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int maxThreads = 10000;
        for (int i = 0; i < maxThreads; i++) {
            Thread thread = new Thread(() -> increment());
            thread.start();
        }

        Thread.sleep(3000);//等待所有子线程执行完成
        Assert.assertEquals(sharedValue.get(), 10000 * 10);
        //Assert Pass
    }
}
