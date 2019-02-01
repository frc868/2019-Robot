package frc.robot.carriage.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.SubsystemManagerChild;


public class Tilt extends SubsystemManagerChild {
  private WPI_TalonSRX primary;

  public Tilt() {
    primary = new WPI_TalonSRX(RobotMap.Carraige.TILT);
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
  public void turnOff() {
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

   /** 
   * 
   * @return state of forward limit switch
   */
  public boolean getForwardLimitSwitch() {
    return true; //TODO fix this
  }

  /** 
   * 
   * @return state of reverse limit switch
   */
  public boolean getReverseLimitSwtich() {
    return true; //TODO fix this
  }
}
