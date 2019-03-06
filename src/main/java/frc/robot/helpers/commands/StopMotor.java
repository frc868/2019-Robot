package frc.robot.helpers.commands;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;

public class StopMotor extends Command {
private SpeedController controller;
private SpeedController[] controllers;

  public StopMotor(SpeedController[] controllers) {
    this.controllers = controllers;
  }

  public StopMotor(SpeedController controller) {
    this.controller = controller;
  }

  @Override
  protected void initialize() {
    if (controller != null) {
        controller.set(0);
    } else {
        for (SpeedController controllerObject : controllers) {
            controllerObject.set(0);
        }
    }
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}