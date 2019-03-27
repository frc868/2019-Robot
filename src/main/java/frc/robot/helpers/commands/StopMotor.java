package frc.robot.helpers.commands;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;

public class StopMotor extends Command {
private SpeedController controller;
private Solenoid sol;
private boolean solSet = false;
private SpeedController[] controllers;

  public StopMotor(SpeedController[] controllers) {
    this.controllers = controllers;
  }

  public StopMotor(SpeedController controller) {
    this.controller = controller;
  }

  public StopMotor(SpeedController controller, Solenoid sol, Boolean solSet){
      this.controller = controller;
      this.sol = sol;
      this.solSet = solSet;
  }

  @Override
  protected void initialize() {
    if (controller != null) {
        if(sol != null) {
            sol.set(solSet);
        }
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