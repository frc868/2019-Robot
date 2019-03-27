package frc.robot.helpers.motorcontrollers;

import edu.wpi.first.wpilibj.Solenoid;

public class SolenoidPlus extends Solenoid {

    public SolenoidPlus(int pcm, int port) {
        super(pcm, port);
    }

    public SolenoidTrigger getTrigger(boolean state) {
        return new SolenoidTrigger(this, state);
    }
}