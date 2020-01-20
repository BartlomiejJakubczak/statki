package game;

import domain.Board;
import manager.GameManager;
import manager.GameManagerImpl;
import utils.BoatGenerator;
import view.BoardView;

public class Game {

    public static void main(String[] args) {
        GameManager gameManager = new GameManagerImpl(new BoardView());
        Board board = new Board(gameManager.getBoardSizeFromUser());
        gameManager.setBoard(board);
        gameManager.generateBoats(new BoatGenerator(board));
        gameManager.play();
    }

}
