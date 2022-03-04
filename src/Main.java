/**
 * This the main class that will run the whole simulation
 */

public class Main {
    public static void main(String[]args){
        SyncQueue queue = new SyncQueue(Constant.CAPACITY);
        
        int noJobs1 = Constant.NO_JOBS/2;
        int noJobs2 = Constant.NO_JOBS - noJobs1;
        
        Producer producer = new Producer(queue, Constant.NO_JOBS);
        Consumer consumer1 = new Consumer(queue, noJobs1);
        Consumer consumer2 = new Consumer(queue, noJobs2);

        try {
            Thread t1 = new Thread(producer, "Producer-1");
            Thread t2 = new Thread(consumer1, "Consumer-1");
            Thread t3 = new Thread(consumer2, "Consumer-2");
            
            t1.start();
            t2.start();
            t3.start();

        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
