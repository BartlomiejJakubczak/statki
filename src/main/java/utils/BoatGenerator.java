package utils;

import domain.Board;
import domain.BoardPosition;
import domain.Boat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static utils.Constants.*;

public final class BoatGenerator {

    private List<BoardPosition> generatedBoatPositions;
    private Random randomGenerator = new Random();
    private Board receivedBoard;
    private int boatSize;

    public BoatGenerator(Board board) {
        receivedBoard = board;
    }

    public Boat generateBoat(int boatSize) {
        generatedBoatPositions = new ArrayList<BoardPosition>();
        this.boatSize = boatSize;
        populateBoatBoardPositions();
        return new Boat(generatedBoatPositions);
    }

    private void populateBoatBoardPositions() {
        generatedBoatPositions.add(pickBoatStartingPoint());
        switch (randomGenerator.nextInt(RANDOMIZATION_BOUNDARY)) {
            case X_RIGHT_DIRECTION:
                addBoatHitPointsToBoat(1, 0);
                break;
            case X_LEFT_DIRECTION:
                addBoatHitPointsToBoat(-1, 0);
                break;
            case Y_UP_DIRECTION:
                addBoatHitPointsToBoat(0, 1);
                break;
            case Y_DOWN_DIRECTION:
                addBoatHitPointsToBoat(0, -1);
                break;
        }
    }

    private void addBoatHitPointsToBoat(int xShift, int yShift) {
        while (generatedBoatPositions.size() != boatSize) {
            BoardPosition lastGeneratedBoardPosition = generatedBoatPositions.get(generatedBoatPositions.size() - 1);
            BoardPosition shiftedBoardPosition =
                    receivedBoard.getBoardPositions()[lastGeneratedBoardPosition.getXPosition() + xShift][lastGeneratedBoardPosition.getYPosition() + yShift];
            if (isBoardPositionValid(shiftedBoardPosition)) {
                generatedBoatPositions.add(shiftedBoardPosition);
            } else {
                generatedBoatPositions.clear();
                populateBoatBoardPositions();
                break;
            }
        }
    }

    private BoardPosition pickBoatStartingPoint() {
        int boardSize = receivedBoard.getBoardSize();
        BoardPosition generatedBoardPosition =
                receivedBoard.getBoardPositions()[randomGenerator.nextInt(boardSize)][randomGenerator.nextInt(boardSize)];
        while (!isBoardPositionValid(generatedBoardPosition)) {
            generatedBoardPosition =
                    receivedBoard.getBoardPositions()[randomGenerator.nextInt(boardSize)][randomGenerator.nextInt(boardSize)];
        }
        return generatedBoardPosition;
    }

    public boolean isBoardPositionValid(BoardPosition generatedBoardPosition) {
        int xPosition = generatedBoardPosition.getXPosition();
        int yPosition = generatedBoardPosition.getYPosition();
        BoardPosition[][] boardPositions = receivedBoard.getBoardPositions();
        try {
            return boardPositions[xPosition][yPosition].getBoatOccupyingPosition() == null
                    && boardPositions[xPosition + 1][yPosition].getBoatOccupyingPosition() == null
                    && boardPositions[xPosition + 1][yPosition + 1].getBoatOccupyingPosition() == null
                    && boardPositions[xPosition + 1][yPosition - 1].getBoatOccupyingPosition() == null
                    && boardPositions[xPosition - 1][yPosition].getBoatOccupyingPosition() == null
                    && boardPositions[xPosition - 1][yPosition + 1].getBoatOccupyingPosition() == null
                    && boardPositions[xPosition - 1][yPosition - 1].getBoatOccupyingPosition() == null
                    && boardPositions[xPosition][yPosition + 1].getBoatOccupyingPosition() == null
                    && boardPositions[xPosition][yPosition - 1].getBoatOccupyingPosition() == null;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

}
