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
        return Math.abs(Math.toRadians(getPitch())%(2*Math.PI));
    }

    public double getYaw() {
        return gyro.getPitch(); // due to vertical mouning
    }

    public double getPitch() {
        return gyro.getYaw(); // due to veritical mounting
    }

    public double getRoll() {
        return gyro.getRoll();
    }
}