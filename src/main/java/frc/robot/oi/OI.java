/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.oi;

import frc.robot.carriage.ballintake.IntakeUntilBallDetected;
import frc.robot.carriage.hatchclaw.GrabWhenDetected;
import frc.robot.carriage.hatchclaw.Toggle;
import frc.robot.climberelevator.forks.DeployForks;
import frc.robot.climberelevator.ramps.DeployRamps;
import frc.robot.helpers.XboxControllerPlus;
import frc.robot.*;

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
    // driver.y.whenPressed(new AutoClimb()); TODO comment back in when auto climb is added

    operator.lstk.whileHeld(new ManualElevator());
    operator.rstk.whileHeld(new ManualTilt());
  }

  public static void update() {
    if (driver.rb.get()) {
      driver.start.whenPressed(new DeployForks());
      driver.menu.whenPressed(new DeployRamps());
    } else {
      driver.start.resetMappings();
      driver.menu.resetMappings();
    }
  }

}
