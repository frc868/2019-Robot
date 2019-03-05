package frc.robot.climberelevator.footdrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.subsystems.SubsystemManagerChild;


public class FootDrive extends SubsystemManagerChild {
  private WPI_TalonSRX motor;

  public FootDrive() {
    super("FootDrive");
    motor = new WPI_TalonSRX(RobotMap.ClimberElevator.FootDrive.MOTOR);
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
  public void stop() {
    motor.stopMotor();
  }

  /**
   * 
   * @return speed motor is set to
   */
  public double getSpeed() {
    return motor.get();
  }

  @Override
  public void initSD() {
    addTab("Motor", motor);
  }
  
  @Override
  public void updateSD() {
    SmartDashboard.putNumber("Foot Drive: Speed", getSpeed());
  }
}
