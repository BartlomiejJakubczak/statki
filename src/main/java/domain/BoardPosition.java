package domain;

public class BoardPosition {

    private int xPosition;
    private int yPosition;
    private boolean isHit = false;
    private Boat boatOccupyingPosition;

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

    public Boat getBoatOccupyingPosition() {
        return boatOccupyingPosition;
    }

    public void setBoatOccupyingPosition(Boat boatOccupyingPosition) {
        this.boatOccupyingPosition = boatOccupyingPosition;
    }

    @Override
    public String toString() {
        if (isHit() && boatOccupyingPosition != null) {
            return "S";
        } else if (isHit() && boatOccupyingPosition == null) {
            return "x";
        } else {
            return "-";
        }
    }

}
