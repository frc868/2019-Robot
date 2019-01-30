package frc.robot.elevator.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.SubsystemManagerChild;


public class Tilt extends SubsystemManagerChild {
  private CANSparkMax primary;

  public Tilt() {
    primary = new CANSparkMax(RobotMap.Elevator.TILT, MotorType.kBrushless);
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
    return primary.getEncoder().getPosition();
  }

   /** 
   * 
   * @return state of forward limit switch
   */
  public boolean getForwardLimitSwitch() {
    return primary.getForwardLimitSwitch(RobotMap.Elevator.TILT_FORWARD_LIMIT_SWITCH_POLARITY).get();
  }

  /** 
   * 
   * @return state of reverse limit switch
   */
  public boolean getReverseLimitSwtich() {
    return primary.getReverseLimitSwitch(RobotMap.Elevator.TILT_REVERSE_LIMIT_SWITCH_POLARITY).get();
  }
}
