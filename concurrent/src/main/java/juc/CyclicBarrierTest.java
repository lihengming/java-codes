package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by 李恒名 on 2017/6/18.
 */
public class CyclicBarrierTest {
    //定义一个barrier并设置parties，当线程数达到parties后，barrier失效，线程可以继续运行，在未达到parties值之前，线程将持续等待。
    static CyclicBarrier barrier = new CyclicBarrier(3,()-> System.out.println("栅栏：“这么多猪，我恐怕扛不住了”"));

    static void go() {
        System.out.println("小猪[" + Thread.currentThread().getName() + "] 在栅栏边等待其他小猪");
        try {
            barrier.await();//等待数+1
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("猪到齐了，小猪[" + Thread.currentThread().getName() + "] 与其他小猪一起冲破栅栏");
    }

    public static void main(String[] args) {

        new Thread(() -> go()).start();
        new Thread(() -> go()).start();
        new Thread(() -> go()).start();

        /**
         输出：
             小猪[Thread-0] 在栅栏边等待其他小猪
             小猪[Thread-1] 在栅栏边等待其他小猪
             小猪[Thread-2] 在栅栏边等待其他小猪
             栅栏：“这么多猪，我恐怕扛不住了”
             猪到齐了，小猪[Thread-2] 与其他小猪一起冲破栅栏
             猪到齐了，小猪[Thread-0] 与其他小猪一起冲破栅栏
             猪到齐了，小猪[Thread-1] 与其他小猪一起冲破栅栏
         */
    }
}


