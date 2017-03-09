import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MooreMachine {
	
	/*
	 * This Moore Machine simulates a vending machine which accepts denominations 
	 * and has a single accepted state
	 * 
	 */
	// TODO : Change the transitionTable to an ArrayList<int[]> (Consumes less memory)
	
	private int transitionTable[][];		// Transition table for the Moore Machine
	private int denominations[];			// Accepted Denominations
	private boolean marked[];			// Used to memoize is dfs
	private int D ;					// Number of Denominations
	private int finalState;				// Final State
	private StringBuilder toStr;			// Stores the transition table in a String
	private HashMap<Integer , Integer> map;	// Map denomiantions to indices
	
	/*
	 *	Used to construct the states that can be visited 
	 */
	private void dfs(int u) {
		marked[u] = true;
		for(int i=0;i<D;i++) {
			if(u + denominations[i] > finalState)
				transitionTable[u][i] = finalState;
			else {
				
				if(!marked[u + denominations[i]])
					dfs(u + denominations[i]);
				
				transitionTable[u][i] = u + denominations[i];
			}
		}
	}
	
	/*
	 * Constructor for the MooreMachine
	 */
	public MooreMachine(int denominations[] , int finalState) {
		this.denominations = denominations;
		this.D = denominations.length;
		this.finalState = finalState;
		marked = new boolean[finalState + 1];
		transitionTable = new int[finalState + 1][denominations.length];
		map = new HashMap<>();
		for(int d : denominations)
			map.put(d, map.size());
		dfs(0);
	}
	
	/*
	 * Gets the output String for a given input from the MooreMachine
	 */
	
	public String getOutput(List<Integer> input) {
		StringBuilder builder = new StringBuilder();
		int curr = 0;
		for(int in : input) {
			if(!map.containsKey(in))
				throw new IllegalArgumentException("User has entered a invalid denomination ");

			if(curr == finalState) {
				builder.append('1');
				break;
			}
			else {
				builder.append('0');
				System.out.print("from = " + curr);
				curr = transitionTable[curr][map.get(in)];
				System.out.println(" to = " + curr);
			}
		}
		
		if(curr == finalState && builder.charAt(builder.length() - 1) == '0')
			builder.append('1');
		
		return builder.toString();
	}
	
	/*
	 * Returns the String format of the transitionTable of the MooreMachine
	 */
	
	@Override
	public String toString() {
		
		if(toStr == null){
			toStr = new StringBuilder();
			toStr.append("      ");
			for(int d : denominations)
				toStr.append(String.format("%6d", d));
			toStr.append('\n');
			for(int i=0;i<=finalState;i++) {
				if(marked[i]) {
					toStr.append(String.format("%6d", i));
					for(int j=0;j<D;j++)
						toStr.append(String.format("%6d", transitionTable[i][j]));
					toStr.append('\n');
				}
			}
		}
		
		return toStr.toString();
	}
	
	public static void main(String[] args) {
		
		MooreMachine machine = new MooreMachine(new int[]{5 , 10 , 20}, 35);
		System.out.println(machine);
		System.out.println(machine.getOutput(Arrays.asList(10,5,20)));
		
	}
}
