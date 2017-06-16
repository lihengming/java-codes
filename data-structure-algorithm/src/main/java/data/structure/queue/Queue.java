package data.structure.queue;

/**
 * Created by 李恒名 on 2017/6/15.
 *
 * 队列（Queue），是先进先出（FIFO, First-In-First-Out）的线性表
 */
public interface Queue {

     void enQueue(Object element);

     Object deQueue();
}
