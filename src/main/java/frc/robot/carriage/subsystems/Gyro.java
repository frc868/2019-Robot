/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.carriage.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Gyro extends Subsystem {

  public static AnalogGyro gyro;
  public double initialAngle;

  public Gyro(int port) {
    gyro = new AnalogGyro(port);
  }

  public Gyro() {
    gyro = new AnalogGyro(1);
  }

  @Override
  public void initDefaultCommand() {
    gyro.initGyro();
    initialAngle = gyro.getAngle();
  }

  public double getAngle(){
    return gyro.getAngle();
  }

  public void reset(){
    gyro.reset();
  }

  public double getTurnRate(){
    return gyro.getRate();
  }
  /**
   * calibrates the gyro
   * this must be done while robot is stationary and powered on
   * @return the center value that results from calibration
   */
  public double calibrate(){
    gyro.calibrate();
    return gyro.getCenter();
  }
}
