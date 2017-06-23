package data.structure.queue;

import org.junit.Test;

/**
 * Created by 李恒名 on 2017/6/8.
 */
public class Tester {


    //测试队列的链表实现
    @Test
    public void test2(){
        Queue queue = new QueueLinkedImpl();

        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");

        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
    }
}
