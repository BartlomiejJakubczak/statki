package view;

import domain.Board;

import java.util.List;

public interface View {

    void showBoard(Board board);

    int getBoardSizeFromUser();

    List<Integer> getBoardCoordinatesFromUser();

    void displayMessage(String message);

    void closeScanner();

}
