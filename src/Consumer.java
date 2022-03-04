/**
 * This class is will consume one node (in front of the queue), and will simulate execution by sleeping (time delay).
 */

public class Consumer implements Runnable {

    private SyncQueue queue;
    private int noJobs;

    //Constructor
    Consumer(SyncQueue queue, int noJobs ){
        this.queue = queue;
        this.noJobs = noJobs;
    }

    //This method will perform "execution" (pause depending on the JobNode's sleepTime)

    @Override
    public void run(){
        for(int i = 0; i < noJobs; i++){
            JobNode node = queue.pop();
            nodeSleep(node);
            System.out.println(node.processId);
        }
    }

    public void nodeSleep(JobNode j){
        try{
            Thread.sleep(j.sleepTime);
            System.out.println(Thread.currentThread().getName());
        }
        catch(Exception e) {
            System.out.println("ERROR! ERROR!");
        }
    }
}
