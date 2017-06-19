package juc;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by 李恒名 on 2017/6/19.
 */
public class ForkJoinTest {

    static class Sum extends RecursiveTask<Long> {
        private static final int THRESHOLD = 10;//可直接求解的临界值
        private final long from;
        private final long to;

        Sum(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            if ((to - from) < THRESHOLD) {//达到直接求解的临界点，直接进行计算
                for (long i = from; i <= to; i++) {
                    sum = sum + i;
                }
            } else {//递归分解计算
                long mid = (from + to) >>> 1;//取中间值
                //以中间值为界将计算任务分解执行
                Sum left = new Sum(from, mid);
                left.fork();
                Sum right = new Sum(mid + 1, to);
                right.fork();
                //合并计算结果
                sum = left.join() + right.join();
            }
            return sum;
        }
    }

    public static void main(String[] args) throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Long> result = forkJoinPool.submit(new Sum(1, 10000));
        System.out.println("Sum：" + result.get());//Sum：50005000
    }
}
