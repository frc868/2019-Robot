package frc.robot.carriage.tilt;

import frc.robot.Robot;

public class BrakeTilt extends SetTiltPosition{
    
    /**
     * puts tilt on brake mode
     */
    public BrakeTilt(){
        super(Robot.tilt.getPotPosition()); // sets tilt position to current position
    }
}
