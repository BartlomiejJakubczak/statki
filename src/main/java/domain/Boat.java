package domain;

import java.util.List;

public class Boat {

    private List<Board.BoardPosition> mBoatHitPoints;
    private int hitPoints;

    public Boat(List<Board.BoardPosition> boatHitPoints) {
        this.mBoatHitPoints = boatHitPoints;
        this.hitPoints = boatHitPoints.size();
    }

    public List<Board.BoardPosition> getBoatHitPoints() {
        return mBoatHitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
