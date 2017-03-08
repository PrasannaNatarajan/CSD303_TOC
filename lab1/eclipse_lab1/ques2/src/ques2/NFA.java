package ques2;

import java.util.ArrayDeque;
import java.util.Stack;

public class NFA {
	
	public static void main(String[] args){
		
		String input = toPost("aab*a");
		System.out.println(input);
		
	}
	
	public static String toPost(String inp){
		
		String s =inp;
		int counter = 0;
		char[] input = s.toCharArray(); 
		
		//make input proper with concatenations
		for(int i=0;i<inp.length()-1;i++){
			
			Boolean first = input[i]!='+' && input[i]!='*' && input[i] != '?' && input[i] != '|' && input[i] != '.' && input[i]!='('&& input[i]!=')';
			Boolean second = input[i+1]!='+' && input[i+1]!='*' && input[i+1] != '?' && input[i+1] != '|' && input[i+1] != '.'&& input[i+1]!='('&& input[i+1]!=')';
			if(first == true && second == true){				
				s = s.substring(0, counter+i+1) + "." + s.substring(counter+i+1,s.length());
				counter++;
			}
		}
		
		//make input into post fix form
		ArrayDeque<Character> opStack = new ArrayDeque();
		input = s.toCharArray();
		StringBuilder output = new StringBuilder();
		for(int i=0;i<input.length;i++){
			Boolean first = input[i]!='+' && input[i]!='*' && input[i] != '?' && 
					input[i] != '|' && input[i] != '.' && input[i]!=')';
			if(first){
				output.append(input[i]);
			}
			
			else if(input[i]==')'){
				char tos = opStack.pop();
				
				while(tos!='('){
					output.append(tos);
					tos = opStack.pop();
				}
			}
			else{
				
				if(opStack.isEmpty()){
					opStack.push(input[i]);
				}
				
				else{
					
					char tos = opStack.peek();
					Boolean isNotOperation = tos!='+' && tos!='*' && tos != '?' && tos != '|' && tos != '.';
					
					if(!isNotOperation){
						int p = getPrecedence(input[i], tos);
						if(p<=0){
							tos = opStack.pop();
							output.append(tos);
						}
					}
					
					opStack.push(input[i]);
				}
			}
			
		}
		
		while(!opStack.isEmpty()){
			output.append(opStack.pop());
		}
		
		s = output.toString();		
		return s;
	}
	
	
    private static int getPrecedence(char a, char b){
    	
        if(a=='*' || a=='+' || a=='?'){
        	return (b=='*' || b=='+' || b=='?')?0:1;
        }

        else if(a=='.'){
            return (b=='*' || b=='+' || b=='?')?-1:(b==a)?0:1;
        }

        else if(a=='|'){
        	return (a==b)?0:-1;
        }

        return -2;
    }
	

}