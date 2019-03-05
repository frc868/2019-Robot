package frc.robot.climberelevator.forks;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class Forks extends SubsystemManagerChild {
    private Solenoid release;
    private final boolean OPEN_MODE = true;

    public Forks() {
        super("Forks");
        release = new Solenoid(RobotMap.PCM, RobotMap.ClimberElevator.Forks.RELEASE);
    }

    /**
     * @param state which state to set the forks to
     */
    public void setState(boolean state) {
        release.set(state);
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
     * @return state of release
     */
    public boolean getState() {
        return release.get();
    }

    /**
     * @return true if forks are open
     */
    public boolean isOpen() {
        return getState() == OPEN_MODE;
    }

    @Override
    public void initEnabled() {
        close();
    }

    @Override
    public void initSD() {
        addTab("Release", release);
    }

    @Override
    public void updateSD() {
        SmartDashboard.putBoolean("Forks: Open?", isOpen());
    }
}