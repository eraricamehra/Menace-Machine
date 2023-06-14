package com.psa.app.service;

import org.springframework.stereotype.Service;

import com.psa.app.model.Board;

@Service
public class TransformationService {

	public static char[][] flipHorizontal(char board[][]) {

		char[][] tempBoard = Board.copyBoard(board);
		char temp = 0;

		for (int i = 0; i <= (tempBoard.length - 1) / 2; i++) {
			for (int j = 0; j <= (tempBoard[0].length - 1); j++) {
				temp = tempBoard[i][j];
				tempBoard[i][j] = tempBoard[(tempBoard.length - 1) - i][j];
				tempBoard[(tempBoard.length - 1) - i][j] = temp;
			}
		}
		return tempBoard;
	}

	public static char[][] transpose(char board[][]) {
		char[][] tempBoard = Board.copyBoard(board);
		int n = tempBoard[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				char tmp = tempBoard[i][j];
				tempBoard[i][j] = tempBoard[j][i];
				tempBoard[j][i] = tmp;
			}
		}
		return tempBoard;
	}

	public static char[][] rotate(char[][] board) {
		char[][] tempBoard = Board.copyBoard(board);

		for (int i = 0; i < tempBoard.length; i++) {
			for (int j = i; j < tempBoard[0].length; j++) {
				char temp = tempBoard[i][j];
				tempBoard[i][j] = tempBoard[j][i];
				tempBoard[j][i] = temp;
			}
		}
		int N = tempBoard.length;
		for (int i = 0; i < tempBoard.length; i++) {
			for (int j = 0; j < N / 2; j++) {
				char temp = tempBoard[i][j];
				tempBoard[i][j] = tempBoard[i][N - 1 - j];
				tempBoard[i][N - 1 - j] = temp;
			}
		}
		return tempBoard;
	}

	public static char[][] flip(char board[][]) {
		char[][] tempBoard = Board.copyBoard(board);

		for (int row = 0; row < tempBoard.length; row++) { // Each column is accessed through each row
			for (int col = 0; col < tempBoard[0].length / 2; col++) { // Access each column for half of the image
				char temp = tempBoard[row][tempBoard[0].length - col - 1]; // Holds the opposite value to swap
				tempBoard[row][tempBoard[0].length - col - 1] = tempBoard[row][col]; // Puts current entry into
																						// 'opposite position'
				tempBoard[row][col] = temp; // Sets the current entry to that of the 'opposite position'
			}
		}
		return tempBoard;
	}

	public static char[][] verticalFlip(char board[][]) {
		char[][] tempBoard = Board.copyBoard(board);
		char[] temp = new char[tempBoard.length]; // This temporarily holds the row that needs to be flipped out
		for (int row = 0; row < tempBoard.length / 2; row++) { // Working one row at a time and only do half the
																// image!!!
			temp = tempBoard[(tempBoard.length) - row - 1]; // Collect the temp row from the 'other side' of the array
			tempBoard[tempBoard.length - row - 1] = tempBoard[row]; // Put the current row in the row on the 'other
																	// side' of the
			// array
			tempBoard[row] = temp; // Now put the row from the other side in the current row
		}
		return tempBoard;
	}

}
