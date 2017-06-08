package pcmodel;

import java.util.concurrent.BlockingQueue;

/**
 * Created by 李恒名 on 2017/6/8.
 *
 *  消费者
 *
 */
public class ConsumerBlockedQueueImpl implements Runnable {

    private BlockingQueue<Product> buffer;

    public ConsumerBlockedQueueImpl(BlockingQueue<Product> buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true) {
            try {
                System.out.println("消费者[" + Thread.currentThread().getName() + "]消费了一个产品：" + buffer.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
