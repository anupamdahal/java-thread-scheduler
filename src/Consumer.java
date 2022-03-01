/**
 * This class is will consume one node (in front of the queue), and will simulate execution by sleeping (time delay).
 */

public class Consumer {
    //Constructor
    Consumer(){}

    //This method will perform "execution" (pause depending on the JobNode's sleepTime)
    public void nodeSleep(JobNode j){
        try{
            Thread.sleep(j.sleepTime);
        }
        catch(Exception e) {
            System.out.println("ERROR! ERROR!");
        }
    }
}
