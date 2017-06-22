package juc.aqs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by 李恒名 on 2017/6/22.
 */
public class MutexLock implements Lock, java.io.Serializable {

    // 继承ASQ实现同步组件
    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        //通过状态判断锁是否已被其他线程获取
        protected boolean isHeldExclusively() {

            return getState() == 1;
        }
        @Override
        //尝试获取锁
        public boolean tryAcquire(int acquires) {
            //尝试获取锁，当 state 为 0 时获得锁，并将state + 1，由CAS保证操作的原子性
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        @Override
        //尝试释放锁
        protected boolean tryRelease(int releases) {
            if (getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            //直接将state 设置为 0，以便等待队列中的线程去获得锁
            setState(0);
            return true;
        }

        // Provides a Condition
        Condition newCondition() { return new ConditionObject(); }

        // Deserializes properly
        private void readObject(ObjectInputStream s)
                throws IOException, ClassNotFoundException {
            s.defaultReadObject();
            setState(0); // reset to unlocked state
        }
    }

    private final Sync sync = new Sync();
    //使用AQS提供的模板方法
    public void lock()                { sync.acquire(1); }
    public boolean tryLock()          { return sync.tryAcquire(1); }
    public void unlock()              { sync.release(1); }
    public Condition newCondition()   { return sync.newCondition(); }
    public boolean isLocked()         { return sync.isHeldExclusively(); }
    public boolean hasQueuedThreads() { return sync.hasQueuedThreads(); }
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }
    public boolean tryLock(long timeout, TimeUnit unit)
            throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

}