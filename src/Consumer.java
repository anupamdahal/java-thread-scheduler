/**
 * This class is will consume one node (in front of the queue), and will simulate execution by sleeping (time delay).
 */

public class Consumer implements Runnable {

    private SyncQueue queue;
    private int capacity;

    //Constructor
    Consumer(SyncQueue queue, int capacity){
        this.queue = queue;
        this.capacity = capacity;
    }

    //This method will perform "execution" (pause depending on the JobNode's sleepTime)

    @Override
    public void run(){
        for(int i = 0; i < capacity; i++){
            JobNode node = queue.pop();
            nodeSleep(node);
            System.out.println(node.processId);
        }
    }

    public void nodeSleep(JobNode j){
        try{
            Thread.sleep(j.sleepTime);
            System.out.println(Thread.currentThread());
        }
        catch(Exception e) {
            System.out.println("ERROR! ERROR!");
        }
    }
}
