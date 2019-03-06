package frc.robot.helpers.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class IRLimit extends DigitalInput {
    private LimitTrigger limitTrigger;
    
    public IRLimit(int port) {
        super(port);
        this.limitTrigger = new LimitTrigger(this);
    }

    @Override
    public boolean get() {
        return !super.get();
    }

    public LimitTrigger getTrigger() {
        return limitTrigger;
    }


}