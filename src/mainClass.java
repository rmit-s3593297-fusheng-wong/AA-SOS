
public class mainClass {

	public static void main(String[] args) {
		BstMultiset<Integer> multiSet=new BstMultiset<Integer>();
		/*SortedLinkedListMultiset<Integer> multiSet=new SortedLinkedListMultiset<Integer>();*/
		System.out.println("Print Empty MultiSet");
		multiSet.print(System.out);
		multiSet.add(5);
		multiSet.add(3);
		multiSet.add(7);
		multiSet.add(8);
		multiSet.add(7);
		multiSet.add(5);
		multiSet.add(5);
		multiSet.add(7);
		multiSet.add(2);
		System.out.println("Print MultiSet after Adding elements");
		multiSet.print(System.out);
		/*System.out.println("Remove 3");
		multiSet.removeOne(3);
		System.out.println("Remove 7");
		multiSet.removeOne(7);
		multiSet.print(System.out);*/
	}

}
