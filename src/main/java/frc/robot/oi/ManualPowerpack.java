
package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.helpers.Helper;

public class ManualPowerpack extends Command {
  public ManualPowerpack() {
    requires(Robot.powerPack);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    double input = Helper.deadzone(OI.operator.getLY(), 0.1); //the correct input for climber mode (derek's preference)
    if(Robot.powerPack.isElevatorMode())  {
      input = -input;  //stick up = elevator up (when in elevator position)
    }

    Robot.powerPack.setSpeed(input);
    // SmartDashboard.putNumber("Climber Speed", input);

    if(input == 0) {    //enables pneumatic brake based on deadzone and powerpack mode
      if(Robot.powerPack.isElevatorMode())  {
        Robot.powerPack.elevatorBrakeOn();
      } else{
        Robot.powerPack.climberBrakeOn();
      }
    } else {
      if(Robot.powerPack.isElevatorMode())  {
        Robot.powerPack.elevatorBrakeOff();
      } else{
        Robot.powerPack.climberBrakeOff();
      }
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.powerPack.stop();
    Robot.powerPack.climberBrakeOn();
  }

  @Override
  protected void interrupted() {
  }
}
