package domain;

import domain.utils.BoatGenerator;
import manager.GameManagerImpl;

import java.util.*;

public final class Board {

    private static Board board_instance = null;
    private static BoardPosition[][] mBoardPositions;

    private static List<Boat> mBoats = new ArrayList<Boat>();

    private static int mBoardSize;

    private Board() {
    }

    public static Board getBoardInstance(int boardSize) {
        if (board_instance == null) {
            board_instance = new Board();
            mBoardSize = boardSize;
            board_instance.setUpBoardPositions();
            mBoats.addAll(BoatGenerator.setUpShips(board_instance));
            setBoatHitPointsOnBoard();
        }
        return board_instance;
    }

    public int getBoardSize() {
        return mBoardSize;
    }

    private static void setBoatHitPointsOnBoard() {
        for (Boat boat : mBoats) {
            for (BoardPosition boatHitPoint : boat.getBoatHitPoints()) {
                mBoardPositions[boatHitPoint.getXPosition()][boatHitPoint.getYPosition()].setBoatHitPoint(true);
            }
        }
    }

    private void setUpBoardPositions() {
        mBoardPositions = new BoardPosition[mBoardSize][mBoardSize];
        for (int i = 0; i < mBoardSize; i++) {
            for (int j = 0; j < mBoardSize; j++) {
                mBoardPositions[i][j] = new BoardPosition(i, j);
            }
        }
    }

    public void showBoard() {
        System.out.println("Number of active two mast boats: " + GameManagerImpl.NUMBER_OF_ACTIVE_TWO_MAST_BOATS);
        System.out.println("Number of active three mast boats: " + GameManagerImpl.NUMBER_OF_ACTIVE_THREE_MAST_BOATS);
        System.out.println("Number of active four mast boats: " + GameManagerImpl.NUMBER_OF_ACTIVE_FOUR_MAST_BOATS);
        System.out.println("Number of active five mast boats: " + GameManagerImpl.NUMBER_OF_ACTIVE_FIVE_MAST_BOATS);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ");
        stringBuilder.append("\t");
        stringBuilder.append(" ");
        stringBuilder.append("\t");
        for (int number = 0; number < mBoardSize; number++) {
            stringBuilder.append(number);
            stringBuilder.append("\t");
        }
        System.out.print(stringBuilder.toString());
        System.out.println();
        stringBuilder.setLength(0);
        for (int i = 0; i < mBoardSize; i++) {
            stringBuilder.append(i);
            stringBuilder.append("\t");
            stringBuilder.append("[");
            stringBuilder.append("\t");
            for (int j = 0; j < mBoardSize; j++) {
                stringBuilder.append(mBoardPositions[i][j]);
                stringBuilder.append("\t");
            }
            stringBuilder.append("]");
            System.out.print(stringBuilder.toString());
            System.out.println();
            stringBuilder.setLength(0);
        }
    }

    public void receiveShot(int xPosition, int yPosition) {
        mBoardPositions[xPosition][yPosition].setHit(true);
        if (mBoardPositions[xPosition][yPosition].isBoatHitPoint()) {
            for (Boat boat : mBoats) {
                for (BoardPosition boatHitPoint : boat.getBoatHitPoints()) {
                    if (boatHitPoint.getXPosition() == xPosition && boatHitPoint.getYPosition() == yPosition) {
                        System.out.println("TRAFILEM STATEK");
                        boat.setHitPoints(boat.getHitPoints() - 1);
                        if (boat.getHitPoints() == 0) {
                            switch (boat.getBoatHitPoints().size()) {
                                case 2:
                                    GameManagerImpl.NUMBER_OF_ACTIVE_TWO_MAST_BOATS = GameManagerImpl.NUMBER_OF_ACTIVE_TWO_MAST_BOATS - 1;
                                    break;
                                case 3:
                                    GameManagerImpl.NUMBER_OF_ACTIVE_THREE_MAST_BOATS = GameManagerImpl.NUMBER_OF_ACTIVE_THREE_MAST_BOATS - 1;
                                    break;
                                case 4:
                                    GameManagerImpl.NUMBER_OF_ACTIVE_FOUR_MAST_BOATS = GameManagerImpl.NUMBER_OF_ACTIVE_FOUR_MAST_BOATS - 1;
                                    break;
                                case 5:
                                    GameManagerImpl.NUMBER_OF_ACTIVE_FIVE_MAST_BOATS = GameManagerImpl.NUMBER_OF_ACTIVE_FIVE_MAST_BOATS - 1;
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }

    public class BoardPosition {

        private int xPosition;
        private int yPosition;
        private boolean isHit = false;
        private boolean isBoatHitPoint = false;

        public BoardPosition(int xPosition, int yPosition) {
            this.xPosition = xPosition;
            this.yPosition = yPosition;
        }

        public int getXPosition() {
            return xPosition;
        }

        public int getYPosition() {
            return yPosition;
        }

        public void setHit(boolean hit) {
            isHit = hit;
        }

        public boolean isHit() {
            return isHit;
        }

        public boolean isBoatHitPoint() {
            return isBoatHitPoint;
        }

        public void setBoatHitPoint(boolean boatHitPoint) {
            isBoatHitPoint = boatHitPoint;
        }

        @Override
        public String toString() {
            if (isHit() && isBoatHitPoint()) {
                return "S";
            } else if (isHit() && !isBoatHitPoint()) {
                return "x";
            } else {
                return "-";
            }
        }
    }

}
