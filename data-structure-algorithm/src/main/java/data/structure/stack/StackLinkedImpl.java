package data.structure.stack;

/**
 * Created by 李恒名 on 2017/6/8.
 *
 */
public class StackLinkedImpl implements Stack {

    private Node first = null;

    private class Node{
        private Node next;
        private Object element;
    }

    @Override
    public void push(Object element) {
        if(first == null){//如果第一个节点为空创建新节点
            Node node = new Node();
            node.element = element;
            first = node;
        }else {//创建新节点并将其移至链表首部
            Node oldFirst = first;
            Node node = new Node();
            node.element = element;
            first = node;
            node.next = oldFirst;
        }
    }

    @Override
    public Object pop() {
        if(first == null){
            throw new RuntimeException("Stack is empty");
        }
        Object element = first.element;//获取首部节点的元素
        first = first.next;//将下一个节点移至链表首部
        return element;
    }
}
