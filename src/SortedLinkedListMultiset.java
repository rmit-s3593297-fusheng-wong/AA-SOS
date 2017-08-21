import java.io.PrintStream;
import java.util.*;

public class SortedLinkedListMultiset<T extends Comparable<T>> extends Multiset<T> implements Comparator<T>
{	
	Node head;
	Node tail;
	
	public SortedLinkedListMultiset() {
		head=new Node(null, null, null, 0);
		tail=new Node(null, null, null, 0);
		head.setNext(tail);
		tail.setPrev(head);
	} // end of SortedLinkedListMultiset()
	
	//Ascending Order
	public void add(T item) {
		//Get frequency of item
		Node currentNode=head;
		while(currentNode.hasNext()){
			currentNode=currentNode.getNext();
			//if Item is greater then current Element then insert at previous node OR if Item is equal then increase frequency
			if(currentNode.getElement().compareTo(item)==0){
				currentNode.setFreq(currentNode.getFreq()+1);
				return;
			}
			else if(currentNode.getElement().compareTo(item)==1){
				Node prev=currentNode.getPrev();
				Node newNode=new Node(currentNode, prev, item, 1);
				prev.setNext(newNode);
				return;
			}
		}
		Node newNode=new Node(tail, currentNode, item, 1);
		currentNode.setNext(newNode);
	} // end of add()
	
	
	public int search(T item) {
		Node currentNode=head;
		//Traverse the list to find element
		while(currentNode.hasNext()){
			currentNode=currentNode.getNext();
			if(currentNode.getElement().compareTo(item)==0){
				return currentNode.getFreq();
			}
		}
		return 0;
	} // end of add()
	
	
	public void removeOne(T item) {
		Node currentNode=head;
		Node prev;
		Node next;
		//Traverse the list to find element
		while(currentNode.hasNext()){
			currentNode=currentNode.getNext();
			if(currentNode.getElement().compareTo(item)==0){
				if(currentNode.getFreq()>1){
					currentNode.setFreq(currentNode.getFreq()-1);
				}
				else{
					prev=currentNode.getPrev();
					next=currentNode.getNext();
					prev.setNext(next);
					next.setPrev(prev);
				}
				return;
			}
		}
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		Node currentNode=head;
		Node prev;
		Node next;
		//Traverse the list to find element
		while(currentNode.hasNext()){
			currentNode=currentNode.getNext();
			if(currentNode.getElement().compareTo(item)==0){
				prev=currentNode.getPrev();
				next=currentNode.getNext();
				prev.setNext(next);
				next.setPrev(prev);
				return;
			}
		}
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		Node currentNode=head;
		//Traverse the list to print elements
		while(currentNode.hasNext()){
			currentNode=currentNode.getNext();
			out.println(currentNode.getElement() + printDelim + currentNode.getFreq());
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

	@Override
	public int compare(T o1, T o2) {
		return o1.compareTo(o2);
	}
	
} // end of class SortedLinkedListMultiset