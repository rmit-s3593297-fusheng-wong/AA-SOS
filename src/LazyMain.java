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
	
	public static String randomizer() {
		Integer randomInt = new Random().nextInt(10000);
		String randomString = randomInt.toString();
		return randomString;
	}


	/**
	 * Main method.  Determines which implementation to test.
	 */
	public static void main(String[] args) {

		// construct in and output streams/writers/readers, then process each operation.
		try {
			//generate random
			
			//only one of the 3 should be variable at one time
			//int[] dataSize = {1000000,100,1000,10000,100000,1000000};
			int[] dataSize = {100,100,100,100,100,100};
			int[] inputSize = {1000,1000,1000,1000,1000,1000};
			//int[] inputSize = {1000000,100,1000,10000,100000,1000000};
			String newRow = "";
			String randomInput = "";
			
			
			
			//this is preloaded before actions
			//String[] dataValues = {"EIGHT","FIVE","FOUR","NINE","ONE","SEVEN","SIX","TEN","THREE","TWO"};
			String[] dataValues = new String[100];
			for(Integer generator=0; generator < 100 ;generator++) {
				dataValues[generator] = generator.toString();
			}
			
			//this is the set of actions onto preloaded data
			
			
			String[] inputDummy = {"A","RO","S"};
			String[] inputTypes1 = {"A","A","A","A","A","A","A","A","A","A","RO","S"};
			String[] inputTypes2 = {"A","A","A","A","A","RO","S"};
			String[] inputTypes3 = {"A","RO","S"};
			String[] inputTypes4 = {"A","RO","S","S","S","S","S"};
			String[] inputTypes5 = {"A","RO","S","S","S","S","S","S","S","S","S","S"};
			String[] [] inputTypes = {inputDummy,inputTypes1,inputTypes2,inputTypes3,inputTypes4,inputTypes5};
			//String[] [] inputTypes = {inputDummy,inputTypes3,inputTypes3,inputTypes3,inputTypes3,inputTypes3};
			//String[] inputValues = {"EIGHT","FIVE","FOUR","NINE","ONE","SEVEN","SIX","TEN","THREE","TWO"};
			String[] inputValues = new String[101];
			for(Integer generator=0; generator < 101 ;generator++) {
				inputValues[generator] = generator.toString();
			}
			//sorted array
			int i=0;
	
			
			double averageLL=0;
			double averageSLL=0;
			double averageBST=0;
			double averageHM=0;
			double averageBAL=0;
			double estimatedTime;
			
			long startTime;
			long endTime;
			int totalTries = 20;
			for(int ratios=0; ratios<6; ratios++) {
				averageLL=0;
				averageSLL=0;
				averageBST=0;
				averageHM=0;
				averageBAL=0;
				//System.out.println(dataSize[ratios]);
				
				for(int tries=0; tries<totalTries; tries++) {
				i=0;
				
				//generate data
				//dummy is because the program starts up slowly for some reason skewing the data
				Multiset<String> dummyset = new LinkedListMultiset<String>();
				Multiset<String> multiset = new LinkedListMultiset<String>();
				Multiset<String> multiset2 = new SortedLinkedListMultiset<String>();
				Multiset<String> multiset3 = new BstMultiset<String>();
				Multiset<String> multiset4 = new HashMultiset<String>();
				Multiset<String> multiset5 = new BalTreeMultiset<String>();
				
				String[] dataList = new String[dataSize[ratios]];
				for(int d=0; d<dataSize[ratios]; d++) {
					newRow = "A ";
					newRow += dataValues[new Random().nextInt(dataValues.length)];
					dataList[d] = newRow;
				}
				
				if(tries==0) {processOperations(dummyset, dataList);}
				processOperations(multiset, dataList);
				processOperations(multiset2, dataList);
				processOperations(multiset3, dataList);
				processOperations(multiset4, dataList);
				processOperations(multiset5, dataList);
				//generate data end
				
				
				
				//generate scenario
				String[] inputList = new String[inputSize[ratios]];
				//generate random start
				for(i=0; i<inputSize[ratios]; i++) {
					newRow = inputTypes[ratios][new Random().nextInt(inputTypes[ratios].length)]+" ";
					randomInput = inputValues[new Random().nextInt(inputValues.length)];
					if(randomInput == "100") {
						newRow += randomizer();
					}else {
						newRow += randomInput;
					}
					
					inputList[i] = newRow;
					//System.out.println (newRow);
				}
				//generate random end
			
				//comment out the one that is not being used
				//generate fixed set start
				//generate forward sort
			/*		for(int out=0;out<10;out++) {
				//generate forward sort end
					
				//generate backward sort
					//for(int out=9;out>=0;out--) {
				//generate backward sort end
						//the -1 here is for the search at the end
						for(int in=0;in<((inputSize/10)*.9);in++) {
							if(in <= (inputSize/10)*.6) {
								//add first 7
								newRow = "A ";
								newRow += inputValues[out];
								inputList[i] = newRow;
								System.out.println (newRow + " 1 " + i);
								i++;
							}else if(in > (inputSize/10)*.6 && in <= (inputSize/10)*.9) {
								//remove next 2
								newRow = "R ";
								newRow += inputValues[out];
								inputList[i] = newRow;
								System.out.println (newRow + " 2 " + i);
								i++;
							}else {
								//no print
							}
						}
					}
					//search
					for(int search=0;search<(inputSize/10);search++) {
						//the -1 here is for the search at the end
						newRow = "S ";
						newRow += inputValues[search];
						inputList[i] = newRow;
						System.out.println (newRow + " 3 " + i);
						i++;
					}
				//generate fixed set end
			*/	
				//process the operations
				//only needed on the first round
				//java compiler issue maybe, slow on first run
				if(tries==0) {processOperations(dummyset, inputList);}	
					
				startTime = System.nanoTime();
				processOperations(multiset4, inputList);
				endTime = System.nanoTime() ;
				estimatedTime = ((double)(endTime - startTime)) / Math.pow ( 10 , 9 ) ;
				averageHM += estimatedTime;
				//System.out.println ( "time taken = " + estimatedTime + " sec " ) ;
				
				startTime = System.nanoTime();
				processOperations(multiset5, inputList);
				endTime = System.nanoTime() ;
				estimatedTime = ((double)(endTime - startTime)) / Math.pow ( 10 , 9 ) ;
				averageBAL += estimatedTime;
				//System.out.println ( "time taken = " + estimatedTime + " sec " ) ;
				
				startTime = System.nanoTime();
				processOperations(multiset, inputList);
				endTime = System.nanoTime() ;
				estimatedTime = ((double)(endTime - startTime)) / Math.pow ( 10 , 9 ) ;
				averageLL += estimatedTime;
				//System.out.println ( "time taken = " + estimatedTime + " sec " ) ;
				
				startTime = System.nanoTime();
				processOperations(multiset2, inputList);
				endTime = System.nanoTime() ;
				estimatedTime = ((double)(endTime - startTime)) / Math.pow ( 10 , 9 ) ;
				averageSLL += estimatedTime;
				//System.out.println ( "time taken = " + estimatedTime + " sec " ) ;
				
				startTime = System.nanoTime();
				processOperations(multiset3, inputList);
				endTime = System.nanoTime() ;
				estimatedTime = ((double)(endTime - startTime)) / Math.pow ( 10 , 9 ) ;
				averageBST += estimatedTime;
				//System.out.println ( "time taken = " + estimatedTime + " sec " ) ;
			}
			/*System.out.println ( "Average LL time taken = " + averageLL/totalTries + " sec " ) ;
			System.out.println ( "Average SLL time taken = " + averageSLL/totalTries + " sec " ) ;
			System.out.println ( "Average BST time taken = " + averageBST/totalTries + " sec " ) ;
			System.out.println ( "Average HM time taken = " + averageHM/totalTries + " sec " ) ;
			System.out.println ( "Average BAL time taken = " + averageBAL/totalTries + " sec " ) ;*/
			if(ratios!=1) {
				System.out.println (averageLL/totalTries) ;
				System.out.println (averageSLL/totalTries) ;
				System.out.println (averageBST/totalTries) ;
				System.out.println (averageHM/totalTries) ;
				System.out.println (averageBAL/totalTries) ;
				System.out.println (" ") ;
			}
			
			}
			
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	} // end of main()

} // end of class MultisetTester
