package frc.robot.sensors.gyro;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class Gyro extends SubsystemManagerChild {

    private AHRS gyro;

    public Gyro() {
        super("Gyro");
        gyro = new AHRS(SPI.Port.kMXP);
    }

    public double getRestrictedAngleRadians() {
        return Math.abs(Math.toRadians(getAngle())%(2*Math.PI));
    }

    public double getAngle() {
        return gyro.getAngle();
    }

    public double getYaw() {
        return gyro.getYaw();
    }

    public double getPitch() {
        return gyro.getPitch();
    }

    public double getRoll() {
        return gyro.getRoll();
    }
}