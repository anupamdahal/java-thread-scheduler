import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {
  
  private SyncQueue queue;
  private int capacity;

  Producer(SyncQueue queue, int capacity){
    this.queue = queue;
    this.capacity = capacity;
  }

  @Override
  public void run() {
    for(int i = 0; i < capacity; i++){
      JobNode node = new JobNode(
        i,
        ThreadLocalRandom.current().nextInt(
          SleepRange.min,
          SleepRange.max + 1 
        ));
      queue.add(node); 
    }
  }
}
