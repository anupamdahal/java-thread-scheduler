import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.locks.*;

public class SyncQueue {

  private Queue<JobNode> queue = new LinkedList<>();
  private int capacity = Constant.CAPACITY;

  private final Lock lock = new ReentrantLock();
  private Condition notFull = lock.newCondition();
  private Condition notEmpty = lock.newCondition();

  SyncQueue(int capacity){
    assert(capacity >= 0);
    this.capacity = capacity;
  }

  int getCapacity(){
    return capacity - queue.size();
  }

  void push(JobNode node){
    try {
      lock.lock();
      
      while(queue.size() >= capacity)
        notFull.await();

      queue.offer(node);
      notEmpty.signalAll();
      assert(queue.size() >= capacity);

    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  void push(JobNode[] nodes){
    try {
      lock.lock();

      for(JobNode node: nodes){
        while(queue.size() >= capacity){
          notFull.await();
        }
        queue.offer(node);
      }

      notEmpty.signalAll();
      assert(queue.size() >= capacity);

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
