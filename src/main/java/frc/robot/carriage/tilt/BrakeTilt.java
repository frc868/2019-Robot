package frc.robot.carriage.tilt;

import frc.robot.Robot;

public class BrakeTilt extends SetTiltPosition {

  public BrakeTilt() {
    super(Robot.tilt.getPotPosition());
    requires(Robot.tilt);
  }
}
