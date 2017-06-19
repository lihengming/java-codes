package pcmodel;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 李恒名 on 2017/6/19.
 *
 * 生产者-消费者模型的 Object.wait()、notify()实现
 */
public class ProducerConsumerModelWaitNotifyImpl {
    public static void main(String[] args) {
        List<Product> buffer = new LinkedList<Product>();
        ExecutorService es = Executors.newFixedThreadPool(5);
        //两个生产者
        es.execute(new Producer(buffer));
        es.execute(new Producer(buffer));
        //三个消费者
        Consumer consumer = new Consumer(buffer);
        es.execute(new Consumer(buffer));
        es.execute(new Consumer(buffer));
        es.execute(new Consumer(buffer));

        es.shutdown();
        /**
         输出：
         ...
         生产者[pool-1-thread-2]生产了一个产品：iPhone 手机
         生产者[pool-1-thread-2]生产了一个产品：iPhone 手机
         消费者[pool-1-thread-5]消费了一个产品：iPhone 手机
         消费者[pool-1-thread-5]消费了一个产品：iPhone 手机
         消费者[pool-1-thread-5]消费了一个产品：iPhone 手机
         消费者[pool-1-thread-5]消费了一个产品：iPhone 手机
         消费者[pool-1-thread-5]消费了一个产品：iPhone 手机
         生产者[pool-1-thread-1]生产了一个产品：iPhone 手机
         消费者[pool-1-thread-4]消费了一个产品：iPhone 手机
         生产者[pool-1-thread-2]生产了一个产品：iPhone 手机
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
    //消费者
    static class Consumer implements Runnable {

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
                    buffer.notifyAll();//消费完毕通知等待池内的其他线程(生产者或消费者都有可能)
                }
            }
        }

    }
}
