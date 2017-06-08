package pcmodel;

import java.util.List;

/**
 * Created by 李恒名 on 2017/6/8.
 * <p>
 * 消费者
 */
public class Consumer implements Runnable {

    private List<Product> buffer;

    public Consumer(List<Product> buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true) {
            synchronized (buffer) {
                while (buffer.isEmpty()) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("消费者[" + Thread.currentThread().getName() + "]消费了一个产品：" + buffer.remove(0));
                buffer.notifyAll();
            }
        }
    }


}
