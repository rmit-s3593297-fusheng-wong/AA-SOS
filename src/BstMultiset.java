import java.io.PrintStream;
import java.util.*;

import LinkedListMultiset.Node;

public class BstMultiset<T> extends Multiset<T>
{
	public BstMultiset() {
		// Implement me!
	} // end of BstMultiset()

	public void add(T item) {
		// Implement me!
	} // end of add()


	public int search(T item) {
		// Implement me!

		// default return, please override when you implement this method
		return 0;
	} // end of add()


	public void removeOne(T item) {
		// Implement me!
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
	} // end of removeAll()


	public void print(PrintStream out) {
		// Implement me!
	} // end of print()
	
	//Node Class
	public class Node{
		private Node left;
		private Node right;
		private T element;
		private int freq;
		
		public Node(Node next, Node prev,T element, int freq){
			this.next=next;
			this.prev=prev;
			this.element=element;
			this.freq=freq;
		}
			
		//set Frequency
		public int setfreq(int freq){
			return this.freq=freq;
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
			
		//check if has Next Node
		public boolean hasNext(){
			return this.getNext()!=tail?true:false;
		}
							
		//check if has Previous Node
		public boolean hasPrev(){
			return this.getPrev()!=head?true:false;
		}
	}
} // end of class BstMultiset
