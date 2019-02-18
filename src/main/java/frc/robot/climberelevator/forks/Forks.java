package frc.robot.climberelevator.forks;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;
import frc.robot.helpers.SubsystemManagerChild;

public class Forks extends SubsystemManagerChild {
    private Solenoid forks;

    public Forks() {
        forks = new Solenoid(RobotMap.ClimberElevator.Forks.FORKS);
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
        setState(false);
    }

    /**
     * closes forks
     */
    public void close() {
        setState(true);
    }

    /**
     * @return state of forks
     */
    public boolean getState() {
        return forks.get();
    }
}