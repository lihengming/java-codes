package pcmodel;


import java.util.concurrent.BlockingQueue;

/**
 * Created by 李恒名 on 2017/6/8.
 *
 *  生产者
 *
 */
public class ProducerBlockedQueueImpl implements Runnable {

    private BlockingQueue<Product> buffer;

    public ProducerBlockedQueueImpl(BlockingQueue<Product> buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true) {
            Product product = new Product("MAC");
            try {
                buffer.put(product);
                System.out.println("生产者[" + Thread.currentThread().getName() + "]生产了一个产品：" + product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
