/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.oi;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.carriage.ballintake.IntakeUntilBallDetected;
import frc.robot.carriage.groundpickup.GiveToClaw;
import frc.robot.carriage.groundpickup.OpenAndIntake;
import frc.robot.carriage.hatchclaw.GrabWhenDetected;
import frc.robot.carriage.hatchclaw.ToggleClaw;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.SetTiltSpeed;
import frc.robot.climberelevator.footdrive.SetFootDriveSpeed;
import frc.robot.climberelevator.forks.DeployForks;
import frc.robot.climberelevator.powerpack.AutoElevatorTilt;
import frc.robot.climberelevator.powerpack.SetClimberSpeed;
import frc.robot.climberelevator.powerpack.SetElevatorSpeed;
import frc.robot.climberelevator.powerpack.ThirdLevelClimb;
import frc.robot.climberelevator.ramps.DeployRamps;
import frc.robot.helpers.oi.XboxControllerPlus;

public class OI {
  
  public static XboxControllerPlus driver;
  public static XboxControllerPlus operator;

  public static void init(){
    driver = new XboxControllerPlus(RobotMap.Controls.DRIVER);
    operator = new XboxControllerPlus(RobotMap.Controls.OPERATOR); 

    SmartDashboard.putBoolean("has OI", true);
    (new ArcadeDrive()).start();
    (new TriggerIntakeControl()).start();

    driver.lb.whenPressed(new ToggleClaw());
    operator.lb.whenPressed(new ToggleClaw());

    // driver.a.whileHeld(new FollowVision());
    driver.b.whileHeld(new GrabWhenDetected());
    driver.x.whileHeld(new IntakeUntilBallDetected());

    operator.lstk.pressToStartReleaseToStop(new ManualElevator(), new SetElevatorSpeed(0));
    operator.rstk.pressToStartReleaseToStop(new ManualTilt(), new SetTiltSpeed(0));

    operator.x.whenPressed(new AutoElevatorTilt(AutoElevatorTilt.State.intakeBall));

    operator.povN.whenPressed(new SetTiltPosition(SetTiltPosition.UPPER));
    operator.povE.whenPressed(new SetTiltPosition(SetTiltPosition.MIDDLE));
    operator.povS.whenPressed(new SetTiltPosition(SetTiltPosition.LOWER));

    operator.start.whenPressed(new OpenAndIntake());
    operator.menu.whenPressed(new GiveToClaw());
  }

  public static void update() {
    if (driver.rb.get()) {
      driver.start.whenPressed(new DeployForks());
      driver.menu.whenPressed(new DeployRamps());
      driver.y.whenPressed(new ThirdLevelClimb(false));

      driver.povN.pressToStartReleaseToStop(new SetClimberSpeed(0.5), new SetClimberSpeed(0));
      driver.povS.pressToStartReleaseToStop(new SetClimberSpeed(-0.5), new SetClimberSpeed(0));

      driver.povE.pressToStartReleaseToStop(new SetFootDriveSpeed(0.5), new SetFootDriveSpeed(0));
      driver.povW.pressToStartReleaseToStop(new SetFootDriveSpeed(-0.5), new SetFootDriveSpeed(0));
    } else {
      driver.start.resetMappings();
      driver.menu.resetMappings();
      driver.y.resetMappings();

      driver.povN.resetMappings();
      driver.povS.resetMappings();
      driver.povE.resetMappings();
      driver.povW.resetMappings();
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
