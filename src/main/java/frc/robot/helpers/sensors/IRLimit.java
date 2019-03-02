package frc.robot.helpers.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class IRLimit extends DigitalInput {
    
    public IRLimit(int port) {
        super(port);
    }

    @Override
    public boolean get() {
        return !super.get();
    }


}