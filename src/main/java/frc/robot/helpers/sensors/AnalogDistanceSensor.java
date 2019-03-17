package frc.robot.helpers.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.helpers.sensors.AnalogDistanceLimit;

public class AnalogDistanceSensor extends AnalogInput {
    private final double SLOPE, Y_INTERCEPT;

    public AnalogDistanceSensor(int port) {
        super(port);
        SLOPE = 0.047;
        Y_INTERCEPT = -7.8 * Math.pow(10, -3);
    }

    public double getDistance() {
        return 1/(getVoltage() * SLOPE + Y_INTERCEPT);
    }

    public AnalogDistanceLimit getLimit(double LIMIT) {
        return new AnalogDistanceLimit(this, LIMIT);
    }
}