package frc.robot.carriage.tilt;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.subsystems.SubsystemManagerChild;


public class Tilt extends SubsystemManagerChild {
  private WPI_TalonSRX motor; 
  private AnalogPotentiometer potentiometer;
  public static final double REVERSE_LIMIT = 0, LOWER = .310, MIDDLE = 0.195, UPPER = .165, FORWARD_LIMIT = 0;

  public Tilt() {
    super("Tilt");
    motor = new WPI_TalonSRX(RobotMap.Carriage.Tilt.MOTOR);
    potentiometer = new AnalogPotentiometer(RobotMap.Carriage.Tilt.POTENTIOMETER);
    motor.setInverted(true);
  }

  /**
   * sets motor's speed
   * @param speed percentage power from -1 to 1, will not work if limits are tripped
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

  /**
   * 
   * @return position of motor according to encoder
   */
  public double getPotPosition() {
    return potentiometer.get();
  }

  /**
   * 
   * @return if forward limit is tripped
   */
  public boolean getForwardLimit() {
    return getPotPosition() > FORWARD_LIMIT;
  }

  /**
   * @return if reverse limit is tripped
   */
  public boolean getReverseLimit() {
    return getPotPosition() < REVERSE_LIMIT;
  }

  @Override
  public void initEnabled() {
    // SmartDashboard.putData("Tilt Up", new SetTiltPosition(Tilt.MIDDLE));
  }

  @Override
  public void init() {
    addTab("Motor", motor);
    addTab("Potentiometer", potentiometer);
  }

  @Override
  public void updateSD() {
    SmartDashboard.putNumber("Tilt: Speed", getSpeed());
    SmartDashboard.putNumber("Tilt: Position", getPotPosition());
  }
}