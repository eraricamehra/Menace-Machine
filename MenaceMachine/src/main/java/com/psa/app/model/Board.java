package com.psa.app.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private char[][] currentBoard;
	private int moveNumber;
	private String label;
	private  List<Integer> playerPositions = new ArrayList<Integer>();
	private  List<Integer> compPositions = new ArrayList<Integer>();
	char[][] cb = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };

	public Board(int[] currentBoard, int moveNumber, String label) {
		super();
		playerPositions = new ArrayList<>();
		compPositions = new ArrayList<>();
		this.currentBoard = cb;
		this.moveNumber = moveNumber;
		this.label = label;

	}

	public char[][] getCb() {
		return cb;
	}

	public void setCb(char[][] cb) {
		this.cb = cb;
	}

	public Board(int[] currentBoard, String label) {
		super();
		playerPositions = new ArrayList<>();
		compPositions = new ArrayList<>();
		this.currentBoard = cb;
		this.label = label;
	}

	public List<Integer> getPlayerPositions() {
		return playerPositions;
	}

	public void setPlayerPositions(List<Integer> playerPositions) {
		this.playerPositions = playerPositions;
	}

	public List<Integer> getCompPositions() {
		return compPositions;
	}

	public void setCompPositions(List<Integer> compPositions) {
		this.compPositions = compPositions;
	}

	public Board( String label,  int moveNumber) {
		super();
		playerPositions = new ArrayList<>();
		compPositions = new ArrayList<>();
		this.moveNumber = moveNumber;
		this.label = label;
	}
	public Board() {
		
	}
	public Board(String label) {
		super();
		playerPositions = new ArrayList<>();
		compPositions = new ArrayList<>();
		this.currentBoard = cb;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public char[][] getCurrentBoard() {
		return currentBoard;
	}

	public void setCurrentBoard(char[][] currentBoard) {
		this.currentBoard = currentBoard;
	}

	public int getMoveNumber() {
		return moveNumber;
	}

	public void setMoveNumber(int moveNumber) {
		this.moveNumber = moveNumber;
	}

	public static void printBoard(char[][] game) {
		for (char[] row : game) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static char[][] copyBoard(char[][] originalBoard) {
		char[][] tempBoard = new char[3][3];
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				tempBoard[i][j] = originalBoard[i][j];
			}
		}
		return tempBoard;
	}

	// TODO get Cell Value

}
