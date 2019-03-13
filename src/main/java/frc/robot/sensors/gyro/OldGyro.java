package frc.robot.sensors.gyro;

import edu.wpi.first.wpilibj.AnalogGyro;
import frc.robot.RobotMap;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class OldGyro extends SubsystemManagerChild {
    private AnalogGyro gyro;

    public OldGyro() {
        super("OldGyro");
        gyro = new AnalogGyro(RobotMap.Sensors.Gyro.OLD_GYRO);
    }

    public double getRestrictedAngleRadians() {
        return Math.abs(Math.toRadians(getAngle())%(2*Math.PI));
    }

    public double getAngle() {
        return gyro.getAngle();
    }

    public void reset() {
        gyro.reset();
    }

    @Override
    public void init() {
        reset();
    }


}