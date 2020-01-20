package domain;

import java.util.List;

public class Boat {

    private List<BoardPosition> boatHitPoints;

    public Boat(List<BoardPosition> boatHitPoints) {
        this.boatHitPoints = boatHitPoints;
        for (BoardPosition boardPosition : boatHitPoints) {
            boardPosition.setBoatOccupyingPosition(this);
        }
    }

    public List<BoardPosition> getBoatHitPoints() {
        return boatHitPoints;
    }

    public boolean isBoatSunk() {
        int hitPoints = boatHitPoints.size();
        for (BoardPosition hitpoint : boatHitPoints) {
            if (hitpoint.isHit()) {
                hitPoints--;
            }
        }
        return hitPoints == 0;
    }

}
