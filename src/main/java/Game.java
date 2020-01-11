import manager.GameManager;
import manager.GameManagerImpl;

public class Game {

    public static void main(String[] args) {
        GameManager gameManager = new GameManagerImpl();
        gameManager.play();
    }

}
