package frc.robot.helpers.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.helpers.sensors.AnalogDistanceLimit;

public class AnalogDistanceSensor extends AnalogInput {
    private final double SLOPE = 0.0968, Y_INTERCEPT = -0.0134;

    // Data Source: https://www.pololu.com/file/0J713/GP2Y0A41SK0F.pdf
    // Calculations: https://docs.google.com/spreadsheets/d/1xHcWuo77p9sg5iKscfKN_5hNPehgSpC8CxtGcfqVukk/edit?usp=sharing

    public AnalogDistanceSensor(int port) {
        super(port);
    }

    public double getDistance() {
        return 1/(getVoltage() * SLOPE + Y_INTERCEPT);
    }

    public AnalogDistanceLimit getLimit(double LIMIT) {
        return new AnalogDistanceLimit(this, LIMIT);
    }
}