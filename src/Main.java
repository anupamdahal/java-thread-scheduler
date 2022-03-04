/**
 * This the main class that will run the whole simulation
 */

public class Main {
    public static void main(String[]args){
       //Testing the sleep
       SyncQueue queue = new SyncQueue(5);
        JobNode node = new JobNode(123, 5000);
        Producer p = new Producer( queue, 5);
        p.run();
        Consumer c = new Consumer(queue, 5);
        // c.nodeSleep(node);
        c.run();
        System.out.println("Hello World!");

    }
}
