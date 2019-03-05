package frc.robot.climberelevator.powerpack;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.sensors.IRLimit;
import frc.robot.helpers.subsystems.SubsystemManagerChild;


public class PowerPack extends SubsystemManagerChild {
  private CANSparkMax primary, secondary;
  private Solenoid switcher, elevator_brake, climber_brake;
  private IRLimit elevator_top_limit, elevator_bottom_limit, climber_top_limit, climber_bottom_limit;
  private final boolean ELEVATOR_MODE = false, BRAKE_MODE = false;

  public static final double INTAKE_BALL = 0, GET_FROM_GROUND_PICKUP = 0, 
    LOWER = 6.69, MIDDLE = 22.428, UPPER = 35.952;

  public PowerPack() {
    super("PowerPack");
    primary = new CANSparkMax(RobotMap.ClimberElevator.Powerpack.PRIMARY, MotorType.kBrushless);
    secondary = new CANSparkMax(RobotMap.ClimberElevator.Powerpack.SECONDARY, MotorType.kBrushless);

    primary.setIdleMode(IdleMode.kBrake);
    secondary.setIdleMode(IdleMode.kBrake);

    secondary.follow(primary);

    switcher = new Solenoid(RobotMap.PCM, RobotMap.ClimberElevator.Powerpack.SWITCHER);
    elevator_brake = new Solenoid(RobotMap.PCM, RobotMap.ClimberElevator.Powerpack.ELEVATOR_BRAKE);
    climber_brake = new Solenoid(RobotMap.PCM, RobotMap.ClimberElevator.Powerpack.CLIMBER_BRAKE);

    elevator_top_limit = new IRLimit(RobotMap.ClimberElevator.Powerpack.ELEVATOR_TOP_LIMIT);
    elevator_bottom_limit = new IRLimit(RobotMap.ClimberElevator.Powerpack.ELEVATOR_BOTTOM_LIMIT);
    climber_top_limit = new IRLimit(RobotMap.ClimberElevator.Powerpack.CLIMBER_TOP_LIMIT);
    climber_bottom_limit = new IRLimit(RobotMap.ClimberElevator.Powerpack.CLIMBER_BOTTOM_LIMIT);
  }

  /**
    * sets motor's speed
    * @param speed percentage power from -1 to 1, will not work if limits are tripped
    */
  public void setSpeed(double speed) {
    speed = Helper.boundValue(speed);

    if (isElevatorMode()) {
      if (getElevatorBottomLimitSwitch()) {
        speed = Helper.boundValue(speed, 0, 1);
      } else if (getElevatorTopLimitSwitch()) {
        speed = Helper.boundValue(speed, -1, 0);
      }
    } else {
      if (getClimberBottomLimitSwitch()) {
        speed = Helper.boundValue(speed, 0, 1);
      } else if (getClimberTopLimitSwitch()) {
        speed = Helper.boundValue(speed, -1, 0);
      }
    }

    primary.set(speed);
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
   */
  public void resetEncPosition() {
    primary.getEncoder().setPosition(0);
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
   */
  public boolean getSwitcher() {
    return switcher.get();
  }

  /**
   * 
   * @return true if powerpack is on elevator mode
   */
  public boolean isElevatorMode() {
    return getSwitcher() == ELEVATOR_MODE;
  }

  /**
   * returns brake solenoid state
   */
  public boolean getElevatorBrake() {
    return elevator_brake.get();
  }

  /**
   * @return true if elevator is braking
   */
  public boolean isElevatorBraked() {
    return getElevatorBrake() == BRAKE_MODE;
  }

  /**
   * turns brake on
   */
  public void elevatorBrakeOn() {
    elevator_brake.set(BRAKE_MODE);
  }

  /**
   * turns brake off
   */
  public void elevatorBrakeOff() {
    elevator_brake.set(!BRAKE_MODE);
  }

  /**
   * returns brake solenoid state
   */
  public boolean getClimberBrake() {
    return climber_brake.get();
  }

  /**
   * @return true if climber is braking
   */
  public boolean isClimberBraked() {
    return getClimberBrake() == BRAKE_MODE;
  }

  /**
   * turns brake on
   */
  public void climberBrakeOn() {
    climber_brake.set(BRAKE_MODE);
  }

  /**
   * turns brake off
   */
  public void climberBrakeOff() {
    climber_brake.set(!BRAKE_MODE);
  }

  // @Override
  // public void update() {
  //   if(getElevatorBottomLimitSwitch()){
  //     resetEncPosition();
  //   }
  // }

  @Override
  public void init() {
    // resetEncPosition();
  }

  @Override
  public void initEnabled() {
    switchToElevator();
    climberBrakeOn();
    elevatorBrakeOn();
  }

  @Override
  public void initDisabled() {
    climberBrakeOn();
    elevatorBrakeOn();
  }

  @Override
  public void updateSD() {
    SmartDashboard.putNumber("PowerPack: Speed", getSpeed());
    SmartDashboard.putNumber("PowerPack: Position", getEncPosition());
    SmartDashboard.putBoolean("PowerPack: Elevator Mode?", isElevatorMode());
    SmartDashboard.putBoolean("PowerPack: Elevator Brake?", isElevatorBraked());
    SmartDashboard.putBoolean("PowerPack: Climber Brake?", isClimberBraked());
    SmartDashboard.putBoolean("PowerPack: Elevator Top Limit?", getElevatorTopLimitSwitch());
    SmartDashboard.putBoolean("PowerPack: Elevator Bottom Limit?", getElevatorBottomLimitSwitch());
    SmartDashboard.putBoolean("PowerPack: Climber Top Limit?", getClimberTopLimitSwitch());
    SmartDashboard.putBoolean("PowerPack: Climber Bottom Limit?", getClimberBottomLimitSwitch());
  }
}
