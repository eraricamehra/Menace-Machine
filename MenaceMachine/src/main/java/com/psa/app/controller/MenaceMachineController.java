package com.psa.app.controller;

import com.psa.app.model.Game;
import com.psa.app.model.Move;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.psa.app.service.MenaceMachineService;

@RestController
@RequestMapping(value = "/menace")
public class MenaceMachineController {

	@Autowired
	private MenaceMachineService menaceMachineService;

	Logger log = LoggerFactory.getLogger(MenaceMachineService.class);

	@RequestMapping(value = "/train")
	public void train() {
		try {
			int wins =0;
			int lost =0;
			int draws =0;
			int gamesPlayed=0;
			for (int i = 1; i <= 10000; i++) {
				log.info("***********************************************************");
				log.info("---------------ROUND NUMBER: " + i + "---------------");
				Game gameResult = menaceMachineService.play();
				log.info("---------------GAME RESULT: " + gameResult + "----------");
				gamesPlayed++;
				if(gameResult==Game.WIN) {
					wins++;
				}else if(gameResult==Game.LOSE) {
					lost++;
				}
				else if(gameResult==Game.DRAW) {
					draws++;
				}
			}
			log.info("***********************************************************");
			log.info("Number of games WON: "+wins);
			log.info("Number of games LOST: "+lost);
			log.info("Number of games DRAW: "+draws);
			log.info("Number of games played: "+gamesPlayed);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/run", produces = "application/json")
	public ModelAndView viewShelf() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");

		return modelAndView;
	}

	@RequestMapping(value = "/next/{label}/{player}", produces = "application/json")
	public ResponseEntity<List<Move>> updateGame(@PathVariable("label") String label,
			@PathVariable("player") String player) {
		List<Move> moves = new ArrayList<>();
		try {
			moves = menaceMachineService.getMenaceMoveFromTrainedData(label, player);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(moves, HttpStatus.OK);

	}

}
