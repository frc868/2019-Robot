package frc.robot.climberelevator.ramps;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;
import frc.robot.helpers.SubsystemManagerChild;


public class Ramps extends SubsystemManagerChild {
    private Solenoid ramps;
    private final boolean RAMPS_MODE = true;

    public Ramps() {
        ramps = new Solenoid(RobotMap.ClimberElevator.Ramps.RAMPS);
    }

    /**
     * opens ramp
     */
    public void open() {
        ramps.set(RAMPS_MODE);
    }


    /**
     * closes ramp
     */
    public void close() {
        ramps.set(!RAMPS_MODE);
    }

    /**
     * 
     * @return true if ramps are open
     */
    public boolean isOpen() {
        return ramps.get() == RAMPS_MODE;
    }
}
