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
			return;
		}
		addTraverse(currentNode, item);
		
	} // end of add()
	public void addTraverse(Node currentNode, T item){
		//if item is smaller than current Element
		if(currentNode.getElement().compareTo(item)==1){
			//if Node has left child - Recursive call
			if(currentNode.hasLeft()){
				addTraverse(currentNode.getLeft(), item);
			}// else Add new left child Node
			else {
				Node newNode=new Node(null, null, item, 1);
				currentNode.setLeft(newNode);
				return;
			}
		}//else if Node has right child - Recursive call
		else if(currentNode.getElement().compareTo(item)==-1){
			if(currentNode.hasRight()){
				addTraverse(currentNode.getRight(), item);
			}//else Add new right child Node
			else {
				Node newNode=new Node(null, null, item, 1);
				currentNode.setRight(newNode);
				return;
			}
		}//else if item = element then freq + 1
		else if(currentNode.getElement().compareTo(item)==0){
			currentNode.setFreq(currentNode.getFreq()+1);
			return;
		}
	}
	public int search(T item) {
		Node currentNode=root;
		//Binary Search
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
		//Check Leaf Node OR Root Node
		if(currentNode.getElement().compareTo(item)==0){
			return currentNode.getFreq();
		}
		return 0;
	} // end of add()


	public void removeOne(T item) {
		Node currentNode=root;
		Node previousNode=null;
		//Check case if only Root Node exists
		if(currentNode.equals(root) && !currentNode.hasLeft() && !currentNode.hasRight()){
			if(currentNode.getElement().compareTo(item)==0){
				//Check if frequency > 1 then just decrease frequency
				if(currentNode.getFreq()>1){
					currentNode.setFreq(currentNode.getFreq()-1);
				}
				else {
					currentNode.setElement(null);
					currentNode.setFreq(0);
				}
			}
			return;
		}//Check all internal Nodes
		while(currentNode.hasLeft() || currentNode.hasRight()){
			if(currentNode.getElement().compareTo(item)==1){
				previousNode=currentNode;
				currentNode=currentNode.getLeft();
			}
			else if(currentNode.getElement().compareTo(item)==-1){
				previousNode=currentNode;
				currentNode=currentNode.getRight();
			}
			else if(currentNode.getElement().compareTo(item)==0){
				//Check if frequency > 1 then just decrease frequency
				if(currentNode.getFreq()>1){
					currentNode.setFreq(currentNode.getFreq()-1);
					
				}//Otherwise ReOrder tree
				else removeTraverse(previousNode,currentNode,item);
				return;
			}
		}
		//Check Leaf Node OR Root Node
		if(currentNode.getElement().compareTo(item)==0){
			//Check if frequency > 1 then just decrease frequency
			if(currentNode.getFreq()>1){
				currentNode.setFreq(currentNode.getFreq()-1);
				
			}//Otherwise ReOrder tree
			else removeTraverse(previousNode,currentNode,item);
		}
	} // end of removeOne()
	
	//Removes Node and ReOrders the Tree
	public void removeTraverse(Node parentNode, Node removeNode,T item){
		//Leaf Node
		if(!removeNode.hasLeft() && !removeNode.hasRight()){
			parentNode.setLeft(null);
			parentNode.setRight(null);
			return;
		}
		//If only Left Child
		if(removeNode.hasLeft() && !removeNode.hasRight()){
			if(removeNode!=root){
				parentNode.setLeft(removeNode.getLeft());
			}
			else {
				root=removeNode.getLeft();
			}
			return;
		}//If only Right Child
		else if(removeNode.hasRight() && !removeNode.hasLeft()){
			if(!removeNode.equals(root)){
				parentNode.setRight(removeNode.getRight());
			}
			else {
				root=removeNode.getRight();
			}
			return;
		}//If it has two children
		else if(removeNode.hasLeft() && removeNode.hasRight()){
			Node successorParentNode;
			Node successorNode;
			//Find successor -> Innermost left child of the first right
			if(removeNode.getRight().hasLeft()){
				//Recursively Find left most
				successorParentNode=removeTraverseLeft(removeNode.getRight(), removeNode);
				successorNode=successorParentNode.getLeft();
			}
			else {// Else successor is right child
				successorParentNode=parentNode;
				successorNode=removeNode.getRight();
			}
			/***ReOrder Tree***/
			//Step 1 -> removeNode parent points to successor
			if(!removeNode.equals(root)){
				if(parentNode.getLeft()==removeNode){
					parentNode.setLeft(successorNode);
				}
				else if(parentNode.getRight()==removeNode){
					parentNode.setRight(successorNode);
				}
			}
			else root=successorNode;	
			//Step 2 -> Successor parent points to successor children
			if(successorParentNode!=null){
				if(successorNode.hasRight()){
					successorParentNode.setLeft(successorNode.getRight());
				}
				else successorParentNode.setLeft(null);
			}
			//Step 3 -> Successor points to Node's children
			successorNode.setLeft(removeNode.getLeft());
			if(removeNode.getRight()!=successorNode){//Avoid loop to point to itself
				successorNode.setRight(removeNode.getRight());
			}
			//Step 4 -> Node points to null (left & right)
			removeNode.setLeft(null);
			removeNode.setRight(null);
			return;
		}
	}
	
	public Node removeTraverseLeft(Node currentNode, Node previousNode){
		if(currentNode.hasLeft()){
			previousNode=currentNode;
			removeTraverseLeft(currentNode.getLeft(), previousNode);
		}
		return previousNode;
	}
	
	public void removeAll(T item) {
		Node currentNode=root;
		Node previousNode=null;
		//Check case if only Root Node exists
		if(currentNode.equals(root) && !currentNode.hasLeft() && !currentNode.hasRight()){
			if(currentNode.getElement().compareTo(item)==0){
					currentNode.setElement(null);
					currentNode.setFreq(0);
			}
			return;
		}//Check all internal Nodes
		while(currentNode.hasLeft() || currentNode.hasRight()){
			if(currentNode.getElement().compareTo(item)==1){
				previousNode=currentNode;
				currentNode=currentNode.getLeft();
			}
			else if(currentNode.getElement().compareTo(item)==-1){
				previousNode=currentNode;
				currentNode=currentNode.getRight();
			}
			else if(currentNode.getElement().compareTo(item)==0){
				removeTraverse(previousNode,currentNode,item);
				return;
			}
		}
		//Check Leaf Node OR Root Node
		if(currentNode.getElement().compareTo(item)==0){
			removeTraverse(previousNode,currentNode,item);
		}
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
