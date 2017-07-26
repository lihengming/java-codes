package data.structure.queue;

/**
 * Created by 李恒名 on 2017/6/23.
 * <p>
 * 保存指向首尾节点的指针，每次从链表尾插入，从链表头删除。
 */
public class QueueLinkedImpl implements Queue {
    private Node first, last;

    private class Node {
        private Node next;
        private Object element;
    }

    @Override
    public void enQueue(Object element) {
        Node oldLast = last;
        last = new Node();
        last.element = element;
        last.next = null;
        if (first == null) {
            first = last;
        } else {
            //2017年7月26日17:28:33 原始链表尾的next指针指向新加的节点
            oldLast.next = last;
        }

    }

    @Override
    public Object deQueue() {
        Object element = first.element;
        first = first.next;
        if (first == null) last = null;
        return element;
    }
}
