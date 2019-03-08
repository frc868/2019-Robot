package frc.robot.carriage.ballintake;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.sensors.IRLimit;

public class BallIntake extends Subsystem {
  private WPI_TalonSRX motor;
  private IRLimit detection_limit;
  private final boolean BALL_DETECTED_STATE = true;

  public BallIntake() {
    super("BallIntake");
    motor = new WPI_TalonSRX(RobotMap.Carriage.BallIntake.MOTOR);
    detection_limit = new IRLimit(RobotMap.Carriage.BallIntake.DETECTION_LIMIT);
  }

  @Override
  protected void initDefaultCommand() {

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
   * @return state of ball detection limit switch
   */
  public boolean getBallDetectionLimit() {
    return detection_limit.get();
  }
  
  /**
   * @return true if ball is detected, else false
   */
  public boolean isBallDetected() {
    return getBallDetectionLimit() == BALL_DETECTED_STATE;
  }

  public void initSD() {
    //addTab("Motor", motor);
    //addTab("Detection Limit", detection_limit);
  }

  public void updateSD() {
    SmartDashboard.putBoolean("Ball Detected?", isBallDetected());
    SmartDashboard.putNumber("Intake Speed", getSpeed());
  }
}
