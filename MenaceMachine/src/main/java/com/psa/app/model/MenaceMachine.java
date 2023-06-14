package com.psa.app.model;

import java.util.List;

public class MenaceMachine {
	
	private List<Box> boxes;

	public List<Box> getBoxes() {
		return boxes;
	}

	public void setBoxes(List<Box> boxes) {
		this.boxes = boxes;
	}

	public MenaceMachine(List<Box> boxes) {
		super();
		this.boxes = boxes;
	}

}
