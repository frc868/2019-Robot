package frc.robot.carriage.ballintake;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.subsystems.SubsystemManagerChild;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;


public class BallIntake extends SubsystemManagerChild {
  private WPI_TalonSRX motor;
  private DigitalInput detection_limit;

  public BallIntake() {
    super("BallIntake");
    motor = new WPI_TalonSRX(RobotMap.Carriage.BallIntake.MOTOR);
    detection_limit = new DigitalInput(RobotMap.Carriage.BallIntake.DETECTION_LIMIT);
  }

  /**
   * sets motor's speed
   * @param speed percentage power from -1 to 1 (anything outside of this range will be bounded)
   */
  public void setSpeed(double speed) {
    motor.set(Helper.boundValue(speed));
  }

  /**
   * turns off the motor
   */
  public void stop() {
    motor.stopMotor();
  }

  /**
   * @return speed that the motor is set to
   */
  public double getSpeed() {
    return motor.get();
  }
  
  /**
   * @return true if ball is detected, else false
   */
  public boolean isBallDetected() {
    return detection_limit.get();
  }

  @Override
  public void updateSD() {
    SmartDashboard.putBoolean("Is Ball Detected", isBallDetected());
  }

  @Override
  public void initDebug() {
    addDebug("Is Ball Detected", detection_limit);
  }

  @Override
  public void initTab() {
    addTab("Motor", motor);
    addTab("Is Ball Detected", detection_limit);
  }

  @Override
  public void updateTab() {
    addTab("Current", motor.getOutputCurrent());
  }
  
}
