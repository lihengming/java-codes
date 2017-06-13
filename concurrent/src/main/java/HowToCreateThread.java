import java.util.concurrent.*;

/**
 * Created by 李恒名 on 2017/6/8.
 */
public class HowToCreateThread {

    public static void main(String[] args) {
        method1();
        method2();
        method3();
    }

    //继承java.lang.Thread
   public static void method1(){
       class Task extends Thread{

           @Override
           public void run() {
               System.out.println(Thread.currentThread().getName() +" Started");
           }
       }

       new Task().start();
   }
    //实现 java.lang.Runnable
   public static void method2(){
       class Task implements Runnable {

           @Override
           public void run() {
               System.out.println(Thread.currentThread().getName() +" Started");
           }
       }

       new Thread(new Task()).start();
   }
    //实现 java.util.concurrent.Callable
    public static void method3(){
        class Task implements Callable{

            @Override
            public Object call() throws Exception {
                System.out.println(Thread.currentThread().getName() +" Started");
                return "execute finished";
            }
        }

        ExecutorService es = Executors.newFixedThreadPool(1);
        Future future = es.submit(new Task());
        try {
            System.out.println(future.get());
        } catch (InterruptedException|ExecutionException e) { e.printStackTrace();}

        es.shutdown();

    }

}
