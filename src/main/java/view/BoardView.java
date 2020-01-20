package view;

import domain.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BoardView implements View {

    private Scanner scanner = new Scanner(System.in);

    public void showBoard(Board board) {
        Map<Integer, Integer> mappedBoats = board.getNumberOfRemainingBoats();
        System.out.println("Number of active two mast boats: " + mappedBoats.get(2));
        System.out.println("Number of active three mast boats: " + mappedBoats.get(3));
        System.out.println("Number of active four mast boats: " + mappedBoats.get(4));
        System.out.println("Number of active five mast boats: " + mappedBoats.get(5));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ");
        stringBuilder.append("\t");
        stringBuilder.append(" ");
        stringBuilder.append("\t");
        for (int number = 0; number < board.getBoardSize(); number++) {
            stringBuilder.append(number);
            stringBuilder.append("\t");
        }
        System.out.print(stringBuilder.toString());
        System.out.println();
        stringBuilder.setLength(0);
        for (int i = 0; i < board.getBoardSize(); i++) {
            stringBuilder.append(i);
            stringBuilder.append("\t");
            stringBuilder.append("[");
            stringBuilder.append("\t");
            for (int j = 0; j < board.getBoardSize(); j++) {
                stringBuilder.append(board.getBoardPositions()[i][j]);
                stringBuilder.append("\t");
            }
            stringBuilder.append("]");
            System.out.print(stringBuilder.toString());
            System.out.println();
            stringBuilder.setLength(0);
        }
    }

    public int getBoardSizeFromUser() {
        System.out.println("Podaj rozmiar planszy, minimum to 10.");
        return scanner.nextInt();
    }

    public List<Integer> getBoardCoordinatesFromUser() {
        System.out.print("Podaj numer rzedu: ");
        int xPosition = scanner.nextInt();
        System.out.print("Podaj numer kolumny: ");
        int yPosition = scanner.nextInt();
        List<Integer> coordinates = new ArrayList<Integer>();
        coordinates.add(xPosition);
        coordinates.add(yPosition);
        return coordinates;
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void closeScanner() {
        scanner.close();
    }

}
