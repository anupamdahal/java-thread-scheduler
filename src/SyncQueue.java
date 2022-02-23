public class SyncQueue{

  class Node{
    JobNode jNode;
    Node next;
    
    Node(JobNode jobNode){
      this.jNode = jobNode;
      this.next = null;
    }
  }

  Node start;
  Node end;

  SyncQueue(){
  }

  void push(Node node){
    return;
  }

  Node pop(){
    return null;
  }
  
}
