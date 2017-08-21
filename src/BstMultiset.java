import java.io.PrintStream;
import java.util.*;

public class BstMultiset<T extends Comparable<T>> extends Multiset<T> implements Comparator<T>
{
	Node root;
	public BstMultiset() {
		root=new Node(null, null, null, 0);
	} // end of BstMultiset()

	public void add(T item) {
		Node currentNode=root;
		//Condition for Empty Tree
		if(currentNode==root && currentNode.getElement()==null){
			currentNode.setElement(item);
			currentNode.setFreq(1);
			System.out.println("rootAdd--");
			return;
		}
		addTraverse(currentNode, item);
		
	} // end of add()
	public void addTraverse(Node currentNode, T item){
		System.out.println("CurrentNode.getElement()---"+currentNode.getElement());
		System.out.println("item---"+item);
		//if item is smaller than current Element
		if(currentNode.getElement().compareTo(item)==1){
			//if Node has left child - Recursive call
			if(currentNode.hasLeft()){
				addTraverse(currentNode.getLeft(), item);
			}// else Add new left child Node
			else {
				System.out.println("leftAdd--");
				Node newNode=new Node(null, null, item, 1);
				currentNode.setLeft(newNode); 
			}
		}//else if Node has right child - Recursive call
		else if(currentNode.getElement().compareTo(item)==-1){
			if(currentNode.hasRight()){
				addTraverse(currentNode.getRight(), item);
			}//else Add new right child Node
			else {
				System.out.println("rightAdd--");
				Node newNode=new Node(null, null, item, 1);
				currentNode.setRight(newNode); 
			}
		}//else if item = element then freq + 1
		else if(currentNode.getElement().compareTo(item)==0){
			currentNode.setFreq(currentNode.getFreq()+1);
			return;
		}
	}
	public int search(T item) {
		Node currentNode=root;
		while(currentNode.hasLeft() || currentNode.hasRight()){
			if(currentNode.getElement().compareTo(item)==1){
				currentNode=currentNode.getLeft();
			}
			else if(currentNode.getElement().compareTo(item)==-1){
				currentNode=currentNode.getRight();
			}
			else if(currentNode.getElement().compareTo(item)==0){
				return currentNode.getFreq();
			}
		}
		return 0;
	} // end of add()


	public void removeOne(T item) {
		// Implement me!
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
	} // end of removeAll()


	public void print(PrintStream out) {
		Node currentNode=root;
		printTraverse(currentNode, out);
	} // end of print()
	
	public void printTraverse(Node currentNode, PrintStream out){
		if(currentNode.hasLeft()){
			printTraverse(currentNode.getLeft(), out);
		}
		out.println(currentNode.getElement() + printDelim + currentNode.getFreq());
		if(currentNode.hasRight()){
			printTraverse(currentNode.getRight(), out);
		}
	}
	
	//Node Class
	public class Node{
		private Node left;
		private Node right;
		private T element;
		private int freq;
		
		public Node(Node left, Node right,T element, int freq){
			this.left=left;
			this.right=right;
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
		
		//set Element
		public void setElement(T element){
			this.element=element;
		}
					
		//get Left Node
		public Node getLeft(){
			return this.left;
		}
		
		//get Right Node
		public Node getRight(){
			return this.right;
		}
		
		//set Right Node
		public void setRight(Node right){
			this.right=right;
		}
				
		//set Left Node
		public void setLeft(Node left){
			this.left=left;
		}
			
		//check if has Left Node
		public boolean hasLeft(){
			return this.getLeft()!=null?true:false;
		}
							
		//check if has Right Node
		public boolean hasRight(){
			return this.getRight()!=null?true:false;
		}
	}

	@Override
	public int compare(T o1, T o2) {
		return o1.compareTo(o2);
	}
} // end of class BstMultiset
