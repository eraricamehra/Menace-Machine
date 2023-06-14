package com.example.demo.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.psa.app.model.Board;
import com.psa.app.model.Game;
import com.psa.app.model.Player;
import com.psa.app.service.GameService;
import com.psa.app.service.MenaceMachineService;

@RunWith(SpringJUnit4ClassRunner.class)
public class GameServiceTest {
	

    @InjectMocks
    private GameService gameService;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkWinningConditionsTestWin() {
    	Board board = new Board();
    	List<Integer> playerMoves = new ArrayList<>();
    	playerMoves.add(1);
    	playerMoves.add(2);
    	playerMoves.add(3);
    	board.setCompPositions(playerMoves);
        Game game =  gameService.checkWin(board);
        assertEquals(game.toString(), "WIN");
    }
    
    @Test
    public void checkWinningConditionsTestLose() {
    	Board board = new Board();
    	List<Integer> compMoves = new ArrayList<>();
    	compMoves.add(5);
    	compMoves.add(6);
    	compMoves.add(4);
    	board.setCompPositions(compMoves);
        Game game =  gameService.checkWin(board);
        assertEquals(game.toString(), "WIN");
    }
    
    
    @Test
    public void checkWinningConditionsTestDraw() {
    	Board board = new Board();
    	List<Integer> playerMoves = new ArrayList<>();
    	List<Integer> compMoves = new ArrayList<>();
    	compMoves.add(1);
    	compMoves.add(2);
    	compMoves.add(6);
    	compMoves.add(7);
    	compMoves.add(8);
    	playerMoves.add(3);
    	playerMoves.add(4);
    	playerMoves.add(5);
    	playerMoves.add(8);
    	board.setCompPositions(playerMoves);
    	board.setPlayerPositions(compMoves);


        Game game =  gameService.checkWin(board);
        assertEquals(game.toString(), "DRAW");
    }

    
    @Test
    public void placePositionTest1() {
    	Board board = new Board();
    	board.setLabel("X--O--XO-");
    	char[][] cb = { { 'X', ' ', ' ' }, { 'O', ' ', ' ' }, { 'X', 'O', ' ' } };
        board.setCurrentBoard(cb);
        Board updatedBoard =  gameService.placePos(board, 2, Player.MENACE.toString());
        assertNotEquals(cb,updatedBoard.getCurrentBoard());
    }

    
    @Test
    public void placePositionTest2() {
    	Board board = new Board();
    	board.setLabel("X--O--XOX");
    	char[][] cb = { { 'X', ' ', ' ' }, { 'O', ' ', ' ' }, { 'X', 'O', 'X' } };
        board.setCurrentBoard(cb);
        Board updatedBoard =  gameService.placePos(board, 2, Player.RANDOM.toString());
        assertNotEquals(cb,updatedBoard.getCurrentBoard());
    }


    @Test
    public void convertBoardtoLabelTest() {
    	char[][] cb = { { 'X', ' ', ' ' }, { 'O', ' ', ' ' }, { 'X', 'O', 'X' } };
    	String label = gameService.convertBoardtoLabel(cb);
    	assertNotNull(label);
    }
}
