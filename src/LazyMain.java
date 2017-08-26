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

		// construct in and output streams/writers/readers, then process each operation.
		try {
			//generate random
			int inputSize = 100000;
			int dataSize = 100000;
			String newRow = "";
			
			//this is preloaded before actions
			String[] dataList = new String[dataSize];
			String[] dataValues = {"EIGHT","FIVE","FOUR","NINE","ONE","SEVEN","SIX","TEN","THREE","TWO"};
					
			//this is the set of actions onto preloaded data
			String[] inputList = new String[inputSize];
			String[] inputTypes = {"A","A","A","RO","S","S","S"};
			String[] inputValues = {"EIGHT","FIVE","FOUR","NINE","ONE","SEVEN","SIX","TEN","THREE","TWO","RANDOM"};
			//sorted array
			int i=0;
	
			Multiset<String> multiset = new LinkedListMultiset<String>();
			Multiset<String> multiset2 = new SortedLinkedListMultiset<String>();
			Multiset<String> multiset3 = new LinkedListMultiset<String>();
			Multiset<String> multiset4 = new HashMultiset<String>();
			Multiset<String> multiset5 = new BalTreeMultiset<String>();
			double averageLL=0;
			double averageSLL=0;
			double averageBST=0;
			double averageHM=0;
			double averageBAL=0;
			double estimatedTime;
			
			long startTime;
			long endTime;
			for(int tries=0; tries<10; tries++) {
				i=0;
				//generate data
				//generate random start
				/*for(i=0; i<inputSize; i++) {
					newRow = inputTypes[new Random().nextInt(inputTypes.length)]+" ";
					newRow += inputValues[new Random().nextInt(inputValues.length)];
					inputList[i] = newRow;
					//System.out.println (newRow);
				}*/
				//generate random end
			
				//comment out the one that is not being used
				//generate fixed set start
				//generate forward sort
					for(int out=0;out<10;out++) {
				//generate forward sort end
					
				//generate backward sort
					//for(int out=9;out>=0;out--) {
				//generate backward sort end
						//the -1 here is for the search at the end
						for(int in=0;in<((inputSize/10)-1);in++) {
							if(in <= (inputSize/10)*.6) {
								//add first 7
								newRow = inputTypes[0]+" ";
								newRow += inputValues[out];
								inputList[i] = newRow;
								//System.out.println (newRow + " 1 " + i);
								i++;
							}else if(in > (inputSize/10)*.6 && in <= (inputSize/10)-1) {
								//remove next 2
								newRow = inputTypes[3]+" ";
								newRow += inputValues[out];
								inputList[i] = newRow;
								//System.out.println (newRow + " 2 " + i);
								i++;
							}else {
								//no print
							}
						}
					}
					//search
					for(int search=0;search<10;search++) {
						//the -1 here is for the search at the end
						newRow = inputTypes[4]+" ";
						newRow += inputValues[search];
						inputList[i] = newRow;
						//System.out.println (newRow + " 3 " + i);
						i++;
					}
				//generate fixed set end
				
				//process the operations
				startTime = System.nanoTime();
				processOperations(multiset4, inputList);
				endTime = System.nanoTime() ;
				estimatedTime = ((double)(endTime - startTime)) / Math.pow ( 10 , 9 ) ;
				averageHM += estimatedTime;
				System.out.println ( "time taken = " + estimatedTime + " sec " ) ;
				
				startTime = System.nanoTime();
				processOperations(multiset5, inputList);
				endTime = System.nanoTime() ;
				estimatedTime = ((double)(endTime - startTime)) / Math.pow ( 10 , 9 ) ;
				averageBAL += estimatedTime;
				System.out.println ( "time taken = " + estimatedTime + " sec " ) ;
				
				startTime = System.nanoTime();
				processOperations(multiset, inputList);
				endTime = System.nanoTime() ;
				estimatedTime = ((double)(endTime - startTime)) / Math.pow ( 10 , 9 ) ;
				averageLL += estimatedTime;
				System.out.println ( "time taken = " + estimatedTime + " sec " ) ;
				
				startTime = System.nanoTime();
				processOperations(multiset2, inputList);
				endTime = System.nanoTime() ;
				estimatedTime = ((double)(endTime - startTime)) / Math.pow ( 10 , 9 ) ;
				averageSLL += estimatedTime;
				System.out.println ( "time taken = " + estimatedTime + " sec " ) ;
				
				/*startTime = System.nanoTime();
				processOperations(multiset3, inputList);
				endTime = System.nanoTime() ;
				estimatedTime = ((double)(endTime - startTime)) / Math.pow ( 10 , 9 ) ;
				averageBST += estimatedTime;*/
				//System.out.println ( "time taken = " + estimatedTime + " sec " ) ;
			}
			System.out.println ( "Average LL time taken = " + averageLL/100 + " sec " ) ;
			System.out.println ( "Average SLL time taken = " + averageSLL/100 + " sec " ) ;
			//System.out.println ( "Average BST time taken = " + averageBST/100 + " sec " ) ;
			System.out.println ( "Average HM time taken = " + averageHM/100 + " sec " ) ;
			System.out.println ( "Average BAL time taken = " + averageBAL/100 + " sec " ) ;
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	} // end of main()

} // end of class MultisetTester
