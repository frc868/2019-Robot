package frc.robot.climberelevator.powerpack;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.subsystems.SubsystemManagerChild;


public class PowerPack extends SubsystemManagerChild {
  private CANSparkMax primary, secondary;
  private Solenoid switcher, brake;
  private DigitalInput elevator_top_limit, elevator_bottom_limit, climber_top_limit, climber_bottom_limit;
  private final boolean ELEVATOR_MODE = true, BRAKE_MODE = true;

  public static final double INTAKE_BALL = 0, GET_FROM_GROUND_PICKUP = 0,
        LOWER_HATCH = 1, LOWER_BALL = 2, 
        MIDDLE_HATCH = 3, MIDDLE_BALL = 4, 
        UPPER_HATCH = 5, UPPER_BALL = 6;

  public PowerPack() {
    super("PowerPack");
    primary = new CANSparkMax(RobotMap.ClimberElevator.Powerpack.PRIMARY, MotorType.kBrushless);
    secondary = new CANSparkMax(RobotMap.ClimberElevator.Powerpack.SECONDARY, MotorType.kBrushless);

    primary.setIdleMode(IdleMode.kBrake);
    secondary.setIdleMode(IdleMode.kBrake);

    secondary.follow(primary);

    switcher = new Solenoid(RobotMap.PCM, RobotMap.ClimberElevator.Powerpack.SWITCHER);
    brake = new Solenoid(RobotMap.PCM, RobotMap.ClimberElevator.Powerpack.BRAKE);

    elevator_top_limit = new DigitalInput(RobotMap.ClimberElevator.Powerpack.ELEVATOR_TOP_LIMIT);
    elevator_bottom_limit = new DigitalInput(RobotMap.ClimberElevator.Powerpack.ELEVATOR_BOTTOM_LIMIT);
    climber_top_limit = new DigitalInput(RobotMap.ClimberElevator.Powerpack.CLIMBER_TOP_LIMIT);
    climber_bottom_limit = new DigitalInput(RobotMap.ClimberElevator.Powerpack.CLIMBER_BOTTOM_LIMIT);
  }

  /**
    * sets motor's speed
    * @param speed percentage power from -1 to 1, will not work if limits are tripped
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
    return primary.getEncoder().getPosition();
  }

  /** 
   * 
   * @return state of forward limit switch
   */
  public boolean getElevatorTopLimitSwitch() {
    return elevator_top_limit.get();
  }

  /** 
   * 
   * @return state of reverse limit switch
   */
  public boolean getElevatorBottomLimitSwitch() {
    return elevator_bottom_limit.get();
  }

  /** 
   * 
   * @return state of forward limit switch
   */
  public boolean getClimberTopLimitSwitch() {
    return climber_top_limit.get();
  }

  /** 
   * 
   * @return state of reverse limit switch
   */
  public boolean getClimberBottomLimitSwitch() {
    return climber_bottom_limit.get();
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
  public void initDebug() {
    addDebug("Primary", primary);
    addDebug("Elevator Top Limit", brake);
    addDebug("Elevator Bottom Limit", brake);
    addDebug("Climber Top Limit", brake);
    addDebug("Climber Bottom Limit", brake);
    addDebug("Encoder", primary.getEncoder());
  }

  @Override
  public void initTab() {
    addTab("Primary", primary);
    addTab("Secondary", secondary);
    addTab("Switcher", switcher);
    addTab("Brake", brake);
    addTab("Elevator Top Limit", brake);
    addTab("Elevator Bottom Limit", brake);
    addTab("Climber Top Limit", brake);
    addTab("Climber Bottom Limit", brake);
    addTab("Encoder", primary.getEncoder());
  }

  @Override
  public void updateTab() {
    addTab("Primary Current", primary.getOutputCurrent());
    addTab("Secondary Current", secondary.getOutputCurrent());
  }
  
}
