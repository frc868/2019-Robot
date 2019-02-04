package frc.robot.powerpack.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;
import frc.robot.helpers.SubsystemManagerChild;

public class Forks extends SubsystemManagerChild {
    private Solenoid left, right;

    public Forks() {
        left = new Solenoid(RobotMap.Powerpack.FORKS_LEFT);
        right = new Solenoid(RobotMap.Powerpack.FORKS_RIGHT);
    }

    /**
     * 
     * @param state which state to set the left fork to
     */
    public void setLeftState(boolean state) {
        left.set(state);
    }

    /**
     * opens left fork
     */
    public void openLeft() {
        setLeftState(false);
    }


    /**
     * closes left fork
     */
    public void closeLeft() {
        setLeftState(true);
    }

    /**
     * 
     * @return state of left fork
     */
    public boolean getLeftState() {
        return left.get();
    }

    /**
     * 
     * @param state which state to set the right fork to
     */
    public void setRightState(boolean state) {
        right.set(state);
    }

    /**
     * opens right fork
     */
    public void openRight() {
        setRightState(false);
    }


    /**
     * closes right fork
     */
    public void closeRight() {
        setRightState(true);
    }

    /**
     * 
     * @return state of right fork
     */
    public boolean getRightState() {
        return right.get();
    }

    /**
     * 
     * @param state which state to set the both forks to
     */
    public void setState(boolean state) {
        setRightState(state);
        setLeftState(state);
    }

    /**
     * opens both forks
     */
    public void open() {
        openLeft();
        openRight();
    }


    /**
     * closes both forks
     */
    public void close() {
        closeLeft();
        closeRight();
    }
    
}