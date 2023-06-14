package com.psa.app.model;

import java.util.ArrayList;
import java.util.List;

import com.psa.app.utils.ApplicationConstants;
import com.psa.app.utils.Utils;

public class Box {

	private List<Bead> beads;

	public List<Bead> getBeads() {
		return beads;
	}

	public Box() {
		super();
		this.beads = new ArrayList<>();
		int arrayLength = ApplicationConstants.ALPHA;
		for(int i =1; i<=arrayLength; i++) {
			this.beads.add(new Bead(Utils.getRandomNumber())); //initializing Box
		}
	}
	
	public Box(List<Bead> beads) {
		super();
		this.beads = beads;
	}

	public void setBeads(List<Bead> beads) {
		this.beads = beads;
	}

	@Override
	public String toString() {
		String str="";
		for(int i=0; i< beads.size(); i++) {
			str = str + "," + beads.get(i).getNum();
		}
		return  str ;
	}
}
