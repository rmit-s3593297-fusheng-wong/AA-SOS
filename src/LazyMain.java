import java.io.*;
import java.util.*;


/**
 * Framework to test the multiset implementations.
 * 
 * @author jkcchan
 */
public class MultisetTester
{
	/** Name of class, used in error messages. */
	protected static final String progName = "MultisetTester";
	
	/** Standard outstream. */
	protected static final PrintStream outStream = System.out;

	/**
	 * Print help/usage message.
	 */
	public static void usage(String progName) {
		System.err.println(progName + ": <implementation> [fileName to output search results to]");
		System.err.println("<implementation> = <linkedlist | sortedlinkedlist | bst| hash | baltree>");
		System.exit(1);
	} // end of usage


	/**
	 * Process the operation commands coming from inReader, and updates the multiset according to the operations.
	 * 
	 * @param inReader Input reader where the operation commands are coming from.
	 * @param searchOutWriter Where to output the results of search.
	 * @param multiset The multiset which the operations are executed on.
	 * 
	 * @throws IOException If there is an exception to do with I/O.
	 */
	public static void processOperations(Multiset<String> multiset, String[] inputList) 
		throws IOException
	{
		String line;
		int lineNum = 0;
		boolean bQuit = false;
		
		// continue reading in commands until we either receive the quit signal or there are no more input commands
		while (lineNum<inputList.length) {
			line = inputList[lineNum];
			String[] tokens = line.split(" ");

			String command = tokens[0];
			// determine which operation to execute
			switch (command.toUpperCase()) {
				// add
				case "A":
					if (tokens.length == 2) {
						multiset.add(tokens[1]);
					}
					break;
				// search
				case "S":
					if (tokens.length == 2) {
						int foundNumber = multiset.search(tokens[1]);
						//searchOutWriter.println(tokens[1] + " " + foundNumber);
					}
					break;
				// remove one instance
				case "RO":
					if (tokens.length == 2) {
						multiset.removeOne(tokens[1]);
					}
					break;
				// remove all instances
				case "RA":
					if (tokens.length == 2) {
						multiset.removeAll(tokens[1]);
					}
					break;		
				// print
				case "P":
					multiset.print(outStream);
					break;
				// quit
				case "Q":
					bQuit = true;
					break;
				default:
					System.err.println(lineNum + ": Unknown command.");
			}

			lineNum++;
		}

	} // end of processOperations() 


	/**
	 * Main method.  Determines which implementation to test.
	 */
	public static void main(String[] args) {

		//String implementationType = args[0];
		String implementationType = "linkedlist";	
		
		// determine which implementation to test
		Multiset<String> multiset = null;
		switch(implementationType) {
			case "linkedlist":
				multiset = new LinkedListMultiset<String>();
				break;
			case "sortedlinkedlist":
				multiset = new SortedLinkedListMultiset<String>();
				break;
			case "bst":
				multiset = new BstMultiset<String>();
				break;
			case "hash":
				multiset = new HashMultiset<String>();
				break;
			case "baltree":
				multiset = new BalTreeMultiset<String>();
				break;
			default:
				System.err.println("Unknown implmementation type.");
				usage(progName);
		}


		// construct in and output streams/writers/readers, then process each operation.
		try {
			//generate random
			int inputSize = 1000000;
			String newRow = "";
			String[] inputList = new String[inputSize];
			String[] inputTypes = {"A","RO","S"};
			String[] inputValues = {"ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT","NINE","TEN"};
			
			for(int i=0; i<inputSize; i++) {
				//TODO generate the code
				newRow = inputTypes[new Random().nextInt(inputTypes.length)]+" ";
				newRow += inputValues[new Random().nextInt(inputValues.length)];
				inputList[i] = newRow;
			}
			//generate random end
			
			// process the operations
			long startTime = System.nanoTime();
			processOperations(multiset, inputList);
			long endTime = System.nanoTime() ;
			double estimatedTime = ((double)(endTime - startTime)) / Math.pow ( 10 , 9 ) ;
			System.out.println ( "time taken = " + estimatedTime + " sec " ) ;

			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	} // end of main()

} // end of class MultisetTester
