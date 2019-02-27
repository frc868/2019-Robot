package frc.robot.climberelevator.ramps;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class Ramps extends SubsystemManagerChild {
    private Solenoid actuator;
    private final boolean RAMPS_MODE = true;

    public Ramps() {
        super("Ramps");
        actuator = new Solenoid(RobotMap.PCM, RobotMap.ClimberElevator.Ramps.ACTUATOR);
    }

    /**
     * opens ramp
     */
    public void open() {
        actuator.set(RAMPS_MODE);
    }


    /**
     * closes ramp
     */
    public void close() {
        actuator.set(!RAMPS_MODE);
    }

    /**
     * 
     * @return true if ramps are open
     */
    public boolean isOpen() {
        return actuator.get() == RAMPS_MODE;
    }

    @Override
    public void updateSD() {
        SmartDashboard.putBoolean("Ramps Open", isOpen());
    }

    @Override
    public void initDebug() {
        addDebug("Ramps", actuator);
    }

    @Override
    public void initTab() {
        addDebug("Actuator", actuator);
    }
}
