package frc.robot.helpers.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class IRLimit extends DigitalInput {
    private LimitTrigger limitTrigger;
    /**
     * the infrared limit switch
     * @param port the RoboRIO port the sensor is plugged into
     */
    public IRLimit(int port) {
        super(port);
        this.limitTrigger = new LimitTrigger(this);
    }

    @Override
    public boolean get() {
        return !super.get();
    }

    /**
     * @return the Trigger for when the limit is triggered
     */
    public LimitTrigger getTrigger() {
        return limitTrigger;
    }


}