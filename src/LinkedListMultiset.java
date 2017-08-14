import java.io.PrintStream;
import java.util.*;

public class LinkedListMultiset<T> extends Multiset<T>
{
	Node head;
	Node tail;
	
	public LinkedListMultiset() {
		head=new Node(null, null, null, 0);
		tail=new Node(null, null, null, 0);
		head.setNext(tail);
		tail.setPrev(head);
	} // end of LinkedListMultiset()
	
	/***Potential Bug -> Does not update Frequency of previously added elements***/
	public void add(T item) {
		//Get frequency of item
		int freq=search(item);
		freq=freq+1;
		ArrayList<Node> nodeList=new ArrayList<Node>();
		Node currentNode=head;
		//Traverse to end of list
		while(currentNode.hasNext()){
			currentNode=currentNode.getNext();
			if(currentNode.getElement()==item){
				nodeList.add(currentNode);
			}
		}
		Node newNode=new Node(tail, currentNode, item, freq);
		currentNode.setNext(newNode);
		for(Node updateNode:nodeList){
			updateNode.setFreq(freq);
		}
	} // end of add()
	
	
	public int search(T item) {
		Node currentNode=head;
		int count=0;
		//Traverse the list to find element
		while(currentNode.hasNext()){
			currentNode=currentNode.getNext();
			if(currentNode.getElement()==item){
				count++;
			}
		}
		return count;
	} // end of add()
	
	
	public void removeOne(T item) {
		Node currentNode=head;
		Node prev;
		Node next;
		//Traverse the list to find element
		while(currentNode.hasNext()){
			currentNode=currentNode.getNext();
			if(currentNode.getElement()==item){
				prev=currentNode.getPrev();
				next=currentNode.getNext();
				prev.setNext(next);
				next.setPrev(prev);
				break;
			}
		}
		//Update Frequency in MultiSet
		int freq=search(item);
		currentNode=head;
		ArrayList<Node> nodeList=new ArrayList<Node>();
		//Traverse to end of list
		while(currentNode.hasNext()){
			currentNode=currentNode.getNext();
			if(currentNode.getElement()==item){
				nodeList.add(currentNode);
			}
		}
		for(Node updateNode:nodeList){
			updateNode.setFreq(freq);
		}
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		Node currentNode=head;
		Node prev;
		Node next;
		//Traverse the list to find element
		while(currentNode.hasNext()){
			currentNode=currentNode.getNext();
			if(currentNode.getElement()==item){
				prev=currentNode.getPrev();
				next=currentNode.getNext();
				prev.setNext(next);
				next.setPrev(prev);
			}
		}
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		Node currentNode=head;
		//Traverse the list to print elements
		while(currentNode.hasNext()){
			currentNode=currentNode.getNext();
			out.println(currentNode.getElement());
			out.println(" ,freq- "+currentNode.getFreq());
		}
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
		
		//set Frequency
		public int setFreq(int freq){
			return this.freq=freq;
		}
		
		//get Frequency
		public int getFreq(){
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
		
		//check if has Next Node
		public boolean hasNext(){
			return this.getNext()!=tail?true:false;
		}
						
		//check if has Previous Node
		public boolean hasPrev(){
			return this.getPrev()!=head?true:false;
		}
	}
	
	
} // end of class LinkedListMultiset