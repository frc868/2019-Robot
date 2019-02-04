package frc.robot.powerpack.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;
import frc.robot.helpers.SubsystemManagerChild;


public class Ramps extends SubsystemManagerChild {
    private Solenoid left, right;

    public Ramps() {
        left = new Solenoid(RobotMap.Powerpack.RAMP_LEFT);
        right = new Solenoid(RobotMap.Powerpack.RAMP_RIGHT);
    } 

    /**
     * 
     * @param state which state to set the ramp to
     */
    public void setState(boolean state) {
        left.set(state);
        right.set(state);
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
        return left.get();
    }
}