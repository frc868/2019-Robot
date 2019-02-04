package frc.robot.powerpack.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.SubsystemManagerChild;


public class FootDrive extends SubsystemManagerChild {
  private WPI_TalonSRX primary;

  public FootDrive() {
    primary = new WPI_TalonSRX(RobotMap.Powerpack.DRIVE);
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
   * @return position of motor according to encoder
   */
  public double getEncPosition() {
    return primary.getSelectedSensorPosition();
  }

  @Override
  public void updateSD() {
    SmartDashboard.putNumber("Foot Drive Speed", getSpeed());
    SmartDashboard.putNumber("Foot Drive Position", getEncPosition());
  }
}
