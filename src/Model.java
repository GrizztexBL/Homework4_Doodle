public class Model {
    
    // member variables
    private double doodleX, doodleY, currentVelocity = 0;

    public Model(int doodleX, int doodleY){
        // set x and y
        this.doodleX = doodleX;
        this.doodleY = doodleY;
    }

    public void moveDoodle(){
        // change velocity and current y based on gravity acceleration
        currentVelocity = currentVelocity + DoodleJumpConstants.GRAVITY * DoodleJumpConstants.DURATION;
        doodleY = doodleY + currentVelocity * DoodleJumpConstants.DURATION;
    }

    public void moveLeft(){
        // move doodle to the left
        doodleX -= 10;
        // if doodle leaves screen, make it appear on the other side
        if (doodleX + DoodleJumpConstants.DOODLE_WIDTH <= 0){
            doodleX = DoodleJumpConstants.SCENE_WIDTH - (doodleX + DoodleJumpConstants.DOODLE_WIDTH);
        }
    }

    public void moveRight(){
        // move doodle to the right
        doodleX += 10;
        // if doodle leaves screen, make it appear on the other side
        if (doodleX >= DoodleJumpConstants.SCENE_WIDTH){
            doodleX = 0;
        }
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

    public void setDoodleY(int y){
        doodleY = y;
    }

    public void setCurrentVelocity(double currentVelocity){
        this.currentVelocity = currentVelocity;
    }
}
