package ques2;

import java.util.ArrayList;

public class Fragment {

	
	private State start;
	private ArrayList<State> outptrs;
	
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
	
	public State getState(){
		return this.start;
	}
	
	public ArrayList<State> getOutPtrs(){
		return this.outptrs;
	}
	 
}
