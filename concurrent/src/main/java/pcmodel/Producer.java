package pcmodel;


import java.util.List;

/**
 * Created by 李恒名 on 2017/6/8.
 * <p>
 * 生产者
 */
public class Producer implements Runnable {

    private final int maxSize = 10;//产品最大库存量

    private List<Product> buffer;

    public Producer(List<Product> buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true) {
            synchronized (buffer) {
                while (buffer.size() >= maxSize) {
                    try {
                        buffer.wait();//将当前线程放入等锁(buffer对象的锁)池，并释放锁。
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //模拟生产需要500毫秒
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Product product = new Product("iPhone 手机");
                buffer.add(product);
                System.out.println("生产者[" + Thread.currentThread().getName() + "]生产了一个产品：" + product);
                buffer.notifyAll();//生产完毕通知等待池内的其他线程(生产者或消费者都有可能)
            }
        }
    }
}
