package com.psa.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.psa.app.model.Board;
import com.psa.app.model.Game;
import com.psa.app.model.Player;

@Service
public class GameService {
	List<Integer> topRow = Arrays.asList(1, 2, 3);
	List<Integer> midRow = Arrays.asList(4, 5, 6);
	List<Integer> botRow = Arrays.asList(7, 8, 9);
	List<Integer> leftCol = Arrays.asList(1, 4, 7);
	List<Integer> midCol = Arrays.asList(2, 5, 8);
	List<Integer> rightCol = Arrays.asList(3, 6, 9);
	List<Integer> diag1 = Arrays.asList(1, 5, 9);
	List<Integer> diag2 = Arrays.asList(3, 5, 7);

	private List<List<Integer>> getWinningLists() {
		List<List<Integer>> winning = new ArrayList<>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(diag1);
		winning.add(diag2);
		return winning;
	}

	public Game checkWin(Board board) {

		List<List<Integer>> winning = getWinningLists();

		for (List<Integer> li : winning) {
			if (board.getPlayerPositions().containsAll(li)) {
				return Game.LOSE;
			} else if (board.getCompPositions().containsAll(li)) {
				return Game.WIN;
			} else if (board.getCompPositions().size() + board.getPlayerPositions().size() == 9) {
				return Game.DRAW;
			}
		}
		return Game.PLAYING;
	}

	public Board placePos(Board currentBoard, int moveNumber, String user) {
		char[][] game = Board.copyBoard(currentBoard.getCurrentBoard());
		char symbol = 'X';
		if (user.equals(Player.RANDOM.toString())) {
			symbol = 'O';
		} else if (user.equals(Player.MENACE.toString())) {
			symbol = 'X';
		}
		switch (moveNumber) {
		case 1:
			game[0][0] = symbol;
			break;
		case 2:
			game[0][1] = symbol;
			break;
		case 3:
			game[0][2] = symbol;
			break;
		case 4:
			game[1][0] = symbol;
			break;
		case 5:
			game[1][1] = symbol;
			break;
		case 6:
			game[1][2] = symbol;
			break;
		case 7:
			game[2][0] = symbol;
			break;
		case 8:
			game[2][1] = symbol;
			break;
		case 9:
			game[2][2] = symbol;
			break;
		}
		currentBoard.setCurrentBoard(Board.copyBoard(game));
		return currentBoard;
	}

	public String convertBoardtoLabel(char[][] currentBoard) {
		String label = "";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (currentBoard[i][j] == ' ') {
					label = label + '-';
				} else
					label = label + currentBoard[i][j];
			}
		}

		return label;
	}
}
