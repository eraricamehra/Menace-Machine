package com.psa.app.model;

public class Move {

	private int cellNumber;
	private String player;

	public int getCellNumber() {
		return cellNumber;
	}

	public Move(int cellNumber, String player) {
		super();
		this.cellNumber = cellNumber;
		this.player = player;
	}

	public void setCellNumber(int cellNumber) {
		this.cellNumber = cellNumber;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

}
