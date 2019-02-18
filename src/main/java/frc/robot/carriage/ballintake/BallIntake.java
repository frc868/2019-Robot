package frc.robot.carriage.ballintake;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.SubsystemManagerChild;
import edu.wpi.first.wpilibj.DigitalInput;


public class BallIntake extends SubsystemManagerChild {
  private WPI_TalonSRX primary;
  private DigitalInput limit;

  public BallIntake() {
    primary = new WPI_TalonSRX(RobotMap.Carriage.BallIntake.MOTOR);
    limit = new DigitalInput(RobotMap.Carriage.BallIntake.LIMIT);
  }

  /**
   * sets motor's speed
   * @param speed percentage power from -1 to 1
   */
  public void setSpeed(double speed) {
    primary.set(Helper.boundValue(speed));
  }

  /**
   * turn off motor
   */
  public void stop() {
    primary.stopMotor();
  }

  /**
   * 
   * @return speed motor is set to
   */
  public double getSpeed() {
    return primary.get();
  }
  
  /**
   * 
   * @return true if ball is detected
   */
  public boolean isBallDetected() {
    return limit.get();
  }

  @Override
  public void updateSD() {
    SmartDashboard.putNumber("Ball Intake Speed", getSpeed());
    SmartDashboard.putBoolean("Is Ball Detected", isBallDetected());
  }
  
}
