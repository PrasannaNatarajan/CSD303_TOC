package ques2;

public class State{
	
	private char a;			// character that the state holds
	private State outflow1;		// state where the out_arrow of this state points to
	private State outflow2;		// state where the out_arrow of this state points to (this is used only in some cases)
	private boolean hasSplit;	// to decide if this state has split into two
	private boolean doesItMatch;	// to decide if a string matches
	private boolean hasChar;	// to decide if it has a character
	private int lastList;		// is a list id to make adding states efficient

	// constructors
	public State(){

		this.outflow1 = null;
		this.outflow2 = null;
		this.hasSplit = false;
		this.doesItMatch = true;
		this.hasChar = false;
		this.lastList = -1;		
	}
	
	public State(char a){
		
		this.a = a;
		this.outflow1 = null;
		this.outflow2 = null;
		this.hasSplit = false;
		this.doesItMatch = false;
		this.hasChar = true;
		this.lastList = -1;		
	}
	
	public State(State s1, State s2){

		this.outflow1 = s1;
		this.outflow2 = s2;
		this.hasSplit = true;
		this.doesItMatch = false;
		this.hasChar = false;
		this.lastList = -1;		
	}
	

	// getters
	public boolean getHasChar(){
		return this.hasChar;
	}	
	public boolean getDoesItMatch(){
		return this.doesItMatch;
	}
	public boolean getHasSplit(){
		return this.hasSplit;
	}
	public int getLastList(){
		return this.lastList;
	}
	public State getOutFlow1(){
		return this.outflow1;
	}
	public State getOutFlow2(){
		return this.outflow2;
	}
	public char getCharacter(){
		return this.a;
	}
	
	//setters
	
	public void setLastList(int x){
		this.lastList = x;
	}
	
	// attaches two/three states
	public void attachStates(State s1){
		if(this.outflow1==null)
			this.outflow1 = s1;
		
		else if(this.outflow2 == null && this.getHasSplit() == true)
				this.outflow2=s1;
		
	}
	
}

