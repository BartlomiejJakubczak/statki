import domain.Board;
import domain.BoardPosition;
import domain.Boat;
import manager.GameManager;
import manager.GameManagerImpl;
import org.junit.Before;
import org.junit.Test;
import utils.BoatGenerator;
import view.BoardView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static utils.Constants.*;

public class GameTest {

    private Board testBoard;

    private GameManager testGameManager;

    @Before
    public void init() {
        testBoard = new Board(MINIMAL_BOARD_SIZE);
        testGameManager = new GameManagerImpl(new BoardView());
        testGameManager.setBoard(testBoard);
    }

    @Test
    public void shouldSetBoatAsHit_hitTheBoat() {
        BoardPosition[][] boardPositions = testBoard.getBoardPositions();
        testBoard.setBoats(Arrays.asList(generateTestBoat()));
        testGameManager.hitTheBoard(2,3);
        assertTrue(boardPositions[2][3].isHit());
    }

    @Test
    public void shouldSinkTheBoat_isBoatSunk() {
        Boat testBoat = generateTestBoat();
        testBoard.setBoats(Arrays.asList(testBoat));
        testGameManager.hitTheBoard(2,3);
        testGameManager.hitTheBoard(2,4);
        testGameManager.hitTheBoard(2,5);
        assertTrue(testBoat.isBoatSunk());
    }

    @Test
    public void shouldGenerateDefinedNumberOfBoats_generateBoats() {
        int expectedNumberOfBoats = NUMBER_OF_FIVE_MAST_BOATS
                + NUMBER_OF_FOUR_MAST_BOATS
                + NUMBER_OF_THREE_MAST_BOATS
                + NUMBER_OF_TWO_MAST_BOATS;
        testGameManager.generateBoats(new BoatGenerator(testBoard));
        assertEquals(testBoard.getBoats().size(), expectedNumberOfBoats);
    }

    @Test
    public void shouldReturnCorrectNumberOfActiveShips_getNumberOfRemainingBoats() {
        BoardPosition[][] boardPositions = testBoard.getBoardPositions();
        Boat boatOne = new Boat(Arrays.asList(boardPositions[1][1], boardPositions[1][2]));
        Boat boatTwo = new Boat(Arrays.asList(boardPositions[3][3], boardPositions[3][4]));
        testBoard.setBoats(Arrays.asList(boatOne, boatTwo));
        Map<Integer, Integer> mappedBoats = testBoard.getNumberOfRemainingBoats();
        int numberOfTwoMastBoats = mappedBoats.get(TWO_MAST_BOAT);
        assertEquals(numberOfTwoMastBoats, 2);
        testGameManager.hitTheBoard(1, 1);
        testGameManager.hitTheBoard(1, 2);
        mappedBoats = testBoard.getNumberOfRemainingBoats();
        numberOfTwoMastBoats = mappedBoats.get(TWO_MAST_BOAT);
        assertEquals(numberOfTwoMastBoats, 1);
    }

    @Test
    public void shouldDetermineBoardPositionAsInvalid_isBoardPositionValid() {
        testBoard.setBoats(Arrays.asList(generateTestBoat()));
        BoatGenerator boatGenerator = new BoatGenerator(testBoard);
        BoardPosition testBoardPosition = testBoard.getBoardPositions()[2][6];
        assertFalse(boatGenerator.isBoardPositionValid(testBoardPosition));
    }

    @Test
    public void shouldDetermineBoardPositionAsValid_isBoardPositionValid() {
        testBoard.setBoats(Arrays.asList(generateTestBoat()));
        BoatGenerator boatGenerator = new BoatGenerator(testBoard);
        BoardPosition testBoardPosition = testBoard.getBoardPositions()[2][8];
        assertTrue(boatGenerator.isBoardPositionValid(testBoardPosition));
    }

    @Test
    public void shouldGenerateTwoMastBoat_generateBoat() {
        BoatGenerator boatGenerator = new BoatGenerator(testBoard);
        checkBoatGeneration(boatGenerator, TWO_MAST_BOAT);
    }

    @Test
    public void shouldGenerateThreeMastBoat_generateBoat() {
        BoatGenerator boatGenerator = new BoatGenerator(testBoard);
        checkBoatGeneration(boatGenerator, THREE_MAST_BOAT);
    }

    @Test
    public void shouldGenerateFourMastBoat_generateBoat() {
        BoatGenerator boatGenerator = new BoatGenerator(testBoard);
        checkBoatGeneration(boatGenerator, FOUR_MAST_BOAT);
    }

    @Test
    public void shouldGenerateFiveMastBoat_generateBoat() {
        BoatGenerator boatGenerator = new BoatGenerator(testBoard);
        checkBoatGeneration(boatGenerator, FIVE_MAST_BOAT);
    }

    private Boat generateTestBoat() {
        BoardPosition[][] boardPositions = testBoard.getBoardPositions();
        List<BoardPosition> boatHitPoints = new ArrayList<BoardPosition>();
        boatHitPoints.add(boardPositions[2][3]);
        boatHitPoints.add(boardPositions[2][4]);
        boatHitPoints.add(boardPositions[2][5]);
        return new Boat(boatHitPoints);
    }

    private void checkBoatGeneration(BoatGenerator boatGenerator, int boatSize) {
        assertEquals(boatGenerator.generateBoat(5).getBoatHitPoints().size(), 5);
    }

}
