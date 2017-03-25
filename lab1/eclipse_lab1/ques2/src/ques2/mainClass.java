package ques2;

import java.util.ArrayList;

public class mainClass {

	
	public static void main(String[] args){
		
		/* Default values */
		String regex = "a*b*";
		String userInput = "aabb";
		  /* Input Arguments */
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
			case "-r":
				regex = args[++i];
				break;
			case "-s":
				userInput = args[++i];
				break;
			default:
				break;
			}
		}
		
		
		String input = NFA.toPost(regex); // converting user input to post fix
		
		//debug print
		System.out.println(input);
		// create NFA for the input
		State startstate = NFA.toNFA(input);
		// simulate the NFA to check if it matches
		Boolean DoesItmatch = matchNFA(startstate, userInput);
		if(DoesItmatch)
		    System.out.println("It's a match");
		else
			System.out.println("No match");
		
	}
	
	// function to simulate the NFA and check if it matches, returns a boolean
	public static boolean matchNFA(State startstate, String input){
		
        ArrayList <State> currentList = new ArrayList<State> ();
        ArrayList <State> nextList = new ArrayList<State> ();
        int listID = 0;
        addState(currentList, startstate, listID);
        for (int i = 0; i < input.length(); i++){        	
            listID = step(currentList, input.charAt(i), nextList, listID);
            currentList = nextList;
            nextList = new ArrayList<State>();
        }
        return containsMatchState(currentList);
    }
    private static boolean containsMatchState(ArrayList<State> finalList){
        for (int i = 0; i < finalList.size(); i++){
            State s = finalList.get(i);
            if (s.getDoesItMatch()){
                return true;
            }
        }
        return false;
    }
    private static int step(ArrayList<State> currentList, char c, ArrayList<State> nextList, int listID){
        listID++;
        for (int i = 0; i < currentList.size(); i++){
            State s = currentList.get(i);
            if(c == s.getCharacter()){
                addState(nextList, s.getOutFlow1(), listID);
            }
        }
        return listID;
    }
    private static void addState(ArrayList<State> list, State s, int listID){
        if (s == null || s.getLastList() == listID)
            return;
        s.setLastList(listID);
        if(s.getHasSplit()){
            addState(list, s.getOutFlow1(), listID);
            addState(list, s.getOutFlow2(), listID);
            return;
        }
        list.add(s);
    }
	
	
	
}
