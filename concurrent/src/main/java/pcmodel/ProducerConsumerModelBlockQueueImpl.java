package pcmodel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by 李恒名 on 2017/6/19.
 * <p>
 * 生产者-消费者模型的阻塞队列实现
 */
public class ProducerConsumerModelBlockQueueImpl {
    public static void main(String[] args) {
        final int maxSize = 10;//产品最大库存量
        BlockingQueue<Product> buffer = new LinkedBlockingQueue<Product>(maxSize);
        ExecutorService es = Executors.newFixedThreadPool(5);
        //两个生产者
        es.execute(new Producer(buffer));
        es.execute(new Producer(buffer));
        //三个消费者
        es.execute(new Consumer(buffer));
        es.execute(new Consumer(buffer));
        es.execute(new Consumer(buffer));

        es.shutdown();

        /**
         输出：
         ...
         生产者[pool-1-thread-1]生产了一个产品：MAC
         消费者[pool-1-thread-3]消费了一个产品：MAC
         消费者[pool-1-thread-4]消费了一个产品：MAC
         消费者[pool-1-thread-4]消费了一个产品：MAC
         消费者[pool-1-thread-4]消费了一个产品：MAC
         生产者[pool-1-thread-2]生产了一个产品：MAC
         生产者[pool-1-thread-2]生产了一个产品：MAC
         生产者[pool-1-thread-2]生产了一个产品：MAC
         ...
         */
    }

    //产品
    static class Product {

        private String name;

        public Product(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
    //生产者
    static class Producer implements Runnable {

        private BlockingQueue<Product> buffer;

        public Producer(BlockingQueue<Product> buffer) {
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
    //消费者
    static class Consumer implements Runnable {

        private BlockingQueue<Product> buffer;

        public Consumer(BlockingQueue<Product> buffer) {
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
}
