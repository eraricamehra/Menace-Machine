package com.example.demo.service;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.psa.app.model.Game;
import com.psa.app.service.MenaceMachineService;

import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
public class MenaceMachineServiceTest {

//    @Mock
//    private ToDoRepository toDoRepository;
//
    @InjectMocks
    private MenaceMachineService menaceMachineService;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getcellsblockedfromlabelTest() {
    	
        String label = "X--O-----";
        Set<Integer> cells =menaceMachineService.getcellsblockedfromlabel(label);
        System.out.println(cells);
        assertNotNull(cells);
        
    }
    
    @Test
    public void getcellsblockedfromlabelTest2() {
    	
        String label = "---------";
        Set<Integer> cells =menaceMachineService.getcellsblockedfromlabel(label);
        System.out.println(cells);
        assertEquals(cells.size(), 0);
        
    }

//	@Test
//	public void playTest() {
//		Game game = null;
//		try {
//			 game = menaceMachineService.play();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		assertNotNull(game);
//
//	}
	
	@Test
	public void generatingRandomMoveTest() {
		Set<Integer> cellsBlocked = new HashSet<>();
		cellsBlocked.add(1);
		cellsBlocked.add(2);
		cellsBlocked.add(3);
		cellsBlocked.add(4);
		cellsBlocked.add(5);
		cellsBlocked.add(6);
		int randomMove = menaceMachineService.generatingRandomMove(cellsBlocked);
		System.out.println(!cellsBlocked.contains(randomMove));
	}

}