import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MealyMachine {

	/*
	 * This Mealy Machine simulates a vending machine which accepts denominations 
	 * and has a single accepted state . If the given input is accepted then it goes to the accepted state
	 * and also returns the change . 
	 * 
	 * In this Mealy Machine the accepted state is 0 every time
	 */

	// TODO : Change the transitionTable to an ArrayList<int[]> (Consumes less memory)

	private int transitionTable[][][];		// Transition table for the Mealy Machine
	private int denominations[];			// Accepted Denominations
	private boolean marked[];				// Used to memoize is dfs
	private int D ;							// Number of Denominations
	private int finalState;					// Final State
	private StringBuilder toStr;			// Stores the transition table in a String
	private HashMap<Integer , Integer> map;	// Map denomiantions to indices

	/*
	 *	Used to construct the states that can be visited 
	 */
	private void dfs(int u) {
		marked[u] = true;
		for(int i=0;i<D;i++) {
			if(u + denominations[i] >= finalState) {
				transitionTable[u][i][0] = 0;
				transitionTable[u][i][1] = u + denominations[i] - finalState;
			}
			else {

				if(!marked[u + denominations[i]])
					dfs(u + denominations[i]);

				transitionTable[u][i][0] = u + denominations[i];
				transitionTable[u][i][1] = 0;
			}
		}
	}

	/*
	 * Constructor for the MealyMachine
	 */
	public MealyMachine(int denominations[] , int finalState) {
		this.denominations = denominations;
		this.D = denominations.length;
		this.finalState = finalState;
		marked = new boolean[finalState + 1];
		transitionTable = new int[finalState + 1][denominations.length][2];
		map = new HashMap<>();
		for(int d : denominations)
			map.put(d, map.size());
		dfs(0);
	}

	/*
	 * Gets the output String for a given input from the Mealy Machine
	 */
	public String getOutput(List<Integer> input) {
		StringBuilder builder = new StringBuilder();
		int curr = 0;
		builder.append("0 change = 0");
		for(int in : input) {
			
			if(!map.containsKey(in))
				throw new IllegalArgumentException("User has entered a invalid denomination ");
		
			int change = transitionTable[curr][map.get(in)][1];
			curr = transitionTable[curr][map.get(in)][0];
			builder.append(" -> " + curr + " change = " + change);
		
		}


		return builder.toString();
	}

	/*
	 * Returns the String format of the transitionTable of the Mealy Machine
	 */

	@Override
	public String toString() {

		if(toStr == null){
			toStr = new StringBuilder();
			toStr.append("      ");
			for(int d : denominations)
				toStr.append(String.format("%6d | change", d));
			toStr.append('\n');
			for(int i=0;i<=finalState;i++) {
				if(marked[i]) {
					toStr.append(String.format("%6d", i));
					for(int j=0;j<D;j++)
						toStr.append(String.format("%6d | %6d", transitionTable[i][j][0] , transitionTable[i][j][1]));
					toStr.append('\n');
				}
			}
		}

		return toStr.toString();
	}

	public static void main(String[] args) {

		MealyMachine machine = new MealyMachine(new int[]{5 , 15 ,20, 35}, 90);
		System.out.println(machine);
		System.out.println(machine.getOutput(Arrays.asList(5,5,20,15,15,35)));

	}
}

/*
 * Denomination {5 , 15 ,20, 35} , cost = 90 , user input = 5,5,20,15,15,35
           5 | change    15 | change    20 | change    35 | change
     0     5 |      0    15 |      0    20 |      0    35 |      0
     5    10 |      0    20 |      0    25 |      0    40 |      0
    10    15 |      0    25 |      0    30 |      0    45 |      0
    15    20 |      0    30 |      0    35 |      0    50 |      0
    20    25 |      0    35 |      0    40 |      0    55 |      0
    25    30 |      0    40 |      0    45 |      0    60 |      0
    30    35 |      0    45 |      0    50 |      0    65 |      0
    35    40 |      0    50 |      0    55 |      0    70 |      0
    40    45 |      0    55 |      0    60 |      0    75 |      0
    45    50 |      0    60 |      0    65 |      0    80 |      0
    50    55 |      0    65 |      0    70 |      0    85 |      0
    55    60 |      0    70 |      0    75 |      0     0 |      0
    60    65 |      0    75 |      0    80 |      0     0 |      5
    65    70 |      0    80 |      0    85 |      0     0 |     10
    70    75 |      0    85 |      0     0 |      0     0 |     15
    75    80 |      0     0 |      0     0 |      5     0 |     20
    80    85 |      0     0 |      5     0 |     10     0 |     25
    85     0 |      0     0 |     10     0 |     15     0 |     30

0 change = 0 -> 5 change = 0 -> 10 change = 0 -> 30 change = 0 -> 45 change = 0 -> 60 change = 0 -> 0 change = 5

 * 
 */
