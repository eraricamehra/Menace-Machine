package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.psa.app.model.Bead;
import com.psa.app.model.Game;
import com.psa.app.service.MenaceMachineService;
import com.psa.app.service.TrainingService;
import com.psa.app.utils.ApplicationConstants;
import com.psa.app.utils.Utils;

@RunWith(SpringJUnit4ClassRunner.class)
public class TrainingServiceTest {

//  @Mock
//  private ToDoRepository toDoRepository;
//
	@InjectMocks
	private TrainingService trainingService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testTrainedForWin() {
		List<Bead> currentbeads = new ArrayList<>();
		int originalLength = currentbeads.size();
		int arrayLength = ApplicationConstants.ALPHA;
		for (int i = 1; i <= arrayLength; i++) {
			currentbeads.add(new Bead(Utils.getRandomNumber())); // initializing Box
		}
		List<Bead> updatedbeads  = new ArrayList<>();
		updatedbeads = trainingService.trainBox(currentbeads, 2, Game.WIN);
		int updatedLength = updatedbeads.size();
		assertNotEquals(originalLength, updatedLength);
	}

	@Test
	public void testTrainedForDraw() {

		List<Bead> currentbeads = new ArrayList<>();
		int originalLength = currentbeads.size();
		int arrayLength = ApplicationConstants.ALPHA;
		for (int i = 1; i <= arrayLength; i++) {
			currentbeads.add(new Bead(Utils.getRandomNumber())); // initializing Box
		}
		List<Bead> updatedbeads  = new ArrayList<>();
		updatedbeads = trainingService.trainBox(currentbeads, 2, Game.DRAW);
		int updatedLength = updatedbeads.size();
		assertNotEquals(originalLength, updatedLength);
	}

	@Test
	public void testTrainedForLose() {
		List<Bead> currentbeads = new ArrayList<>();
		int originalLength = currentbeads.size();
		int arrayLength = ApplicationConstants.ALPHA;
		for (int i = 1; i <= arrayLength; i++) {
			currentbeads.add(new Bead(Utils.getRandomNumber())); // initializing Box
		}
		List<Bead> updatedbeads  = new ArrayList<>();
		updatedbeads = trainingService.trainBox(currentbeads, 2, Game.LOSE);
		int updatedLength = updatedbeads.size();
		assertNotEquals(originalLength, updatedLength);
	}

}
