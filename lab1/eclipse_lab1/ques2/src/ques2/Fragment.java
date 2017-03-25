package ques2;

import java.util.ArrayList;

public class Fragment {

	
	private State start;			// start state of a fragment
	private ArrayList<State> outptrs;	// arraylist containing out pointers (arrows)
	
	// constructors
	public Fragment(State start, State out){
		this.outptrs = new ArrayList<State>();
		outptrs.add(out);
		this.start = start;
	}
	 
	public Fragment(State start, ArrayList<State> outptrs){
        this.outptrs = outptrs;
        this.start = start;
	}
	
	public Fragment(){
		this.outptrs = null;
		this.start = null;
	}
	
	// getters
	public State getState(){
		return this.start;
	}
	
	public ArrayList<State> getOutPtrs(){
		return this.outptrs;
	}
	 
}
