package frc.robot.sensors.ultrasonic;

//import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.RobotMap;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class UltrasonicArray extends SubsystemManagerChild {
    //private Ultrasonic ultrasonic;
    private AnalogInput ultrasonic;
    private final double MAX_DISTANCE = 100; // TODO: re-tune this

    public UltrasonicArray() {
        super("Ultrasonic");
        //ultrasonic = new Ultrasonic(RobotMap.Sensors.Ultrasonic.FRONT_TRIGGER, RobotMap.Sensors.Ultrasonic.FRONT_ECHO);
        ultrasonic = new AnalogInput(RobotMap.Sensors.Ultrasonic.ANALOG_PORT);
    }

    public double getDistance() {
        return ultrasonic.getVoltage() * 5; // https://github.com/Team5593/mb1013/blob/master/MB1013_Analog.cpp
        //return ultrasonic.getRangeInches();
    }

    public double getAdjustedDistance() {
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