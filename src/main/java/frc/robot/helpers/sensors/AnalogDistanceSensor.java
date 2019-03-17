package frc.robot.helpers.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.helpers.sensors.AnalogDistanceLimit;

public class AnalogDistanceSensor extends AnalogInput {
    private final double SLOPE, Y_INTERCEPT;

    // Data Source: https://www.pololu.com/product/136
    // Calculations: https://docs.google.com/spreadsheets/d/1xHcWuo77p9sg5iKscfKN_5hNPehgSpC8CxtGcfqVukk/edit?usp=sharing

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