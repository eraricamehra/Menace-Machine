package com.psa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.app.model.Bead;
import com.psa.app.model.Board;
import com.psa.app.model.Box;
import com.psa.app.model.Game;
import com.psa.app.model.Move;
import com.psa.app.model.Player;
import com.psa.app.utils.ApplicationConstants;
import com.psa.app.utils.FileUtils;
import com.psa.app.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MenaceMachineService {

	Logger log = LoggerFactory.getLogger(MenaceMachineService.class);

	@Autowired
	private GameService gameService;

	@Autowired
	private TrainingService trainingService;

	public Game play() throws Exception {

		// get all possible states in a hashmap with key as state/board and Box(initial)
		// as value
		Board board = new Board("---------"); // initial state
//		Reading from training  data and storing in a map
		Map<String, Box> map = new HashMap<>();
		map = FileUtils.readTrainingData();
		List<Board> allOccuredStates = new LinkedList<>();
//		Properties props = loadPropertiesFile("position.properties");
		Set<Integer> cellsBlocked = new HashSet<>();

		Game game = Game.PLAYING;
		for (Map.Entry<String, Box> entry : map.entrySet()) {
			entry.getValue();
		}

		// random number generator
		StringBuilder sb = new StringBuilder();
		while (game == Game.PLAYING) {
			int computerMove = 0;

			if (cellsBlocked.size() == 9) {
				allOccuredStates = draw(game, board, allOccuredStates);
				game = Game.DRAW;
				if (!map.containsKey(board.getLabel()))
					map.put(board.getLabel(), new Box());
				break;
			}

			if (!map.containsKey(board.getLabel())) {
				map.put(board.getLabel(), new Box());
				sb.append(board.getLabel());
				sb.append(System.getProperty("line.separator"));
			}

			if (map.containsKey(board.getLabel())) {
				Box box = map.get(board.getLabel());
				computerMove = getMaxiMumProbableMove(box, cellsBlocked);

				cellsBlocked.add(computerMove);
				if (cellsBlocked.size() == 9) {
					allOccuredStates = draw(game, board, allOccuredStates);
					game = Game.DRAW;
					if (!map.containsKey(board.getLabel()))
						map.put(board.getLabel(), new Box());
					break;
				}

			}

			log.info("Computer Move: " + computerMove);
			board.getCompPositions().add(computerMove);

			int humanMove = generatingRandomMove(cellsBlocked);
			cellsBlocked.add(humanMove);
			board.getPlayerPositions().add(humanMove);

			log.info("Human Move   : " + humanMove);
			board.setMoveNumber(humanMove);

			// update board --input current board, + computerMove
			board = gameService.placePos(board, computerMove, Player.MENACE.toString());
			board = gameService.placePos(board, humanMove, Player.RANDOM.toString());

			game = gameService.checkWin(board);
			if (game != Game.PLAYING) {
				board.setLabel(gameService.convertBoardtoLabel(board.getCurrentBoard()));
				log.info("Labels: " + board.getLabel());
				board.setMoveNumber(humanMove);
				allOccuredStates.add(new Board(board.getLabel(), board.getMoveNumber()));
				if (!map.containsKey(board.getLabel()))
					map.put(board.getLabel(), new Box());
				break;
			}

			board.setLabel(gameService.convertBoardtoLabel(board.getCurrentBoard()));
			log.info("Labels: " + board.getLabel());
			board.setMoveNumber(humanMove);

			allOccuredStates.add(new Board(board.getLabel(), board.getMoveNumber()));
		}
		FileUtils.writeToFile(sb.toString(), "results.txt");

		// TODO test
		trainingService.trainMenace(allOccuredStates, map, game);

		// maintain the new hashmap
		StringBuilder data = new StringBuilder();
		for (Map.Entry<String, Box> entry : map.entrySet()) {
			data.append(entry.getKey() + "=" + entry.getValue().toString());
			data.append(System.getProperty("line.separator"));
//			writeToProps();
		}
		FileUtils.writeToProps(map);
		FileUtils.writeToFile(data.toString(), "training.txt");

		return game;
	}

	public List<Move> getMenaceMoveFromTrainedData(String label, String currentPlayer) throws Exception {
		Map<String, Box> map = new HashMap<>(); //
		map = FileUtils.readTrainingData();
		List<Move> moves = new ArrayList<>();
		// get computer move
		int menaceMove = 0;
		// getcellsblockedfromlabel //X-O------
		Set<Integer> cellsBlocked = getcellsblockedfromlabel(label);
		if (!map.containsKey(label)) {
			map.put(label, new Box());
		}
		if (map.containsKey(label)) {
			Box box = map.get(label);
			List<Bead> beads = box.getBeads();
			Map<Integer, Integer> frequency = new HashMap<>();
			for (Bead bead : beads) {
				frequency.put(bead.getNum(), frequency.getOrDefault(bead.getNum(), 0) + 1);
			}
			PriorityQueue<Integer> minheap = new PriorityQueue<>((n1, n2) -> frequency.get(n2) - frequency.get(n1));

			for (int c : frequency.keySet()) {
				minheap.offer(c);
			}
			if (!minheap.isEmpty()) {
				menaceMove = minheap.poll();
				while (cellsBlocked.contains(menaceMove) && !minheap.isEmpty()) {
					menaceMove = minheap.poll();
				}
			} else {
				menaceMove = Utils.getRandomNumber();
				while (cellsBlocked.contains(menaceMove)) {
					menaceMove = Utils.getRandomNumber();
				}
			}

		}
		System.out.println(ApplicationConstants.MENACE_PLAYER == currentPlayer);
		System.out.println(ApplicationConstants.MENACE_PLAYER + "men");
		System.out.println(currentPlayer + "currentPlayer");
		if (ApplicationConstants.MENACE_PLAYER.equals(currentPlayer)) {
			System.out.println("menace");
			Move menace = new Move(menaceMove, ApplicationConstants.MENACE_PLAYER);
			moves.add(menace);
		} else {
			System.out.println("random");

			int randomMove = Utils.getRandomNumber();

			while (cellsBlocked.contains(randomMove)) {
				randomMove = Utils.getRandomNumber();
			}
			Move random = new Move(randomMove, ApplicationConstants.RANDOM_PLAYER);

			moves.add(random);
		}

		return moves;

	}

	public Set<Integer> getcellsblockedfromlabel(String label) {
		Set<Integer> cellsBlocked = new HashSet<>();
		for (int i = 0; i < label.length(); i++) {
			if (label.charAt(i) == 'X' || label.charAt(i) == 'O') {
				cellsBlocked.add(i + 1);
			}
		}
		return cellsBlocked;

	}

	public List<Board> draw(Game game, Board board, List<Board> allOccuredStates) {
		board.setLabel(gameService.convertBoardtoLabel(board.getCurrentBoard()));
		allOccuredStates.add(new Board(board.getLabel(), board.getMoveNumber()));

		return allOccuredStates;
	}

	public int getMaxiMumProbableMove(Box box, Set<Integer> cellsBlocked) {
		List<Bead> beads = box.getBeads();
		Map<Integer, Integer> frequency = new HashMap<>();
		for (Bead bead : beads) {
			frequency.put(bead.getNum(), frequency.getOrDefault(bead.getNum(), 0) + 1);
		}
		PriorityQueue<Integer> minheap = new PriorityQueue<>((n1, n2) -> frequency.get(n2) - frequency.get(n1));

		for (int c : frequency.keySet()) {
			minheap.offer(c);
		}
		int computerMove = 0;
		if (!minheap.isEmpty()) {
			computerMove = minheap.poll();
			while (cellsBlocked.contains(computerMove) && !minheap.isEmpty()) {
				computerMove = minheap.poll();
			}
		} else {
			computerMove = Utils.getRandomNumber();
			while (cellsBlocked.contains(computerMove)) {
				computerMove = Utils.getRandomNumber();
			}
		}
		return computerMove;

	}

	public int generatingRandomMove(Set<Integer> cellsBlocked) {
		int humanMove = Utils.getRandomNumber();
		while (cellsBlocked.contains(humanMove)) {
			humanMove = Utils.getRandomNumber();
		}
		return humanMove;
	}

}
