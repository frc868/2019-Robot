package frc.robot.carriage.tilt;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.subsystems.SubsystemManagerChild;


public class Tilt extends SubsystemManagerChild {
  private WPI_TalonSRX motor; 
  // private final double CLAW_CLOSED_THRESHOLD = 10; 

  public Tilt() {
    motor = new WPI_TalonSRX(RobotMap.Carriage.Tilt.MOTOR);
    motor.setInverted(true);
  }

  /**
   * sets motor's speed
   * @param speed percentage power from -1 to 1, will not work if limits are tripped
   */
  public void setSpeed(double speed) {
    if (!getLimits()) {
      motor.set(Helper.boundValue(speed));
    }
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
  public double getEncPosition() {
    return motor.getSelectedSensorPosition();
  }

  /**
   * 
   * @return if forward limit is tripped
   */
  public boolean getForwardLimit() {
    return motor.getSensorCollection().isFwdLimitSwitchClosed();
  }

  /**
   * @return if reverse limit is tripped
   */
  public boolean getReverseLimit() {
    return motor.getSensorCollection().isRevLimitSwitchClosed();
  }

  /**
   * @return if one of the limits are tripped
   */
  public boolean getLimits() {
    return getForwardLimit() || getReverseLimit();
  }
  
  public void update() {
    if (getLimits()) {
      stop();
    }

    // if (getEncPosition() < CLAW_CLOSED_THRESHOLD) {
    //   Robot.hatchClaw.grab();
    // }
  }

  @Override
  public void updateSD() {
    SmartDashboard.putNumber("Tilt Speed", getSpeed());
    SmartDashboard.putNumber("Tilt Position", getEncPosition());
    SmartDashboard.putBoolean("Tilt Forward Limit", getForwardLimit());
    SmartDashboard.putBoolean("Tilt Reverse Limit", getReverseLimit());
  }
}