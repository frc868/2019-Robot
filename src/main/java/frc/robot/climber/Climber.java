package frc.robot.climber;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Helper;
import frc.robot.RobotMap;


public class Climber extends Subsystem {
  private CANSparkMax motor;

  public Climber() {
    motor = new CANSparkMax(RobotMap.Climber.MOTOR, MotorType.kBrushless);
  } 

  @Override
  public void initDefaultCommand() {

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
  public void off() {
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
    return motor.getEncoder().getPosition();
  }

  /**
   * 
   * @return velocity of motor according to encoder
   */
  public double getEncVelocity() {
    return motor.getEncoder().getVelocity();
  }

  /** 
   * 
   * @return state of forward limit switch
   */
  public boolean getForwardLimitSwitch() {
    return motor.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen).get();
  }

  /** 
   * 
   * @return state of reverse limit switch
   */
  public boolean getReverseLimitSwtich() {
    return motor.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen).get();
  }
}
