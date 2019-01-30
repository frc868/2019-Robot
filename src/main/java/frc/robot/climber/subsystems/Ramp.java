package frc.robot.climber.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;
import frc.robot.helpers.SubsystemManagerChild;


public class Ramp extends SubsystemManagerChild {
    private Solenoid actuator;

    public Ramp() {
        actuator = new Solenoid(RobotMap.Climber.RAMP);
    } 

    /**
     * 
     * @param state which state to set the ramp to
     */
    public void setState(boolean state) {
        actuator.set(state);
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
        return actuator.get();
    }
}
