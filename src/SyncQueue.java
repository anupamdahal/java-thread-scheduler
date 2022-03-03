import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.locks.*;

public class SyncQueue {

  private Queue<JobNode> queue = new LinkedList<>();
  private int maxSize = -1;

  private final Lock lock = new ReentrantLock();
  private Condition notFull = lock.newCondition();
  private Condition notEmpty = lock.newCondition();

  SyncQueue(int maxSize){
    assert(maxSize >= 0);
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

    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
  
  JobNode pop(){
    JobNode node = null;

    try {
      lock.lock();
      while(queue.isEmpty())
        notEmpty.await();

      node = queue.poll();
      notFull.signalAll();
      assert(!queue.isEmpty());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
    
    return node;
  }
  
}
