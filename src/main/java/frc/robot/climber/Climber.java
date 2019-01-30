package frc.robot.climber;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Helper;
import frc.robot.RobotMap;


public class Climber extends Subsystem {
  private CANSparkMax footPrimary, footSecondary;
  private CANSparkMax drive;
  private Solenoid ramp;

  public Climber() {
    footPrimary = new CANSparkMax(RobotMap.Climber.FOOT_PRIMARY, MotorType.kBrushless);
    footSecondary = new CANSparkMax(RobotMap.Climber.FOOT_SECONDARY, MotorType.kBrushless);

    footSecondary.follow(footPrimary);

    drive = new CANSparkMax(RobotMap.Climber.DRIVE, MotorType.kBrushless);
    ramp = new Solenoid(RobotMap.Climber.RAMP);
  } 

  @Override
  public void initDefaultCommand() {

  }

  /**
     * sets foot motor's speed
     * @param speed percentage power from -1 to 1
     */
    public void setFootSpeed(double speed) {
    footPrimary.set(Helper.boundValue(speed));
  }

  /**
   * turn off foot motor
   */
  public void turnFootOff() {
    footPrimary.stopMotor();
  }

  /**
   * 
   * @return speed foot motor is set to
   */
  public double getFootSpeed() {
    return footPrimary.get();
  }

  /**
   * 
   * @return position of foot motor according to encoder
   */
  public double getFootEncPosition() {
    return footPrimary.getEncoder().getPosition();
  }

  /**
   * sets drive motor's speed
   * @param speed percentage power from -1 to 1
   */
  public void setDriveSpeed(double speed) {
    drive.set(Helper.boundValue(speed));
  }

  /**
   * turn off drive motor
   */
  public void turnDriveOff() {
    drive.stopMotor();
  }

  /**
   * 
   * @return speed drive motor is set to
   */
  public double getDriveSpeed() {
    return drive.get();
  }

  /**
   * 
   * @return position of drive motor according to encoder
   */
  public double getDriveEncPosition() {
    return drive.getEncoder().getPosition();
  }

  /**
   * 
   * @param state which state to set the ramp to
   */
  public void setRampState(boolean state) {
    ramp.set(state);
  }

  /**
   * opens ramp
   */
  public void openRamp() {
    setRampState(false);
  }


  /**
   * closes ramp
   */
  public void closeRamp() {
    setRampState(true);
  }

  /**
   * 
   * @return state of claw
   */
  public boolean getRampState() {
    return ramp.get();
  }

  /** 
   * 
   * @return state of foot's forward limit switch
   */
  public boolean getForwardLimitSwitch() {
    return footPrimary.getForwardLimitSwitch(RobotMap.Climber.FORWARD_LIMIT_SWITCH_POLARITY).get();
  }

  /** 
   * 
   * @return state of foot's reverse limit switch
   */
  public boolean getReverseLimitSwtich() {
    return footPrimary.getReverseLimitSwitch(RobotMap.Climber.REVERSE_LIMIT_SWITCH_POLARITY).get();
  }
}
