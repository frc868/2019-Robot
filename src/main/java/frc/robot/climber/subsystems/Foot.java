package frc.robot.climber.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.SubsystemManagerChild;


public class Foot extends SubsystemManagerChild {
  private CANSparkMax primary, secondary;

  public Foot() {
    primary = new CANSparkMax(RobotMap.Climber.FOOT_PRIMARY, MotorType.kBrushless);
    secondary = new CANSparkMax(RobotMap.Climber.FOOT_SECONDARY, MotorType.kBrushless);

    secondary.follow(primary);
  }

  /**
     * sets foot motor's speed
     * @param speed percentage power from -1 to 1
     */
    public void setSpeed(double speed) {
    primary.set(Helper.boundValue(speed));
  }

  /**
   * turn off foot motor
   */
  public void turnOff() {
    primary.stopMotor();
  }

  /**
   * 
   * @return speed foot motor is set to
   */
  public double getSpeed() {
    return primary.get();
  }

  /**
   * 
   * @return position of foot motor according to encoder
   */
  public double getEncPosition() {
    return primary.getEncoder().getPosition();
  }

  /** 
   * 
   * @return state of foot's forward limit switch
   */
  public boolean getForwardLimitSwitch() {
    return primary.getForwardLimitSwitch(RobotMap.Climber.FORWARD_LIMIT_SWITCH_POLARITY).get();
  }

  /** 
   * 
   * @return state of foot's reverse limit switch
   */
  public boolean getReverseLimitSwtich() {
    return primary.getReverseLimitSwitch(RobotMap.Climber.REVERSE_LIMIT_SWITCH_POLARITY).get();
  }
}
