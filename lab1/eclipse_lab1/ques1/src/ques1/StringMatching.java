package ques1;

public class StringMatching {
	
	private static int alpha = 256;
	
	public static void main(String[] args) {
		String T = "Here is he full text";
		String P = "sunny";
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
		int charIndex = 0;
		for(int j=0;j<alpha;j++){
			trans[0][j] = 0;
		}
		trans[0][P[0]] = 1;
		
		for(int i=1;i<P.length;i++){
			
			for(int j=0;j<alpha;j++){
				trans[i][j] = trans[charIndex][j];
			}
			trans[i][P[i]] = i+1;
			if(i<P.length)
				charIndex = trans[charIndex][P[i]];
		}
		
		return trans;
	}

}
