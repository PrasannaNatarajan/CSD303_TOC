package ques1;

public class StringMatching {
	
	private static int alpha = 256;		// assuming all ascii characters as possible alphabets
	
	public static void main(String[] args) {
		
		/* Default values */
		String T = "Here isssss he full text";	// The full text
		String P = "Here is";			// The pattern that needs to be searched 
		  /* Input Arguments */
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
			case "-T":
				T = args[++i];
				break;
			case "-P":
				P = args[++i];
				break;
			default:
				break;
			}
		}

		
		
		int [][] trans = computeTransitionFunction(P.toCharArray());	//creates transition table
		matcher(T.toCharArray(),trans,P.length());			//checks if the text matches the pattern
	}
	/*
	* Description: checks if the text matches the pattern
	* Input: text, transition table and size of pattern
	* Output: Prints if the text matches or not
	*/
	private static void matcher(char[] T,int[][]trans,int m){
		int q=0;
		// loop to traverse through the transition table
		for(int i=0;i<T.length;i++){
			q = trans[q][T[i]];
			// checks if q is equal to the pattern length
			if(q==m){
				System.out.println("Pattern occurs with shift "+(i-m+1)+" ");
				return;
			}				
		}
		System.out.println("Pattern does not occur");
	}
	
	/*
	* Description: Creates transtion table
	* Input: pattern
	* Output: a 2d array (transtion table)
	*/
	private static int[][] computeTransitionFunction(char[] P){
		int[][] trans = new int[P.length][alpha];
		// loop to create the transtion table
		for(int i=0;i<P.length;i++){			
			for(int j=0;j<alpha;j++){
				trans[i][j] = 0;
			}
			trans[i][P[i]] = i+1;	// add current_length_of_pattern+1 to alphabet places that are in the pattern
			//debug print
			//System.out.println("trans["+i+"]["+P[i]+"] = "+(i+1));			
		}
		
		return trans;
	}

}
