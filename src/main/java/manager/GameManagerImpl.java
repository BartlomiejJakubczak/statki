package manager;

import domain.Board;
import domain.utils.BoatGenerator;

import java.util.*;

public class GameManagerImpl implements GameManager {

    public static int NUMBER_OF_ACTIVE_TWO_MAST_BOATS = BoatGenerator.NUMBER_OF_TWO_MAST_BOATS;
    public static int NUMBER_OF_ACTIVE_THREE_MAST_BOATS = BoatGenerator.NUMBER_OF_THREE_MAST_BOATS;
    public static int NUMBER_OF_ACTIVE_FOUR_MAST_BOATS = BoatGenerator.NUMBER_OF_FOUR_MAST_BOATS;
    public static int NUMBER_OF_ACTIVE_FIVE_MAST_BOATS = BoatGenerator.NUMBER_OF_FIVE_MAST_BOATS;

    private Board mBoard;
    private Scanner mScanner;

    public GameManagerImpl() {
        this.mScanner = new Scanner(System.in);
    }

    private int getBoardSizeFromUser() {
        System.out.println("Podaj rozmiar planszy, minimum to 10.");
        int size = mScanner.nextInt();
        while (!isBoardSizeWithingRange(size)) {
            System.out.println("Podana wartosc jest mniejsza od 10. Sprobuj jeszcze raz.");
            size = mScanner.nextInt();
        }
        return size;
    }

    private List<Integer> getShotCoordinatesFromUser() {
        System.out.println("Podaj numer rzedu: ");
        int xPosition = mScanner.nextInt();
        System.out.println("Podaj numer kolumny: ");
        int yPosition = mScanner.nextInt();
        List<Integer> coordinates = new ArrayList<Integer>();
        coordinates.add(xPosition);
        coordinates.add(yPosition);
        return coordinates;
    }

    private void initializeBoard(int size) {
        mBoard = Board.getBoardInstance(size);
    }

    private void showBoard() {
        mBoard.showBoard();
    }

    public void play() {
        initializeBoard(getBoardSizeFromUser());
        showBoard();
        while (!allShipsSunk()) {
            List<Integer> coordinates = getShotCoordinatesFromUser();
            int xPosition = coordinates.get(0);
            int yPosition = coordinates.get(1);
            hitTheBoard(xPosition, yPosition);
            showBoard();
        }
        System.out.println("Game over");
    }

    private void hitTheBoard(int xPosition, int yPosition) {
        mBoard.receiveShot(xPosition, yPosition);
    }

    private boolean isBoardSizeWithingRange(int size) {
        return size >= 10;
    }

    private boolean allShipsSunk() {
        return NUMBER_OF_ACTIVE_TWO_MAST_BOATS == 0 &&
                NUMBER_OF_ACTIVE_THREE_MAST_BOATS == 0 &&
                NUMBER_OF_ACTIVE_FOUR_MAST_BOATS == 0 &&
                NUMBER_OF_ACTIVE_FIVE_MAST_BOATS == 0;
    }

}
