package ques1;

public class StringMatching {
	
	private static int alpha = 256;
	
	public static void main(String[] args) {
		String T = "Here isssss he full text";
		String P = "Here is";
		int [][] trans = computeTransitionFunction(P.toCharArray());
		matcher(T.toCharArray(),trans,P.length());
	}
	
	private static void matcher(char[] T,int[][]trans,int m){
		int q=0;
		for(int i=0;i<T.length;i++){
			q = trans[q][T[i]];
			if(q==m){
				System.out.println("Pattern occurs with shift "+(i-m+1)+" ");
				return;
			}				
		}
		System.out.println("Pattern does not occur");
	}
	
	private static int[][] computeTransitionFunction(char[] P){
		int[][] trans = new int[P.length][alpha];
		
		for(int i=0;i<P.length;i++){			
			for(int j=0;j<alpha;j++){
				trans[i][j] = 0;
			}
			trans[i][P[i]] = i+1;
			System.out.println("trans["+i+"]["+P[i]+"] = "+(i+1));			
		}
		
		return trans;
	}

}
