public class Model {
    
    private double doodleX, doodleY, currentVelocity = 0;

    public Model(int doodleX, int doodleY){
        this.doodleX = doodleX;
        this.doodleY = doodleY;
    }

    public void moveDoodle(){
        currentVelocity = currentVelocity + DoodleJumpConstants.GRAVITY * DoodleJumpConstants.DURATION;
        doodleY = doodleY + currentVelocity * DoodleJumpConstants.DURATION;
    }

    public void moveLeft(){
        doodleX -= 10;
        if (doodleX + DoodleJumpConstants.DOODLE_WIDTH <= 0){
            doodleX = DoodleJumpConstants.SCENE_WIDTH - (doodleX + DoodleJumpConstants.DOODLE_WIDTH);
        }
    }

    public void moveRight(){
        doodleX += 10;
        if (doodleX >= DoodleJumpConstants.SCENE_WIDTH){
            doodleX = 0;
        }
    }

    public void jumpDoodle(){
        currentVelocity = DoodleJumpConstants.REBOUND_VELOCITY;
    }

    public double getDoodleX() {
        return doodleX;
    }

    public double getDoodleY() {
        return doodleY;
    }

    public double getCurrentVelocity() {
        return currentVelocity;
    }
}
