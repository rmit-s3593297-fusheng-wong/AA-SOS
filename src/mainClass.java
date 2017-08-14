
public class mainClass {

	public static void main(String[] args) {
		/*LinkedListMultiset<Integer> multiSet=new LinkedListMultiset<Integer>();
		System.out.println("Print Empty MultiSet");
		multiSet.print(System.out);
		multiSet.add(5);
		multiSet.add(3);
		multiSet.add(7);
		multiSet.add(3);
		System.out.println("Print MultiSet after Adding elements");
		multiSet.print(System.out);
		System.out.println("Remove 3");
		multiSet.removeOne(3);
		multiSet.print(System.out);*/
		/***SortedLinkedMultiSet***/
		SortedLinkedListMultiset<Integer> sortedMultiSet=new SortedLinkedListMultiset<Integer>();
		sortedMultiSet.add(5);
		sortedMultiSet.print(System.out);
		sortedMultiSet.add(3);
		sortedMultiSet.print(System.out);
		sortedMultiSet.add(7);
		sortedMultiSet.print(System.out);
		sortedMultiSet.add(3);
		System.out.println("Print MultiSet after Adding elements");
		sortedMultiSet.print(System.out);
		System.out.println("Remove 3");
		sortedMultiSet.removeOne(3);
		sortedMultiSet.print(System.out);
	}

}
