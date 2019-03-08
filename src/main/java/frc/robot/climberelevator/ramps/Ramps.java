package frc.robot.climberelevator.ramps;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class Ramps extends Subsystem {
    private Solenoid actuator;
    private final boolean DEPLOYED_STATE = true;

    public Ramps() {
        super("Ramps");
        actuator = new Solenoid(RobotMap.PCM, RobotMap.ClimberElevator.Ramps.ACTUATOR);
    }

    @Override
    protected void initDefaultCommand() {

    }

    /**
     * @param state state to set actuator to
     */
    public void setState(boolean state) {
        actuator.set(state);
    }

    /**
     * opens ramp
     */
    public void open() {
       setState(DEPLOYED_STATE);
    }


    /**
     * closes ramp
     */
    public void close() {
        setState(!DEPLOYED_STATE);
    }

    /**
     * @return state of ramps
     */
    public boolean getState() {
        return actuator.get();
    }

    /**
     * 
     * @return true if ramps are open
     */
    public boolean isOpen() {
        return getState() == DEPLOYED_STATE;
    }

    public void initEnabled() {
        close();
    }

    public void initSD() {
        //addTab("Actuator", actuator);
    }

    public void updateSD() {
        SmartDashboard.putBoolean("Ramps: Open?", isOpen());
    }
}
