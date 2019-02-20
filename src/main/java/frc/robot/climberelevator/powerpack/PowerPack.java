package frc.robot.climberelevator.powerpack;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.SubsystemManagerChild;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class PowerPack extends SubsystemManagerChild {
  private CANSparkMax primary, secondary;
  private Solenoid switcher, brake;
  // private DigitalInput top_limit, bottom_limit;
  private final boolean ELEVATOR_MODE = true, BRAKE_MODE = true;

  public PowerPack() {
    primary = new CANSparkMax(RobotMap.ClimberElevator.Powerpack.PRIMARY, MotorType.kBrushless);
    secondary = new CANSparkMax(RobotMap.ClimberElevator.Powerpack.SECONDARY, MotorType.kBrushless);

    primary.setIdleMode(IdleMode.kBrake);
    secondary.setIdleMode(IdleMode.kBrake);

    secondary.follow(primary);

    switcher = new Solenoid(RobotMap.PCM, RobotMap.ClimberElevator.Powerpack.SWITCHER);
    brake = new Solenoid(RobotMap.PCM, RobotMap.ClimberElevator.Powerpack.BRAKE);

    // top_limit = new DigitalInput(RobotMap.ClimberElevator.Powerpack.TOP_LIMIT);
    // bottom_limit = new DigitalInput(RobotMap.ClimberElevator.Powerpack.BOTTOM_LIMIT);
  }

  /**
    * sets motor's speed
    * @param speed percentage power from -1 to 1, will not work if limits are tripped
    */
  public void setSpeed(double speed) {
    if (!getLimits()) {
      primary.set(Helper.boundValue(speed));
    }
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
    return primary.getEncoder().getPosition();
  }

  /** 
   * 
   * @return state of forward limit switch
   */
  public boolean getTopLimitSwitch() {
    return false;
    // return top_limit.get();
  }

  /** 
   * 
   * @return state of reverse limit switch
   */
  public boolean getBottomLimitSwitch() {
    return false;
    // return bottom_limit.get();
  }

  /**
   * @return state of limits
   */
  public boolean getLimits() {
    return getTopLimitSwitch() || getBottomLimitSwitch();
  }

  /**
   * switches powerpack to elevator mode
   */
  public void switchToElevator() {
    switcher.set(ELEVATOR_MODE);
  }

  /**
   * switches powerpack to climber mode
   */
  public void switchToClimber() {
    switcher.set(!ELEVATOR_MODE);
  }

  /**
   * 
   * @return true if powerpack is on elevator mode
   */
  public boolean isElevatorMode() {
    return switcher.get() == ELEVATOR_MODE;
  }

  /**
   * turns brake on
   */
  public void brakeOn() {
    brake.set(BRAKE_MODE);
  }

  /**
   * turns brake off
   */
  public void brakeOff() {
    brake.set(!BRAKE_MODE);
  }

  /**
   * returns brake solenoid state
   */
  public boolean getBrakeMode() {
    return brake.get();
  }

  /**
   * returns true if breaking, false if not
   */
  public boolean isBrakeMode() {
    return getBrakeMode() == BRAKE_MODE;
  }

  @Override
  public void update() {
    if (getLimits()) {
      stop();
    }
  }


  @Override
  public void updateSD() {
    SmartDashboard.putBoolean("Elevator Mode", isElevatorMode());
    SmartDashboard.putNumber("Powerpack Speed", getSpeed());
    SmartDashboard.putNumber("Powerpack Position", getEncPosition());
    SmartDashboard.putBoolean("Top Limit", getTopLimitSwitch());
    SmartDashboard.putBoolean("Bottom Limit", getBottomLimitSwitch());
    SmartDashboard.putNumber("Primary Ele Current", primary.getOutputCurrent());
    SmartDashboard.putNumber("Secondary Ele Current", primary.getOutputCurrent());
  }
  
}
