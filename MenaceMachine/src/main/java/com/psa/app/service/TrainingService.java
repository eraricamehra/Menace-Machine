package com.psa.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.psa.app.model.Bead;
import com.psa.app.model.Board;
import com.psa.app.model.Box;
import com.psa.app.model.Game;
import com.psa.app.utils.ApplicationConstants;

@Service
public class TrainingService {

	public List<Bead> trainBox(List<Bead> currentBeads, int moveNumber, Game gameVerdict) {
		// adding the move taken beta number of times in the beads list
		if (gameVerdict == Game.WIN) {
			List<Bead> newList = new ArrayList<>();
			int count = ApplicationConstants.GAMMA;
			for (int i = 0; i < currentBeads.size(); i++) {

				if (currentBeads.get(i).getNum() == moveNumber && count > 0) {
					count--;
					continue;
				}
				newList.add(currentBeads.get(i));
			}
			currentBeads = newList;

		} else if (gameVerdict == Game.LOSE) {
			for (int i = 1; i <= ApplicationConstants.BETA; i++) {
				currentBeads.add(new Bead(moveNumber));
			}

		} else if (gameVerdict == Game.DRAW) {
			for (int i = 1; i <= ApplicationConstants.DELTA; i++) {
				currentBeads.add(new Bead(moveNumber));
			}
		}
		return currentBeads;

	}
	
	public Map<String, Box> trainMenace(List<Board> allOccuredStates, Map<String, Box> map, Game game) {
		for (Board b : allOccuredStates) {
			String stateLabel = b.getLabel();
			if (map.containsKey(stateLabel)) {
				Box state = map.get(stateLabel);
				List<Bead> currentBeads = state.getBeads();
				int moveNumber = b.getMoveNumber();
				currentBeads = trainBox(currentBeads, moveNumber, game);
				state.setBeads(currentBeads);
				map.put(stateLabel, state);
			}
		}
		return map;
	}

}
