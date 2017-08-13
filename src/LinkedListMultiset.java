import java.io.PrintStream;
import java.util.*;

public class LinkedListMultiset<T> extends Multiset<T>
{
	Node head;
	
	public LinkedListMultiset() {
		head=new Node(null, null, null, 0);
	} // end of LinkedListMultiset()
	
	
	public void add(T item) {
		//Get frequency of item
		int freq=search(item);
		Node currentNode=head;
		//Traverse to end of list
		while(currentNode.getNext()!=null){
			currentNode=currentNode.getNext();
		}
		Node newNode=new Node(null, currentNode, item, freq+1);
		currentNode.setNext(newNode);
	} // end of add()
	
	
	public int search(T item) {
		Node currentNode=head;
		int count=0;
		//Traverse the list to find element
		do{
			if(currentNode.getElement()==item){
				count++;
			}
			currentNode=currentNode.getNext();
		}
		while(currentNode.getNext()!=null);
		return count;
	} // end of add()
	
	
	public void removeOne(T item) {
		/*Node currentNode=head;
		//Traverse the list to find element
		do{
			if(currentNode.getElement()==item){
				count++;
			}
			currentNode=currentNode.getNext();
		}
		while(currentNode.getNext()!=null);*/
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		Node currentNode=head;
		//Traverse the list to print elements
		do{
			out.println(currentNode.getElement());
			currentNode=currentNode.getNext();
		}
		while(currentNode.getNext()!=null);
	} // end of print()
	
	//Node Class
	public class Node{
		private Node next;
		private Node prev;
		private T element;
		private int freq;
		
		public Node(Node next, Node prev,T element, int freq){
			this.next=next;
			this.prev=prev;
			this.element=element;
			this.freq=freq;
		}
		
		//get Frequency
		public int getfreq(){
			return this.freq;
		}
				
		//get Element
		public T getElement(){
			return this.element;
		}
				
		//get Next Node
		public Node getNext(){
			return this.next;
		}
		
		//get Previous Node
		public Node getPrev(){
			return this.prev;
		}
		//set Next Node
		public void setNext(Node next){
			this.next=next;
		}
				
		//set Previous Node
		public void setPrev(Node prev){
			this.prev=prev;
		}
	}
	
	
} // end of class LinkedListMultiset