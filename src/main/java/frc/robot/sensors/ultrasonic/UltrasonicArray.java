package frc.robot.sensors.ultrasonic;

import edu.wpi.first.wpilibj.Ultrasonic;
import frc.robot.RobotMap;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class UltrasonicArray extends SubsystemManagerChild {
    private Ultrasonic ultrasonic;
    private final double MAX_DISTANCE = 100;

    public UltrasonicArray() {
        ultrasonic = new Ultrasonic(RobotMap.Sensors.Ultrasonic.FRONT_TRIGGER, RobotMap.Sensors.Ultrasonic.FRONT_ECHO);
    }

    public double getDistance() {
        return ultrasonic.getRangeInches();
    }

    public double getAdjustedDistnace() {
        if (objectDetected()) {
            return getDistance();
        } else {
            return 0;
        }
    }

    public boolean objectDetected() {
        return getDistance() <= MAX_DISTANCE;
    }

  
}