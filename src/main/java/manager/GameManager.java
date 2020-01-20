package manager;


import domain.Board;
import utils.BoatGenerator;


public interface GameManager {

    int getBoardSizeFromUser();
    void setBoard(Board board);
    void generateBoats(BoatGenerator boatGenerator);
    void hitTheBoard(int xPosition, int yPosition);
    void play();

}
