package frc.robot.carriage.tilt;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.SubsystemManagerChild;


public class Tilt extends SubsystemManagerChild {
  private WPI_TalonSRX motor;
  private Encoder encoder;

  public Tilt() {
    motor = new WPI_TalonSRX(RobotMap.Carriage.Tilt.MOTOR);
    encoder = new Encoder(RobotMap.Carriage.Tilt.ENCODER_A, RobotMap.Carriage.Tilt.ENCODER_B);
  }

  /**
   * sets motor's speed
   * @param speed percentage power from -1 to 1
   */
  public void setSpeed(double speed) {
    motor.set(Helper.boundValue(speed));
  }

  /**
   * turn off motor
   */
  public void turnOff() {
    motor.stopMotor();
  }

  /**
   * 
   * @return speed motor is set to
   */
  public double getSpeed() {
    return motor.get();
  }

  /**
   * 
   * @return position of motor according to encoder
   */
  public double getEncPosition() {
    return encoder.get();
  }

  @Override
  public void updateSD() {
    SmartDashboard.putNumber("Hatch Pickup Wrist Speed", getSpeed());
    SmartDashboard.putNumber("Hatch Pickup Wrist Position", getEncPosition());
  }
}
