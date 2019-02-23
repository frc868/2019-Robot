package frc.robot.climberelevator.forks;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class Forks extends SubsystemManagerChild {
    private Solenoid forks;
    private final boolean OPEN_MODE = true;

    public Forks() {
        forks = new Solenoid(RobotMap.PCM, RobotMap.ClimberElevator.Forks.FORKS);
    }

    /**
     * @param state which state to set the forks to
     */
    public void setState(boolean state) {
        forks.set(state);
    }

    /**
     * opens forks
     */
    public void open() {
        setState(OPEN_MODE);
    }

    /**
     * closes forks
     */
    public void close() {
        setState(!OPEN_MODE);
    }

    /**
     * @return true if forks are open
     */
    public boolean isOpen() {
        return forks.get() == OPEN_MODE;
    }
}