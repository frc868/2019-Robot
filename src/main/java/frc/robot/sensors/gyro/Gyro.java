package frc.robot.sensors.gyro;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class Gyro extends SubsystemManagerChild {

    private AHRS gyro;

    public Gyro() {
        super("Gyro");
        gyro = new AHRS(RobotMap.Sensors.Gyro.GYRO);
    }

    /**
     * @return the angle, in radians restricted from 0 to 2pi
     */
    public double getRestrictedAngleRadians() {
        return Math.abs(Math.toRadians(getAngle())%(2*Math.PI));
    }

    /**
     * @return the yaw angle
     */
    public double getAngle() {
        return gyro.getAngle();
    }

    /**
     * @return yaw, or horizontal rotation of the bot
     */
    public double getYaw() {
        return gyro.getYaw();
    }

    /**
     * @return pitch, or forward/backward tipping of the bot
     */
    public double getPitch() {
        return gyro.getPitch();
    }

    /**
     * @return roll, or left/right tipping of the bot
     */
    public double getRoll() {
        return gyro.getRoll();   
    }

    /**
     * resets gyro angle
     */
    public void reset() {
        gyro.reset();
    }

    /**
     * @return true if the camera is calibrating
     */
    public boolean isCalibrating() {
        return gyro.isCalibrating();
    }

    @Override
    public void init() {
        reset();
    }

    @Override
    public void updateSD() {
        // System.out.println(gyro.getAngle());
        SmartDashboard.putNumber("Angle", getAngle());
        SmartDashboard.putNumber("Pitch", getPitch());
        SmartDashboard.putNumber("Roll", getRoll());
        SmartDashboard.putBoolean("Is Calibrating", isCalibrating());
    }
}