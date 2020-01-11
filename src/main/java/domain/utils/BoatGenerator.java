package domain.utils;

import domain.Board;
import domain.Boat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoatGenerator {

    public static final int NUMBER_OF_TWO_MAST_BOATS = 2;
    public static final int NUMBER_OF_THREE_MAST_BOATS = 2;
    public static final int NUMBER_OF_FOUR_MAST_BOATS = 2;
    public static final int NUMBER_OF_FIVE_MAST_BOATS = 1;
    private static boolean left_direction_tried = false;
    private static boolean right_direction_tried = false;
    private static boolean up_direction_tried = false;
    private static boolean down_direction_tried = false;

    private static List<Boat> generatedBoats;
    private static Board board;
    private static Random randomGenerator;

    public static List<Boat> setUpShips(Board currentBoard) {
        int currentTwoMastBoatsNumber = 0;
        int currentThreeMastBoatsNumber = 0;
        int currentFourMastBoatsNumber = 0;
        int currentFiveMastBoatsNumber = 0;
        generatedBoats = new ArrayList<Boat>();
        board = currentBoard;
        randomGenerator = new Random();

        while (currentTwoMastBoatsNumber != NUMBER_OF_TWO_MAST_BOATS) {
            generateBoat(2);
            currentTwoMastBoatsNumber++;
        }

        while (currentThreeMastBoatsNumber != NUMBER_OF_THREE_MAST_BOATS) {
            generateBoat(3);
            currentThreeMastBoatsNumber++;
        }

        while (currentFourMastBoatsNumber != NUMBER_OF_FOUR_MAST_BOATS) {
            generateBoat(4);
            currentFourMastBoatsNumber++;
        }

        while (currentFiveMastBoatsNumber != NUMBER_OF_FIVE_MAST_BOATS) {
            generateBoat(5);
            currentFiveMastBoatsNumber++;
        }

        return generatedBoats;
    }

    private static void generateBoat(int numberOfMasts) {
        left_direction_tried = false;
        right_direction_tried = false;
        up_direction_tried = false;
        down_direction_tried = false;
        List<Board.BoardPosition> boatHitPoints = new ArrayList<Board.BoardPosition>();
        Board.BoardPosition startingPoint = generateBoatStartingPoint();
        startingPoint.setBoatHitPoint(true);
        boatHitPoints.add(startingPoint);
        if (randomGenerator.nextInt(2) == 0) {
            generateHorizontally(numberOfMasts, boatHitPoints, startingPoint);
        } else {
            generateVertically(numberOfMasts, boatHitPoints, startingPoint);
        }
    }

    private static void generateHorizontally(int numberOfMasts, List<Board.BoardPosition> boatHitPoints, Board.BoardPosition startingPoint) {
        if (randomGenerator.nextInt(2) == 0) {
            addToTheLeft(numberOfMasts, boatHitPoints, startingPoint);
        } else {
            addToTheRight(numberOfMasts, boatHitPoints, startingPoint);
        }
    }

    private static void addToTheLeft(int numberOfMasts, List<Board.BoardPosition> boatHitPoints, Board.BoardPosition startingPoint) {
        left_direction_tried = true;
        boolean creationFailed = false;
        Board.BoardPosition generatedBoatHitPoint = board.new BoardPosition(startingPoint.getXPosition(), startingPoint.getYPosition());
        while(boatHitPoints.size() != numberOfMasts) {
            Board.BoardPosition shiftedBoatHitPoint = board.new BoardPosition(generatedBoatHitPoint.getXPosition() - 1, generatedBoatHitPoint.getYPosition());
            if (checkIfHitPointIsValid(shiftedBoatHitPoint)) {
                shiftedBoatHitPoint.setBoatHitPoint(true);
                boatHitPoints.add(shiftedBoatHitPoint);
                generatedBoatHitPoint = shiftedBoatHitPoint;
            } else if (!right_direction_tried) {
                creationFailed = true;
                boatHitPoints.clear();
                boatHitPoints.add(startingPoint);
                addToTheRight(numberOfMasts, boatHitPoints, startingPoint);
                break;
            } else {
                creationFailed = true;
                generateBoat(numberOfMasts);
                break;
            }
        }
        if (!creationFailed) {
            generatedBoats.add(new Boat(boatHitPoints));
        }
    }

    private static void addToTheRight(int numberOfMasts, List<Board.BoardPosition> boatHitPoints, Board.BoardPosition startingPoint) {
        right_direction_tried = true;
        boolean creationFailed = false;
        Board.BoardPosition generatedBoatHitPoint = board.new BoardPosition(startingPoint.getXPosition(), startingPoint.getYPosition());
        while(boatHitPoints.size() != numberOfMasts) {
            Board.BoardPosition shiftedBoatHitPoint = board.new BoardPosition(generatedBoatHitPoint.getXPosition() + 1, generatedBoatHitPoint.getYPosition());
            if (checkIfHitPointIsValid(shiftedBoatHitPoint)) {
                shiftedBoatHitPoint.setBoatHitPoint(true);
                boatHitPoints.add(shiftedBoatHitPoint);
                generatedBoatHitPoint = shiftedBoatHitPoint;
            } else if (!left_direction_tried) {
                creationFailed = true;
                boatHitPoints.clear();
                boatHitPoints.add(startingPoint);
                addToTheLeft(numberOfMasts, boatHitPoints, startingPoint);
                break;
            } else {
                creationFailed = true;
                generateBoat(numberOfMasts);
                break;
            }
        }
        if (!creationFailed) {
            generatedBoats.add(new Boat(boatHitPoints));
        }
    }

    private static void generateVertically(int numberOfMasts, List<Board.BoardPosition> boatHitPoints, Board.BoardPosition startingPoint) {
        if (randomGenerator.nextInt(2) == 0) {
            addToTheTop(numberOfMasts, boatHitPoints, startingPoint);
        } else {
            addToTheBottom(numberOfMasts, boatHitPoints, startingPoint);
        }
    }

    private static void addToTheTop(int numberOfMasts, List<Board.BoardPosition> boatHitPoints, Board.BoardPosition startingPoint) {
        up_direction_tried = true;
        boolean creationFailed = false;
        Board.BoardPosition generatedBoatHitPoint = board.new BoardPosition(startingPoint.getXPosition(), startingPoint.getYPosition());
        while(boatHitPoints.size() != numberOfMasts) {
            Board.BoardPosition shiftedBoatHitPoint = board.new BoardPosition(generatedBoatHitPoint.getXPosition(), generatedBoatHitPoint.getYPosition() - 1);
            if (checkIfHitPointIsValid(shiftedBoatHitPoint)) {
                shiftedBoatHitPoint.setBoatHitPoint(true);
                boatHitPoints.add(shiftedBoatHitPoint);
                generatedBoatHitPoint = shiftedBoatHitPoint;
            } else if (!down_direction_tried) {
                creationFailed = true;
                boatHitPoints.clear();
                boatHitPoints.add(startingPoint);
                addToTheBottom(numberOfMasts, boatHitPoints, startingPoint);
                break;
            } else {
                creationFailed = true;
                generateBoat(numberOfMasts);
                break;
            }
        }
        if (!creationFailed) {
            generatedBoats.add(new Boat(boatHitPoints));
        }
    }

    private static void addToTheBottom(int numberOfMasts, List<Board.BoardPosition> boatHitPoints, Board.BoardPosition startingPoint) {
        down_direction_tried = true;
        boolean creationFailed = false;
        Board.BoardPosition generatedBoatHitPoint = board.new BoardPosition(startingPoint.getXPosition(), startingPoint.getYPosition());
        while(boatHitPoints.size() != numberOfMasts) {
            Board.BoardPosition shiftedBoatHitPoint = board.new BoardPosition(generatedBoatHitPoint.getXPosition(), generatedBoatHitPoint.getYPosition() + 1);
            if (checkIfHitPointIsValid(shiftedBoatHitPoint)) {
                shiftedBoatHitPoint.setBoatHitPoint(true);
                boatHitPoints.add(shiftedBoatHitPoint);
                generatedBoatHitPoint = shiftedBoatHitPoint;
            } else if (!up_direction_tried) {
                creationFailed = true;
                boatHitPoints.clear();
                boatHitPoints.add(startingPoint);
                addToTheTop(numberOfMasts, boatHitPoints, startingPoint);
                break;
            } else {
                creationFailed = true;
                generateBoat(numberOfMasts);
                break;
            }
        }
        if (!creationFailed) {
            generatedBoats.add(new Boat(boatHitPoints));
        }
    }

    private static Board.BoardPosition generateBoatStartingPoint() {
        Board.BoardPosition generatedBoatHitPoint = board.new BoardPosition(randomGenerator.nextInt(board.getBoardSize()), randomGenerator.nextInt(board.getBoardSize()));
        while (!checkIfHitPointIsValid(generatedBoatHitPoint)) {
            generatedBoatHitPoint = board.new BoardPosition(randomGenerator.nextInt(board.getBoardSize()), randomGenerator.nextInt(board.getBoardSize()));
        }
        return generatedBoatHitPoint;
    }

    private static boolean checkIfHitPointIsValid(Board.BoardPosition boardPosition) {
        if (boardPosition.getXPosition() < 0 || boardPosition.getXPosition() >= board.getBoardSize() || boardPosition.getYPosition() < 0 || boardPosition.getYPosition() >= board.getBoardSize()) {
            return false;
        } else {
            int xPosition = boardPosition.getXPosition();
            int yPosition = boardPosition.getYPosition();
            for (Boat boat : generatedBoats) {
                for (Board.BoardPosition boatHitPoint : boat.getBoatHitPoints()) {
                    int boatXPosition = boatHitPoint.getXPosition();
                    int boatYPosition = boatHitPoint.getYPosition();
                    if (boatXPosition == xPosition && boatYPosition == yPosition) {
                        return false;
                    } else if (boatXPosition + 1 == xPosition && boatYPosition == yPosition
                            || boatXPosition - 1 == xPosition && boatYPosition == yPosition
                            || boatYPosition + 1 == yPosition && boatXPosition == xPosition
                            || boatYPosition - 1 == yPosition && boatXPosition == xPosition) {
                        return false;
                    } else if ((boatXPosition + 1 == xPosition && boatYPosition + 1 == yPosition)
                            || (boatXPosition + 1 == xPosition && boatYPosition - 1 == yPosition)
                            || (boatXPosition - 1 == xPosition && boatYPosition + 1 == yPosition)
                            || (boatXPosition - 1 == xPosition && boatYPosition - 1 == yPosition)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

}
