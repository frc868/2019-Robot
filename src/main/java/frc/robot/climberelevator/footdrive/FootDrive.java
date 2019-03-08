package frc.robot.climberelevator.footdrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;


public class FootDrive extends Subsystem {
  private WPI_TalonSRX motor;

  public FootDrive() {
    super("FootDrive");
    motor = new WPI_TalonSRX(RobotMap.ClimberElevator.FootDrive.MOTOR);
  }

  @Override
  protected void initDefaultCommand() {

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

  public void initSD() {
    //addTab("Motor", motor);
  }
  
  public void updateSD() {
    SmartDashboard.putNumber("Foot Drive: Speed", getSpeed());
  }
}
