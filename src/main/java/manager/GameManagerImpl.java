package manager;

import domain.Board;
import domain.BoardPosition;
import domain.Boat;
import utils.BoatGenerator;
import view.View;

import java.util.ArrayList;
import java.util.List;

import static utils.Constants.*;

public class GameManagerImpl implements GameManager {

    private Board board;

    private final View boardView;

    public GameManagerImpl(View view) {
        boardView = view;
    }

    public int getBoardSizeFromUser() {
        int receivedValue = boardView.getBoardSizeFromUser();
        while (!isBoardSizeWithingRange(receivedValue)) {
            boardView.displayMessage("Podana wartosc jest mniejsza od 10. Sprobuj jeszcze raz.");
        }
        return receivedValue;
    }

    public void setBoard(Board board) {
        this.board = board;
        setUpBoardPositions();
    }

    public void generateBoats(BoatGenerator boatGenerator) {
        int currentTwoMastBoatsNumber = 0;
        int currentThreeMastBoatsNumber = 0;
        int currentFourMastBoatsNumber = 0;
        int currentFiveMastBoatsNumber = 0;
        List<Boat> generatedBoats = new ArrayList<Boat>();
        while (currentTwoMastBoatsNumber != NUMBER_OF_TWO_MAST_BOATS) {
            generatedBoats.add(boatGenerator.generateBoat(TWO_MAST_BOAT));
            currentTwoMastBoatsNumber++;
        }
        while (currentThreeMastBoatsNumber != NUMBER_OF_THREE_MAST_BOATS) {
            generatedBoats.add(boatGenerator.generateBoat(THREE_MAST_BOAT));
            currentThreeMastBoatsNumber++;
        }
        while (currentFourMastBoatsNumber != NUMBER_OF_FOUR_MAST_BOATS) {
            generatedBoats.add(boatGenerator.generateBoat(FOUR_MAST_BOAT));
            currentFourMastBoatsNumber++;
        }
        while (currentFiveMastBoatsNumber != NUMBER_OF_FIVE_MAST_BOATS) {
            generatedBoats.add(boatGenerator.generateBoat(FIVE_MAST_BOAT));
            currentFiveMastBoatsNumber++;
        }
        board.setBoats(generatedBoats);
    }

    public void hitTheBoard(int xPosition, int yPosition) {
        BoardPosition[][] boardPositions = board.getBoardPositions();
        boardPositions[xPosition][yPosition].setHit(true);
    }

    public void play() {
        boardView.showBoard(board);
        while(areBoatsFloating()) {
            List<Integer> coordinates = boardView.getBoardCoordinatesFromUser();
            int xPosition = coordinates.get(0);
            int yPosition = coordinates.get(1);
            hitTheBoard(xPosition, yPosition);
            boardView.showBoard(board);
        }
        boardView.closeScanner();
    }

    private void setUpBoardPositions() {
        int boardSize = board.getBoardSize();
        BoardPosition[][] boardPositions = new BoardPosition[boardSize][boardSize];
        for (int i = 0; i < boardSize; i ++) {
            for (int j = 0; j < boardSize; j++) {
                boardPositions[i][j] = new BoardPosition(i, j);
            }
        }
        board.setBoardPositions(boardPositions);
    }

    private boolean isBoardSizeWithingRange(int size) {
        return size >= MINIMAL_BOARD_SIZE;
    }

    private boolean areBoatsFloating() {
        List<Boat> boats = board.getBoats();
        int numberOfBoats = boats.size();
        for (Boat boat : boats) {
            if (boat.isBoatSunk()) {
                numberOfBoats--;
            }
        }
        return numberOfBoats != 0;
    }
}
