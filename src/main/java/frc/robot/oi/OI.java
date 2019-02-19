/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.oi;

import frc.robot.RobotMap;
import frc.robot.carriage.ballintake.IntakeUntilBallDetected;
import frc.robot.carriage.hatchclaw.GrabWhenDetected;
import frc.robot.carriage.hatchclaw.Toggle;
import frc.robot.climberelevator.ThirdLevelClimb;
import frc.robot.climberelevator.forks.DeployForks;
import frc.robot.climberelevator.ramps.DeployRamps;
import frc.robot.commands.AutoElevatorTilt;
import frc.robot.helpers.XboxControllerPlus;

public class OI {
  
  public static XboxControllerPlus driver;
  public static XboxControllerPlus operator;

  public static void init(){
    driver = new XboxControllerPlus(RobotMap.Controls.DRIVER);
    operator = new XboxControllerPlus(RobotMap.Controls.OPERATOR); 

    (new ArcadeDrive()).start();
    (new TriggerIntakeControl()).start();

    driver.rb.whenPressed(new Toggle());
    operator.rb.whenPressed(new Toggle());

    // driver.a.whileHeld(new FollowVision()); TODO comment back in when follow vision is added
    driver.b.whileHeld(new GrabWhenDetected());
    driver.x.whileHeld(new IntakeUntilBallDetected());

    operator.lstk.whileHeld(new ManualElevator());
    operator.rstk.whileHeld(new ManualTilt());

    operator.x.whenPressed(new AutoElevatorTilt(AutoElevatorTilt.State.intakeBall));
  }

  public static void update() { //TODO mappings for POV buttons on driver/operator
    if (driver.rb.get()) {
      driver.start.whenPressed(new DeployForks());
      driver.menu.whenPressed(new DeployRamps());
      driver.y.whenPressed(new ThirdLevelClimb(false));
    } else {
      driver.start.resetMappings();
      driver.menu.resetMappings();
      driver.y.resetMappings();
    }

    if (operator.rb.get()) {
      operator.a.whenPressed(new AutoElevatorTilt(AutoElevatorTilt.State.ballLower));
      operator.b.whenPressed(new AutoElevatorTilt(AutoElevatorTilt.State.ballMiddle));
      operator.y.whenPressed(new AutoElevatorTilt(AutoElevatorTilt.State.ballUpper));
    } else {
      operator.a.whenPressed(new AutoElevatorTilt(AutoElevatorTilt.State.hatchLower));
      operator.b.whenPressed(new AutoElevatorTilt(AutoElevatorTilt.State.hatchMiddle));
      operator.y.whenPressed(new AutoElevatorTilt(AutoElevatorTilt.State.hatchUpper));
    }
  }

}
