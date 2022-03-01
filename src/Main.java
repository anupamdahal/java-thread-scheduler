/**
 * This the main class that will run the whole simulation
 */

public class Main {
    public static void main(String[]args){
       //Testing the sleep
        JobNode node = new JobNode(123, 2000);
        Consumer c = new Consumer();
        c.nodeSleep(node);

    }
}
