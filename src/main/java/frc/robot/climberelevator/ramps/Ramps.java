package frc.robot.climberelevator.ramps;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;
import frc.robot.helpers.SubsystemManagerChild;


public class Ramps extends SubsystemManagerChild {
    private Solenoid ramps;

    public Ramps() {
        ramps = new Solenoid(RobotMap.ClimberElevator.Ramps.RAMPS);
    } 

    /**
     * 
     * @param state which state to set the ramp to
     */
    public void setState(boolean state) {
        ramps.set(state);
    }

    /**
     * opens ramp
     */
    public void open() {
        setState(false);
    }


    /**
     * closes ramp
     */
    public void close() {
        setState(true);
    }

    /**
     * 
     * @return state of ramp
     */
    public boolean getState() {
        return ramps.get();
    }
}
