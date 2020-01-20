package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Constants.*;

public class Board {

    private BoardPosition[][] boardPositions;
    private List<Boat> boats;
    private int boardSize;

    public Board(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public List<Boat> getBoats() {
        return boats;
    }

    public void setBoats(List<Boat> boats) {
        this.boats = boats;
    }

    public void setBoardPositions(BoardPosition[][] boardPositions) {
        this.boardPositions = boardPositions;
    }

    public BoardPosition[][] getBoardPositions() {
        return boardPositions;
    }

    public Map<Integer, Integer> getNumberOfRemainingBoats() {
        Map<Integer, Integer> mappedBoats = new HashMap<Integer, Integer>();
        int numberOfTwoMastBoats = 0;
        int numberOfThreeMastBoats = 0;
        int numberOfFourMastBoats = 0;
        int numberOfFiveMastBoats = 0;
        for (Boat boat: boats) {
            switch (boat.getBoatHitPoints().size()) {
                case TWO_MAST_BOAT:
                    if (!boat.isBoatSunk()) {
                        mappedBoats.put(TWO_MAST_BOAT, ++numberOfTwoMastBoats);
                    }
                    break;
                case THREE_MAST_BOAT:
                    if (!boat.isBoatSunk()) {
                        mappedBoats.put(THREE_MAST_BOAT, ++numberOfThreeMastBoats);
                    }
                    break;
                case FOUR_MAST_BOAT:
                    if (!boat.isBoatSunk()) {
                        mappedBoats.put(FOUR_MAST_BOAT, ++numberOfFourMastBoats);
                    }
                    break;
                case FIVE_MAST_BOAT:
                    if (!boat.isBoatSunk()) {
                        mappedBoats.put(FIVE_MAST_BOAT, ++numberOfFiveMastBoats);
                    }
                    break;
            }
        }
        return mappedBoats;
    }

}
