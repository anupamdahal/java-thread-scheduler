import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.locks.*;

public class SyncQueue {

  private Queue<JobNode> queue = new LinkedList();
  private int maxSize = null;

  private final Lock lock = new ReentrantLock();
  private Condition notFull = lock.newCondition();
  private Condition notEmpty = lock.newCondition();

  SyncQueue(int maxSize){
    this.maxSize = maxSize;
  }

  void push(JobNode node){
    try {
      lock.lock();
      
      while(queue.size() >= maxSize)
        notFull.await();

      queue.offer(node);
      notEmpty.signalAll();
      assert(queue.size() >= maxSize);

    } finally {
      lock.unlock();
    }
  }
  
  JobNode pop(){
    JobNode node = null;

    try {
      lock.lock();
      
      while(queue.size() != 0)
        notEmpty.await();

      node = queue.poll();
      notFull.signalAll();
      assert(queue.size() != 0);
    } finally {
      lock.unlock();
    }
    
    return node;
  }
  
}
