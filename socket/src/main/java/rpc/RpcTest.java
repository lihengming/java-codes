package rpc;

import java.util.concurrent.TimeUnit;

import static rpc.SimpleRpcFramework.export;
import static rpc.SimpleRpcFramework.use;

/**
 * Created by 李恒名 on 2017/8/17.
 */
public class RpcTest {
    private static final String HOST = "localhost";
    private static final int PORT = 8888;

    interface ComputeService {
        int sum(int a, int b);
    }


    static class ComputeServiceImpl implements ComputeService {

        @Override
        public int sum(int a, int b) {
            return a + b;
        }
    }

    //服务提供者
    static class Provider implements Runnable {
        @Override
        public void run() {
            //暴露服务
            export(new ComputeServiceImpl(), PORT);
        }
    }

    //服务消费者
    static class Customer implements Runnable {
        @Override
        public void run() {
            //使用服务
            ComputeService computeService = use(ComputeService.class, HOST, PORT);
            for (int i = 0; i < 10; i++) {
                int sum = computeService.sum(1, 1);
                System.out.println("SUM:" + sum);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Provider()).start();
        new Thread(new Customer()).start();
        new Thread(new Customer()).start();
    }
}
