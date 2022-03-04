import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {
  
  private SyncQueue queue;
  private int noJobs;
  private int producedJobs = 0;

  Producer(SyncQueue queue, int noJobs ){
    this.queue = queue;
    this.noJobs = noJobs;
  }

  @Override
  public void run() {
    while(producedJobs < noJobs){
      System.out.println("Adding Jobs");
      queue.push(getNodes(queue.getCapacity()));
      sleep();
    }
  }

  private JobNode[] getNodes(int numOfNodes){
    JobNode[] nodes = new JobNode[numOfNodes];
    for(int i = 0; i < numOfNodes; i++){
      nodes[i] = new JobNode(
        producedJobs,
        ThreadLocalRandom.current().nextInt(
          Constant.SLEEP_MIN,
          Constant.SLEEP_MAX + 1 
        ));
      producedJobs++;
    }
    return nodes;
  }

  private void sleep(){
    int sleepTime = ThreadLocalRandom.current().nextInt(
        Constant.SLEEP_MIN*10,
        Constant.SLEEP_MAX*10 + 1 
    );

    try {
      Thread.sleep(sleepTime);
    } catch (Exception e) {
      //TODO: handle exception
    }
    
  }
}
