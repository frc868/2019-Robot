package frc.robot.helpers.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.helpers.sensors.AnalogDistanceLimit;

public class AnalogDistanceSensor extends AnalogInput {
    private final double SLOPE = 0, Y_INTERCEPT = 0;

    public AnalogDistanceSensor(int port) {
        super(port);
    }

    public double getDistance() {
        return 1/(getVoltage()*SLOPE + Y_INTERCEPT);
    }

    public AnalogDistanceLimit getLimit(double LIMIT) {
        return new AnalogDistanceLimit(this, LIMIT);
    }
}